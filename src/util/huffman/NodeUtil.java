package util.huffman;

import util.map.BSTMap;
import util.map.FrequencyTable;
import util.io.FileByteList;
import util.map.Map;
import util.queue.MinPriorityQueue;

public class NodeUtil {


    /**
     * 创建huffman树，返回根节点
     * @param fileByteList
     * @return
     */
    public static Node buildTril(FileByteList fileByteList)
    {
        FrequencyTable table = new FrequencyTable();
        MinPriorityQueue<Node> minPq = new MinPriorityQueue<>();
        while (fileByteList.hasNext())
        {
            table.put(fileByteList.next());
        }
        FrequencyTable.Entry[] entries = table.entrySet();
        for(FrequencyTable.Entry e: table.entrySet())
        {
            if(e != null)
            {
                while (e != null)
                {
                    Node n = new Node(e.key, e.value);
                    minPq.enqueue(n);
                    e = e.next;
                    //System.out.println(n);
                }
            }
        }

        //构建huffman树
        while (minPq.getSize() > 1)
        {
            Node x = minPq.dequeue();
            Node y = minPq.dequeue();
            Node parent = new Node((byte) -1, x.getFreq()+y.getFreq() , x, y);
            minPq.enqueue(parent);
        }

        return minPq.dequeue();

    }

    /**
     * 返回 huffman树对应的压缩表
     * @param root
     * @return
     */
    public static BSTMap<Byte, String> buildCode(Node root)
    {
        BSTMap<Byte, String> map = new BSTMap<>();
        buildCode(map, root, "");
        return map;
    }

    private static void buildCode(Map<Byte, String> map, Node x, String s)
    {
        if(x.isLeaf())
        {
            map.add(x.getCh(), s, x.getFreq());
            return;
        }
        buildCode(map, x.left, s+"0");
        buildCode(map, x.right, s+"1");
    }


}

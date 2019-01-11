package util.huffman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.io.BitInputStream;
import util.io.BitOutputStream;
import util.io.FileByteList;
import util.map.BSTMap;
import util.map.Map;

import java.io.*;


public class HuffmanTree {

    public BitOutputStream bos;
    private BitInputStream bis;


    public void compress(String src, String dest) throws IOException {
        bos = new BitOutputStream(new FileOutputStream(dest));
        File f = new File(dest);
        if(!f.exists())
        {
            f.createNewFile();
        }

        FileByteList fbl = new FileByteList(src);
        Node root = NodeUtil.buildTril(fbl);
        Map<Byte, String> byteStringMap = NodeUtil.buildCode(root);

        long length = fbl.getSize();
        writeTrie(root);
        String s = Long.toBinaryString(length);
        //System.out.println(length);
        int binaryLength = s.length();
        for(int i = 0; i < 64-binaryLength; i++)
        {
            bos.writeBit(0);
        }


        for(char c: s.toCharArray())
        {
            bos.writeBit((int)c);
        }
        fbl.close();

        fbl = new FileByteList(src);
        while(fbl.hasNext())
        {
            byte next = fbl.next();
            String ss = byteStringMap.get(next);
            for(char c: ss.toCharArray())
            {
                bos.writeBit(Integer.parseInt(c+""));
                //bos.write();
            }
            //System.out.println(fbl.getSize());
        }
        bos.close();
        fbl.close();


    }


    public void depress(String src, String dest) throws IOException {
        //解压文件
        FileOutputStream fos = new FileOutputStream(dest);
        //源压缩文件
        bis = new BitInputStream(new FileInputStream(src));
        Node root = readTril();

        long binaryLength = 0;
        boolean flag = true;
        //记录这是读取数字的第几位
        int temp = 0;
        for(int i = 0; i < 64; i++)
        {
            int i1 = bis.readBit();
            if(flag && i != 0 )
            {
                flag  = false;
            }
            if(!flag)
            {
                binaryLength = (binaryLength << 1) + i1;
                temp++;
            }
        }
        //System.out.println(binaryLength);


        Node cur = root;
        for(long i = 0; i < binaryLength; )
        {
            int i1 = bis.readBit();
            if(i1 == 0)
            {
                cur = cur.left;
            }else
            {
                cur = cur.right;
            }

            if(cur.isLeaf())
            {
                fos.write(cur.getCh());
                cur = root;
                i++;
            }
        }

        fos.close();
        bis.close();




    }

    private void writeTrie(Node root) throws IOException {
        if(root.isLeaf())
        {
            bos.writeBit(1);
            bos.write(root.getCh(), 8);
            //System.out.println((char)root.getCh());
            return;
        }else {
            bos.writeBit(0);
        }
        writeTrie(root.left);
        writeTrie(root.right);
    }

    public  Node readTril() throws IOException {
        if(bis.readBit() == 1)
        {
            int num = 0;
            for(int i = 0; i < 8; i++)
            {
                int i1 = bis.readBit();
                num += i1 << i;
            }
            return new Node((byte) num, 0, null, null);
        }
        return new Node((byte) -1, 0, readTril(), readTril());
    }




    public void compressWithProcess(String src, String dest, Process p) throws IOException {
        bos = new BitOutputStream(new FileOutputStream(dest));
        File f = new File(dest);
        if(!f.exists())
        {
            f.createNewFile();
        }
        p.add().add().add();
        FileByteList fbl = new FileByteList(src);
        p.add().add().add();
        Node root = NodeUtil.buildTril(fbl);
        Map<Byte, String> byteStringMap = NodeUtil.buildCode(root);

        long length = fbl.getSize();
        writeTrie(root);
        String s = Long.toBinaryString(length);
        //System.out.println(length);
        int binaryLength = s.length();
        for(int i = 0; i < 64-binaryLength; i++)
        {
            bos.writeBit(0);
        }

        p.add().add().add();
        for(char c: s.toCharArray())
        {
            bos.writeBit((int)c);
        }
        fbl.close();
        p.add();
        fbl = new FileByteList(src);

        int i = 0;
        while(fbl.hasNext())
        {
            p.setNum((int) (p.getNum() + (i++/length)*90));
            byte next = fbl.next();
            String ss = byteStringMap.get(next);
            for(char c: ss.toCharArray())
            {
                bos.writeBit(Integer.parseInt(c+""));
                //bos.write();
            }
            //System.out.println(fbl.getSize());
        }
        bos.close();
        fbl.close();


    }

}

package test;

import org.junit.Test;
import util.huffman.Node;
import util.huffman.NodeUtil;
import util.io.BitOutputStream;
import util.io.FileByteList;
import util.map.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CompressTest
{
    private BitOutputStream bos;

    @Test
    public void testCompress() throws Exception
    {
        int n = 3;
        String src = "";
        if(n == 0)
            src = "D:\\src-c\\sort_pointer.c";  //2kb
        else if(n == 1)
            src = "D:\\src-c\\a.txt";
        else if(n == 2)
            src = "repeat_number.c";
        else if(n == 3)
            src = "D:\\src-c\\测试题目.docx";
        String dest = "";
        //BitOutputStream bos = new BitOutputStream(new FileOutputStream(dest));

        FileByteList fbl = new FileByteList(src);
        Node root = NodeUtil.buildTril(fbl);
        Map<Byte, String> byteStringMap = NodeUtil.buildCode(root);
        System.out.println(byteStringMap.toString());
        System.out.print("size: ");
        System.out.println(byteStringMap.getSize());
        System.out.println("origin length: "  + byteStringMap.getSize()*8);

//        long length = fbl.getSize();
//        writeTrie(root);
//        String s = Long.toBinaryString(length);
//
//
//        int binaryLength = s.length();
//        for(int i = 0; i < 64-binaryLength; i++)
//        {
//            bos.writeBit(0);
//        }
//
//
//        for(char c: s.toCharArray())
//        {
//            bos.writeBit((int)c);
//        }
//        fbl.close();
//
//        fbl = new FileByteList(src);
//        while(fbl.hasNext())
//        {
//            byte next = fbl.next();
//            String ss = byteStringMap.get(next);
//            System.out.println(ss);
//            System.out.println(next);
//            System.out.println("--------------");
//            for(char c: ss.toCharArray())
//            {
//                bos.writeBit(Integer.parseInt(c+""));
//                //bos.write();
//            }
//            //System.out.println(fbl.getSize());
//        }
        //bos.close();
    }

    private void writeTrie(Node root) throws IOException
    {
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
}

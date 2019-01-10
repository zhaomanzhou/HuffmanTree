package util.huffman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.io.BitInputStream;
import util.io.BitOutputStream;
import util.io.FileByteList;
import util.map.Map;

import java.io.*;


public class HuffmanTree {

    private BitOutputStream bos;
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
            System.out.println(ss);
            System.out.println(next);
            System.out.println("--------------");
            for(char c: ss.toCharArray())
            {
                bos.writeBit(Integer.parseInt(c+""));
                //bos.write();
            }
            //System.out.println(fbl.getSize());
        }
        bos.close();


    }


    public void depress(String src, String dest) throws IOException {
        FileInputStream fis = new FileInputStream(dest);
        bis = new BitInputStream(new FileInputStream(dest));

        Node node = readTril();
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

    private void writeZipCode()
    {

    }

    public static void main(String[] args) {
        try {
            new HuffmanTree().compress("d:/1.txt", "d:/2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

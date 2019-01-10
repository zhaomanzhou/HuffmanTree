package test;

import org.junit.Test;

import java.io.*;

public class DataOutputStreamTest {


    @Test
    public void testWrite() throws IOException {
        FileOutputStream fos = new FileOutputStream("D:/1.txt");
        DataOutputStream dos = new DataOutputStream(fos);
        //dos.writeByte(1);
        dos.writeBoolean(true);

    }

    @Test
    public void test02() throws FileNotFoundException {
        RandomAccessFile raf = new RandomAccessFile("D:/1.txt", "rw");


    }
}

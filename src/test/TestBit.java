package test;

import org.junit.Test;
import util.io.BitOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestBit {
    BitOutputStream bos = new BitOutputStream(new FileOutputStream("D:/3.txt"));

    public TestBit() throws FileNotFoundException {
    }

    @Test
    public void test01() throws IOException {
        //bos.write();
        //bos.write(12, 4);
        bos.writeBit(1);
        bos.writeBit(0);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);

        bos.writeBit(1);
        bos.writeBit(0);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);

        bos.flush();
        bos.close();
    }

    @Test
    public void test02() throws IOException {
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.write(3, 8);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
        bos.writeBit(1);
    }
}

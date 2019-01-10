package test;

import org.junit.Test;
import util.io.FileByteList;

public class TestFileByteList {

    @Test
    public void test01()
    {
        FileByteList fbl = new FileByteList("d:/testfile/test1.txt");
        while(fbl.hasNext())
        {
            byte next = fbl.next();
            System.out.print(Integer.toHexString(next & 0x00ff) + "  ");
        }

        fbl.reInit();
        System.out.println("-----------------");
        while(fbl.hasNext())
        {
            byte next = fbl.next();
            System.out.print(Integer.toHexString(next & 0x00ff) + "  ");
        }
    }


}

package test;

import org.junit.Test;

public class HashTest {


    static final int hash(Object var0) {
        int var1;
        return var0 == null ? 0 : (var1 = var0.hashCode()) ^ var1 >>> 16;
    }


    @Test
    public void testHash()
    {
        System.out.println(hash(305675675));
    }

    @Test
    public void IntegerTest()
    {
        Integer i1 = 222;
        Integer i2 = 222;
        byte b1 = 122;
        byte b2 = 122;
        System.out.println(i1 == i2);
        System.out.println(b1  == b2);
    }
}
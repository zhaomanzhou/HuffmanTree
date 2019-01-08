package util;

public class FrequencyTable {
    private class Entry{
        byte key;
        byte value;
        Entry next;
    }
    //默认桶的长度 2^4
    private final int DEFAULT_BUCKET_SIZE = 2 >> 4;
    private Entry[] bucket;
    private int bucketLength;
    private int size;
    
    //jdk中的hash函数实现，直接copy的
    static final int hash(byte var0) {
        int var1;
        return  (var1 = var0) ^ var1 >>> 16;
    }

    public FrequencyTable()
    {
        init();
    }
    public FrequencyTable(int length)
    {
        bucketLength = length;
        init();
    }

    private void init()
    {
        bucket = new Entry[bucketLength];
        size = 0;
    }
    
    
    public void put(byte b)
    {
        //对应桶的下标
        int index = hash(b)%bucketLength;
        //如果桶的这个没元素，直接插
        if(bucket[index] == null)
        {
            Entry e = new Entry();
            e.key = b;
            e.value =1;
            bucket[index] = e;
        }else
        {
            Entry e = bucket[index];
            for(;e != null && e.key != b; e = e.next);
            //如果已经存在这个元素
            if(e != null)
            {
                e.value++;
            }else
            //通里有元素的情况
            {
                for(e = bucket[index];e.next != null; e = e.next);
                Entry e1 = new Entry();
                e1.key = b;
                e1.value =1;
                e.next = e1;
            }
        }
    }

    /**
     * 获取孩字节出现的次数
     * @param b 获取的字节
     * @return
     */
    public int get(byte b)
    {
        int index = hash(b)%bucketLength;
        Entry e = bucket[index];
        for(;e != null && e.key != b; e = e.next);
        if(e != null)
        {
            return 0;
        }else
        {
            return e.value;
        }
    }



}

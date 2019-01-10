package util.map;

public class FrequencyTable {
    public class Entry{
        public byte key;
        public int value;
        public Entry next;
    }
    private final int DEFAULT_BUCKET_SIZE = 2 << 8;
    private Entry[] bucket;
    private int bucketLength;
    private int size;
    private Entry next;
    private int curIndex;
    static final int hash(byte var0) {
        int var1;
        return  (var1 = var0) ^ var1 >>> 16;
    }

    public FrequencyTable()
    {
        bucketLength = DEFAULT_BUCKET_SIZE;
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

        int index = Math.abs(hash(b)%bucketLength);
        if(bucket[index] == null)
        {
            Entry e = new Entry();
            e.key = b;
            e.value =1;
            bucket[index] = e;
            size++;
        }else
        {
            Entry e = bucket[index];
            for(;e != null && e.key != b; e = e.next);
            if(e != null)
            {
                e.value++;
            }else{
                for(e = bucket[index];e.next != null; e = e.next);
                Entry e1 = new Entry();
                e1.key = b;
                e1.value =1;
                e.next = e1;
                size++;
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

    public int size()
    {
        return size;
    }


    public Entry[] entrySet()
    {
        return bucket;
    }


    //开始遍历
    public void beginIterator()
    {
        curIndex = 0;
        for(int i = 0; i < bucketLength; i++)
        {
            if(bucket[i] != null)
            {

            }
        }
    }

}

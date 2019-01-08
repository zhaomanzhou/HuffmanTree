package util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileByteList {
    //默认缓冲大小
    private final int DEFAULT_BUFFER_SIZE = 1024*1024*10;
    private String filePath;

    private int bufferSize;
    private byte[] buffer;
    //当前缓冲数组的下标
    private int offset;
    private FileInputStream fis;
    private Boolean isEnd = false;
    public FileByteList() {
        this.bufferSize = DEFAULT_BUFFER_SIZE;
        buffer = new byte[bufferSize];
    }


    public FileByteList(String filePath)
    {
        this.bufferSize = DEFAULT_BUFFER_SIZE;
        this.filePath = filePath;
        buffer = new byte[bufferSize];
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        readFirst();

    }

    public FileByteList(String filePath, int bufferSize) {
        this.bufferSize = bufferSize;
        this.filePath = filePath;
        buffer = new byte[bufferSize];
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        readFirst();
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        isEnd = false;
        readFirst();
    }

    public boolean hasNext()
    {
        return !isEnd;
    }

    public byte next()
    {
        if(fis == null)
            throw new IllegalArgumentException("没有初始化文件");

        if(isEnd)
            throw new IllegalArgumentException("已经到达文件末尾");
        if(offset == bufferSize)
        {
            try {
                int result = fis.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            offset = 0;
        }
        byte result = buffer[offset++];
        if(buffer[offset] == 0)
        {
            isEnd = true;
        }

        return result;
    }

    private void readFirst()
    {
        try {
            fis.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        offset = 0;
        isEnd = (buffer[offset] == 0);

    }







}

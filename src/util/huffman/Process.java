package util.huffman;

public class Process {
    private int num = 0;
    public Process add()
    {
        num++;
        return this;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

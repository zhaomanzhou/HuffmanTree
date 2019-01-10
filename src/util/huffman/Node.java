package util.huffman;

public class Node implements Comparable<Node> {

    private byte ch;

    //Huffman树的结点值
    private int freq;

    Node left, right;

    public Node() {
    }

    public Node(byte ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    public Node(byte ch, int freq, Node left, Node right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf()
    {
        return left == null && right == null;
    }

    @Override
    public int compareTo(Node o) {
        return this.freq - o.freq;
    }

    public byte getCh() {
        return ch;
    }

    public void setCh(byte ch) {
        this.ch = ch;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

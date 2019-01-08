package util;

public class Node implements Comparable<Node> {

    private char ch;

    //Huffman树的结点值
    private int freq;

    Node left, right;

    public boolean isLeaf()
    {
        return left == null && right == null;
    }

    @Override
    public int compareTo(Node o) {
        return this.freq - o.freq;
    }
}

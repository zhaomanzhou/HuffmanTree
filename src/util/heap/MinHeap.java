package util.heap;

import util.array.Array;

public class MinHeap <E extends Comparable<E>>{
    private Array<E> data;

    public MinHeap()
    {
        data = new Array<>();
    }

    public MinHeap(int capacity)
    {
        data = new Array<>(capacity);
    }

    public int size()
    {
        return data.getSize();
    }


    public boolean isEmpty()
    {
        return data.isEmpty();
    }

    private int parent(int index)
    {
        if(index <= 0)
            throw new IllegalArgumentException("wrong index");
        return (index-1)/2;
    }

    private int leftChild(int index)
    {
        return index*2 + 1;
    }

    private int rightChild(int index)
    {
        return index*2+2;
    }


    public void add(E e)
    {
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    private void siftUp(int k)
    {
        while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) > 0)
        {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    public E findMin(){
        if(data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMin when heap is empty.");
        return data.get(0);
    }

    // 取出堆中最大元素
    public E extractMin(){

        E ret = findMin();

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    private void siftDown(int k){

        while(leftChild(k) < data.getSize()){
            int j = leftChild(k); // 在此轮循环中,data[k]和data[j]交换位置
            if( j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) < 0 )
                j ++;
            // data[j] 是 leftChild 和 rightChild 中的最大值

            if(data.get(k).compareTo(data.get(j)) <= 0 )
                break;

            data.swap(k, j);
            k = j;
        }
    }

    // 取出堆中的最小元素，并且替换成元素e
    public E replace(E e){

        E ret = findMin();
        data.set(0, e);
        siftDown(0);
        return ret;
    }


}

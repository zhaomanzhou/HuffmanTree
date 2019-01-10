package util.queue;

import util.heap.MinHeap;

public class MinPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MinHeap<E> heap = new MinHeap<>();
    @Override
    public int getSize() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        heap.add(e);
    }

    @Override
    public E dequeue() {
        return heap.extractMin();
    }

    @Override
    public E getFront() {
        return heap.findMin();
    }
}

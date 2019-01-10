package test;

import org.junit.Test;
import util.heap.MinHeap;

public class TestMinHeap {
    @Test
    public void testCorrect()
    {
        MinHeap<Integer> heap = new MinHeap<>();
        for(int i = 0; i < 100; i++)
        {
            heap.add((int) (Math.random()*1000));
        }

        while(!heap.isEmpty())
        {
            System.out.println(heap.extractMin());
        }
    }
}

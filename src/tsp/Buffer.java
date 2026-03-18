package tsp;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Buffer<T> {
    private final BlockingQueue<T> queue;

    public Buffer(int capacity) {
        queue = new LinkedBlockingQueue<>(capacity);
    }

    public void put(T item) throws InterruptedException {
        queue.put(item);
    }

    public T take() throws InterruptedException {
        return queue.take();
    }
}
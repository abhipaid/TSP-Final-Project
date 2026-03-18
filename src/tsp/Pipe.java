package tsp;

public class Pipe<T> {
    private final Buffer<T> buffer;

    public Pipe(int capacity) {
        buffer = new Buffer<>(capacity);
    }

    public void send(T item) throws InterruptedException {
        buffer.put(item);
    }

    public T receive() throws InterruptedException {
        return buffer.take();
    }
}
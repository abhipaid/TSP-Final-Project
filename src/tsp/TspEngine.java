package tsp;

import java.util.ArrayList;
import java.util.List;

public class TspEngine {
    private final List<City> cities;
    private final TspFrame frame;

    public TspEngine(List<City> cities, TspFrame frame) {
        this.cities = cities;
        this.frame = frame;
    }

    public void runDistributedNearestNeighbor() {
        int workerCount = Runtime.getRuntime().availableProcessors();

        Pipe<Task> pipe = new Pipe<>(cities.size());
        Pub pub = new Pub();
        ReaderWriterBestTourStore store = new ReaderWriterBestTourStore();
        Sub sub = new Sub(store, frame);

        pub.subscribe(sub);

        List<Thread> workers = new ArrayList<>();

        for (int i = 0; i < workerCount; i++) {
            Thread t = new Thread(new Worker(cities, pipe, pub), "worker-" + i);
            workers.add(t);
            t.start();
        }

        new Thread(new Outsourcer(cities, pipe, workerCount), "outsourcer").start();
    }
}
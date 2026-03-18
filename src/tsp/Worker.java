package tsp;

import java.util.List;

public class Worker implements Runnable {
    private final List<City> cities;
    private final Pipe<Task> pipe;
    private final Pub pub;

    public Worker(List<City> cities, Pipe<Task> pipe, Pub pub) {
        this.cities = cities;
        this.pipe = pipe;
        this.pub = pub;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Task task = pipe.receive();

                if (task.getStartIndex() == -1) {
                    break;
                }

                int start = task.getStartIndex();

                List<Integer> tour = NearestNeighborSolver.solve(cities, start);
                double len = NearestNeighborSolver.length(cities, tour);

                pub.publish(new TourResult(start, tour, len));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
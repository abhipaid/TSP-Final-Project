package tsp;

import java.util.List;

public class Outsourcer implements Runnable {
    private final List<City> cities;
    private final Pipe<Task> pipe;
    private final int workers;

    public Outsourcer(List<City> cities, Pipe<Task> pipe, int workers) {
        this.cities = cities;
        this.pipe = pipe;
        this.workers = workers;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < cities.size(); i++) {
                pipe.send(new Task(i));
            }

            for (int i = 0; i < workers; i++) {
                pipe.send(new Task(-1)); // stop signal
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
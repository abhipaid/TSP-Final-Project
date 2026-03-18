package tsp;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderWriterBestTourStore {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private List<Integer> bestTour = List.of();
    private double bestLength = Double.POSITIVE_INFINITY;

    public boolean updateIfBetter(TourResult result) {
        lock.writeLock().lock();
        try {
            if (result.getLength() < bestLength) {
                bestLength = result.getLength();
                bestTour = result.getTour();
                return true;
            }
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public List<Integer> getBestTour() {
        lock.readLock().lock();
        try {
            return bestTour;
        } finally {
            lock.readLock().unlock();
        }
    }
}
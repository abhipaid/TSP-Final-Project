package tsp;

import java.util.List;

public class TourResult {
    private final int startIndex;
    private final List<Integer> tour;
    private final double length;

    public TourResult(int startIndex, List<Integer> tour, double length) {
        this.startIndex = startIndex;
        this.tour = tour;
        this.length = length;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public List<Integer> getTour() {
        return tour;
    }

    public double getLength() {
        return length;
    }
}
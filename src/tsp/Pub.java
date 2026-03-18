package tsp;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Pub {
    private final List<ResultListener> listeners = new CopyOnWriteArrayList<>();

    public void subscribe(ResultListener listener) {
        listeners.add(listener);
    }

    public void publish(TourResult result) {
        for (ResultListener l : listeners) {
            l.onResult(result);
        }
    }
}
package tsp;

public class Sub implements ResultListener {
    private final ReaderWriterBestTourStore store;
    private final TspFrame frame;

    public Sub(ReaderWriterBestTourStore store, TspFrame frame) {
        this.store = store;
        this.frame = frame;
    }

    @Override
    public void onResult(TourResult result) {
        boolean improved = store.updateIfBetter(result);

        if (improved) {
            javax.swing.SwingUtilities.invokeLater(() -> {
                frame.showTour(store.getBestTour());
                frame.appendLog(
                        "finished start city " + result.getStartIndex()
                                + " | length = " + String.format("%.3f", result.getLength()) + "\n"
                );
            });
        }
    }
}
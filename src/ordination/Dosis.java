package ordination;

import java.time.LocalTime;

public class Dosis {
    private final LocalTime tid;
    private final double antal;
    private final DagligSkæv dagligSkæv;
    private final DagligFast dagligFast;


    /** Note: DagligSkæv og DagligFast is nullable */
    public Dosis(LocalTime tid, double antal, DagligSkæv dagligSkæv, DagligFast dagligFast) {
        this.tid = tid;
        this.antal = antal;
        this.dagligSkæv = dagligSkæv;
        this.dagligFast = dagligFast;
    }

    public double getAntal() {
        return antal;
    }

    public LocalTime getTid() {
        return tid;
    }

    @Override
    public String toString() {
        return "Kl: " + tid + "   antal:  " + antal;
    }
}

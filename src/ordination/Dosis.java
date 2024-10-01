package ordination;

import java.time.LocalTime;

public class Dosis {
    private final LocalTime tid;
    private final double antal;
    private final DagligFast dagligFast;


    public Dosis(LocalTime tid, double antal, DagligFast dagligFast ) {
        this.tid = tid;
        this.antal = antal;
        this.dagligFast = dagligFast;

    }

    public double getAntal() {
        return antal;
    }

    @Override
    public String toString() {
        return "Kl: " + tid + "   antal:  " + antal;
    }
}

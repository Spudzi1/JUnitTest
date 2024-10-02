package ordination;

import java.time.LocalDate;

public class DagligFast extends Ordination {
    private final Dosis[] doser = new Dosis[4];

    public DagligFast(LocalDate startDato, LocalDate slutDato){
        super(startDato, slutDato);
    }

    public Dosis[] getDoser() {
        return doser;
    }

    public void addDosis(Dosis dosis, int index) {
        if (index < 0 || index >= doser.length) {
        }
        doser[index] = dosis;
    }

    public void removeDosis(int index) {
        if (index < 0 || index >= doser.length) {
        }
        doser[index] = null;
    }

    @Override
    public double samletDosis(){
        double totalDosis = 0;
        for (Dosis dosis : doser) {
            if (dosis != null) {
                totalDosis += dosis.getAntal();
            }
        }
        return totalDosis;
    }

    @Override
    public double døgnDosis(){
        return samletDosis() / antalDage();
    }

    @Override
    public String getType(){
        return "Daglig fast: ";
    }

}

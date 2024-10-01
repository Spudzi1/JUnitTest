package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DagligSkæv extends Ordination  {

  private final  List<Dosis> doser = new ArrayList<>();

    public DagligSkæv(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }

    public List<Dosis> getDoser() {
        return new ArrayList<>(doser);
    }

    public void addDosis(Dosis dosis) {
        doser.add(dosis);
    }

    public void removeDosis(Dosis dosis) {
        doser.remove(dosis);
    }

    @Override
    public double samletDosis() {
      double samletDosis = 0;
      double antalDage = antalDage();
        for (Dosis dosis : doser) {
            samletDosis += dosis.getAntal();
        }
        return samletDosis / antalDage;
    }

    @Override
    public double døgnDosis() {
        double døgnDosis = 0;
        for (Dosis dosis : doser) {
            døgnDosis += dosis.getAntal();
        }
        return døgnDosis;
    }

    @Override
    public String getType() {
        return "DagligSkæv";
    }
}

package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DagligFast extends Ordination {
    private Dosis[] doser = new Dosis[4];

    public DagligFast(LocalDate startDato, LocalDate slutDato, Lægemiddel lægemiddel, double morgenDosis, double middagDosis, double aftenDosis, double natDosis) {
        super(startDato, slutDato, lægemiddel);
        doser = new Dosis[4];

        if (morgenDosis>=0) {
            doser[0] = new Dosis(LocalTime.of(6,0), morgenDosis, null, this);
        }
        if (middagDosis>=0) {
            doser[1] = new Dosis(LocalTime.of(12,0), middagDosis, null, this);
        }
        if (aftenDosis>=0) {
            doser[2] = new Dosis(LocalTime.of(18,0), aftenDosis, null, this);
        }
        if (natDosis>=0) {
            doser[3] = new Dosis(LocalTime.of(21,0), natDosis, null, this);
        }
    }

    public Dosis[] getDoser() {
        return doser;
    }

    /** Returner den totale dosis, der er givet i den periode, ordinationen er gyldig. */
    @Override
    public double samletDosis(){
        // Tjek at den samlede dosis per døgn ikke overstiger 4
        double dagligDosis = 0;
        for (Dosis dosis : doser) {
            dagligDosis += dosis.getAntal();
        }
        if (dagligDosis > 4) {
            throw new IllegalArgumentException("Ordinationen kan ikke lade sig gøre, fordi samlet dosis må højest være 4 doser pr. døgn");
        }

        // Tjek at startdatoen er før slutdatoen
        if (super.getStartDato().isAfter(super.getSlutDato())) {
            throw new IllegalArgumentException("Ordinationen kan ikke lade sig gøre fordi slut datoen er før start datoen");
        }

        // Beregn antal dage og den samlede dosis over perioden
        long daysBetween = ChronoUnit.DAYS.between(getStartDato(), getSlutDato()) + 1;
        return daysBetween * dagligDosis;
    }

    /** Returner den gennemsnitlige dosis givet per dag. */
    @Override
    public double døgnDosis(){
        if ((doser[0].getAntal() + doser[1].getAntal() +
                doser[2].getAntal() + doser[3].getAntal()) > 4) {
            throw new IllegalArgumentException("Ordinationen kan ikke lade sig gøre fordi døgn dosisen er for over 4 pr. døgn");
        }
        return doser[0].getAntal() + doser[1].getAntal() + doser[2].getAntal() + doser[3].getAntal();
    }

    /** Returner ordinationstypen som en String. */
    @Override
    public String getType(){
        return "Daglig fast: ";
    }

}

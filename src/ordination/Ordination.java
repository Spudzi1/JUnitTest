package ordination;

import com.sun.source.tree.ReturnTree;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Ordination {
    private final LocalDate startDato;
    private final LocalDate slutDato;
    private Lægemiddel lægemiddel;
    private Patient patient;

    protected Ordination(LocalDate startDato, LocalDate slutDato, Patient patient, Lægemiddel lægemiddel) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.patient = patient;
        this.lægemiddel = lægemiddel;
//        setLægemiddel(lægemiddel); //Sikrer at ordinationen er tilknyttet det korrekte lægemiddel
//        patient.addOrdination(this); //Tilføjer ordinationen til patientens liste af ordinationer
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    /**
     * Returner antal hele dage mellem startdato og slutdato
     * (begge dage inklusive).
     */
    public int antalDage() {
        return (int) ChronoUnit.DAYS.between(startDato, slutDato) + 1;
    }

    @Override
    public String toString() {
        return startDato.toString();
    }

    /** Returner den totale dosis, der er givet i den periode, ordinationen er gyldig. */
    public abstract double samletDosis();

    /** Returner den gennemsnitlige dosis givet per dag. */
    public abstract double døgnDosis();

    /** Returner ordinationstypen som en String. */
    public abstract String getType();

    /** Returner Lægemidlet
     * Note: Nullable return value. */
    public Lægemiddel getLægemiddel() {
        return lægemiddel;
    }

    /** Note: Nullable param lægemiddel */
    public void setLægemiddel(Lægemiddel lægemiddel) {
        this.lægemiddel = lægemiddel;
    }
}

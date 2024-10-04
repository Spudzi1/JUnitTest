package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PN extends Ordination {

    private final double antalEnheder;
    private final List<LocalDate> anvendteDoser = new ArrayList<>();

    public PN(LocalDate startDato, LocalDate slutDato, double antalEnheder, Lægemiddel lægemiddel) {
        super(startDato, slutDato, lægemiddel);
        this.antalEnheder = antalEnheder;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    public List<LocalDate> getAnvendteDoser() {
        return new ArrayList<>(anvendteDoser);
    }

    public void addAnvendtDosis(LocalDate dato) {
        anvendteDoser.add(dato);
    }

    public void removeAnvendtDosis(LocalDate dato) {
        anvendteDoser.remove(dato);
    }

    /**
     * Registrer datoen for en anvendt dosis.
     */
    public void anvendDosis(LocalDate dato) {
        anvendteDoser.add(dato);
    }

    /**
     * Returner antal gange ordinationen er anvendt.
     */
    public int antalGangeAnvendt() {
        return anvendteDoser.size();
    }

    @Override
    public double samletDosis() {
        return antalEnheder * anvendteDoser.size();
    }

    @Override
    public double døgnDosis() {
        if (anvendteDoser.isEmpty()) return 0;
        Collections.sort(anvendteDoser);
        LocalDate førstAnvendt = anvendteDoser.getFirst();
        LocalDate sidstAnvendt = anvendteDoser.getLast();
        long dagsinterval = ChronoUnit.DAYS.between(førstAnvendt, sidstAnvendt) + 1;
        return samletDosis() / dagsinterval;
    }

    @Override
    public String getType() {
        return "PN: ";
    }

}

package ordination;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PN extends Ordination {

    private final double antalEnheder;
    private final List<LocalDate> anvendteDoser = new ArrayList<>();

   public PN(LocalDate startDato, LocalDate slutDato, double antalEnheder){
        super(startDato, slutDato);
        this.antalEnheder = antalEnheder;
   }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    /**
     * Registrer datoen for en anvendt dosis.
     */
    public void anvendDosis(LocalDate dato) {
        if ((dato.isEqual(getStartDato()) || dato.isAfter(getStartDato())) &&
                (dato.isEqual(getSlutDato()) || dato.isBefore(getSlutDato()))) {
            anvendteDoser.add(dato);
        } else {
            throw new IllegalArgumentException("Datoen ligger uden for ordinationens start- og slutdato.");
        }
    }

    /** Returner antal gange ordinationen er anvendt. */
    public int antalGangeAnvendt() {
        return anvendteDoser.size();
    }

    @Override
    public double samletDosis(){
        return antalEnheder * antalGangeAnvendt();
    }

    @Override
    public double døgnDosis(){
        return samletDosis() / antalDage();
    }

    @Override
    public String getType(){
        return "PN: ";
    }
}

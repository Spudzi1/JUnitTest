package ordination;

import controller.Controller;

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

    public List<LocalDate> getAnvendteDoser(){
        return new ArrayList<>(anvendteDoser);
    }

    public void addAnvendtDosis(LocalDate dato){
        anvendteDoser.add(dato);
    }

    public void removeAnvendtDosis(LocalDate dato){
        anvendteDoser.remove(dato);
    }

    /**
     * Registrer datoen for en anvendt dosis.
     */
    public void anvendDosis(LocalDate dato) {
        anvendteDoser.add(dato);
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
    //
}

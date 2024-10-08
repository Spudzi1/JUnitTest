package ordination;

import java.util.ArrayList;

public class Patient {
    private final String cprNr;
    private final String navn;
    private final double vægt;
    private final ArrayList<Ordination> ordinationer = new ArrayList<>();

    public ArrayList<Ordination> getOrdinationer() {
        return new ArrayList<>(ordinationer);
    }

    public Patient(String cprNr, String navn, double vægt) {
        this.cprNr = cprNr;
        this.navn = navn;
        this.vægt = vægt;
    }

    public double getVægt() {
        return vægt;
    }

    @Override
    public String toString() {
        return navn + "  " + cprNr;
    }

    public void addOrdination(Ordination ordination){
        ordinationer.add(ordination);
    }

    public void removeOrdination(Ordination ordination){
        ordinationer.remove(ordination);
    }
}

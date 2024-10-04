package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PNTest {
    @Test
    void constructorPN() {

        //Arrange
        Lægemiddel paracetamol = new Lægemiddel("Paracetamol", 0.1, 0.15, 0.16, "Styk");

        //Act
        PN pn = new PN(LocalDate.of(2024, 9, 6), LocalDate.of(2024, 9, 16), 123, paracetamol);

        // assert
        assertEquals(LocalDate.of(2024, 9, 6), pn.getStartDato());
        assertEquals(LocalDate.of(2024, 9, 16), pn.getSlutDato());
        assertEquals(123, pn.getAntalEnheder());
        assertEquals(paracetamol, pn.getLægemiddel());
        assertEquals(new ArrayList<LocalDate>(), pn.getAnvendteDoser());
    }

    @Test
    void samletDosis() {

        //Arrange
        Lægemiddel paracetamol = new Lægemiddel("Paracetamol", 0.1, 0.15, 0.16, "Styk");

        //Act
        PN pn = new PN(LocalDate.of(2024, 9, 6), LocalDate.of(2024, 9, 8), 123, paracetamol);

        // assert
        assertEquals(LocalDate.of(2024, 9, 6), pn.getStartDato());
        assertEquals(LocalDate.of(2024, 9, 16), pn.getSlutDato());
        assertEquals(123, pn.getAntalEnheder());
        assertEquals(paracetamol, pn.getLægemiddel());
        assertEquals(new ArrayList<LocalDate>(), pn.getAnvendteDoser());
    }


    // @Override
    //    public double samletDosis() {
    //        double samletDosis = 0;
    //        for (LocalDate date : anvendteDoser) {
    //            samletDosis += antalEnheder;
    //        }
    //        return samletDosis;
    //    }
}

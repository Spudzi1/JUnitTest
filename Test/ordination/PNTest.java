package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
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
        PN pn = new PN(LocalDate.of(2024, 9, 6), LocalDate.of(2024, 9, 8), 5, paracetamol);


        //Act
        pn.addAnvendtDosis(LocalDate.of(2024,9,6));
        pn.addAnvendtDosis(LocalDate.of(2024,9,7));
        double samletDosis = pn.samletDosis();

        //Assert
        assertEquals(10, samletDosis);
    }

}

package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PNTest {
    @Test
    void tc1_constructPN() {

        //Arrange
        Lægemiddel paracetamol = new Lægemiddel("Paracetamol", 0.1, 0.15, 0.16, "Styk");
        PN pn = new PN(LocalDate.of(2024, 9, 6), LocalDate.of(2024, 9, 16), 123, paracetamol);

        // assert
        assertEquals(123, pn.getAntalEnheder());
        assertEquals(paracetamol, pn.getLægemiddel());


    }
}

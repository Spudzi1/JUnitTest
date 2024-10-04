package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DagligSkævTest {

    @Test
    void ConstructDagligSkæv() {
        //Arrange
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");

        //Act
        DagligSkæv DagligSkæv1 = new DagligSkæv(LocalDate.of(2024, 9, 10), LocalDate.of(2024, 9, 10), L1);

        //Assert
        assertEquals(LocalDate.of(2024,9,10), DagligSkæv1.getStartDato());
        assertEquals(LocalDate.of(2024,9,10), DagligSkæv1.getSlutDato());
        assertEquals(L1, DagligSkæv1.getLægemiddel());
        assertEquals(new ArrayList<Dosis>(), DagligSkæv1.getDoser());
    }

    @Test
    void samletDosis() {
        //Arrange
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");


    }

    @Test
    void døgnDosis() {
    }
}
package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DagligSkævTest {

    @Test
    void ConstructDagligSkæv() {
        //Arrange
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");

        //Act
        DagligSkæv DagligSkæv = new DagligSkæv(LocalDate.of(2024, 9, 10), LocalDate.of(2024, 9, 10), L1);

        //Assert
        assertEquals(LocalDate.of(2024,9,10), DagligSkæv.getStartDato());
        assertEquals(LocalDate.of(2024,9,10), DagligSkæv.getSlutDato());
        assertEquals(L1, DagligSkæv.getLægemiddel());
        assertEquals(new ArrayList<Dosis>(), DagligSkæv.getDoser());
    }

    @Test
    void TC1_samletDosis() {
        //Arrange
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        DagligSkæv DagligSkæv1 = new DagligSkæv(LocalDate.of(2024, 9, 20), LocalDate.of(2024, 9, 30), L1);
        DagligSkæv1.addDosis(new Dosis(LocalTime.of(7, 30), 2, DagligSkæv1, null));
        DagligSkæv1.addDosis(new Dosis(LocalTime.of(15, 30), 2, DagligSkæv1, null));

        //Act
        double samletDosis = DagligSkæv1.samletDosis();

        //Assert
        assertEquals(44, samletDosis);
    }
    @Test
    void TC2_samletDosis() {
        //Arrange
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        DagligSkæv DagligSkæv1 = new DagligSkæv(LocalDate.of(2024, 8, 8), LocalDate.of(2024, 8, 8), L1);
        DagligSkæv1.addDosis(new Dosis(LocalTime.of(7, 30), 2, DagligSkæv1, null));
        DagligSkæv1.addDosis(new Dosis(LocalTime.of(15, 30), 2, DagligSkæv1, null));

        //Act
        double samletDosis = DagligSkæv1.samletDosis();

        //Assert
        assertEquals(4, samletDosis);
    }

    @Test
    void TC1_døgnDosis() {
        //Arrange
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        DagligSkæv DagligSkæv1 = new DagligSkæv(LocalDate.of(2024, 8, 8), LocalDate.of(2024, 8, 8), L1);
        DagligSkæv1.addDosis(new Dosis(LocalTime.of(10, 30), 1, DagligSkæv1, null));
        DagligSkæv1.addDosis(new Dosis(LocalTime.of(11, 30), 2, DagligSkæv1, null));
        DagligSkæv1.addDosis(new Dosis(LocalTime.of(12, 30), 2, DagligSkæv1, null));

        //Act
        double døgnDosis = DagligSkæv1.døgnDosis();

        //Assert
        assertEquals(5, døgnDosis);
    }

    @Test
    void TC2_døgnDosis() {
        //Arrange
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        DagligSkæv DagligSkæv1 = new DagligSkæv(LocalDate.of(2024, 8, 8), LocalDate.of(2024, 8, 8), L1);
        DagligSkæv1.addDosis(new Dosis(LocalTime.of(10, 30), 0, DagligSkæv1, null));
        DagligSkæv1.addDosis(new Dosis(LocalTime.of(11, 30), 0, DagligSkæv1, null));

        //Act
        double døgnDosis = DagligSkæv1.døgnDosis();

        //Assert
        assertEquals(0, døgnDosis);
    }
}
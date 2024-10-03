package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    @Test
    void TC1_samletDosis() {
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1,0.15,0.16,"Styk");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2024,9,20), LocalDate.of(2024,9,30), lægemiddel, 4,0,0,0);

        //Act
        double faktiskSamletDosis = dagligFast.samletDosis();

        //Assert
        double forventetSamletDosis = 44;
        assertEquals(forventetSamletDosis, faktiskSamletDosis);
    }

    @Test
    void TC2_samletDosis(){
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1,0.15,0.16,"Styk");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2024,8,10), LocalDate.of(2024,8,10), lægemiddel, 0,2,0,2);

        //Act
        double faktiskSamletDosis = dagligFast.samletDosis();

        //Assert
        double forventetSamletDosis = 4;
    }

    @Test
    void TC1_døgnDosis(){
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1,0.15,0.16,"Styk");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2024,9,10), LocalDate.of(2024,9,10), lægemiddel, 1,2,0,0);

        //Act
        double faktiskDøgnDosis = dagligFast.døgnDosis();

        //Assert
        double forventetDøgnDosis = 3;
        assertEquals(forventetDøgnDosis, faktiskDøgnDosis);
    }

    @Test
    void TC2_døgnDosis(){
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1,0.15,0.16,"Styk");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);
        DagligFast dagligFast = new DagligFast(LocalDate.of(2024,9,10), LocalDate.of(2024,9,10), lægemiddel, 0,0,0,0);

        //Act
        double faktiskDøgnDosis = dagligFast.døgnDosis();

        //Assert
        double forventetDøgnDosis = 0;
        assertEquals(forventetDøgnDosis, faktiskDøgnDosis);
    }
    @Test
    void TC1_getDoser_returnDoser() {
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        DagligFast dagligFast = new DagligFast(LocalDate.of(2024, 9, 10), LocalDate.of(2024, 9, 15), lægemiddel, 1, 2, 3, 4);

        //Act
        Dosis[] doser = dagligFast.getDoser();

        //Assert
        assertEquals(1, doser[0].getAntal());
        assertEquals(2, doser[1].getAntal());
        assertEquals(3, doser[2].getAntal());
        assertEquals(4, doser[3].getAntal());
    }

    @Test
    void TC1_getType_returnString() {
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        DagligFast dagligFast = new DagligFast(LocalDate.of(2024, 9, 10), LocalDate.of(2024, 9, 15), lægemiddel, 1, 2, 3, 4);

        String type = dagligFast.getType();

        assertEquals("Daglig fast: ", type);
    }


}
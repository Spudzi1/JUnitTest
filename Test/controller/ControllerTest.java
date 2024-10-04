package controller;

import ordination.DagligFast;
import ordination.Lægemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void opretPNOrdination() {

    }

    @Test
    void TC1_opretDagligFastOrdination() {
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);

        //Act
        DagligFast dagligFast = Controller.opretDagligFastOrdination(
                LocalDate.of(2024, 9, 20), LocalDate.of(2024, 9, 30), patient, lægemiddel, 4, 0, 0, 0);

        //Assert
        assertNotNull(dagligFast);
        assertEquals(LocalDate.of(2024, 9, 20), dagligFast.getStartDato());
        assertEquals(LocalDate.of(2024, 9, 30), dagligFast.getSlutDato());
        assertEquals(lægemiddel, dagligFast.getLægemiddel());
        assertTrue(patient.getOrdinationer().contains(dagligFast));
    }

    @Test
    void TC2_opretDagligFastOrdination() {
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);

        //Act
        DagligFast dagligFast = Controller.opretDagligFastOrdination(
                LocalDate.of(2024, 8, 8), LocalDate.of(2024, 8, 8), patient, lægemiddel, 0, 2, 0, 2);

        //Assert
        assertNotNull(dagligFast);
        assertEquals(LocalDate.of(2024, 8, 8), dagligFast.getStartDato());
        assertEquals(LocalDate.of(2024, 8, 8), dagligFast.getSlutDato());
        assertEquals(lægemiddel, dagligFast.getLægemiddel());
        assertTrue(patient.getOrdinationer().contains(dagligFast));
    }

    @Test
    void TC3_opretDagligFastOrdination(){
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);

        //Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Controller.opretDagligFastOrdination(
                    LocalDate.of(2024, 8, 29), LocalDate.of(2024, 8, 27), patient, lægemiddel, 1, 1, 0, 2);
        });

        //Assert
        assertAll(exception.getMessage(), () -> {
            assertEquals("Slutdato skal være efter startdato", exception.getMessage());
        });
    }

    @Test
    void TC4_opretDagligFastOrdination(){
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);

        //Act
        DagligFast dagligFast = Controller.opretDagligFastOrdination(
                LocalDate.of(2024, 8, 8), LocalDate.of(2024, 9, 20), patient, lægemiddel, 0, 0, 0, 0);

        //Assert
        assertNotNull(dagligFast);
        assertEquals(LocalDate.of(2024, 8, 8), dagligFast.getStartDato());
        assertEquals(LocalDate.of(2024, 9, 20), dagligFast.getSlutDato());
        assertEquals(lægemiddel, dagligFast.getLægemiddel());
        assertTrue(patient.getOrdinationer().contains(dagligFast));

    }

    @Test
    void TC5_opretDagligFastOrdination(){
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);

        //Act
        DagligFast dagligFast = Controller.opretDagligFastOrdination(
                LocalDate.of(2024, 8, 10), LocalDate.of(2024, 8, 10), patient, lægemiddel, 1, 1, 0, 2);

        //Assert
        assertNotNull(dagligFast);
        assertEquals(LocalDate.of(2024, 8, 10), dagligFast.getStartDato());
        assertEquals(LocalDate.of(2024, 8, 10), dagligFast.getSlutDato());
        assertEquals(lægemiddel, dagligFast.getLægemiddel());
        assertTrue(patient.getOrdinationer().contains(dagligFast));
    }
    @Test
    void TC6_opretDagligFastOrdinationn(){
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);

        //Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Controller.opretDagligFastOrdination(
                    LocalDate.of(2024, 8, 10), LocalDate.of(2024, 8, 10), patient, lægemiddel, -1, -1, -1, 0);
        });

        //Assert
        assertAll(exception.getMessage(), () -> {
            assertEquals("Doserne skal være større end eller lig med 0", exception.getMessage());
        });
    }




    @Test
    void opretDagligSkævOrdination() {
    }

    @Test
    void anvendOrdinationPN() {
    }

    @Test
    void anbefaletDosisPrDøgn() {
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }
}
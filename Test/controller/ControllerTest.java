package controller;

import com.sun.scenario.animation.AnimationPulseMBean;
import ordination.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @BeforeEach
    public void setUp() {
        Storage storage = new Storage();
    }

    @Test
    void TC1_opretPNOrdination() {
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);
        LocalDate startDato = LocalDate.of(2024, 9, 20);
        LocalDate slutDato = LocalDate.of(2024, 9, 30);

        //Act
        PN pn = Controller.opretPNOrdination(startDato, slutDato, patient, lægemiddel, 123);

        //Assert
        assertNotNull(pn);
        assertEquals(LocalDate.of(2024, 9, 20), pn.getStartDato());
        assertEquals(LocalDate.of(2024, 9, 30), pn.getSlutDato());
        assertEquals(lægemiddel, pn.getLægemiddel());
        assertTrue(patient.getOrdinationer().contains(pn));
    }

    @Test
    void TC2_opretPNOrdination() {
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);
        LocalDate startDato = LocalDate.of(2024, 8, 8);
        LocalDate slutDato = LocalDate.of(2024, 8, 8);

        //Act
        PN pn = Controller.opretPNOrdination(startDato, slutDato, patient, lægemiddel, 123);

        //Assert
        assertNotNull(pn);
        assertEquals(LocalDate.of(2024, 8, 8), pn.getStartDato());
        assertEquals(LocalDate.of(2024, 8, 8), pn.getSlutDato());
        assertEquals(lægemiddel, pn.getLægemiddel());
        assertTrue(patient.getOrdinationer().contains(pn));
    }

    @Test
    void TC3_opretPNOrdination(){
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);
        LocalDate startDato = LocalDate.of(2024, 8, 29);
        LocalDate slutDato = LocalDate.of(2024, 8, 27);

        //Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Controller.opretPNOrdination(startDato, slutDato, patient, lægemiddel, 123);
        });

        //Assert
        assertAll(exception.getMessage(), () -> {
            assertEquals("Startdato er efter slutdato og du kan derfor ikke oprette ordinationen", exception.getMessage());
        });
    }
    @Test
    void TC4_opretPNOrdination(){
        //Arrange
        Lægemiddel lægemiddel = new Lægemiddel("Paracetamol", 1, 1.5, 2, "Ml");
        Patient patient = new Patient("123456-7890", "Jane Jensen", 63.4);
        LocalDate startDato = LocalDate.of(2024, 8, 8);
        LocalDate slutDato = LocalDate.of(2024, 9, 30);

        //Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Controller.opretPNOrdination(startDato, slutDato, patient, lægemiddel, 0);
        });

        //Assert
        assertAll(exception.getMessage(), () -> {
            assertEquals("Antallet på ordinationen skal være større end 0", exception.getMessage());
        });
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
    void TC1_opretDagligSkævOrdination() {
        //Arrange
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient P1 = new Patient("123456-7890", "Jane Jensen", 63.4);
        LocalTime[] tidspunkter = {
                LocalTime.of(9, 30),
                LocalTime.of(12, 15),
                LocalTime.of(16, 00),
                LocalTime.of(20,30),
                LocalTime.of(22, 00),
        };
        double[] antal = {
                1.0, 2.0, 1.0, 1.0, 2.0
        };

                //Act
        DagligSkæv DagligSkæv = Controller.opretDagligSkævOrdination(LocalDate.of(2024, 9, 20),
                LocalDate.of(2024,9,30), P1, L1, tidspunkter, antal);

        //Assert
        assertEquals(LocalDate.of(2024,9,20), DagligSkæv.getStartDato());
        assertEquals(LocalDate.of(2024,9,30), DagligSkæv.getSlutDato());
        assertEquals(true, P1.getOrdinationer().contains(DagligSkæv));
        assertEquals(L1, DagligSkæv.getLægemiddel());
        assertEquals(5, DagligSkæv.getDoser().size());
        for (int i = 0; i < tidspunkter.length; i++) {
            Dosis dosis = DagligSkæv.getDoser().get(i);
            assertEquals(tidspunkter[i], dosis.getTid());
            assertEquals(antal[i], dosis.getAntal());
        }
    }

    @Test
    void TC2_opretDagligSkævOrdination() {
        //Arrange
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient P1 = new Patient("123456-7890", "Jane Jensen", 63.4);
        LocalTime[] tidspunkter = {
                LocalTime.of(9, 30),
                LocalTime.of(12, 15),
                LocalTime.of(16, 00),
                LocalTime.of(20, 30),
                LocalTime.of(22, 00),
                LocalTime.of(23, 00),
        };
        double[] antal = {
                1.0, 2.0, 1.0, 1.0, 2.0
        };

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Controller.opretDagligSkævOrdination(LocalDate.of(2024, 8, 8),
                    LocalDate.of(2024, 9, 9), P1, L1, tidspunkter, antal);
        });

        //Assert
        assertEquals(exception.getMessage(), "Forskel i antal tidspunkter og antal enheder");
    }

    @Test
    void TC3_opretDagligSkævOrdination() {
        //Arrange
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient P1 = new Patient("123456-7890", "Jane Jensen", 63.4);
        LocalTime[] tidspunkter = {
                LocalTime.of(9, 30),
                LocalTime.of(12, 15),
                LocalTime.of(16, 00),
                LocalTime.of(20, 30),
                LocalTime.of(22, 00),
        };
        double[] antal = {
                1.0, 2.0, 1.0, 1.0, 2.0
        };

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Controller.opretDagligSkævOrdination(LocalDate.of(2024, 8, 29),
                    LocalDate.of(2024, 8, 27), P1, L1, tidspunkter, antal);
        });

        //Assert
        assertEquals(exception.getMessage(), "Startdato er efter slutdato og du kan derfor ikke oprette ordinationen");
    }

    @Test
    void TC4_opretDagligSkævOrdination() {
        //Arrange
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        Patient P1 = new Patient("123456-7890", "Jane Jensen", 63.4);
        LocalTime[] tidspunkter = {
                LocalTime.of(9, 30),
                LocalTime.of(12, 15),
                LocalTime.of(16, 00),
                LocalTime.of(20,30),
                LocalTime.of(22, 00),
        };
        double[] antal = {
                1.0, 2.0, 1.0, 1.0, 2.0
        };

        //Act
        DagligSkæv DagligSkæv = Controller.opretDagligSkævOrdination(LocalDate.of(2024, 8, 30),
                LocalDate.of(2024,8,30), P1, L1, tidspunkter, antal);

        //Assert
        assertEquals(LocalDate.of(2024,8,30), DagligSkæv.getStartDato());
        assertEquals(LocalDate.of(2024,8,30), DagligSkæv.getSlutDato());
        assertEquals(true, P1.getOrdinationer().contains(DagligSkæv));
        assertEquals(L1, DagligSkæv.getLægemiddel());
        assertEquals(5, DagligSkæv.getDoser().size());
        for (int i = 0; i < tidspunkter.length; i++) {
            Dosis dosis = DagligSkæv.getDoser().get(i);
            assertEquals(tidspunkter[i], dosis.getTid());
            assertEquals(antal[i], dosis.getAntal());
        }
    }
    @Test
    void anvendOrdinationPN() {
    }

    @Test
    void TC1_anbefaletDosisPrDøgn() {
        //Arrange
        double vægt = 63.4;
        Patient P1 = new Patient("123456-7890", "Jane Jensen", vægt);
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");

        //Act
        double anbefaletDosis = Controller.anbefaletDosisPrDøgn(P1, L1);
        double forventedeAnbefaletDosis = L1.getEnhedPrKgPrDøgnNormal() * vægt;

        //Assert
        assertEquals(forventedeAnbefaletDosis, anbefaletDosis);
    }

    @Test
    void TC2_anbefaletDosisPrDøgn() {
        //Arrange
        double vægt = 18;
        Patient P2 = new Patient("123456-7890", "Jane Jensen", vægt);
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");

        //Act
        double anbefaletDosis = Controller.anbefaletDosisPrDøgn(P2, L1);
        double forventedeAnbefaletDosis = L1.getEnhedPrKgPrDøgnLet() * vægt;

        //Assert
        assertEquals(forventedeAnbefaletDosis, anbefaletDosis);
    }

    @Test
    void TC3_anbefaletDosisPrDøgn() {
        //Arrange
        double vægt = 125;
        Patient P3 = new Patient("123456-7890", "Jane Jensen", vægt);
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");

        //Act
        double anbefaletDosis = Controller.anbefaletDosisPrDøgn(P3, L1);
        double forventedeAnbefaletDosis = L1.getEnhedPrKgPrDøgnTung() * vægt;

        //Assert
        assertEquals(forventedeAnbefaletDosis, anbefaletDosis);
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() { //Storage problemer, jeg ik forstå
        //Arrange
        Storage storage = new Storage(); //Tjaa

        Patient P1 = Controller.opretPatient("123456-7890", "Jane Jensen", 15.4);
        Patient P2 = Controller.opretPatient("070985-1153", "Finn Madsen", 33.2);
        Patient P3 = Controller.opretPatient("050972-1233", "Hans Jørgensen", 89.4);
        Patient P4 = Controller.opretPatient("011064-1522", "Ulla Nielsen", 129.9);
        Lægemiddel L1 = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        DagligSkæv O1 = new DagligSkæv(LocalDate.of(2024, 9, 10), LocalDate.of(2024, 9, 12), L1);
        DagligSkæv O2 = new DagligSkæv(LocalDate.of(2024, 9, 10), LocalDate.of(2024, 9, 11), L1);

        P1.addOrdination(O1);
        P2.addOrdination(O1);
        P3.addOrdination(O1);
        P4.addOrdination(O1);
        P1.addOrdination(O2);
        P3.addOrdination(O2);

        //Act
        double antalOrdinationer = Controller.antalOrdinationerPrVægtPrLægemiddel(63.4, 90, L1);

        //Assert
        assertEquals(2, antalOrdinationer);
    }



}

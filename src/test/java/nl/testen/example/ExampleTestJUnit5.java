package nl.testen.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExampleTestJUnit5 {

    // Testmethodes zijn minimaal package private
    // Setup/Teardown methodes mogen private zijn. Meestal worden deze package private gemaakt.
    // Before/After All MOETEN static zijn.
    @BeforeAll
    private static void setup() {
        System.out.println("BeforeClass");
    }

    @BeforeEach
    private void setUpMethod() {
        System.out.println("BeforeMethod");
    }

    @AfterEach
    private void tearDownMethod() {
        System.out.println("AfterClass");
    }

    @AfterAll
    private static void tearDown() {
        System.out.println("AfterMethod");
    }

    @Test // Let op de juiste import!
    void test1() { // private zorgt ervoor dat de test niet gerund wordt...
        System.out.println("In test 1");
        String halloExpected = "hallo";
        String halloOutput = "hallo";
        assertEquals(halloExpected, halloOutput);
    }

    @Test
    void test2() {
        System.out.println("In test 2");
    }
}
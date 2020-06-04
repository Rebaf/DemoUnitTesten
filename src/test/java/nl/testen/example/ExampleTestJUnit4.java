package nl.testen.example;

// JUnit 4 annotaties

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExampleTestJUnit4 {

    // Methodes MOETEN public zijn
    // Before/After Class MOETEN static zijn.
    @BeforeClass
    public static void setUp() {
        System.out.println("BeforeClass");
    }

    @Before
    public void setUpMethod() {
        System.out.println("BeforeMethod");
    }

    @After
    public void tearDownMethod() {
        System.out.println("AfterClass");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("AfterMethod");
    }

    @Test // Let op de juiste import!
    public void test1() {
        System.out.println("In test 1");
        String halloExpected = "hallo";
        String halloOutput = "hallo";
        assertEquals(halloExpected, halloOutput);
    }

    @Test
    public void test2() {
        System.out.println("In test 2");
    }
}
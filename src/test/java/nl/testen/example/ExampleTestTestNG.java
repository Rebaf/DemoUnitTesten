package nl.testen.example;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ExampleTestTestNG {

    // TestNG kent ook nog BeforeGroup/Suite/Test
    // Before/After Class hoeven niet static te zijn
    // Alle methodes mogen gewoon private zijn.
    @BeforeClass
    private void setUp() {
        System.out.println("BeforeClass");
    }

    @BeforeMethod
    private void setUpMethod() {
        System.out.println("BeforeMethod");
    }

    @AfterMethod
    private void tearDownMethod() {
        System.out.println("AfterMethod");
    }

    @AfterClass
    private void tearDown() {
        System.out.println("AfterClass");
    }

    @Test // Let op de juiste import!
    private void test1() {
        System.out.println("In test 1");
        String halloExpected = "hallo";
        String halloOutput = "hallo";
        assertEquals(halloExpected, halloOutput);
    }

    @Test
    private void test2() {
        System.out.println("In test 2");
    }
}

/*
Bron: https://stackoverflow.com/questions/20394759/beforeclass-annotation-junit-vs-testng#:~:text=Because%20of%20the%20one-instance,must%20be%20aware%20of%20that.
The main difference between JUnit and TestNG is the test class instantiation.
JUnit always creates a new instance of the test class for each test method run.
TestNG only creates one test class instance and then runs all test methods of this instance.

The JUnit approach guarantees the independency of all test methods.
It just does not matter, in which order they run.
Additionally, all instance fields are always setup the same for each test method.
Initializing data, that is common for all test methods, must take place at the class level,
thus it must be static. This is the reason, why the @BeforeClass method must be static.

The TestNG approach does not guarantee the independency.
In fact, you cannot use an instance field in the same manner as in JUnit tests.
If you change such a field in one test method, the changed value is still observable
in another test method. However, this behavior has also an advantage:
Sometimes there are dependencies between some test methods.
With TestNG, a tester can express them.

Because of the one-instance-approach of TestNG, the @BeforeClass setup can also be
a non-static method, but it is still run only once. It was a design decision, but
testers using TestNG must be aware of that.
 */
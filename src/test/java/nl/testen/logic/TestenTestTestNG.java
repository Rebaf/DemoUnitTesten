package nl.testen.logic;

import nl.testen.exceptions.WaardeBuitenRange;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// assertj Maven dependency via https://joel-costigliola.github.io/assertj/assertj-core-quick-start.html
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;


public class TestenTestTestNG {
    private Testen testen;
//
//    @BeforeClass
//    public void setUpClass() {
//        testen = new Testen(); // Of 1 instantie voor alle testen
//    }

    @BeforeMethod
    public void setUp() {
        testen = new Testen(); // Voor elke test nieuwe instantie
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testTeTestenMethode() {
        assertTrue(testen.teTestenMethode(1));
        assertTrue(testen.teTestenMethode(10), "Van 1 t/m 10 en 10 valt daar tussen");
        assertFalse(testen.teTestenMethode(0));
        assertFalse(testen.teTestenMethode(11), "Van 1 t/m 10 en 11 valt daar dus buiten");
    }

    // je mag de name = weglaten en dan bij de @Test de naam van deze methode opnemen.
//  @DataProvider(name = "TestGetalVarianten", parallel = true)
    @DataProvider // TestNG annotatie
    public static Object[][] getallen() {
        return new Object[][]{
                {-1, false, "-1 ligt buiten bereik 1 t/m 10"},
                {0, false, "0 ligt buiten bereik 1 t/m 10"},
                {1, true, "1 ligt binnen bereik 1 t/m 10"},
                {9, true, "9 ligt binnen bereik 1 t/m 10"},
                {10, false, "10 ligt buiten bereik 1 t/m 10"},
                {11, false, "11 ligt buiten bereik 1 t/m 10"}
        };
    }

    //  @Test(dataProvider = "TestGetalVarianten")
    @Test(dataProvider = "getallen")
    public void testVarianten(int getal, boolean uitkomst, String message)  {
        testen = new Testen();
        // TestNG
//        assertEquals(uitkomst, testen.teTestenMethode(getal), message);
        // AsertJ
        assertThat(testen.teTestenMethode(getal))
                .as(message)
                .isEqualTo(uitkomst);
    }

    @Test
    void testTeTestenMethodeException() {
        assertThrows(WaardeBuitenRange.class, () -> testen.teTestenMethodeInclException(15));

        assertThatCode(() -> testen.teTestenMethodeInclException(5))
                .doesNotThrowAnyException();
    }

    @Test(expectedExceptions = {WaardeBuitenRange.class}, expectedExceptionsMessageRegExp = "Opgegeven waarde ligt buiten de range van 1 t/m 10")
    void testTeTestenMethodeExceptionOldSchool() throws Exception{
        assertFalse(testen.teTestenMethodeInclException(15)); // maakt niet uit wat je hier assert, je krijgt toch een exception
    }

    // AssertJ
    @Test
    public void testTeTestenMethodeAssertJ() {
        assertThat(testen.teTestenMethode(1)).isTrue();
        assertThat(testen.teTestenMethode(10))
                .as("Van 1 t/m 10 en 10 valt daar tussen")
                .isTrue();
        assertThat(testen.teTestenMethode(0)).isFalse();
        assertThat(testen.teTestenMethode(11))
                .as("Van 1 t/m 10 en 11 valt daar dus buiten")
                .isFalse();
    }

    @Test
    void testTeTestenMethodeAssertJException() {
        assertThatThrownBy(() -> testen.teTestenMethodeInclException(15))
                .isInstanceOf(WaardeBuitenRange.class)
                .as("tussen 0 en 10 en -5 valt daar dus buiten")
                .hasMessage("Opgegeven waarde ligt buiten de range van 1 t/m 10");

        assertThatCode(() -> testen.teTestenMethodeInclException(5))
                .doesNotThrowAnyException();
    }
}
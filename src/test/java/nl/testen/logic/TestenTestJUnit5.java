package nl.testen.logic;

import nl.testen.exceptions.WaardeBuitenRange;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TestenTestJUnit5 {
    private Testen testen;

    @BeforeEach
    private void setUpMethod() {
        testen = new Testen();
    }

    @Test
    void teTestenMethodeTest() {
        assertTrue(testen.teTestenMethode(1));
        assertTrue(testen.teTestenMethode(10), "Van 1 t/m 10 en 10 valt daar tussen");
        assertFalse(testen.teTestenMethode(0));
        assertFalse(testen.teTestenMethode(11), "Van 1 t/m 10 en 11 valt daar dus buiten");
    }

    private static Stream<Arguments> providerVanBereikWaarden() {
        return Stream.of(
                Arguments.of(-1, false, "-1 ligt buiten bereik 1 t/m 10"),
                Arguments.of(0, false, "0 ligt buiten bereik 1 t/m 10"),
                Arguments.of(1, true, "1 ligt binnen bereik 1 t/m 10"),
                Arguments.of(9, true, "9 ligt binnen bereik 1 t/m 10"),
                Arguments.of(10, false, "10 ligt buiten bereik 1 t/m 10"),
                Arguments.of(11, false, "11 ligt buiten bereik 1 t/m 10")
        );
    }

    @ParameterizedTest
    @MethodSource("providerVanBereikWaarden")
    void teTestenMethodeMetProvider(int getal, boolean uitkomst, String message) {
        testen = new Testen();
        assertEquals(uitkomst, testen.teTestenMethode(getal), message);
    }

    @Test
    void teTestenMethodeTestException() {
        assertThrows(WaardeBuitenRange.class, () -> testen.teTestenMethodeInclException(0));
        assertThrows(WaardeBuitenRange.class, () -> testen.teTestenMethodeInclException(11), "tussen 0 en 10 en 10 valt daar dus buiten");

        assertDoesNotThrow(() -> testen.teTestenMethodeInclException(9));
    }


}

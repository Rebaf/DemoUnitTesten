package nl.testen.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PersoonTestJUnit5 {
    private static final String KAREL = "Karel";
    private static final String KARELSEN = "Karelsen";
    public static final int LEEFTIJD_KARELSEN = 30;

    @Test
    public void makenPersoon() {
        Persoon persoon = new Persoon(LEEFTIJD_KARELSEN, KAREL, null, KARELSEN);

        assertAll("Persoonsgegevens",
                () -> assertEquals(LEEFTIJD_KARELSEN, persoon.getLeeftijd()),
                () -> assertEquals(KAREL, persoon.getVoornaam()),
                () -> assertNull(persoon.getTussenvoegsel()),
                () -> assertEquals(KARELSEN, persoon.getAchternaam()),
                () -> assertNull(persoon.getGeslacht())
        );
    }
}
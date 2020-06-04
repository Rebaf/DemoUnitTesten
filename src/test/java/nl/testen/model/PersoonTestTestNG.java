package nl.testen.model;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class PersoonTestTestNG {

    private static final String KAREL = "Karel";
    private static final String KAREL_KARELSEN = "Karel Karelsen";
    private static final String KARELSEN = "Karelsen";
    public static final String VON = "Von";
    public static final String KAREL_VON_KARELSEN = "Karel Von Karelsen";
    public static final int LEEFTIJD_KARELSEN = 30;

    @Test
    public void testToString() {
        Persoon persoon = new Persoon(LEEFTIJD_KARELSEN, KAREL, null, KARELSEN);
        assertThat(persoon.toString()).isEqualTo(KAREL_KARELSEN);

        persoon.setTussenvoegsel("");
        assertThat(persoon.toString()).isEqualTo(KAREL_KARELSEN);

        persoon.setTussenvoegsel(VON);
        assertThat(persoon.toString()).isEqualTo(KAREL_VON_KARELSEN);
    }

    @Test
    public void testToStringTussenvoegselNull() {
        Persoon persoon = new Persoon(LEEFTIJD_KARELSEN, KAREL, null, KARELSEN);
        assertThat(persoon.toString()).isEqualTo(KAREL_KARELSEN);
    }

    @Test
    public void testToStringTussenvoegselLeeg() {
        Persoon persoon = new Persoon(LEEFTIJD_KARELSEN, KAREL, "", KARELSEN);
        assertThat(persoon.toString()).isEqualTo(KAREL_KARELSEN);
    }

    @Test
    public void testToStringTussenvoegselGevuld() {
        Persoon persoon = new Persoon(LEEFTIJD_KARELSEN, KAREL, VON, KARELSEN);
        assertThat(persoon.toString()).isEqualTo(KAREL_VON_KARELSEN);
    }

    @Test
    public void makenPersoon() {
        Persoon persoon = new Persoon(LEEFTIJD_KARELSEN, KAREL, null, KARELSEN);

        assertThat(persoon.getLeeftijd()).isEqualTo(LEEFTIJD_KARELSEN);
        assertThat(persoon.getVoornaam()).isEqualTo(KAREL);
        assertThat(persoon.getTussenvoegsel()).isNullOrEmpty();
        assertThat(persoon.getAchternaam()).isEqualTo(KARELSEN);
        assertThat(persoon.getGeslacht()).isNull();

//        SoftAssertions softly = new SoftAssertions();
//        softly.assertThat(persoon.getLeeftijd()).isEqualTo(LEEFTIJD_KARELSEN);
//        softly.assertThat(persoon.getVoornaam()).isEqualTo(KAREL);
//        softly.assertThat(persoon.getTussenvoegsel()).isNullOrEmpty();
//        softly.assertThat(persoon.getAchternaam()).isEqualTo(KARELSEN);
//        softly.assertThat(persoon.getGeslacht()).isNull();
//        softly.assertAll();
    }

    @Test
    public void testPersonenLijst() {
        List<Persoon> personenLijst = maakPersonenLijst();
//        assertThat(personenLijst).contains(new Persoon(30, "Karel", null, KARELSEN));  // werkt niet (of override equals)
        assertThat(personenLijst).flatExtracting("voornaam", "achternaam")
                .contains(KAREL, KARELSEN)
                .contains(KAREL, "Richardsen", "Pieter") //!!!
                .doesNotContain("Peter", "Petersen")
                .contains("Karelsen", "Karel");

        List<Persoon> vijftigOfOuder = personenLijst.stream()
                .filter(persoon -> persoon.getLeeftijd() >= 50)
                .collect(Collectors.toList());
        assertThat(vijftigOfOuder).hasSize(3);
        assertThat(vijftigOfOuder).extracting("voornaam", "achternaam")
                .contains(Assertions.tuple("Frans", "Fransen"))
        .contains(Assertions.tuple("Frans", "Richardsen")) //!!! (Dit is geen tuple (combi van gegevens binnen 1 object))
                .doesNotContain(Assertions.tuple(KAREL, KARELSEN));
    }

    private List<Persoon> maakPersonenLijst() {
        List<Persoon> personenLijst = new ArrayList<>();
        personenLijst.add(new Persoon(LEEFTIJD_KARELSEN, KAREL, null, KARELSEN, Geslacht.MAN));
        personenLijst.add(new Persoon(40, "Pieter", null, "Pietersen", Geslacht.MAN));
        personenLijst.add(new Persoon(50, "Frans", null, "Fransen", Geslacht.MAN));
        personenLijst.add(new Persoon(60, "Richard", null, "Richardsen", Geslacht.MAN));
        personenLijst.add(new Persoon(54, "Femke", null, "Jansen", Geslacht.VROUW));

        return personenLijst;
    }

    @Test
    public void testLijsten() {
        List<String> abc = Arrays.asList("a", "b", "c");
        assertThat(abc)
                .isNotEmpty()
                .hasSize(3)
                .containsAnyOf("b")
                .containsAnyOf("b", "c")
                .containsAnyOf("a", "b", "c")
                .containsAnyOf("a", "b", "c", "d")
                .containsAnyOf("e", "f", "g", "b");

        //ToDo per commentaar een aparte methode maken
        // met containsOnly, MOETEN alle elementen aanwezig zijn. (volgorde is niet van belang)
        assertThat(Arrays.asList("a", "a", "b"))
                .containsOnly("b", "a")
                .containsOnly("a", "a", "b", "b")
                .doesNotContain("c")
                .containsExactlyInAnyOrder("b", "a", "a") // niet hetzelfde als containsOnly
                .containsExactly("a", "a", "b")
                .hasSameElementsAs(Lists.newArrayList("a", "a", "b"))
                .hasSameElementsAs(Lists.newArrayList("a", "a", "b", "a", "b"))
                .containsAll(Arrays.asList("a", "a", "b", "a", "b"));

        Persoon[] personenArray = new Persoon[3];
        assertThat(abc).hasSameSizeAs(personenArray);
        assertThat(personenArray).containsNull()
                .containsOnlyNulls();
        assertThat(abc).doesNotContainNull();

        // lege lijst controles
        List lijst = new ArrayList();
        assertThat(lijst).isEmpty();
        assertThat(lijst).contains();
        assertThat(lijst).isNullOrEmpty();
        lijst = null;
        assertThat(lijst).isNullOrEmpty();
//    assertThat(lijst).isEmpty();

        // begin/einde/volgorde
        List<String> abcdef = Arrays.asList("a", "b", "c", "d", "e", "f");
        assertThat(abcdef)
                .startsWith("a", "b")
                .endsWith("e", "f");
        assertThat(abcdef)
                .containsSequence("b", "c", "d")
                .containsSequence(Arrays.asList("b", "c", "d"))
                .containsSubsequence("b", "d")
                .containsSubsequence(Arrays.asList("b", "d", "f"))
                .containsSubsequence(Arrays.asList("b", "d"))
//        .containsSubsequence("e", "d") // dit werkt niet. Moet wel in de juiste volgorde staan
                .doesNotContainSequence("e", "d");

        // contains all naar boven verplaatsen. beter bij aab
        assertThat(abcdef).containsAll(abc);
        assertThat(abcdef).containsAll(Arrays.asList("b", "c", "a"));
//    assertThat(abc).containsAll(abcdef); // Dit werkt dus ook niet.
    }

    @Test
    public void voorbeeldenMetExtracting() {
        List<Persoon> personen = maakPersonenLijst();
        assertThat(personen)
                .extracting("voornaam")
                .contains(KAREL);

        // met eigen getter, maar geen veld met deze benaming!
        assertThat(personen)
                .extracting("naam")
                .contains(KAREL_KARELSEN);

        // geneste waarden
        assertThat(personen)
                .extracting("geslacht.omschrijving")
                .contains("vrouw", "man");

        List<Persoon> dames = personen.stream() // aanroep van methode die dit voor je uitvoert.
                .filter(persoon -> persoon.getGeslacht().equals(Geslacht.VROUW))
                .collect(Collectors.toList());
        assertThat(dames)
                .extracting("geslacht.omschrijving")
                .contains("vrouw")
                .doesNotContain("man");

        assertThat(personen)
                .extracting("geslacht")
                .contains(Geslacht.MAN);


        /// Adres
        Adres adres = new Adres("Straatweg", "123a", "Plaats");
        personen.forEach(persoon -> persoon.setAdres(adres));

        assertThat(personen)
                .extracting("adres")
                .contains(adres);

        assertThat(personen)
                .extracting("adres")
                .extracting("woonplaats")
                .contains("Plaats");

        //Alternatief (extracten wordt nu niet door assert gedaan, maar door static methode
        assertThat(Assertions.extractProperty("adres", Adres.class).from(personen))
                .contains(adres);

        assertThat(Assertions.extractProperty("adres.woonplaats", String.class).from(personen))
                .contains("Plaats");

        //Alternatief anders geschreven
        assertThat(Assertions.extractProperty("adres").ofType(Adres.class).from(personen))
                .contains(adres);

        assertThat(Assertions.extractProperty("adres.woonplaats").ofType(String.class).from(personen))
                .contains("Plaats");
    }
}
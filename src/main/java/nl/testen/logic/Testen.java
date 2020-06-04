package nl.testen.logic;

import nl.testen.exceptions.WaardeBuitenRange;

public class Testen {
    boolean teTestenMethode(int getalOnderDeTien) {
        return getalOnderDeTien > 0 && getalOnderDeTien <= 10;
    }

    boolean teTestenMethodeInclException(int getalOnderDeTien) throws WaardeBuitenRange {
        return binnenRange(getalOnderDeTien);
    }

    private boolean binnenRange(int getalOnderDeTien) throws WaardeBuitenRange {
        if (getalOnderDeTien <= 0) throw new WaardeBuitenRange();
        if (getalOnderDeTien >= 10) throw new WaardeBuitenRange();
//        if (getalOnderDeTien >= 10) throw new Exception();

        return true;
    }
}



// Stap 1: 3 examples tonen (JUnit4 / JUnit5 / TestNG)

// Stap 2: Testen (TestNG icm AssertJ & JUnit5)
//  a) TestNG van teTestenMethode uit Testen. Intro AssertJ. Incl tonen JUnit5
//  b) DataProvider (JUnit 5, extra maven dependency)
//  c) Exceptions

// Stap 3: Persoon (TestNG icm AssertJ & JUnit5)
//  a) 1 of 3 testen? (incl JUnit 5)
//  b) SoftAssertions / AssertAll (JUnit 5)

// ToDo Keuze maken voor JUnit of TestNG ?? Incl integratie van AssertJ => TestNG
// ToDo Eigen AssertJ maken
// ToDo Overzicht van mogelijkheden
// ToDo Checken van lijsten.
// ToDo Eerst opzet met eenvoudige Strings, dan uitgebreider voorbeeld/opdracht met een willekeurige klasse (zie vb van elvenRings en zo)
// ToDo eventueel een losse abstrace testclass aanmaken, die geëxtend kan worden.

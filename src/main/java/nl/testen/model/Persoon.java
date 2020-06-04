package nl.testen.model;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Persoon {
    private int leeftijd;
    private String voornaam;
    private String achternaam;
    private String tussenvoegsel;
    private Geslacht geslacht;
    private Adres adres;

    Persoon(int leeftijd, String voornaam, String tussenvoegsel, String achternaam, Geslacht geslacht) {
        this(leeftijd, voornaam, tussenvoegsel, achternaam);
        setGeslacht(geslacht);

    }

    Persoon(int leeftijd, String voornaam, String tussenvoegsel, String achternaam) {
        setLeeftijd(leeftijd);
        setVoornaam(voornaam);
        setTussenvoegsel(tussenvoegsel);
        setAchternaam(achternaam);
    }

    @Override
    public String toString() {
//    return String.join(" ", voornaam, tussenvoegsel, achternaam);
        return Stream.of(voornaam, tussenvoegsel, achternaam)
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining(" "));
    }

    public String getNaam() {
        return this.toString();
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
}

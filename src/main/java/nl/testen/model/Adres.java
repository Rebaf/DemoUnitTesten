package nl.testen.model;

public class Adres {
    private String straat;
    private String huisnummer;
    private String woonplaats;

    public Adres(String straat, String huisnummer, String woonplaats) {
        setStraat(straat);
        setHuisnummer(huisnummer);
        setWoonplaats(woonplaats);
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
}

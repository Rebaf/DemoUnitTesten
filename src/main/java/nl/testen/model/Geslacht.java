package nl.testen.model;

public enum Geslacht {
    MAN("man"),
    VROUW("vrouw");

    private final String omschrijving;

    private Geslacht(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getOmschrijving() {
        return omschrijving;
    }
}

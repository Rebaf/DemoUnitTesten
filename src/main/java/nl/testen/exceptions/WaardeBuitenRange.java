package nl.testen.exceptions;

public class WaardeBuitenRange extends Exception {
    public WaardeBuitenRange() {
        super("Opgegeven waarde ligt buiten de range van 1 t/m 10");
    }
}

import java.io.IOException;
import java.util.Random;

/**
 * trennt aus einem String mit beides englisch und deutsch vokabel entweder die deutsche oder englische Vokabel raus
 * ladet die Kategorien
 */
public class HangmanModel {
    private KategorienLesen kategorieLoader;

    public HangmanModel() {
        this.kategorieLoader = new KategorienLesen();
    }


    public String ladeEnglischVokabel(String ungetrennt){
        String u = ungetrennt;
        String[] teile = u.split("-");
        String eVok = teile[0];
        return eVok;
    }

    public String ladeDeutschVokabel(String ungetrennt){
        String u = ungetrennt;
        String[] teile = ungetrennt.split("-");
        String dVok = teile[1];
        return dVok;
    }

    /**
     * Lädt die Kategorien aus der Datei.
     */
    private String[] ladeKategorien() {
        try {
            return kategorieLoader.loadKategorie();
        } catch (IOException e) {
            System.out.println("Fehler beim Laden der Kategorien: " + e.getMessage());
            return new String[]{"Fehler beim Laden"};
        }
    }

    public String[] getKategorien(){
        return ladeKategorien();
    }

    public String getEnglischVokabel(String ganzVok){
        return ladeEnglischVokabel(ganzVok);
    }

    public String getDeutschVokabel(String ganzVok){
        return ladeDeutschVokabel(ganzVok);
    }

}

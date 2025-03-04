import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * loadKategorie() liest aus der Datei die Kategorien aus und gibt sie zurück
 * loadVokabeln() liest aus der Datei die zugehörigen Vokabeln aus und gibt sie zurück
 * @author Julia Stuppnig
 * @version 23-01-2025
 */
public class KategorienLesen {
    private String[] kategorien;
    public String[] loadKategorie() throws IOException{
        String filepath = "Vokabeln.txt";
        try(Scanner s = new Scanner(new BufferedReader (new FileReader(filepath)))){
            int y = wievieleKat();
            String[] Kategorien = new String[y];
            int k = 0;
            for(int i = 0; s.hasNextLine(); i++){
                if (s.nextLine().equals("$")) {
                    Kategorien[k] = s.nextLine();
                    k++;
                }
            }
            kategorien = Kategorien;
            return Kategorien;
        }

    }

    public String[] loadVokabeln(String Kategorie) throws IOException{
        String filepath = "Vokabeln.txt";
        try(Scanner s = new Scanner(new BufferedReader (new FileReader(filepath)))) {
            int y = wievieleVok(Kategorie);
            String[] Vokabeln = new String[y];
            int k = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine(); // Zeile lesen
                if (line.equals(Kategorie)) { // Wenn die Kategorie gefunden wurde
                    while (s.hasNextLine()) {
                        String vokabel = s.nextLine();
                        if (vokabel.isEmpty()) { // Falls eine Leerzeile kommt, abbrechen
                            break;
                        }
                        if (k < y) { // Sicherstellen, dass kein Array-Index-Fehler auftritt
                            Vokabeln[k] = vokabel;
                            k++;
                        }
                    }
                }
            }
            return Vokabeln;
        }
    }
    /**
     * gibt zurück wieviele Zeilen das Dokument hat, denn die Kategorien oder VOkabeln können nicht über dieser Zahl liegen
     * @return die Kategorien Anzahl
    */
    public int wievieleKat() throws IOException{
        try(Scanner y = new Scanner(new BufferedReader (new FileReader("Vokabeln.txt")))){
            int i = 0;
            do{
                if(y.nextLine().equals("$")){
                    i++;
                }
            }while(y.hasNextLine());
            return i;
        }
    }

    public int wievieleVok(String Kategorie) throws IOException{
        try(Scanner i = new Scanner(new BufferedReader (new FileReader("Vokabeln.txt")))){
            int z = 0;
            while (i.hasNextLine()) {
                String line = i.nextLine();
                if (line.equals(Kategorie)) {
                    while (i.hasNextLine()) {
                        String vokabel = i.nextLine();
                        if (vokabel.isEmpty()) {
                            break;
                        }
                        z++;
                    }
                }
            }
            return z;
        }
    }

}

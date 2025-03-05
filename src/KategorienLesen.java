import javax.swing.*;import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.io.*;
import java.util.HashMap;import java.util.Iterator;import java.util.Map;import java.util.Scanner;

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

    public void writeScore(int score){
        try(FileWriter fw = new FileWriter("Score.txt")){
            fw.write("" + score);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int readScore(){
        try(Scanner y = new Scanner(new BufferedReader (new FileReader("Score.txt")))){
            if(y.hasNextInt()){
                return y.nextInt();
            }else{
                return 0;
            }
        }catch (FileNotFoundException e) {
            return 0;
        }
    }

    public void writeKategorie(String kategorie, String[] vokabeln){
        try {
            String[] kategorien = this.loadKategorie();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean gotKategorie = vokabeln == null ? true : false;
        Map<String, String[]> map = new HashMap<>();
        for(int i = 0; i < kategorien.length; i++){
            if(!kategorien[i].equals(kategorie)){
                try {
                    map.put(kategorien[i], loadVokabeln(kategorien[i]));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else if(vokabeln != null){
                map.put(kategorie, vokabeln);
                gotKategorie = true;
            }
        }
        if(!gotKategorie){
            map.put(kategorie, vokabeln);
        }

        if(map.size() < 1){
            return;
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Vokabeln.txt"))){
            Iterator<Map.Entry<String, String[]>> iterator = map.entrySet().iterator();

                while (iterator.hasNext()) {
                    Map.Entry<String, String[]> entry = iterator.next();

                    writer.write('$'); // Trennzeichen für eine neue Kategorie
                    writer.newLine();
                    writer.write(entry.getKey()); // Kategorie (Key) schreiben

                    // Durch das Array im Value gehen und jedes Element schreiben
                    for (String s : entry.getValue()) {
                        writer.newLine();
                        writer.write(s);
                    }

                    // Falls noch weitere Elemente in der Map vorhanden sind → Leerzeilen einfügen
                    if (iterator.hasNext()) {
                        writer.newLine();
                        writer.newLine();
                    }
                }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

}

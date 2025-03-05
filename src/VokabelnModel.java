import javax.swing.*;import java.io.IOException;public class VokabelnModel {

    private KategorienLesen kategorieLoader;
    private String kategorie;
    private String[] vokabelliste;
    private int index;
    public VokabelnModel() {
        this.kategorieLoader = new KategorienLesen();
    }

    public String[] ladeKategorien() {
        try {
            return kategorieLoader.loadKategorie();
        } catch (IOException e) {
            System.out.println("Fehler beim Laden der Kategorien: " + e.getMessage());
            return new String[]{"Fehler beim Laden"};
        }
    }

    public void changeListe(String kategorie){
        try {
            this.kategorie = kategorie;
            this.vokabelliste = kategorieLoader.loadVokabeln(kategorie);
            index = 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeIndex(boolean increase){
        if(increase){
            index++;
            if(index == vokabelliste.length){
                index = 0;
            }
        }else{
            index--;
            if(index == -1){
                index = vokabelliste.length-1;
            }
        }
    }

    public String getText(){
        return vokabelliste[index];
    }

    public boolean hasVocablist(){
        return vokabelliste != null && vokabelliste.length > 0;
    }

    public void setText(String text){
        if(text == null){
            if(vokabelliste.length > 1){
                String[] neu = new String[vokabelliste.length-1];
                for(int i = 0; i < index; i++){
                    neu[i] = vokabelliste[i];
                }
                for(int i = index; i < neu.length; i++){
                    neu[i] = vokabelliste[i+1];
                }
                vokabelliste = neu;
                if(index >= vokabelliste.length){
                    index--;
                }
            }else{
                JOptionPane.showMessageDialog(null, "Vokabel konnte nicht gelöscht werden,\nda es nur mehr diesen Eintrag\nin der Kategorie gibt", "Löschen Fehlgeschlagen", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            vokabelliste[index] = text;
        }

    }

    public void neu(String text){
        String[] neu = new String[vokabelliste.length+1];
        for(int i = 0; i < vokabelliste.length; i++){
            neu[i] = vokabelliste[i];
        }
        neu[vokabelliste.length] = text;
        this.vokabelliste = neu;
    }

    public void speichern(){
        if(kategorie != null && vokabelliste != null){
            kategorieLoader.writeKategorie(kategorie, vokabelliste);
        }
    }

    public void resetHighscore(){
        kategorieLoader.writeScore(0);
    }

    public String getKategorie(){
        return this.kategorie;
    }

}

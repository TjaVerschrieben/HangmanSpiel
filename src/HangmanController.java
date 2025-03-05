import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * verbindet view und model & macht die ActionListener sowie zufällige Vokabel auswählen
 * @author Julia Stuppnig
 * @version 02-02-2025
 */
public class HangmanController implements ActionListener{
    private HangmanModel model;
    private HangmanPanel panel;
    private KategorienLesen leser;
    private int fehler = 0;
/**/private boolean ersterVersuch = true;
/**/private int score = 0;
/**/private int highScore = 0;
    private String ganzVok;
    private HangmanDraw draw;

    public HangmanController(HangmanModel model, HangmanPanel panel) {
        this.model = model;
        this.panel = panel;
        this.leser = new KategorienLesen();
        highScore = leser.readScore();
    }

     @Override
     public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("KatVerändert")) {
            String kategorie = (String) panel.getAusgewaehlteKategorie();
            if (kategorie != null) {
                String zufallsVokabel = ladeZufaelligeVokabel(kategorie);
                panel.getVokabelLabel().setText(zufallsVokabel);
            }

        }else if(ganzVok == null){
            return;
        }else if(e.getActionCommand().equals("Übersetzt")) {
            if(!panel.getTextBoxText().equals(model.getDeutschVokabel(ganzVok)) && fehler <= 8){
/**/            if(ersterVersuch){
                    ersterVersuch = false;
                    String vokabel = model.getDeutschVokabel(ganzVok);
                    panel.setTextBox(vokabel.charAt(0) + "..." + vokabel.charAt(vokabel.length()-1));
                    return;
                }
                fehler++;
                HangmanDraw draw = new HangmanDraw(this);
                panel.repaintHangman();
            }else if(panel.getTextBoxText().equals(model.getDeutschVokabel(ganzVok)) && fehler <= 8){
                String kategorie = (String) panel.getAusgewaehlteKategorie();
                if (kategorie != null) {
                    String zufallsVokabel = ladeZufaelligeVokabel(kategorie);
                    panel.getVokabelLabel().setText(zufallsVokabel);
                    panel.setTextBox();
/**/                ersterVersuch = true;
/**/                score++;
/**/                if(score > highScore){
                        highScore = score;
                    }
                }

            }else if(fehler > 8){
                JOptionPane.showMessageDialog(null, "Super! Du konntest " + score + " Wörter richtig übersetzen\nDein Highscore liegt bei " + highScore, "Ende", JOptionPane.INFORMATION_MESSAGE);
                reset();
            }
        }else if(e.getActionCommand().equals("Zurück")){
/**/        HangMain.changePanel(HangMain.getStartSeite());
            leser.writeScore(highScore);
        }
     }


    private String ladeZufaelligeVokabel(String kategorie) {
        try {
            String[] vokabeln = leser.loadVokabeln(kategorie); // Hier sollten Vokabeln geladen werden!
            if (vokabeln.length > 0) {
                Random rand = new Random();
                ganzVok = vokabeln[rand.nextInt(vokabeln.length)];
                return model.getEnglischVokabel(ganzVok);
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Laden der Vokabeln: " + e.getMessage());
        }
        return "Keine Vokabel gefunden";
    }

    public int getFehler(){
        return fehler;
    }

    public void reset(){
        score = 0;
        ersterVersuch = true;
        fehler = 0;
        String kategorie = (String) panel.getAusgewaehlteKategorie();
        if (kategorie != null) {
            String zufallsVokabel = ladeZufaelligeVokabel(kategorie);
            panel.getVokabelLabel().setText(zufallsVokabel);
            panel.setTextBox();
        }
        panel.repaintHangman();
        leser.writeScore(highScore);
    }

    public void aktualisiereHighScore(){
        this.highScore = leser.readScore();
    }

}



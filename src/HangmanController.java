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
    private String ganzVok;
    private HangmanDraw draw;

    public HangmanController(HangmanModel model, HangmanPanel panel) {
        this.model = model;
        this.panel = panel;
        this.leser = new KategorienLesen();
    }

     @Override
     public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("KatVerändert")) {
            String kategorie = (String) panel.getAusgewaehlteKategorie();
            if (kategorie != null) {
                String zufallsVokabel = ladeZufaelligeVokabel(kategorie);
                panel.getVokabelLabel().setText(zufallsVokabel);
            }

        }else if(e.getActionCommand().equals("Übersetzt")) {
            if(!panel.getTextBoxText().equals(model.getDeutschVokabel(ganzVok)) && fehler <= 8){
                fehler++;
                HangmanDraw draw = new HangmanDraw(this);
                panel.repaintHangman();
            }else if(panel.getTextBoxText().equals(model.getDeutschVokabel(ganzVok)) && fehler <= 8){
                String kategorie = (String) panel.getAusgewaehlteKategorie();
                if (kategorie != null) {
                    String zufallsVokabel = ladeZufaelligeVokabel(kategorie);
                    panel.getVokabelLabel().setText(zufallsVokabel);
                    panel.setTextBox();
                }

            }
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


}



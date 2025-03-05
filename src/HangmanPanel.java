import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

/**
 * Erzeugt das Layout für das Hangman-Spiel.
 * Ein Label zeigt die zufällige Vokabel aus der gewählten Kategorie.
 * Ein darunter liegendes Textfeld ermöglicht die Eingabe.
 * @author Julia Stuppnig
 * @version 23-01-2025
 */
public class HangmanPanel extends JPanel {
    private JComboBox<String> kategorien; // Kategorien-Dropdown
    private JLabel vokabelLabel; // Zeigt die zufällige Vokabel an
    private JTextField textBox; // Eingabefeld für den Nutzer
/**/private JButton startB, zurueckB;
    private HangmanController hang;
    private HangmanModel model;
    private HangmanDraw hangmanDraw;

    public HangmanPanel(HangmanModel model) {
        this.setLayout(new BorderLayout()); // Setzt explizit BorderLayout für das Panel

        this.model = model;
        hang = new HangmanController(model, this);
        String[] kategorieListe = model.getKategorien();
        kategorien = new JComboBox<>(kategorieListe);
        kategorien.setActionCommand("KatVerändert");
        kategorien.addActionListener(hang);

        // **Panel für die Kategorien oben**
        JPanel pKategorien = new JPanel();
        pKategorien.setLayout(new FlowLayout(FlowLayout.CENTER)); // Links ausgerichtet
        pKategorien.add(new JLabel("Kategorie:"));
        pKategorien.add(kategorien);
        hangmanDraw = new HangmanDraw(hang);
        // **OBERE GRAUE FLÄCHE**
        JPanel pOben = new JPanel();
        pOben.setBackground(Color.LIGHT_GRAY);
        pOben.setPreferredSize(new Dimension(600, 300));

        pOben.add(hangmanDraw, BorderLayout.CENTER);

        // **MITTLERE FLÄCHE: Vokabel-Anzeige**
        JPanel pMitte = new JPanel();
        vokabelLabel = new JLabel("Wähle eine Kategorie"); // Standard-Text
        vokabelLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Größere Schrift
        pMitte.add(vokabelLabel);

        // **UNTERE HÄLFTE: TextBox**
        JPanel pUnten = new JPanel();
        textBox = new JTextField(20);
        textBox.setActionCommand("Übersetzt");
        textBox.addActionListener(hang);
        pUnten.add(textBox);


        // **Hauptbereich mit GridBagLayout**
        JPanel pCenter = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        pCenter.add(pOben, gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        pCenter.add(pMitte, gbc); // Mittleres Label (Vokabel)

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
/**/    //pCenter.add(pUnten, gbc); // Textfeld

        // **LINKE SEITE: Zurück Button**
        zurueckB = new JButton("<");
        zurueckB.setActionCommand("Zurück");
        zurueckB.addActionListener(hang);
        zurueckB.setSize(new Dimension(50, 50));

        // **KOMPONENTEN HINZUFÜGEN**
        this.add(pKategorien, BorderLayout.NORTH);  // Kategorien-Dropdown kommt ganz oben
        this.add(pUnten, BorderLayout.SOUTH);
        this.add(pCenter, BorderLayout.CENTER);     // Rest kommt in die Mitte
        this.add(zurueckB, BorderLayout.WEST);
    }

    public String getAusgewaehlteKategorie() {
        return (String) kategorien.getSelectedItem();
    }

    public JLabel getVokabelLabel() {
        return vokabelLabel;
    }

    public String getTextBoxText() {
        return textBox.getText().trim();
    }

    public void setTextBox(){
        this.textBox.setText("");
    }

/**/public void setTextBox(String text){this.textBox.setText(text);}

    public void repaintHangman() {
        hangmanDraw.repaintHangman();
    }

    public void reloadScore(){
        hang.aktualisiereHighScore();
    }

}

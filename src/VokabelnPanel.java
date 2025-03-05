import javax.swing.*;import java.awt.*;
public class VokabelnPanel extends JPanel {

    private VokabelnController controller;
    private VokabelnModel model;
    private JLabel label;
    private JComboBox<String> kategorien;
    private JButton linksB, rechtsB, bearbeitenB, saveB, zurueckB, resetScoreB, changeKatB;
    private JPanel bottomPanel, pKategorien;

    public VokabelnPanel(Controller ctr){

        model = new VokabelnModel();
        controller = new VokabelnController(model, this, ctr);

        this.controller = controller;
        this.setLayout(new BorderLayout());

        bottomPanel = new JPanel(new FlowLayout());

        // -
        resetScoreB = new JButton("Resetscore");
        resetScoreB.setActionCommand("resetScore");
        resetScoreB.addActionListener(controller);
        resetScoreB.setPreferredSize(new Dimension(150, 50));

        changeKatB = new JButton("add/delete");
        changeKatB.setActionCommand("katÄndern");
        changeKatB.addActionListener(controller);
        changeKatB.setPreferredSize(new Dimension(150, 50));
        // -

        String[] kategorieListe = model.ladeKategorien();
        kategorien = new JComboBox<>(kategorieListe);
        kategorien.setActionCommand("KatVerändert");
        kategorien.addActionListener(controller);

        // **Panel für die Kategorien oben**
        pKategorien = new JPanel();
        pKategorien.setLayout(new FlowLayout(FlowLayout.CENTER)); // Links ausgerichtet
        pKategorien.add(resetScoreB);
        pKategorien.add(new JLabel("Kategorie:"));
        pKategorien.add(kategorien);
        pKategorien.add(changeKatB);

        //**MITTE**
        label = new JLabel("Bitte eine Kategorie auswählen", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 24));

        //**UNTERER BEREICH**
        linksB = new JButton("<-");
        linksB.setActionCommand("Links");
        linksB.addActionListener(controller);
        linksB.setPreferredSize(new Dimension(100, 50));

        bearbeitenB = new JButton("Bearbeiten");
        bearbeitenB.setActionCommand("Bearbeiten");
        bearbeitenB.addActionListener(controller);
        bearbeitenB.setPreferredSize(new Dimension(150, 50));

        rechtsB = new JButton("->");
        rechtsB.setActionCommand("Rechts");
        rechtsB.addActionListener(controller);
        rechtsB.setPreferredSize(new Dimension(100, 50));

        bottomPanel.add(linksB);
        bottomPanel.add(bearbeitenB);
        bottomPanel.add(rechtsB);

        //**LINKER BEREICH**
        zurueckB = new JButton("<");
        zurueckB.setActionCommand("Zurück");
        zurueckB.addActionListener(controller);
        zurueckB.setPreferredSize(new Dimension(50, 50));

        //**RECHTER BEREICH**
        saveB = new JButton("<html>S<br>a<br>v<br>e</html>");
        saveB.setActionCommand("Speichern");
        saveB.addActionListener(controller);
        saveB.setPreferredSize(new Dimension(50, 50));

        this.add(pKategorien, BorderLayout.NORTH);
        this.add(label, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(zurueckB, BorderLayout.WEST);
        this.add(saveB, BorderLayout.EAST);

    }

    public void setText(String text){
        this.label.setText(text);
    }

    public String getAusgewaehlteKategorie() {
        return (String) kategorien.getSelectedItem();
    }

}

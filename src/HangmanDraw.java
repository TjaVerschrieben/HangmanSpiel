import javax.swing.*;
import java.awt.*;

/**
 * Zeichnet den Hangman
 * @author Julia Stuppnig
 * @version 25-01-2025
 */
public class HangmanDraw extends JLabel {
    private HangmanController controller;

    public HangmanDraw(HangmanController controller) {
        this.controller = controller;
        setPreferredSize(new Dimension(300, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        int fehler = controller.getFehler();
        super.paintComponent(g);

        g.setColor(Color.RED);

        if (fehler >= 1) g.drawLine(100, 250, 100, 50); // Galgen nach oben
        if (fehler >= 2) g.drawLine(100, 50, 180, 50);  // Galgen nach rechts
        if (fehler >= 3) g.drawLine(180, 50, 180, 80);  // Galgen nach unten (Seil)

        // Hangman zeichnen
        if (fehler >= 4) g.drawOval(160, 80, 40, 40);  // Kopf
        if (fehler >= 5) g.drawLine(180, 120, 180, 140); // Hals
        if (fehler >= 6) g.drawLine(180, 140, 180, 180); // Körper
        if (fehler >= 7) {
            g.drawLine(180, 140, 160, 160); // linker Arm
            g.drawLine(180, 140, 200, 160); // rechter Arm
        }
        if (fehler >= 8) {
            g.drawLine(180, 180, 160, 230); // linkes Bein
            g.drawLine(180, 180, 200, 230); // rechtes Bein
        }
    }

    public void repaintHangman() {
        repaint();
    }

}

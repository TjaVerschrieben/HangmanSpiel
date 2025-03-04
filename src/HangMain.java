import javax.swing.*;

public class HangMain {
    public static void main(String[] args) {
            HangmanModel model = new HangmanModel();
            HangmanPanel panel = new HangmanPanel(model);
            new HangmanController(model, panel);

            JFrame frame = new JFrame("Hangman Spiel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
    }
}


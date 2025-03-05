import javax.swing.*;

public class HangMain {

    private static JFrame frame;
    private static JPanel startSeite;
    private static JPanel panel;

    public static void main(String[] args) {

            Controller controller = new Controller();
            HangMain.panel = new StartseitePanel(controller);
            startSeite = panel;

            HangMain.frame = new JFrame("Hangman Spiel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 450);
            frame.setLocationRelativeTo(null);
            frame.add(panel);
            frame.setVisible(true);
    }

    public static void changePanel(JPanel panel) {
        HangMain.frame.remove(HangMain.panel);
        HangMain.frame.add(panel);
        HangMain.frame.revalidate();
        HangMain.frame.repaint();
        HangMain.panel = panel;
    }

    public static JPanel getStartSeite(){
        return HangMain.startSeite;
    }
}


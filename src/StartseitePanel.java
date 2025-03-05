import javax.swing.*;
import java.awt.*;
public class StartseitePanel extends JPanel {

    private JLabel titel;
    private JButton hangman, pool;
    private JPanel panel, placeholder;
    private Controller controller;

    public StartseitePanel(Controller controller){
        this.setLayout(new BorderLayout());

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 20, 0, 20);

        placeholder = new JPanel();
        placeholder.setPreferredSize(new Dimension(150, 50));

        titel = new JLabel("Hangman", SwingConstants.CENTER);
        titel.setFont(new Font("Arial", Font.BOLD, 24));

        hangman = new JButton("Hangman");
        hangman.setPreferredSize(new Dimension(200, 100));
        hangman.setActionCommand("Hangman");
        hangman.addActionListener(controller);
        pool = new JButton("Pool-Bearbeitung");
        pool.setPreferredSize(new Dimension(200, 100));
        pool.setActionCommand("Pool");
        pool.addActionListener(controller);

        gbc.gridx = 0;
        panel.add(hangman);
        gbc.gridx = 1;
        panel.add(placeholder);
        gbc.gridx = 2;
        panel.add(pool);

        this.add(titel, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);

    }

}

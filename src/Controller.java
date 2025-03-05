import java.awt.event.ActionEvent;import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private HangmanModel hangmanModel;
    private HangmanPanel hangmanPanel;
    private HangmanController hangmanController;

    private VokabelnModel vokabelnModel;
    private VokabelnPanel vokabelnPanel;
    private VokabelnController vokabelnController;

    public Controller(){
        hangmanModel = new HangmanModel();
        hangmanPanel = new HangmanPanel(hangmanModel);
        //hangmanController = new HangmanController(hangmanModel, hangmanPanel);

        vokabelnPanel = new VokabelnPanel(this);
    }


@Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        if(ac.equals("Hangman")){
            HangMain.changePanel(hangmanPanel);
            hangmanPanel.reloadScore();
        }else if(ac.equals("Pool")){
            HangMain.changePanel(vokabelnPanel);
        }else if(ac.equals("reloadPool")){
            this.vokabelnPanel = new VokabelnPanel(this);
            HangMain.changePanel(vokabelnPanel);
            hangmanModel = new HangmanModel();
            hangmanPanel = new HangmanPanel(hangmanModel);
            //hangmanController = new HangmanController(hangmanModel, hangmanPanel);
        }
    }
}

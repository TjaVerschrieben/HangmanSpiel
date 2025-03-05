import javax.swing.*;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;

public class VokabelnController implements ActionListener{

    private VokabelnModel model;
    private VokabelnPanel panel;
    private Controller controller;

    public VokabelnController(VokabelnModel model, VokabelnPanel panel, Controller controller){
        this.model = model;
        this.panel = panel;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();

        if(ac.equals("KatVerändert")){
            model.speichern(); //?
            model.changeListe((String) panel.getAusgewaehlteKategorie());
            panel.setText(model.getText());
        }else if(ac.equals("Zurück")){
            HangMain.changePanel(HangMain.getStartSeite());
        }else if(ac.equals("Speichern")){
            model.speichern();
        }else if(ac.equals("resetScore")){
            model.resetHighscore();
        }else if(!model.hasVocablist()){
            return;
        }else if(ac.equals("katÄndern")){
            showDialogKat();
            ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "reloadPool");
            controller.actionPerformed(event);
        }else if(ac.equals("Links")){
            model.changeIndex(false);
            panel.setText(model.getText());
        }else if(ac.equals("Rechts")){
            model.changeIndex(true);
            panel.setText(model.getText());
        }else if(ac.equals("Bearbeiten")){
            showDialog();
            panel.setText(model.getText());
        }
    }

        // Methode zum Starten des Dialogs
        public void showDialog() {
            // Aktuellen Text holen
            String currentText = model.getText();

            // Auswahlmöglichkeiten
            String[] options = {"Löschen", "Bearbeiten", "Neu"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Was möchten Sie tun?",
                    "Auswahl",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0: // Löschen
                    model.setText(null); // Text löschen
                    JOptionPane.showMessageDialog(null, "Eintrag gelöscht.");
                    break;
                case 1: // Bearbeiten
                    // Text anzeigen und bearbeiten
                    String editedText = JOptionPane.showInputDialog(null, "Bearbeiten:", currentText);
                    if (editedText != null && !editedText.trim().isEmpty()) {
                        String[] parts = editedText.split("-");  // Text in zwei Teile teilen
                        if(parts.length == 2){
                            model.setText(editedText);  // Text am Index bearbeiten
                            JOptionPane.showMessageDialog(null, "Eintrag bearbeitet.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Kein gültiger Text.");
                    }
                    break;
                case 2: // Neu
                    // Neuen Text eingeben
                    String newText = JOptionPane.showInputDialog(null, "Neuen Eintrag hinzufügen:");
                    if (newText != null && !newText.trim().isEmpty()) {
                        String[] parts = newText.split("-");  // Text in zwei Teile teilen
                        if(parts.length == 2){
                            model.neu(newText);  // Neuen Eintrag hinzufügen
                            JOptionPane.showMessageDialog(null, "Eintrag hinzugefügt.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Kein gültiger Text.");
                    }
                    break;
                default:
                    break;
            }
        }
    public void showDialogKat() {
            // Optionen für den ersten Dialog
            String[] options = {"Löschen", "Hinzufügen"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Möchten Sie eine Kategorie löschen oder hinzufügen?",
                    "Aktion wählen",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            KategorienLesen temp = new KategorienLesen();

            if (choice == 0) { // Löschen
                temp.writeKategorie(model.getKategorie(), null);
                return;
            }

            if (choice == 1) { // Hinzufügen
                // Kategorie eingeben
                String category;

                category = JOptionPane.showInputDialog(null, "Geben Sie eine Kategorie ein (eine Zeile):");


                if (category == null ||  category.contains("\n")){
                    return; // Abbrechen, falls der Benutzer auf "Abbrechen" klickt
                }

                // Vokabelpaar eingeben
                String vokabel;
                vokabel = JOptionPane.showInputDialog(null, "Geben Sie das erste Vokabelpaar ein (Format: Englisch-Deutsch):");

                if (vokabel == null || vokabel.split("-").length != 2){
                    return; // Abbrechen
                }

                temp.writeKategorie(category, new String[]{vokabel});

                // --- Hier kommt dein Code für das Speichern des Vokabelpaars in der Kategorie hin ---
            }
        }

}

package vue;
import javax.swing.*;
import java.awt.*;

public class PanneauInformation extends JPanel {
    private Fenetre maFenetre;
    private JLabel message;

    public PanneauInformation(Fenetre maFenetre) {
        this.maFenetre = maFenetre;
        this.setLayout(new GridLayout(2, 1));

        JLabel titre = new JLabel("Bataille Navale", SwingConstants.CENTER);
        this.message = new JLabel("Phase de placement. Placez vos bateaux.", SwingConstants.CENTER);

        this.add(titre);
        this.add(message);
    }

    public void afficherMessage(String msg) {
        this.message.setText(msg);
    }
}
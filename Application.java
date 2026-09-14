import modele.Partie;
import vue.Fenetre;

public class Application {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Partie partie = new Partie();
                new Fenetre("Bataille Navale", partie);
            }
        });
    }
}
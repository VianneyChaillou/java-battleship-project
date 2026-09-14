package vue;
import javax.swing.*;
import java.awt.*;

import modele.Partie;
import modele.EtatJeu;

public class Fenetre extends JFrame {
    private PanneauGrille grilleJoueur;
    private PanneauGrille grilleOrdinateur;
    private PanneauInformation information;
    private Partie partie;

    public Fenetre(String nom, Partie partie) {
        super(nom);
        this.partie = partie;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.information = new PanneauInformation(this);
        this.grilleJoueur = new PanneauGrille(this, false, partie.getFlotteJoueur());
        this.grilleOrdinateur = new PanneauGrille(this, true, partie.getFlotteOrdinateur());

        JPanel pNord = new JPanel(new BorderLayout());
        pNord.add(information, BorderLayout.CENTER);
        
        JButton btnStart = new JButton("Démarrer Combat");
        btnStart.addActionListener(e -> {
            if (partie.getEtat() == EtatJeu.PLACEMENT) {
                partie.setEtat(EtatJeu.COMBAT);
                afficherMessage("Au combat !");
                btnStart.setEnabled(false);
            }
        });
        pNord.add(btnStart, BorderLayout.EAST);

        this.add(pNord, BorderLayout.NORTH);

        JPanel pJoueur = new JPanel(new BorderLayout());
        pJoueur.add(grilleJoueur, BorderLayout.CENTER);
        pJoueur.add(new JLabel("Ma Grille", SwingConstants.CENTER), BorderLayout.SOUTH);
        this.add(pJoueur, BorderLayout.WEST);

        JPanel pOrdi = new JPanel(new BorderLayout());
        pOrdi.add(grilleOrdinateur, BorderLayout.CENTER);
        pOrdi.add(new JLabel("Ordinateur", SwingConstants.CENTER), BorderLayout.SOUTH);
        this.add(pOrdi, BorderLayout.EAST);

        this.pack();
        this.setVisible(true);
    }

    public PanneauInformation getInformation() { return information; }
    public PanneauGrille getGrilleJoueur() { return grilleJoueur; }
    public PanneauGrille getGrilleOrdinateur() { return grilleOrdinateur; }
    public Partie getPartie() { return partie; }

    public void afficherMessage(String s) {
        information.afficherMessage(s);
    }
}
package controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import vue.PanneauGrille;
import modele.Partie;

public class GestionSouris extends MouseAdapter {
    private PanneauGrille monPanneau;

    public GestionSouris(PanneauGrille panneau) {
        this.monPanneau = panneau;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Partie partie = monPanneau.getFenetre().getPartie();
        
        if (partie.getEtat() != modele.EtatJeu.COMBAT) {
            return;
        }

        int x = e.getX() / modele.Constantes.TAILLE_CASE_PX;
        int y = e.getY() / modele.Constantes.TAILLE_CASE_PX;

        if (x < 0 || x >= modele.Constantes.TAILLE_GRILLE || y < 0 || y >= modele.Constantes.TAILLE_GRILLE) return;

        int res = partie.tirerJoueur(x, y);
        if (res == -1) return; // Case déjà ciblée, on ignore

        String msg = "A l'eau !";
        if (res == 1) msg = "Touché !";
        if (res == 2) msg = "Touché Coulé !";

        int restants = partie.getCoupsRestantsJoueur();
        if (restants > 0) {
            monPanneau.getFenetre().afficherMessage(msg + " (Encore " + restants + " tirs)");
        } else {
            monPanneau.getFenetre().afficherMessage(msg + " (Tour terminé)");
        }

        monPanneau.repaint();

        if (partie.joueurAGagne()) {
            partie.setEtat(modele.EtatJeu.FIN);
            JOptionPane.showMessageDialog(monPanneau, "Vous avez gagné !");
            return;
        }

        if (restants == 0) {
            partie.faireTourOrdinateur();
            monPanneau.getFenetre().getGrilleJoueur().repaint();

            if (partie.ordinateurAGagne()) {
                partie.setEtat(modele.EtatJeu.FIN);
                JOptionPane.showMessageDialog(monPanneau, "L'ordinateur a gagné !");
                return;
            }
            
            monPanneau.getFenetre().afficherMessage("A vous de jouer ! (3 tirs)");
        }
    }
}
package controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import vue.PanneauGrille;

public class GestionPlacement extends MouseAdapter {
    private PanneauGrille monPanneau;

    public GestionPlacement(PanneauGrille panneau) {
        this.monPanneau = panneau;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (monPanneau.getFenetre().getPartie().getEtat() != modele.EtatJeu.PLACEMENT) {
            return;
        }

        int x = e.getX() / modele.Constantes.TAILLE_CASE_PX;
        int y = e.getY() / modele.Constantes.TAILLE_CASE_PX;

        if (x < 0 || x >= modele.Constantes.TAILLE_GRILLE || y < 0 || y >= modele.Constantes.TAILLE_GRILLE)
            return;

        boolean retire = monPanneau.getFlotte().retirerBateau(x, y);

        if (retire) {
            monPanneau.repaint();
        }
    }
}

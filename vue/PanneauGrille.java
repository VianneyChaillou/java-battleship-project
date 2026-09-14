package vue;

import javax.swing.*;
import java.awt.*;
import modele.Flotte;
import modele.Bateau;
import modele.Position;
import modele.PorteAvions;
import modele.Cuirasse;
import modele.ContreTorpilleur;
import modele.SousMarin;
import modele.LanceTorpille;
import modele.Constantes;
import controleur.GestionSouris;

public class PanneauGrille extends JPanel {
    private Fenetre maFenetre;
    private boolean grilleOrdinateur;
    private Flotte maFlotte;

    public PanneauGrille(Fenetre maFenetre, boolean grilleOrdinateur, Flotte flotte) {
        this.maFenetre = maFenetre;
        this.grilleOrdinateur = grilleOrdinateur;
        
        int tailleVue = Constantes.TAILLE_GRILLE * Constantes.TAILLE_CASE_PX + 1;
        this.setPreferredSize(new Dimension(tailleVue, tailleVue));

        this.maFlotte = flotte;

        if (grilleOrdinateur) {
            GestionSouris controleur = new GestionSouris(this);
            this.addMouseListener(controleur);
        } else {
            controleur.GestionPlacement controleurPlacement = new controleur.GestionPlacement(this);
            this.addMouseListener(controleurPlacement);
        }
    }

    public Flotte getFlotte() {
        return maFlotte;
    }

    public Fenetre getFenetre() {
        return maFenetre;
    }

    public boolean estGrilleOrdinateur() {
        return grilleOrdinateur;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int c = Constantes.TAILLE_CASE_PX;
        int maxVue = Constantes.TAILLE_GRILLE * c;

        g.setColor(Color.GRAY);
        for (int i = 0; i <= Constantes.TAILLE_GRILLE; i++) {
            g.drawLine(i * c, 0, i * c, maxVue);
            g.drawLine(0, i * c, maxVue, i * c);
        }

        if (!grilleOrdinateur) {
            for (Bateau b : maFlotte.getBateaux()) {
                if (b instanceof PorteAvions) g.setColor(Color.MAGENTA);
                else if (b instanceof Cuirasse) g.setColor(Color.ORANGE);
                else if (b instanceof ContreTorpilleur) g.setColor(Color.CYAN);
                else if (b instanceof SousMarin) g.setColor(Color.YELLOW);
                else if (b instanceof LanceTorpille) g.setColor(Color.GREEN);
                else g.setColor(Color.BLACK);

                for (Position p : b.getPositions()) {
                    int px = p.getX() * c;
                    int py = p.getY() * c;
                    g.fillRect(px, py, c, c);
                    
                    if (p.estTouche()) {
                        g.setColor(Color.BLACK);
                        g.drawLine(px, py, px + c, py + c);
                        g.drawLine(px + c, py, px, py + c);
                        
                        // Restaurer la couleur du bateau
                        if (b instanceof PorteAvions) g.setColor(Color.MAGENTA);
                        else if (b instanceof Cuirasse) g.setColor(Color.ORANGE);
                        else if (b instanceof ContreTorpilleur) g.setColor(Color.CYAN);
                        else if (b instanceof SousMarin) g.setColor(Color.YELLOW);
                        else if (b instanceof LanceTorpille) g.setColor(Color.GREEN);
                        else g.setColor(Color.BLACK);
                    }
                }
            }
        }

        for (Position p : maFlotte.getTirs()) {
            int px = p.getX() * c;
            int py = p.getY() * c;

            if (p.estTouche()) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawLine(px, py, px + c, py + c);
            g.drawLine(px + c, py, px, py + c);
        }
    }
}
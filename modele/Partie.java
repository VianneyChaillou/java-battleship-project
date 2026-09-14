package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partie {
    private EtatJeu etatCourant;
    private Flotte flotteJoueur;
    private Flotte flotteOrdinateur;

    // Gestion du tour par tour
    private int coupsRestantsJoueur;
    private List<Position> ciblesPossiblesIA;

    public Partie() {
        this.etatCourant = EtatJeu.PLACEMENT;
        this.flotteJoueur = new Flotte();
        this.flotteOrdinateur = new Flotte();
        
        // Initialisation aléatoire de base pour gagner du temps
        this.flotteJoueur.initialiser();
        this.flotteOrdinateur.initialiser();

        this.coupsRestantsJoueur = Constantes.NB_TIRS_PAR_TOUR;
        initialiserIA();
    }

    private void initialiserIA() {
        ciblesPossiblesIA = new ArrayList<>();
        for (int i = 0; i < Constantes.TAILLE_GRILLE; i++) {
            for (int j = 0; j < Constantes.TAILLE_GRILLE; j++) {
                ciblesPossiblesIA.add(new Position(i, j));
            }
        }
        Collections.shuffle(ciblesPossiblesIA); // L'IA piochera au hasard dans cette liste
    }

    public int getCoupsRestantsJoueur() {
        return coupsRestantsJoueur;
    }

    public int tirerJoueur(int x, int y) {
        if (flotteOrdinateur.dejaTire(x, y)) return -1;
        int res = flotteOrdinateur.salve(x, y);
        coupsRestantsJoueur--;
        return res;
    }

    public void faireTourOrdinateur() {
        for (int i = 0; i < Constantes.NB_TIRS_PAR_TOUR; i++) {
            if (ciblesPossiblesIA.isEmpty() || flotteJoueur.estVaincu()) break;
            Position cible = ciblesPossiblesIA.remove(0); // Tire et retire la coordonnée de la liste
            flotteJoueur.salve(cible.getX(), cible.getY());
        }
        coupsRestantsJoueur = Constantes.NB_TIRS_PAR_TOUR; // Reset pour le tour du joueur
    }

    public boolean joueurAGagne() {
        return flotteOrdinateur.estVaincu();
    }

    public boolean ordinateurAGagne() {
        return flotteJoueur.estVaincu();
    }

    public EtatJeu getEtat() {
        return etatCourant;
    }

    public void setEtat(EtatJeu etat) {
        this.etatCourant = etat;
    }

    public Flotte getFlotteJoueur() {
        return flotteJoueur;
    }

    public Flotte getFlotteOrdinateur() {
        return flotteOrdinateur;
    }
}


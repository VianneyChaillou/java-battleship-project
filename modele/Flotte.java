package modele;

import java.util.ArrayList;
import java.util.List;

public class Flotte {
    private List<Bateau> bateaux;
    private List<Position> tirs;
    private java.util.Random random;

    public Flotte() {
        this.bateaux = new ArrayList<>();
        this.tirs = new ArrayList<Position>();
        this.random = new java.util.Random();
    }

    public void ajouterBateau(Bateau b) {
        bateaux.add(b);
    }

    public void initialiser() {
        placementAleatoire();
    }

    public void placementAleatoire() {
        bateaux.clear();

        placerBateauAleatoire(new LanceTorpille(0, 0, false));
        placerBateauAleatoire(new ContreTorpilleur(0, 0, false));
        placerBateauAleatoire(new SousMarin(0, 0, false));
        placerBateauAleatoire(new Cuirasse(0, 0, false));
        placerBateauAleatoire(new PorteAvions(0, 0, false));
    }

    private void placerBateauAleatoire(Bateau modele) {
        boolean place = false;
        while (!place) {
            int x = random.nextInt(Constantes.TAILLE_GRILLE);
            int y = random.nextInt(Constantes.TAILLE_GRILLE);
            boolean horizontal = random.nextBoolean();

            Bateau b = null;
            if (modele instanceof LanceTorpille)
                b = new LanceTorpille(x, y, horizontal);
            else if (modele instanceof ContreTorpilleur)
                b = new ContreTorpilleur(x, y, horizontal);
            else if (modele instanceof SousMarin)
                b = new SousMarin(x, y, horizontal);
            else if (modele instanceof Cuirasse)
                b = new Cuirasse(x, y, horizontal);
            else if (modele instanceof PorteAvions)
                b = new PorteAvions(x, y, horizontal);

            if (b != null && estPlacementValide(b)) {
                ajouterBateau(b);
                place = true;
            }
        }
    }

    private boolean estPlacementValide(Bateau b) {
        for (Position p : b.positions) {
            if (p.getX() < 0 || p.getX() >= Constantes.TAILLE_GRILLE || p.getY() < 0 || p.getY() >= Constantes.TAILLE_GRILLE)
                return false;
        }

        for (Bateau existant : bateaux) {
            for (Position p : b.positions) {
                if (existant.contient(p.getX(), p.getY()))
                    return false;
            }
        }
        return true;
    }

    public boolean retirerBateau(int x, int y) {
        Bateau aRetirer = null;
        for (Bateau b : bateaux) {
            if (b.contient(x, y)) {
                aRetirer = b;
                break;
            }
        }
        if (aRetirer != null) {
            bateaux.remove(aRetirer);
            return true;
        }
        return false;
    }

    public boolean dejaTire(int x, int y) {
        for (Position p : tirs) {
            if (p.getX() == x && p.getY() == y)
                return true;
        }
        return false;
    }

    public int salve(int x, int y) {
        if (dejaTire(x, y))
            return 0;

        boolean touche = false;
        int resultat = 0;

        tirs.add(new Position(x, y));

        java.util.Iterator<Bateau> it = bateaux.iterator();
        while (it.hasNext()) {
            Bateau b = it.next();
            if (b.contient(x, y)) {
                b.recevoirTir(x, y);
                touche = true;
                if (b.estCoule()) {
                    resultat = 2;
                    it.remove();
                } else {
                    resultat = 1;
                }
                break;
            }
        }

        if (touche) {
            tirs.get(tirs.size() - 1).touche();
        }

        return resultat;
    }

    public boolean estVaincu() {
        return bateaux.isEmpty();
    }

    public List<Bateau> getBateaux() {
        return bateaux;
    }

    public List<Position> getTirs() {
        return tirs;
    }

}
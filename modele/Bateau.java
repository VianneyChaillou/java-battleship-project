package modele;

public abstract class Bateau {
    protected int taille;
    protected Position[] positions;

    public Bateau(int taille, int x, int y, boolean horizontal) {
        this.taille = taille;
        this.positions = new Position[taille];

        int dx = x;
        int dy = y;
        for (int i = 0; i < taille; i++) {
            this.positions[i] = new Position(dx, dy);
            if (horizontal) dx++; else dy++;
        }
    }

    public Position[] getPositions() {
        return positions;
    }

    public boolean estCoule() {
        for (Position p : positions) {
            if (!p.estTouche()) return false;
        }
        return true;
    }

    public boolean contient(int x, int y) {
        for (Position p : positions) {
            if (p.getX() == x && p.getY() == y) return true;
        }
        return false;
    }

    public boolean recevoirTir(int x, int y) {
        for (Position p : positions) {
            if (p.getX() == x && p.getY() == y) {
                p.touche();
                return true;
            }
        }
        return false;
    }
}
package modele;

public class Position {
    private int x;
    private int y;
    private boolean touche;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.touche = false;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean estTouche() { return touche; }
    
    public void touche() {
        this.touche = true;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
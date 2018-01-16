package turingsourcemh;

import java.io.Serializable;

public class TuRegle implements Serializable, Cloneable {

    //attrbuts
    private int ec; //etat courant
    private Character sl; //symbole lu
    private int es; // etat suivant
    private Character se; // symbole écrit
    private Direction di; // direction de la tete de lecture

    //constructeur basique
    public TuRegle(int ec, Character sl, int es, Character se, Direction di) {
        this.ec = ec;
        this.sl = sl;
        this.es = es;
        this.se = se;
        this.di = di;
    }

    //methode de verification si la regle correspond
    public boolean verif(int ecp, Character slp) {
        System.out.println("verif : " + this + "avec ec:" + ecp + "/" + this.ec + " et sl:" + slp + "/" + this.sl);
        if ((this.ec == (ecp)) && (this.sl.compareTo(slp) == 0)) {
            System.out.println("ok y a une regle");
            return true;
        } else {
            return false;
        }
    }

    //getters pour l'application de la regle
    public int etatSuiv() {
        return this.es;
    }

    public Character symbEcrit() {
        return this.se;
    }

    public Direction dirTete() {
        return this.di;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        String res;
        res = "(" + Integer.toString(ec) + "," + sl + "=>" + Integer.toString(es) + "," + se + "," + di.toString() + ")";
        return res;
    }
}

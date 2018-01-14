/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turingsourcemh;

import java.io.Serializable;

/**
 *
 * @author Jean-Loup
 */
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
    public boolean verif(int ec, Character sl) {
        return (this.ec == ec && this.sl == sl);
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

}

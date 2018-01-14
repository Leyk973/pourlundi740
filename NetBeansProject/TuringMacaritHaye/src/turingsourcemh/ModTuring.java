package turingsourcemh;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jean-Loup
 */
public class ModTuring {

    private boolean arret;
    private ArrayList<TuRegle> tabRegles;
    private int etatCourant;
    private Character[] ruban;
    private int posTete; //position de la tete de lecture

    public ModTuring() {
        this.arret = false;
        this.etatCourant = 0;
        this.tabRegles = new ArrayList<TuRegle>();
        this.ruban = new Character[0];
        this.posTete = 0;
    }

    public Character rubanPos(int pos) {
        return this.ruban[pos];
    }

    public int getPosTete() {
        return this.posTete;
    }

    // ajout regle
    public void ajoutTuRegle(int ec, Character sl, int es, Character se, Direction di) {
        TuRegle r = new TuRegle(ec, sl, es, se, di);
        this.tabRegles.add(r);
    }

    // retirer regle
    public void retraitTuRegle(int pos) {
        this.tabRegles.remove(pos);
    }

    /*
    // collection de règles
    public class TabTuRegles implements Serializable, Cloneable {

        private ArrayList<TuRegle> tabTuRegles;

        public TabTuRegles() {
            this.tabTuRegles = new ArrayList<TuRegle>();
        }
        
        public TabTuRegles(ArrayList<TuRegle> param) {
            this.tabTuRegles = param;
        }

        public void ajoutRegle(TuRegle tr) {
            this.tabTuRegles.add(tr);
        }
        
        public void retraitRegle(int pos){
            this.tabTuRegles.remove(pos);
        }
        
        public int tailleTabRegles () {
            return this.tabTuRegles.size();
        }

    }
     */
}

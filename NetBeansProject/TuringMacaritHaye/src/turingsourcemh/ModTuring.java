package turingsourcemh;

//import java.io.Serializable;
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
        this.tabRegles = new ArrayList<>();
        this.ruban = new Character[0];
        this.posTete = 0;
    }

    public Character rubanPos(int pos) {
        return this.ruban[pos];
    }

    public int getPosTete() {
        return this.posTete;
    }

    public boolean arretee(){
        return this.arret;
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

    // lire symbole
    public Character lireSymb() {
        Character lu;
        try {
            lu = ruban[posTete];
        } catch (IndexOutOfBoundsException e) {
            lu = Character.MIN_VALUE; //caractere vide, mais quand meme caractere, a la difference de null
        }
        return lu;
    }

    //verifier presence d'une regle
    //retourne l'indice de la regle dans la collection
    //retourne -1 si pas de regle correspondante
    public int trouveRegle() {
        boolean e = false; // voir si regle existe
        int i = -1;
        while (!e) {
            ++i;
            e = this.tabRegles.get(i).verif(this.etatCourant, lireSymb());
        }
        if (e) {
            return i;
        } else {
            return -1;
        }
    }

    //ecrire dans le ruban
    public void ecrireRuban (int pos, Character sym){
        try{
            this.ruban[pos] = sym;
        } catch (IndexOutOfBoundsException e){
            // nothing
        }
    }    
    
    //bouger la tete de lecture
    public void updatePosTete (Direction dir){
        switch (dir){
            case dr :
                ++this.posTete;
                break;
            case ga :
                --this.posTete;
                break;
            case pm :
                //nothing
                break;
        }
    }
    
    //appliquer une regle
    public boolean appliquer() {
        boolean wentWell = true;
        int indRegle = trouveRegle();
        
        if (indRegle != -1){
            TuRegle regle = this.tabRegles.get(indRegle);
            this.etatCourant = regle.etatSuiv();
            ecrireRuban(this.posTete, regle.symbEcrit());
            updatePosTete (regle.dirTete());
        } else {
            wentWell = false;
        }     
        
        return wentWell;
    }
    
    

    // verifier quand on lit que la case est definie, si elle ne l'est pas, 
    // faire comme si c'était une case vide (particulierement vrai pour indices negatifs
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

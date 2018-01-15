package turingsourcemh;

//import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Jean-Loup
 */
public class ModTuring extends Observable {

    private boolean arret;
    private boolean demar;
    private ArrayList<TuRegle> tabRegles;
    private int etatCourant;
    private Character[] ruban;
    private int posTete; //position de la tete de lecture
    private TuRegle lastRuleUsed;

    public ModTuring() {
        this.arret = false;
        this.demar = false;
        this.etatCourant = 0;
        this.tabRegles = new ArrayList<>();
        this.ruban = new Character[0];
        this.posTete = 0;
    }

    public void iniRuban(Character[] pruban) {
        this.arret = false;
        this.demar = false;
        this.etatCourant = 0;
        this.ruban = pruban;
        this.posTete = 0;
        setChanged();
        notifyObservers(rubanParsed());
        // DEBUG
        System.out.println(this.stringRuban());
        System.out.println("ruban : " + this.ruban[0]);
    }

    public void iniRegles(ArrayList<TuRegle> tabR) {
        this.tabRegles = tabR;
    }

    public void viderListeRegles() {
        this.tabRegles.clear();
    }

    public String stringRuban() {
        String sRub = "";
        for (int i = 0; i < ruban.length; ++i) {
            sRub += ruban[i];
        }
        return sRub;
    }

    public Character[] getRuban() {
        return this.ruban;
    }

    public TuRegle derniereRegle() {
        return this.lastRuleUsed;
    }

    public Character rubanPos(int pos) {
        return this.ruban[pos];
    }

    public int getPosTete() {
        return this.posTete;
    }

    public boolean arretee() {
        return this.arret;
    }

    public boolean demarree() {
        return this.demar;
    }

    // ajout regle
    public void ajoutTuRegle(int ec, Character sl, int es, Character se, Direction di) {
        TuRegle r = new TuRegle(ec, sl, es, se, di);
        this.tabRegles.add(r);
    }

    public void ajoutTuRegle(TuRegle r) {
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
        System.out.println("hello");
        boolean e = false; // voir si regle existe
        int i = -1;
        while ((!e) && (i < tabRegles.size() - 1)) {
            ++i;
            e = this.tabRegles.get(i).verif(this.etatCourant, lireSymb());

            System.out.println("i:" + i + "/" + Integer.toString(tabRegles.size() - 1));
        }
        if (e) {
            return i;
        } else {
            return -1;
        }
    }

    //ecrire dans le ruban
    public void ecrireRuban(int pos, Character sym) {
        try {
            this.ruban[pos] = sym;
            System.out.println("j'écris : " + sym);
        } catch (IndexOutOfBoundsException e) {
            // nothing
        }
    }

    //bouger la tete de lecture
    public void updatePosTete(Direction dir) {
        switch (dir) {
            case dr:
                ++this.posTete;
                break;
            case ga:
                --this.posTete;
                break;
            case pm:
                //nothing
                break;
        }
    }

    //appliquer une regle
    public boolean appliquer() {
        boolean stopit = false;
        int indRegle = trouveRegle();
        System.out.println("indice regle : " + indRegle);
        if (indRegle != -1) {
            TuRegle regle = this.tabRegles.get(indRegle);
            this.lastRuleUsed = regle;
            this.etatCourant = regle.etatSuiv();
            ecrireRuban(this.posTete, regle.symbEcrit());
            updatePosTete(regle.dirTete());
        } else {
            stopit = true;
        }

        return stopit;
    }

    /*
    public String rubanToString() {
        String rub = ruban.toString();
        return rub;
    }
     */
    // donne une chaine de 9 caracteres centree sur la tete de lecture
    public char[] rubanParsed() {
        int limit = 9;
        char[] rub = new char[9];
        for (int i = 0; i < limit; ++i) {
            try {
                rub[i] = ruban[posTete - 4 + i];
            } catch (IndexOutOfBoundsException e) {
                rub[i] = Character.MIN_VALUE; //caractere vide, mais quand meme caractere, a la difference de null
            }
        }
        for (int i = 0; i < limit; ++i) {
            System.out.println(rub[i]);
        }

        return rub;
    }

    public void faireUnPas() {
        if (this.tabRegles.isEmpty()) {
            System.out.println("TABLE VIDE");
            this.arret = true;
        }
        if (!this.arret) {
            if (this.demar == false) {
                this.demar = true;
            }
            this.arret = appliquer();
            System.out.println(ruban[0]);
            if (!(this.arretee())) {
                setChanged();
                notifyObservers(rubanParsed());
            }

// DEBUG
            System.out.println("StringRuban : " + this.stringRuban());
            System.out.println("ruban : " + this.ruban);
        }

    }

    public void deroulerTresVite() {
        while (!this.arret) {
            faireUnPas();
        }
    }

    // affichage des regles
    public void affReglesConsole() {
        for (TuRegle r : tabRegles) {
            System.out.println(r);
        }
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

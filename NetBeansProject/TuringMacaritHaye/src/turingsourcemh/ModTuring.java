package turingsourcemh;

import java.util.ArrayList;

/**
 *
 * @author Jean-Loup
 */    
public class ModTuring {

    private boolean arret;
    private TabTuRegles tabRegles;
    private int etatCourant;
    private Character[] ruban;
    private int posTete; //position de la tete de lecture
    
    public ModTuring() {
        this.arret=false;
        this.etatCourant=0;
        this.tabRegles=new TabTuRegles();
        this.ruban=new Character[0];
        this.posTete=0;
    }

    public Character rubanPos(int pos){
        return this.ruban[pos];
    }
    
    public int getPosTete(){
        return this.posTete;
    }
    
    
    // règles 
    public class TuRegle {

        //attrbuts
        private int ec; //etat courant
        private Character sl; //symbole lu
        private int es; // etat suivant
        private Character se; // symbole écrit
        private Direction di; // direction de la tete de lecture
        
        //constructeur basique
        public TuRegle (int ec, Character sl, int es, Character se, Direction di){
            this.ec = ec;
            this.sl = sl;
            this.es = es;
            this.se = se;
            this.di = di;
        }
        
        //methode de verification si la regle correspond
        public boolean verif(int ec, Character sl){
            return (this.ec == ec && this.sl == sl);
        }
        
        //getters pour l'application de la regle
        public int etatSuiv(){
            return this.es;
        }
        
        public Character symbEcrit(){
            return this.se;
        }
        
        public Direction dirTete(){
            return this.di;
        }

    }

    // collection de règles
    public class TabTuRegles {

        private ArrayList<TuRegle> tabTuRegles;

        public TabTuRegles() {
            tabTuRegles = new ArrayList<TuRegle>();
        }
        
        public void ajoutRegle(TuRegle tr){
            tabTuRegles.add(tr);
        }

    }

}

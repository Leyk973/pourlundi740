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
    
    public enum Direction {
        dr,
        ga,
        fi
    }

    public ModTuring() {

    }

    public class TuRegle {

        private int ec; //etat courant
        private Character sl; //symbole lu
        private int es; // etat suivant
        private Character se; // symbole écrit
        private Direction di; // direction de la tete de lecture
        
        public TuRegle (int ec, Character sl, int es, Character se, Direction di){
            this.ec = ec;
            this.sl = sl;
            this.es = es;
            this.se = se;
            this.di = di;
        }

    }

    public class TabTuRegles {

        private ArrayList<TuRegle> tabTuRegle;

        public TabTuRegles() {

        }

    }

}

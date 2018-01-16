package turingsourcemh;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//panel de l'historique
public class Historique extends JPanel implements Observer {

    private ModTuring modele;
    private JTextArea txtHist;
    private JScrollPane scroll;

    public Historique(ModTuring mod) {
        super(null);
        this.modele = mod;
        txtHist = new JTextArea();
        txtHist.setEditable(false);
        scroll = new JScrollPane(txtHist);

        scroll.setSize(300, 600);
        scroll.setLocation(30, 30);
        this.add(scroll);
    }

    @Override
    public void update(Observable o, Object arg) {
        if ((modele.demarree())) {
            txtHist.append("Règle : " + modele.derniereRegle() + "\nRuban " + modele.stringRuban() + "\n");
        } else {
            txtHist.setText("");
            txtHist.append("Ruban " + modele.stringRuban() + "\n");
        }

    }
}

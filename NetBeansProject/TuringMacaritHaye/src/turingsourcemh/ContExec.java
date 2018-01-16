package turingsourcemh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// panel d'exécution
public class ContExec extends JPanel {

    private Integer vitesse; // a acceder dans un synchronize
    private ModTuring modele;
    private JButton btnInit;
    private JButton btnStep;
    private JButton btnStart;
    private JButton btnStop;
    private JLabel lblInit;
    private JTextField txtRubanIni;
    private JLabel lblPlus;
    private JLabel lblMinus;
    private JSlider slideSpeed;

    private Runnable aFaire;
    private Thread rebours;

    // fonction récupérer la vitesse depuis le curseur
    class Rebours implements Runnable {

        private int multVitInv; // multiplicateur temps a attendre, inv car valeur directe
        // du curseur, le vrai multiplicateur étant 11-multVitInv
        private Runnable aFaire;

        public Rebours(int mv, Runnable aFaire) {
            this.multVitInv = mv;
            this.aFaire = aFaire;
        }

        public void run() {
            try {
                btnStep.setEnabled(false);
                btnStart.setEnabled(false);
                btnInit.setEnabled(false);

                while (!(modele.arretee()) && !(Thread.currentThread().isInterrupted()) && modele.lancee()) {
                    vitFromCursor();
                    int temps = 10 * (31 - multVitInv);
                    Thread.sleep(temps);
                    modele.faireUnPas();
                }
            } catch (InterruptedException ex) {
                //nothing
            }
        }

        public void vitFromCursor() {
            synchronized (vitesse) {
                multVitInv = vitesse.intValue();
            }
        }

    }

    public ContExec(ModTuring mod) {
        super(null);

        this.modele = mod;
        //création éléments
        this.btnStart = new JButton("Démarrer");
        this.btnStop = new JButton("Stopper");
        this.btnStop.setEnabled(false);
        this.lblInit = new JLabel("Ruban initial:");
        this.txtRubanIni = new JTextField();
        this.btnStep = new JButton("Faire un pas");
        this.btnInit = new JButton("Initialiser");
        this.lblMinus = new JLabel("-", SwingConstants.CENTER);
        this.lblPlus = new JLabel("+", SwingConstants.CENTER);
        this.slideSpeed = new JSlider(1, 30, 15);
        this.vitesse = new Integer(slideSpeed.getValue());
        this.rebours = new Thread(new Rebours(vitesse.intValue(), new Runnable() {
            public void run() {
            }
        }));

        //action listeners
        btnInit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char[] tChar = txtRubanIni.getText().toCharArray();
                Character[] tCharMod = new Character[tChar.length];
                for (int i = 0; i < tChar.length; ++i) {
                    tCharMod[i] = new Character(tChar[i]);
                }
                modele.iniRuban(tCharMod);
            }
        });

        btnStep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (modele.arretee()) {
                    JOptionPane.showMessageDialog(null,
                            "Machine arrêtée",
                            "Arrêt",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    modele.faireUnPas();
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (modele.arretee()) {
                    JOptionPane.showMessageDialog(null,
                            "Machine arrêtée",
                            "Arrêt",
                            JOptionPane.WARNING_MESSAGE);
                } else {    
                    rebours.interrupt();
                    rebours = new Thread(new Rebours(vitesse.intValue(), new Runnable() {
                        public void run() {
                        }
                    }));
                    modele.lancer();
                    btnStop.setEnabled(true);
                    rebours.start();
                }

            }
        });

        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnStart.setEnabled(true);
                btnInit.setEnabled(true);
                btnStep.setEnabled(true);
                rebours.interrupt();
            }
        });

        slideSpeed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                synchronized (vitesse) {
                    vitesse = slideSpeed.getValue();
                }
            }
        });

        //placement éléments
        lblInit.setSize(80, 30);
        lblInit.setLocation(70, 30);
        this.add(lblInit);

        txtRubanIni.setSize(100, 30);
        txtRubanIni.setLocation(180, 30);
        this.add(txtRubanIni);

        btnInit.setSize(120, 30);
        btnInit.setLocation(30, 90);
        this.add(btnInit);

        btnStep.setSize(120, 30);
        btnStep.setLocation(210, 90);
        this.add(btnStep);

        btnStart.setSize(120, 30);
        btnStart.setLocation(30, 150);
        this.add(btnStart);

        btnStop.setSize(120, 30);
        btnStop.setLocation(210, 150);
        this.add(btnStop);

        lblMinus.setSize(30, 30);
        lblMinus.setLocation(60, 210);
        this.add(lblMinus);

        slideSpeed.setSize(180, 30);
        slideSpeed.setLocation(90, 210);
        this.add(slideSpeed);

        lblPlus.setSize(30, 30);
        lblPlus.setLocation(270, 210);
        this.add(lblPlus);
    }

    public void setModel(ModTuring m) {
        this.modele = m;
    }
}

package turingsourcemh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// panel des regles
public class ContRegles extends JPanel {

    // modèle
    private ModTuring modele;
    // elements
    private JButton btnCharge;
    private JButton btnSave;
    private JButton btnAdd;
    //textfields
    private JTextField txtRule1;
    private JTextField txtRule2;
    private JTextArea txtRulesList;
    //label
    private JLabel lblArrow;
    //scroll
    private JScrollPane scroll;

    public ContRegles(ModTuring mod) {
        super(null);
        this.modele = mod;
        btnCharge = new JButton("Charger");
        btnSave = new JButton("Sauvegarder");
        btnAdd = new JButton("Ajouter");
        txtRule1 = new JTextField();
        txtRule2 = new JTextField();
        txtRulesList = new JTextArea();
        lblArrow = new JLabel("=>", SwingConstants.CENTER);
        txtRulesList.setEditable(false);
        scroll = new JScrollPane(txtRulesList);

        btnCharge.setSize(120, 30);
        btnCharge.setLocation(30, 30);
        this.add(btnCharge);

        btnSave.setSize(120, 30);
        btnSave.setLocation(210, 30);
        this.add(btnSave);

        btnAdd.setSize(120, 30);
        btnAdd.setLocation(30, 90);
        this.add(btnAdd);

        txtRule1.setSize(60, 30);
        txtRule1.setLocation(180, 90);
        this.add(txtRule1);

        lblArrow.setSize(30, 30);
        lblArrow.setLocation(240, 90);
        this.add(lblArrow);

        txtRule2.setSize(60, 30);
        txtRule2.setLocation(270, 90);
        this.add(txtRule2);

        scroll.setSize(300, 100);
        scroll.setLocation(30, 150);
        this.add(scroll);

        // configuration des actionListener
        //ajout règle
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    modele.ajoutTuRegle(ruleFromTF());
                    txtRulesList.setEditable(true);
                    txtRulesList.append(stringFromTF() + "\n");
                    txtRulesList.setEditable(false);
                } catch (Exception exce) {
                    JOptionPane.showMessageDialog(null,
                            "Problème d'édition de la règle.",
                            "Erreur",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File fichierPersistant = new File(".listeregles");

                // sauvegarder la couleur actuelle dans le fichier .choixcouleur
                try {
                    FileOutputStream fos = new FileOutputStream(fichierPersistant);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(txtRulesList.getText());
                    oos.close();                    
                } catch (FileNotFoundException ex) {
                    //Logger.getLogger(ChoixCouleur.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //Logger.getLogger(ChoixCouleur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void setModel(ModTuring m) {
        this.modele = m;
    }

    //fonctions utilitaire pour éviter les indexoutofboundsexception
    // test pour la récupération d'un symbole depuis un String[]
    // si ioobe ou vide, donne un caractere vide
    // sinon, donne le caractere
    public Character recupSymbole(String[] tabS) {
        Character res;
        try {
            res = tabS[1].charAt(0);
        } catch (IndexOutOfBoundsException ioobe) {
            res = Character.MIN_VALUE;
        }
        return res;
    }

    public Direction recupDirection(String[] tabS) {
        Direction dir;
        String dirS;

        try {
            dirS = tabS[2];
            switch (dirS) {
                case (">"):
                    dir = Direction.dr;
                    break;
                case ("<"):
                    dir = Direction.ga;
                    break;
                default: // si vide ou autre
                    dir = Direction.pm;
                    break;
            }
        } catch (IndexOutOfBoundsException ioobe) {
            dir = Direction.pm;
        }

        return dir;
    }

    // creer une TuRegle a partir des textFields
    public TuRegle ruleFromTF() {
        String s1 = txtRule1.getText();
        String s2 = txtRule2.getText();
        String[] ecsl = s1.split(",");
        String[] essedi = s2.split(",");
        int ec = Integer.parseInt(ecsl[0]);
        Character sl = recupSymbole(ecsl);
        int es = Integer.parseInt(essedi[0]);
        Character se = recupSymbole(essedi);
        Direction di = recupDirection(essedi);

        TuRegle regle = new TuRegle(ec, sl, es, se, di);

        return regle;
    }

    public String stringFromTF() {
        String s1 = txtRule1.getText();
        String s2 = txtRule2.getText();
        String regleAff = "(" + s1 + ") => (" + s2 + ")";
        return regleAff;
    }

}

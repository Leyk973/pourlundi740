package turingsourcemh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
    private JButton btnDel;
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
        btnDel = new JButton("Supprimer");
        txtRule1 = new JTextField();
        txtRule2 = new JTextField();
        txtRulesList = new JTextArea();
        lblArrow = new JLabel("=>", SwingConstants.CENTER);
        txtRulesList.setEditable(false);
        scroll = new JScrollPane(txtRulesList);

        //placement éléments
        btnCharge.setSize(120, 30);
        btnCharge.setLocation(30, 30);
        this.add(btnCharge);

        btnSave.setSize(120, 30);
        btnSave.setLocation(210, 30);
        this.add(btnSave);

        btnAdd.setSize(120, 30);
        btnAdd.setLocation(30, 75);
        this.add(btnAdd);

        btnDel.setSize(120, 30);
        btnDel.setLocation(30, 105);
        this.add(btnDel);

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
                    modele.affReglesConsole();
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

        // supprimer dernière regle
        btnDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delLastRule();
            }
        });

        btnCharge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chargeInList();
                setRulesFromList();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFromList();
            }
        });
    }

    //sauvegarder dans un fichier les règle contenues dans la JTextArea
    public void saveFromList() {
        File saveFile = null;
        String path;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            saveFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + saveFile.getAbsolutePath());

            if (!saveFile.exists()) {
                path = saveFile.getAbsolutePath();
                saveFile = new File(path);
                try {
                    FileOutputStream fos = new FileOutputStream(saveFile);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(txtRulesList.getText());
                    oos.close();
                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                }
            }
        }
    }

    //supprime la dernière règle de la liste
    public void delLastRule() {
        String[] rules = txtRulesList.getText().split("\n");
        try {
            txtRulesList.setText("");
            for (int i = 0; i < rules.length - 1; ++i) {
                txtRulesList.append(rules[i] + "\n");
            }
            modele.retraitTuRegle(rules.length - 1);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null,
                    "Impossible de supprimer la règle, vérifiez qu'il en reste une.",
                    "Erreur",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    //charge la liste de règles avec le contenu d'un fichier
    public void chargeInList() {
        File fileToCharge = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileToCharge = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + fileToCharge.getAbsolutePath());
        }

        if (fileToCharge.exists()) {
            try {
                FileInputStream fis = new FileInputStream(fileToCharge);
                ObjectInputStream ois = new ObjectInputStream(fis);
                txtRulesList.setText((String) ois.readObject());
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            } catch (ClassNotFoundException ex) {
            }
        }
    }

    public void setRulesFromList() {
        // suppression des regles
        modele.viderListeRegles();
        String[] sRegles = txtRulesList.getText().split("\n");
        for (int i = 0; i < sRegles.length; ++i) {
            String[] avap = sRegles[i].split("=>");

            // suppression des parenthèes            
            String sub1 = avap[0].substring(1, avap[0].lastIndexOf(")"));
            String sub2 = avap[1].substring(1, avap[1].lastIndexOf(")"));

            String[] ecsl = sub1.split(",");
            String[] essedi = sub2.split(",");
            int ec = Integer.parseInt(ecsl[0]);
            Character sl = recupSymbole(ecsl);
            int es = Integer.parseInt(essedi[0]);
            Character se = recupSymbole(essedi);
            Direction di = recupDirection(essedi);

            TuRegle regle = new TuRegle(ec, sl, es, se, di);
            modele.ajoutTuRegle(regle);
        }
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

    public String recupDirectionString(String[] tabS) {
        String res;
        String dirS;

        try {
            dirS = tabS[2];
            switch (dirS) {
                case (">"):
                    res = dirS;
                    break;
                case ("<"):
                    res = dirS;
                    break;
                default: // si vide ou autre
                    res = "";
                    break;
            }
        } catch (IndexOutOfBoundsException ioobe) {
            res = "";
        }
        return res;
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

    public String dirPourAff(String dir) {
        String res;
        switch (dir) {
            case ("<"):
                res = dir;
                break;
            case (">"):
                res = dir;
                break;
            default:
                res = "";
                break;
        }
        return res;
    }

    public String stringFromTF() {
        String s1 = txtRule1.getText();
        String[] s2 = txtRule2.getText().split(",");
        String regleAff = "(" + s1 + ")=>(" + s2[0] + "," + recupSymbole(s2) + "," + recupDirectionString(s2) + ")";
        return regleAff;
    }
}

package turingsourcemh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    private JTextField txtRule;
    private JTextField txtRule2;
    private JTextArea txtRulesList;
    //label
    private JLabel lblArrow;
    //scroll
    private JScrollPane scroll;
    
    public ContRegles() {
        super(null);
        JButton btnCharge = new JButton("Charger");
        JButton btnSave = new JButton("Sauvegarder");
        JButton btnAdd = new JButton("Ajouter");
        JTextField txtRule1 = new JTextField();
        JTextField txtRule2 = new JTextField();
        JTextArea txtRulesList = new JTextArea();
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
                modele.ajoutTuRegle(ruleFromTF());
                txtRulesList.setEditable(true);
                txtRulesList.append(stringFromTF() + "\n");
                txtRulesList.setEditable(false);
            }
        });
        
    }
    
    public void setModel(ModTuring m) {
        this.modele = m;
    }

    // creer une TuRegle a partir des textFields
    public TuRegle ruleFromTF() {
        String s1 = txtRule.getText();
        String s2 = txtRule2.getText();
        String[] ecsl = s1.split(",");
        String[] essedi = s2.split(",");
        int ec = Integer.parseInt(ecsl[0]);
        Character sl = new Character(ecsl[1].charAt(0));
        int es = Integer.parseInt(essedi[0]);
        Character se = new Character(essedi[1].charAt(0));
        Direction di;
        switch (essedi[2]) {
            case (">"):
                di = Direction.dr;
                break;
            case ("<"):
                di = Direction.ga;
                break;
            default: // si vide ou autre
                di = Direction.pm;
                break;
        }
        TuRegle regle = new TuRegle(ec, sl, es, se, di);
        
        return regle;
    }
    
    public String stringFromTF() {
        String s1 = txtRule.getText();
        String s2 = txtRule2.getText();
        String regleAff = "(" + s1 + ") => (" + s2 + ")";        
        return regleAff;
    }
    
}

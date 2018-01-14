package turingsourcemh;

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
    }
    
    public void setModel (ModTuring m){
        this.modele=m;
    }
    
    

}

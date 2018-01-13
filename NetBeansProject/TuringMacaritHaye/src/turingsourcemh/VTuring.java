/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turingsourcemh;

import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author Arthur
 */
public class VTuring extends JFrame {
    
    public VTuring() {
        super("Turing");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ModTuring modele = new ModTuring();
        
        JButton btnCharge = new JButton("Charger");
        JButton btnSave = new JButton("Sauvegarder");
        JButton btnAdd = new JButton("Ajouter");
        JButton btnInit = new JButton("Initialiser");
        JButton btnStep = new JButton("Faire un pas");
        JButton btnStart = new JButton("Démarrer");
        JButton btnStop = new JButton("Stopper");
        JLabel lblArrow = new JLabel("=>");
        JLabel lblInit = new JLabel("Ruban initial:");
        JLabel lblSpeed = new JLabel("Vitesse");
        JLabel lblMinus = new JLabel("-");
        JLabel lblPlus = new JLabel("+");
        JTextField txtRule1 = new JTextField();
        JTextField txtRule2 = new JTextField();
        JTextArea txtRulesList = new JTextArea("a\n a\n a\n a\n a\n a\n a\n a\n a\n a\n a\n a\n a\n");
        JScrollPane scroll = new JScrollPane(txtRulesList);
        JTextField txtRubanIni = new JTextField();
        JSlider slideSpeed = new JSlider(0,10,5);
        
        //Ruban ruban = new Ruban();
        //LabelCasesTab ruban = new LabelCasesTab();
        
        txtRulesList.setEditable(false);
        
        //modele.addObserver(txtRulesList);
        //addObserver
        
        JPanel pnlRule = new JPanel();
        JPanel pnlExec = new JPanel();
        JPanel pnlRuban = new JPanel();
        JPanel pnlMain = new JPanel();
        pnlRule.setLayout(new GridLayout(3,4));
        pnlExec.setLayout(new GridLayout(2,4));
        pnlRuban.setLayout(new GridLayout(1,1));
        pnlMain.setLayout(new GridLayout(3,1));
        
        JPanel pnlRuleA = new JPanel();
        JPanel pnlRuleB = new JPanel();
        pnlRuleA.setLayout(new GridLayout(1,2));
        pnlRuleB.setLayout(new GridLayout(1,4));
        pnlRuleA.add(btnCharge);
        pnlRuleA.add(btnSave);
        pnlRuleB.add(btnAdd);
        pnlRuleB.add(txtRule1);
        pnlRuleB.add(lblArrow);
        pnlRuleB.add(txtRule2);
        pnlRule.add(pnlRuleA);
        pnlRule.add(pnlRuleB);
        pnlRule.add(scroll);
        
        JPanel pnlExecA = new JPanel();
        JPanel pnlExecB = new JPanel();
        pnlExecA.setLayout(new GridLayout(3,2));
        pnlExecB.setLayout(new GridLayout(1,4));
        pnlExecA.add(lblInit);
        pnlExecA.add(txtRubanIni);
        pnlExecA.add(btnInit);
        pnlExecA.add(btnStep);
        pnlExecA.add(btnStart);
        pnlExecA.add(btnStop);
        pnlExecB.add(lblSpeed);
        pnlExecB.add(lblMinus);
        pnlExecB.add(slideSpeed);
        pnlExecB.add(lblPlus);
        pnlExec.add(pnlExecA);
        pnlExec.add(pnlExecB);
        
        //pnlRuban.add(ruban);
        
        pnlRule.setBorder(BorderFactory.createTitledBorder("Règles"));
        pnlExec.setBorder(BorderFactory.createTitledBorder("Exécution"));
        pnlRuban.setBorder(BorderFactory.createTitledBorder("Ruban"));
        
        pnlMain.add(pnlRule);
        pnlMain.add(pnlExec);
        pnlMain.add(pnlRuban);
        
        this.getContentPane().add(pnlMain);
        this.pack();
        this.setVisible(true);
    }
    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                new VTuring();
            }
        });
    }
}

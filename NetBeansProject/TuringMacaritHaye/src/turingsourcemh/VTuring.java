/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * TODO : ajouter gestion de la sauvegarde et de la restauration
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

        //ModTuring modele = new ModTuring();
        JButton btnCharge = new JButton("Charger");
        JButton btnSave = new JButton("Sauvegarder");
        JButton btnAdd = new JButton("Ajouter");
        JButton btnInit = new JButton("Initialiser");
        JButton btnStep = new JButton("Faire un pas");
        JButton btnStart = new JButton("Démarrer");
        JButton btnStop = new JButton("Stopper");
        JLabel lblArrow = new JLabel("=>", SwingConstants.CENTER);
        JLabel lblInit = new JLabel("Ruban initial:");
        JTextField txtRule1 = new JTextField();
        JTextField txtRule2 = new JTextField();
        JTextArea txtRulesList = new JTextArea();
        txtRulesList.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtRulesList);
        JTextField txtRubanIni = new JTextField();

        Ruban ruban = new Ruban();

        //modele.addObserver(txtRulesList);
        //modele.addObserver(ruban);
        
        //préparation panels
        JPanel pnlRule = new JPanel(null);
        pnlRule.setSize(360, 280);
        pnlRule.setLocation(0, 0);
        pnlRule.setBorder(BorderFactory.createTitledBorder("Règles"));

        JPanel pnlExec = new JPanel(null);
        pnlExec.setSize(360, 210);
        pnlExec.setLocation(0, 290);
        pnlExec.setBorder(BorderFactory.createTitledBorder("Exécution"));

        JPanel pnlRuban = new JPanel();
        pnlRuban.setLayout(new GridLayout(1, 1));
        pnlRuban.setSize(360, 90);
        pnlRuban.setLocation(0, 510);
        pnlRuban.setBorder(BorderFactory.createTitledBorder("Ruban"));

        JPanel pnlMain = new JPanel(null);

        //insertion panel règles
        btnCharge.setSize(120, 30);
        btnCharge.setLocation(30, 30);
        pnlRule.add(btnCharge);

        btnSave.setSize(120, 30);
        btnSave.setLocation(210, 30);
        pnlRule.add(btnSave);

        btnAdd.setSize(120, 30);
        btnAdd.setLocation(30, 90);
        pnlRule.add(btnAdd);

        txtRule1.setSize(60, 30);
        txtRule1.setLocation(180, 90);
        pnlRule.add(txtRule1);

        lblArrow.setSize(30, 30);
        lblArrow.setLocation(240, 90);
        pnlRule.add(lblArrow);

        txtRule2.setSize(60, 30);
        txtRule2.setLocation(270, 90);
        pnlRule.add(txtRule2);

        scroll.setSize(300, 100);
        scroll.setLocation(30, 150);
        pnlRule.add(scroll);

        //insertion panel exécution
        lblInit.setSize(80, 30);
        lblInit.setLocation(70, 30);
        pnlExec.add(lblInit);

        txtRubanIni.setSize(100, 30);
        txtRubanIni.setLocation(180, 30);
        pnlExec.add(txtRubanIni);

        btnInit.setSize(120, 30);
        btnInit.setLocation(30, 90);
        pnlExec.add(btnInit);

        btnStep.setSize(120, 30);
        btnStep.setLocation(210, 90);
        pnlExec.add(btnStep);

        btnStart.setSize(120, 30);
        btnStart.setLocation(30, 150);
        pnlExec.add(btnStart);

        btnStop.setSize(120, 30);
        btnStop.setLocation(210, 150);
        pnlExec.add(btnStop);

        //insertion panel ruban
        pnlRuban.add(ruban);

        //insertion panel main
        pnlMain.add(pnlRule);
        pnlMain.add(pnlExec);
        pnlMain.add(pnlRuban);

        this.getContentPane().add(pnlMain);
        this.pack();
        this.setVisible(true);
        this.setSize(360, 650);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VTuring();
            }
        });
    }
}

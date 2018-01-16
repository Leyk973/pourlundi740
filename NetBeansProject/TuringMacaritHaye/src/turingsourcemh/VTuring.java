package turingsourcemh;

import java.awt.GridLayout;
import javax.swing.*;

public class VTuring extends JFrame {

    public VTuring() {
        super("Turing");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Ruban ruban = new Ruban();

        //Ajout du modele
        ModTuring machine = new ModTuring();

        //préparation panels
        ContRegles pnlRule = new ContRegles(machine);
        pnlRule.setSize(360, 280);
        pnlRule.setLocation(0, 0);
        pnlRule.setBorder(BorderFactory.createTitledBorder("Règles"));

        ContExec pnlExec = new ContExec(machine);
        pnlExec.setSize(360, 270);
        pnlExec.setLocation(0, 290);
        pnlExec.setBorder(BorderFactory.createTitledBorder("Exécution"));

        JPanel pnlRuban = new JPanel();
        pnlRuban.setLayout(new GridLayout(1, 1));
        pnlRuban.setSize(360, 90);
        pnlRuban.setLocation(0, 570);
        pnlRuban.setBorder(BorderFactory.createTitledBorder("Ruban"));

        Historique pnlHist = new Historique(machine);
        pnlHist.setSize(360, 660);
        pnlHist.setLocation(370, 0);
        pnlHist.setBorder(BorderFactory.createTitledBorder("Historique"));

        JPanel pnlMain = new JPanel(null);

        machine.addObserver(ruban);
        machine.addObserver(pnlHist);

        //insertion panel ruban
        pnlRuban.add(ruban);

        //insertion panel main
        pnlMain.add(pnlRule);
        pnlMain.add(pnlExec);
        pnlMain.add(pnlRuban);
        pnlMain.add(pnlHist);

        this.getContentPane().add(pnlMain);
        this.pack();
        this.setVisible(true);
        this.setSize(740, 700);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VTuring();
            }
        });
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turingsourcemh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jean-Loup
 */
public class ContExec extends JPanel {
    
    private ModTuring modele;
    
    private JButton btnInit;
    private JButton btnStep;
    private JButton btnStart;
    private JButton btnStop;
    private JLabel lblInit;
    private JTextField txtRubanIni;
    
    class StartListener implements ActionListener {
      public void actionPerformed(ActionEvent evt) {
          
      }
    }
    
    class StopListener implements ActionListener {
      public void actionPerformed(ActionEvent evt) {
          
      }
    }
    
    class StepListener implements ActionListener {
      public void actionPerformed(ActionEvent evt) {
          
      }
    }
    
    class InitListener implements ActionListener {
      public void actionPerformed(ActionEvent evt) {
          
      }
    }
    
    public ContExec(){
        super(null);
        //création éléments
        this.btnStart = new JButton("Démarrer");
        this.btnStop = new JButton("Stopper");
        this.lblInit = new JLabel("Ruban initial:");
        this.txtRubanIni = new JTextField();
        this.btnStep = new JButton("Faire un pas");
        this.btnInit = new JButton("Initialiser");
        
        //action listeners
        
        
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
    }
    
    public void setModel (ModTuring m){
        this.modele=m;
    }
}

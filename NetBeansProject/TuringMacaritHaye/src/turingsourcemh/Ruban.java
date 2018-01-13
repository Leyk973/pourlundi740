/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turingsourcemh;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class Ruban extends JPanel{
    
    // nombre de cases
    private int nbC = 9;
    
    // tableau des cases du ruban
    private JLabel[] tabLab;
    
    public Ruban (){
        super(null);
        tabLab = new JLabel[nbC];
        
        Border border = LineBorder.createBlackLineBorder();
        
        for (int i = 0; i < nbC; ++i)
        {
            tabLab[i] = new JLabel("a", SwingConstants.CENTER);
            tabLab[i].setBorder(border);
            tabLab[i].setOpaque(true);
            tabLab[i].setBackground(Color.white);
            tabLab[i].setSize(30,30);
            tabLab[i].setLocation(i*30,0);
            this.add(tabLab[i]);
        }
        tabLab[4].setBackground(Color.cyan);
    }

    public void updateTab (char[] tabChar){
        for (int i = 0; i < nbC; ++i) {
            tabLab[i].setText("" + tabChar[i]);
        }
    }
}  
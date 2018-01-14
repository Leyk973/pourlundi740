/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turingsourcemh;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class Ruban extends JPanel implements Observer {

    // nombre de cases
    private int nbC = 9;

    //tableau contenant les caractères affichés
    private char[] dispRub;

    //tableaux contenant les coordonnées du polygone de la tête du pointeur
    private int[] coorX, coorY;

    //sécurité sur la méthode paint()
    private boolean fait = false;

    //solution graphics
    public Ruban() {
        super(null);
        dispRub = new char[9];
        coorX = new int[3];
        coorY = new int[3];

        for (int i = 0; i < nbC; ++i) {
            dispRub[i] = 0;
        }

        coorX[0] = 120;
        coorX[1] = 115;
        coorX[2] = 125;
        coorY[0] = 55;
        coorY[1] = 65;
        coorY[2] = 65;
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        if (fait == false) {
            int bor = 30;
            int dim = 20;

            g.setColor(Color.decode("#EDEDED"));

            g.fillRect(10, 10, 200, 60);

            g.setColor(Color.BLACK);

            g.drawLine(bor, bor, bor + nbC * dim, bor);
            g.drawLine(bor, bor + dim, bor + nbC * dim, bor + dim);

            for (int i = 0; i < nbC; ++i) {
                g.drawLine(bor + dim * i, bor, bor + dim * i, bor + dim);
                g.drawString(Character.toString(dispRub[i]), (bor + dim * i) + 7, (bor + dim / 2) + 6);
            }
            g.drawLine(bor + dim * nbC, bor, bor + dim * nbC, bor + dim);

            g.fillPolygon(coorX, coorY, 3);

            fait = true;
        }
    }
    
    /*
    public void updateTab(char[] newRub) {
        for (int i = 0; i < nbC; ++i) {
            dispRub[i] = newRub[i];
        }
        fait = false;
        this.repaint();
    }
    */

    public void update(Observable o, Object arg) {
        if ((o instanceof ModTuring) && (arg instanceof char[])) {
            char[] newRub = (char[])arg;
            for (int i = 0; i < nbC; ++i) {
                dispRub[i] = newRub[i];
                //System.out.println(dispRub[i]);
            }
            fait = false;
            this.repaint();
        }
    }

    //solution JLabel
    /*
    // tableau des cases du ruban
    private JLabel[] tabLab;
    
    public Ruban (){
        super(null);
        tabLab = new JLabel[nbC];
        
        Border border = LineBorder.createBlackLineBorder();
        
        for (int i = 0; i < nbC; ++i)
        {
            tabLab[i] = new JLabel("", SwingConstants.CENTER);
            tabLab[i].setBorder(border);
            tabLab[i].setOpaque(true);
            tabLab[i].setBackground(Color.white);
            tabLab[i].setSize(30,30);
            tabLab[i].setLocation((i*30)+30,30);
            this.add(tabLab[i]);
        }
        tabLab[4].setBackground(Color.cyan);
    }

    public void updateTab (char[] tabChar){
        for (int i = 0; i < nbC; ++i) {
            tabLab[i].setText("" + tabChar[i]);
        }
    }*/
}

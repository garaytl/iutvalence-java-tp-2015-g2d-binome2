package fr.iutvalence.blancarayt.testIHM;

import java.awt.Graphics;
import javax.swing.JPanel;
 
public class Panneau extends JPanel {
  public void paintComponent(Graphics g){
    g.drawString("Tiens ! Le Site du Zéro !", 10, 20);
  }               
}
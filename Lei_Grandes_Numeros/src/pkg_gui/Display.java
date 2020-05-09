/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_gui;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


/**
 *
 * @author igor
 */
public class Display {
    
    public Display(int sizeX, int sizeY, int posX, int posY, Color color, boolean visible){
        
        try {
            
            this.color = color;
            this.posX = posX;
            this.posY = posY;
            this.sizeX = sizeX;
            this.sizeY = sizeY;

            this.panel = new JPanel();
            this.panel.setLayout(null);

            this.panel.setBounds(this.posX, this.posY, this.sizeX, this.sizeY);
            this.panel.setBackground(this.color);
            this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
            this.panel.setVisible(visible);
            
        } catch (Exception e) {
            
            System.err.println(e.toString());
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getPanel() {
        return panel;
    }
    
    private int sizeX;
    private int sizeY;
    private int posX;
    private int posY;
    private Color color;
    private JPanel panel;
}

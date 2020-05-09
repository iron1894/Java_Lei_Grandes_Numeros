/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pkg_main.Main;

/**
 *
 * @author igor
 */
public class Gcomponents extends JComponent{
    
    public Gcomponents(int x, int y, Color color){
        
        this.py     = y;
        this.px     = x;
        this.color  = color;

        this.setBounds(this.px, this.py, 10, 10);
    }
    
    public JComponent getComponent(){
        return this;
    }
    
    @Override
    public void paint(Graphics graphics){
        this.setBounds(this.px, this.py, 10, 10);
        
        System.out.println(this.px);
        graphics.fillOval(0, 0, 10, 10);
        setForeground(this.color);
        repaint();
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getPx() {
        return px;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public int getPy() {
        return py;
    }

    
    
    
    private int px;
    private int py;
    private Color color;
    private int sizeX;
    private int sizeY;
}

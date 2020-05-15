/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author igor
 */
public class Gcomponents extends JComponent{
    
    public Gcomponents(int x, int y, Color color){
        
        this.py             = y;
        this.px             = x;
        this.p0x            = x;
        this.p0y            = y;
        this.color          = color;
        this.block_left     = true;
        this.block_right    = false;
        this.stop           = true;

        this.setBounds(this.px, this.py, 10, 10);
    }
    
    public JComponent getComponent(){
        return this;
    }
    
    @Override
    public void paint(Graphics graphics){
        this.setBounds(this.px, this.py, 10, 10);
        graphics.fillOval(0, 0, 10, 10);
        setForeground(this.color);
        repaint();
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
    
    public boolean getStop(){
        return this.stop;
    }

    public void setP0x(int p0x) {
        this.p0x = p0x;
    }

    public void setP0y(int p0y) {
        this.p0y = p0y;
    }

    public int getP0x() {
        return p0x;
    }

    public int getP0y() {
        return p0y;
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

    public void setBlock_left(boolean block_left) {
        this.block_left = block_left;
    }

    public void setBlock_right(boolean block_right) {
        this.block_right = block_right;
    }
    
    public boolean getBlock_left(){
        return this.block_left;
    }
    
    public boolean getBlock_rigth(){
        return this.block_right;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
    private int px;
    private int py;
    private int p0x;
    private int p0y;
    private Color color;
    private int sizeX;
    private int sizeY;
    private boolean block_right;
    private boolean block_left;
    private boolean stop;
}

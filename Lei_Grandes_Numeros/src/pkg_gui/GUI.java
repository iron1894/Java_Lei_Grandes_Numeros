/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_gui;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pkg_main.Main;

/**
 *
 * @author igor
 */
public class GUI {
    
    public GUI(int sizeX, int sizeY, int posX, int posY, String title){
        
        this.sizeX  = sizeX;
        this.sizeY  = sizeY;
        this.posX   = posX;
        this.posY   = posY;
        this.title  = title;
    }
    
    public void create_frame(boolean visible){
        
        try{
            this.frame = new JFrame();
            this.frame.setLayout(null);
            this.frame.setTitle(this.title);
            this.frame.setBounds(this.posX, this.posY, this.sizeX, this.sizeY);
            
            Display display = new Display(500, 300, 10, 10, Color.WHITE, true);
            
            List<Gcomponents> l = new ArrayList<>();
            
            Random r = new Random();
            
            for(int i = 0; i < Main.total_particles; i++)
            {
                int iColor = r.nextInt((1 - 0) + 1) + 0;

                int x = (int)(299.0 * Math.random());
                int y = (int)(299.0 * Math.random());
                
                if(iColor == 1){
                    Main.total_particles_1++;
                    Gcomponents g = new Gcomponents(x, y, Color.BLACK);
                    l.add(g);
                    display.getPanel().add(l.get(i));
                }else{
                    Main.total_particles_2++;
                    Gcomponents g = new Gcomponents(x, y, Color.RED);
                    l.add(g);
                    display.getPanel().add(l.get(i));
                }
            }

            create_panel();
            
            this.frame.getContentPane().add(display.getPanel());
            this.frame.setVisible(visible);
            this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    
                    while(true)
                    {
                        int k = l.get(0).getPx();
                        
                        if(!block_right && block_left)
                        {
                            k++; 
                            l.get(0).setPx(k);
                            
                            if(k == 500){
                              block_right = true;
                              block_left = false;
                            }
                        }
                        
                        if(block_right && !block_left)
                        {
                            k--;
                            l.get(0).setPx(k);
                            
                            if(k == 0){
                                block_right = false;
                                block_left = true;
                            }
                        }
                        
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            t.start();
            
            
        }catch(HeadlessException e)
        {
            System.err.println(e.toString());
        }
    }

    public void create_panel(){
        
        JPanel panel_menu = new JPanel();
        panel_menu.setLayout(null);
        panel_menu.setBackground(new Color(200, 200, 200));
        panel_menu.setBounds(520, 10, 250, 300);
        panel_menu.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        
        JLabel label_tile_menu = new JLabel();
        label_tile_menu.setBounds(10, 10, 200, 20);
        label_tile_menu.setText("Informações: ");
        panel_menu.add(label_tile_menu);
        
        JLabel label_total_esferas = new JLabel();
        label_total_esferas.setBounds(10, 30, 200, 20);
        label_total_esferas.setText("Total de esferas: " + Main.total_particles);
        panel_menu.add(label_total_esferas);
        
        JLabel label_total_color_1 = new JLabel();
        label_total_color_1.setBounds(10, 50, 200, 20);
        label_total_color_1.setText("Pretas: " + Main.total_particles_1);
        panel_menu.add(label_total_color_1);
        
        JLabel label_total_color_2 = new JLabel();
        label_total_color_2.setBounds(10, 70, 200, 20);
        label_total_color_2.setText("Vermelhas: " + Main.total_particles_2);
        panel_menu.add(label_total_color_2);
        
        this.frame.getContentPane().add(panel_menu);
        
    }
    
    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosY() {
        return posY;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JFrame getFrame() {
        return frame;
    }

    private String title;
    private int sizeX;
    private int sizeY;
    private int posX;
    private int posY;
    private JFrame frame;
    
    private boolean block_left = true;
    private boolean block_right = false;
}

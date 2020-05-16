/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_main;

import pkg_gui.GUI;

/**
 *
 * @author igor
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public Main()
    {
        GUI g = new GUI(800, 700, 300, 10, "Simulador LGN");
        g.create_frame(true);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Main m = new Main();
    }
    
    public static int total_particles = 10;
    public static int total_particles_1 = 0;
    public static int total_particles_2 = 0;
}

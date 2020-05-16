/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_gui;

import com.sun.corba.se.spi.activation.Server;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import pkg_main.Main;
import pkg_main.Math_Geo;

/**
 *
 * @author igor
 */
public class GUI {
    
    public GUI(int sizeX, int sizeY, int posX, int posY, String title){
        
        System.gc();
        GUI.time = 0;
        GUI.lyP = new ArrayList<>();
        
        GUI.lyV = new ArrayList<>();
        
        this.sizeX  = sizeX;
        this.sizeY  = sizeY;
        this.posX   = posX;
        this.posY   = posY;
        this.title  = title;
    }
    
    public void check_detect(Color c){
        
        if(c == Color.RED){

            double dv = ((double)total_detect_1/(double)total_de_sorteios);
            total_detect_1++;   
            label_detect_1.setText("Detector partícula vermelha: " + (dv * Main.total_particles));
            
            if(Double.isFinite(dv)){
                chart.getDataset().addValue(dv, "Vermelha", String.valueOf(GUI.time));
            }
            
        }else{
            
            double dp = ((double)total_detect_2/(double)total_de_sorteios);        
            total_detect_2++;
            label_detect_2.setText("Detector partícula preta: " + (dp * Main.total_particles));
        }
    }
    
    public void comput_movement(Gcomponents g, boolean stop){
        if(!stop)
        {
            int k = g.getPx();

            //if(!l.get(index).getBlock_rigth() && l.get(index).getBlock_left())
            if(!g.getBlock_rigth() && g.getBlock_left())
            {
                k++; 
                g.setPx(k);

                if(k == 460){
                    g.setPx(460);
                    check_detect(g.getColor());
                    g.setBlock_right(true);
                    g.setBlock_left(false);
                }
            }

            if(g.getBlock_rigth() && !g.getBlock_left())
            {
                k--;
                g.setPx(k);

                if(k == 0){
                    total_de_sorteios++;
                    g.setP0x(0);
                    g.setPx(0);
                    g.setBlock_right(false);
                    g.setBlock_left(true);
                }
            }
        }
    }
    
    public Gcomponents build_first(double range1, double range2){
        
        int x = (int)(range1 * Math.random());
        int y = (int)(range2 * Math.random());
        
        Random r = new Random();
        int c = (int) r.nextInt((1 - 0) + 1) + 0;
        
        if(c == 1)
        {
            Main.total_particles_1++;
            Gcomponents particula = new Gcomponents(x, y, Color.BLACK);
            return particula;
            
        }else
        {
            Main.total_particles_2++;
            Gcomponents particula = new Gcomponents(x, y, Color.RED);
            return particula;
        }
    }
    
    public Gcomponents build(int x, int y){
        
        Random r = new Random();
        int c = (int) r.nextInt((1 - 0) + 1) + 0;
        
        if(c == 1)
        {
            Main.total_particles_1++;
            Gcomponents particula = new Gcomponents(x, y, Color.BLACK);
            return particula;
            
        }else
        {
            Main.total_particles_2++;
            Gcomponents particula = new Gcomponents(x, y, Color.RED);
            return particula;
        }
    }
    
    public void paint_particles(List<Gcomponents> list_particles, Display display){
        list_particles.forEach((particula) -> {
            display.getPanel().add(particula);
        });
    }
    
    public double[] check_collision(List<Gcomponents> list, double range1, double range2, int x, int y){
        
        boolean coolision = false;
        double[] l = new double[2];
        
        Math_Geo mathGeo = new Math_Geo();        
        
        for(int i = 0; i < list.size(); i++){
            
            double r = mathGeo.r((double) x, list.get(i).getP0x(), (double) y, list.get(i).getP0y());
            if(r < 20.0){
                coolision = true;
            }
        }
        
        if(!coolision)
        {
            l[0] = x;
            l[1] = y;
            return l;
            
        }
        
        x = (int)(range1 * Math.random());
        y = (int)(range2 * Math.random());
        return check_collision(list, range1, range2, x, y);
    }
    
    public void create_frame(boolean visible){
        
        try{
            
            this.frame = new JFrame();
            this.frame.setLayout(null);
            this.frame.setTitle(this.title);
            this.frame.setBounds(this.posX, this.posY, this.sizeX, this.sizeY);
            
            Display display                     = new Display(500, 300, 10, 10, Color.WHITE, true);     //display
            List<Gcomponents> list_particles    = new ArrayList<>();                                    //lista com todas as particulas
            int ID_particula = 0;
            
            //............Construcao da primeira particula..................
            list_particles.add(build_first(400.0, 285.0));
            ID_particula++;
            //..............................................................
                
            do
            {
                //Condicao para a construcao das novas particulas sem interseccao
                if(list_particles.size() > 0){
                    
                    int x = (int)(400.0 * Math.random());
                    int y = (int)(285.0 * Math.random());
        
                    double[] par_ordenado = check_collision(list_particles, 400.0, 285.0, x, y);
                    list_particles.add(build((int) par_ordenado[0], (int) par_ordenado[1]));
                    ID_particula++;
                }
            
            }while(ID_particula < Main.total_particles);
                    
            paint_particles(list_particles, display);
            
            create_panel();
            
            chart = new Chart_LNG();
            cpanel = chart.create_chart("Gráfico de convergência", 760, 300);
            cpanel.setBounds(10, 330, 760, 300);
            
            this.frame.getContentPane().add(cpanel);
            
            this.frame.getContentPane().add(display.getPanel());
            this.frame.setVisible(visible);
            this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    
                    while(true)
                    {       
                        GUI.time++;
                        try {
                            
                            for(Gcomponents particula : list_particles)
                            {
                                comput_movement(particula, false);
                            }

                            if(cpanel != null){
                                cpanel.updateUI();
                                cpanel.repaint();
                            }
                            System.gc();
                            
                            Thread.sleep(2);
                            
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
        
        JPanel panel_detect = new JPanel();
        panel_detect.setLayout(null);
        panel_detect.setBounds(5, 100, 239, 190);
        panel_detect.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        panel_detect.setBackground(new Color(250, 250, 250));
        
        JLabel label_detect = new JLabel();
        label_detect.setText("Detector de partículas");
        label_detect.setBounds(10, 10, 200, 20);
        panel_detect.add(label_detect);
        
        label_detect_1 = new JLabel();
        label_detect_1.setText("Detector partícula vermelha: " + total_detect_1);
        label_detect_1.setBounds(10, 30, 200, 20);
        panel_detect.add(label_detect_1);
        
        label_detect_2 = new JLabel();
        label_detect_2.setText("Detector partícula preta: " + total_detect_2);
        label_detect_2.setBounds(10, 50, 200, 20);
        panel_detect.add(label_detect_2);

        panel_menu.add(panel_detect);
        
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
    private static int ID = 0;
    
    public static JLabel label_detect_1;
    public static JLabel label_detect_2;
    public static int total_detect_1 = 0;
    public static int total_detect_2 = 0;
    public static int total_de_sorteios = 0;
    
    public static ChartPanel cpanel;
    public static Chart_LNG chart;
    
    public static List<Double> lyP;
    
    public static List<Double> lyV;
    
    public static int time;
}

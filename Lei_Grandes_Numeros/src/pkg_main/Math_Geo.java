/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_main;

/**
 *
 * @author Igor
 */
public class Math_Geo {
    
    public Math_Geo(){
    }
    
    public double r(double x0, double x1, double y0, double y1){
       
        double dx = x1 - x0;
        double dy = y1 - y0;
        
        return Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2));
    }
}

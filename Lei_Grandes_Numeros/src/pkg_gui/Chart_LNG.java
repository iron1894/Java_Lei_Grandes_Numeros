/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import static pkg_gui.GUI.chart;

/**
 *
 * @author Igor
 */
public class Chart_LNG {

    public Chart_LNG() {
    }
    
    public DefaultCategoryDataset create_dataset(/*List<Double> x, List<Double> y, String legenda*/ ){
        
        this.dataset = new DefaultCategoryDataset();
        
        /*for(int i = 0; i < x.size(); i++){
            
            dataset.addValue(x.get(i), legenda, y.get(i));
        }*/
        
        return dataset;
    }
    
    public ChartPanel create_chart(String title, int sizeX, int sizeY){
               
        String str_x = "Tempo";
        String str_y = "Convergência";
        
        JFreeChart lineChart = ChartFactory.createLineChart(title, 
                                                            str_x, 
                                                            str_y, 
                                                            create_dataset(), 
                                                            PlotOrientation.VERTICAL,
                                                            true, true, false);
        
        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);

        final CategoryAxis categoryAxis = (CategoryAxis) plot.getDomainAxis();
        categoryAxis.setAxisLineVisible(false);
        categoryAxis.setTickMarksVisible(false);
        categoryAxis.setTickLabelsVisible(false);
        
        //ValueAxis range = plot.getRangeAxis();
        
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(300, 300));
        
        
        //
        
        return chartPanel;
       
    }

    public void setDataset(DefaultCategoryDataset dataset) {
        this.dataset = dataset;
    }

    public DefaultCategoryDataset getDataset() {
        return dataset;
    }
    
    
    
    public DefaultCategoryDataset dataset;
}   

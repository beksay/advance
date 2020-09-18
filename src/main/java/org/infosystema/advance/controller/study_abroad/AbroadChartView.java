package org.infosystema.advance.controller.study_abroad;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.enums.PersonStatus;
import org.infosystema.advance.service.PersonService;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
 
@ManagedBean
public class AbroadChartView implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
    @Inject
    private PersonService service;
    private Integer newStudents;
    private Integer alumniStudents;
    private Integer failedStudents;
    private Integer total;
 
    @PostConstruct
    public void init() {
    	
    	 List<FilterExample> examples=new ArrayList<>();
         examples.add(new FilterExample("status",PersonStatus.NEW,InequalityConstants.EQUAL));
         newStudents=service.countByExample(examples).intValue();
         examples=new ArrayList<>();
         examples.add(new FilterExample("status",PersonStatus.ALUMNI,InequalityConstants.EQUAL));
         alumniStudents=service.countByExample(examples).intValue();
         examples=new ArrayList<>();
         examples.add(new FilterExample("status",PersonStatus.FAILED,InequalityConstants.EQUAL));
         failedStudents=service.countByExample(examples).intValue();
         total=(int)service.countAll();
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Gender");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(total+1);
    }
     
    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Statistics");
        boys.set("TOTAL", total);
        boys.set("FAILED STUDENTS", failedStudents);
        boys.set("ALUMNI", alumniStudents);
        boys.set("NEW STUDENTS", newStudents);
 
 
        horizontalBarModel.addSeries(boys);
       
         
        horizontalBarModel.setTitle("STATISTICS");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
        
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("rthjrh");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("rthrthtrh");
        yAxis.setMin(0);
        yAxis.setMax(total+1);
    }
 
}
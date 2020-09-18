package org.infosystema.advance.controller.study_abroad;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.enums.VisaStatus;
import org.infosystema.advance.service.Step14Service;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
 
@ManagedBean
public class VisaChartView implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
    @Inject
    private Step14Service service;
    private Integer accepted;
    private Integer rejected;
    private Integer total;
 
    @PostConstruct
    public void init() {
    	
    	 List<FilterExample> examples=new ArrayList<>();
         examples.add(new FilterExample("visaStatus",VisaStatus.ACCEPTED,InequalityConstants.EQUAL));
         accepted=service.countByExample(examples).intValue();
         examples=new ArrayList<>();
         examples.add(new FilterExample("visaStatus",VisaStatus.REJECTED,InequalityConstants.EQUAL));
         rejected=service.countByExample(examples).intValue();
         examples=new ArrayList<>();
         examples.add(new FilterExample("visaStatus",Arrays.asList(VisaStatus.ACCEPTED,VisaStatus.REJECTED),InequalityConstants.IN));
         total=service.countByExample(examples).intValue();
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
        boys.setLabel("Visa Status");
        boys.set("TOTAL", total);
        boys.set("ACCEPTED", accepted);
        boys.set("REJECTED", rejected);
        boys.set("TOTAL", total);
 
 
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

	public Integer getAccepted() {
		return accepted;
	}

	public void setAccepted(Integer accepted) {
		this.accepted = accepted;
	}

	public Integer getRejected() {
		return rejected;
	}

	public void setRejected(Integer rejected) {
		this.rejected = rejected;
	}
 
}
package org.infosystema.advance.controller.study_abroad;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.domain.Dictionary;
import org.infosystema.advance.enums.PersonStatus;
import org.infosystema.advance.service.DictionaryService;
import org.infosystema.advance.service.PersonService;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
 
@ManagedBean
public class AbroadCountryChartView implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BarChartModel barModel;
    private HorizontalBarChartModel countryBarModel;
    @Inject
    private PersonService service;
    @Inject
    private DictionaryService dictService;
    private Integer total;
    
    //Countries
    private List<Dictionary> germanyList;
    private List<Dictionary> usaList;
    private List<Dictionary> koreaList;
    private List<Dictionary> ukList;
    private List<Dictionary> malaysiaList;
    private List<Dictionary> polandList;
    private List<Dictionary> czechList;
    private List<Dictionary> romaniaList;
    private List<Dictionary> canadaList;
    private Integer germany;
    private Integer usa;
    private Integer korea;
    private Integer uk;
    private Integer malaysia;
    private Integer poland;
    private Integer czech;
    private Integer romania;
    private Integer canada;
 
    @PostConstruct
    public void init() {
    	 List<FilterExample> list=new ArrayList<>();
         list.add(new FilterExample("id",115,InequalityConstants.EQUAL));
         germanyList = dictService.findByExample(0, 10, list);
         list=new ArrayList<>();
         list.add(new FilterExample("id",116,InequalityConstants.EQUAL));
         usaList = dictService.findByExample(0, 10, list);
         list=new ArrayList<>();
         list.add(new FilterExample("id",117,InequalityConstants.EQUAL));
         koreaList = dictService.findByExample(0, 10, list);
         list=new ArrayList<>();
         list.add(new FilterExample("id",118,InequalityConstants.EQUAL));
         ukList = dictService.findByExample(0, 10, list);
         list=new ArrayList<>();
         list.add(new FilterExample("id",119,InequalityConstants.EQUAL));
         malaysiaList = dictService.findByExample(0, 10, list);
         list=new ArrayList<>();
         list.add(new FilterExample("id",120,InequalityConstants.EQUAL));
         polandList = dictService.findByExample(0, 10, list);
         list=new ArrayList<>();
         list.add(new FilterExample("id",121,InequalityConstants.EQUAL));
         czechList = dictService.findByExample(0, 10, list);
         list=new ArrayList<>();
         list.add(new FilterExample("id",122,InequalityConstants.EQUAL));
         romaniaList = dictService.findByExample(0, 10, list);
         list=new ArrayList<>();
         list.add(new FilterExample("id",123,InequalityConstants.EQUAL));
         canadaList = dictService.findByExample(0, 10, list);
    	
    	 List<FilterExample> examples=new ArrayList<>();
         examples.add(new FilterExample("status",PersonStatus.NEW,InequalityConstants.EQUAL));
         examples.add(new FilterExample("countries",germanyList,InequalityConstants.MEMBER_OF));
         germany=service.countByExample(examples).intValue();
         examples=new ArrayList<>();
         examples.add(new FilterExample("status",PersonStatus.NEW,InequalityConstants.EQUAL));
         examples.add(new FilterExample("countries",usaList,InequalityConstants.MEMBER_OF));
         usa=service.countByExample(examples).intValue();
         examples=new ArrayList<>();
         examples.add(new FilterExample("status",PersonStatus.NEW,InequalityConstants.EQUAL));
         examples.add(new FilterExample("countries",koreaList,InequalityConstants.MEMBER_OF));
         korea=service.countByExample(examples).intValue();
         examples=new ArrayList<>();
         examples.add(new FilterExample("status",PersonStatus.NEW,InequalityConstants.EQUAL));
         examples.add(new FilterExample("countries",ukList,InequalityConstants.MEMBER_OF));
         uk=service.countByExample(examples).intValue();
         examples=new ArrayList<>();
         examples.add(new FilterExample("status",PersonStatus.NEW,InequalityConstants.EQUAL));
         examples.add(new FilterExample("countries",malaysiaList,InequalityConstants.MEMBER_OF));
         malaysia=service.countByExample(examples).intValue();
         examples=new ArrayList<>();
         examples.add(new FilterExample("status",PersonStatus.NEW,InequalityConstants.EQUAL));
         examples.add(new FilterExample("countries",polandList,InequalityConstants.MEMBER_OF));
         poland=service.countByExample(examples).intValue();
         examples=new ArrayList<>();
         examples.add(new FilterExample("status",PersonStatus.NEW,InequalityConstants.EQUAL));
         examples.add(new FilterExample("countries",czechList,InequalityConstants.MEMBER_OF));
         czech=service.countByExample(examples).intValue();
         examples=new ArrayList<>();
         examples.add(new FilterExample("status",PersonStatus.NEW,InequalityConstants.EQUAL));
         examples.add(new FilterExample("countries",romaniaList,InequalityConstants.MEMBER_OF));
         romania=service.countByExample(examples).intValue();
         examples=new ArrayList<>();
         examples.add(new FilterExample("status",PersonStatus.NEW,InequalityConstants.EQUAL));
         examples.add(new FilterExample("countries",canadaList,InequalityConstants.MEMBER_OF));
         canada=service.countByExample(examples).intValue();
         total=(int)service.countAll();
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
    
    public HorizontalBarChartModel getCountryBarModel() {
		return countryBarModel;
	}
 
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
        createCountryBarModel();
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
    
    private void createCountryBarModel() {
    	countryBarModel = new HorizontalBarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Statistics");
        boys.set("Romania", romania);
        boys.set("Czech Republic", czech);
        boys.set("Poland", poland);
        boys.set("Canada", canada);
        boys.set("Malaysia", malaysia);
        boys.set("United Kingdom", uk);
        boys.set("South Korea", korea);
        boys.set("USA", usa);
        boys.set("Germany", germany);
 
 
        countryBarModel.addSeries(boys);
       
         
        countryBarModel.setTitle("Statistics By Countries");
        countryBarModel.setLegendPosition("e");
        countryBarModel.setStacked(true);
        
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("rthjrh");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("rthrthtrh");
        yAxis.setMin(0);
        yAxis.setMax(total+1);
    }

	public List<Dictionary> getGermanyList() {
		return germanyList;
	}

	public void setGermanyList(List<Dictionary> germanyList) {
		this.germanyList = germanyList;
	}

	public List<Dictionary> getUsaList() {
		return usaList;
	}

	public void setUsaList(List<Dictionary> usaList) {
		this.usaList = usaList;
	}

	public List<Dictionary> getKoreaList() {
		return koreaList;
	}

	public void setKoreaList(List<Dictionary> koreaList) {
		this.koreaList = koreaList;
	}

	public Integer getUk() {
		return uk;
	}

	public void setUk(Integer uk) {
		this.uk = uk;
	}

	public Integer getMalaysia() {
		return malaysia;
	}

	public void setMalaysia(Integer malaysia) {
		this.malaysia = malaysia;
	}

	public Integer getPoland() {
		return poland;
	}

	public void setPoland(Integer poland) {
		this.poland = poland;
	}

	public Integer getCzech() {
		return czech;
	}

	public void setCzech(Integer czech) {
		this.czech = czech;
	}

	public Integer getRomania() {
		return romania;
	}

	public void setRomania(Integer romania) {
		this.romania = romania;
	}

	public Integer getCanada() {
		return canada;
	}

	public void setCanada(Integer canada) {
		this.canada = canada;
	}

	public List<Dictionary> getUkList() {
		return ukList;
	}

	public void setUkList(List<Dictionary> ukList) {
		this.ukList = ukList;
	}

	public List<Dictionary> getMalaysiaList() {
		return malaysiaList;
	}

	public void setMalaysiaList(List<Dictionary> malaysiaList) {
		this.malaysiaList = malaysiaList;
	}

	public List<Dictionary> getPolandList() {
		return polandList;
	}

	public void setPolandList(List<Dictionary> polandList) {
		this.polandList = polandList;
	}

	public List<Dictionary> getCzechList() {
		return czechList;
	}

	public void setCzechList(List<Dictionary> czechList) {
		this.czechList = czechList;
	}

	public List<Dictionary> getRomaniaList() {
		return romaniaList;
	}

	public void setRomaniaList(List<Dictionary> romaniaList) {
		this.romaniaList = romaniaList;
	}

	public List<Dictionary> getCanadaList() {
		return canadaList;
	}

	public void setCanadaList(List<Dictionary> canadaList) {
		this.canadaList = canadaList;
	}
 
}
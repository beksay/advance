package org.infosystema.advance.controller.report;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import net.sf.jasperreports.engine.JasperPrint;

import org.infosystema.advance.controller.report.BaseReportController;
import org.infosystema.advance.controller.report.JasperViewerController;


@RequestScoped
@ManagedBean
public class PersonViewReport extends BaseReportController {
	
	@Inject
	private JasperViewerController jasperViewerController;
	
	
	public void generatePerson(Integer personId) {
		System.out.println("generate");
		Map<String, Object> map = new HashMap<>();
		map = new HashMap<>();
		map.put("id", personId);
		System.out.println("=====id=====" + personId);
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = generateJasperPrint(map, ds.getConnection(), "person_info.jasper");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(jasperPrint);
				
		jasperViewerController.view(jasperPrint);
	}

}

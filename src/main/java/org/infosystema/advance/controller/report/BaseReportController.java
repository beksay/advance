package org.infosystema.advance.controller.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import org.infosystema.advance.util.ReportGenerator;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/***
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public abstract class BaseReportController extends ReportGenerator {
	
	@Resource(mappedName="java:jboss/datasources/advanceDS") 
	protected DataSource ds;
	
	protected StreamedContent getStream(JasperPrint jasperPrint) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		List<JasperPrint> jasperPrintList = new ArrayList<>();
		jasperPrintList.add(jasperPrint);
		
		JRPdfExporter exporter = new JRPdfExporter();
		
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);
		exporter.setConfiguration(configuration);

		try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		try {			
			MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
			InputStream stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			byteArrayOutputStream.close();
			String name = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Locale.ENGLISH).format(new Date()) + "report.pdf";
			return new DefaultStreamedContent(stream, mimeTypesMap.getContentType(name), name);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	
}
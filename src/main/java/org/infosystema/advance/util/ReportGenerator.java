package org.infosystema.advance.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.HtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleDocxExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsxExporterConfiguration;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class ReportGenerator {
	
	private static final String FORMAT_PDF = "pdf";
	private static final String FORMAT_XLS = "xls";
	private static final String FORMAT_XLSX = "xlsx";
	private static final String FORMAT_DOC = "doc";
	private static final String FORMAT_DOCX = "docx";
	private static final String FORMAT_HTML = "html";
	
	public InputStream pdf(Map<String, Object> map, Connection connection, String file) {
		System.out.println("generate");

		JasperPrint jasperPrint = generateJasperPrint(map, connection, file);
		InputStream stream = getStream(jasperPrint, FORMAT_PDF);
		
		return stream;
	}
	
	public InputStream html(Map<String, Object> map, Connection connection, String file) {
		System.out.println("generate");

		JasperPrint jasperPrint = generateJasperPrint(map, connection, file);
		InputStream stream = getStream(jasperPrint, FORMAT_HTML);
		
		return stream;
	}
	
	protected JasperPrint generateJasperPrint(Map<String, Object> map, Collection<Object> collection, String path){
		JasperPrint jasperPrint = null;
		InputStream inputStream = getStreamFromReports(path);
		
		Thread cur = Thread.currentThread();
		ClassLoader save = cur.getContextClassLoader();
		cur.setContextClassLoader(JasperCompileManager.class.getClassLoader());
		
		try {
			JasperReport report = (JasperReport)JRLoader.loadObject(inputStream);
			jasperPrint = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(collection)) ;
		} catch (Exception e) {
			if ((e instanceof JRException)) {
				JRException exception = (JRException)e;
				exception.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			cur.setContextClassLoader(save);
		}
		
		return jasperPrint;
	}
	
	protected JasperPrint generateJasperPrint(Map<String, Object> map, Connection connection, String path){
		JasperPrint jasperPrint = null;
		InputStream inputStream = getStreamFromReports(path);
		
		Thread cur = Thread.currentThread();
		ClassLoader save = cur.getContextClassLoader();
		cur.setContextClassLoader(JasperCompileManager.class.getClassLoader());
		
		try {
			JasperReport report = (JasperReport)JRLoader.loadObject(inputStream);
			jasperPrint = JasperFillManager.fillReport(report, map, connection) ;
		} catch (Exception e) {
			if ((e instanceof JRException)) {
				JRException exception = (JRException)e;
				exception.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			cur.setContextClassLoader(save);
			if(connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return jasperPrint;
	}
	
	private InputStream getStreamFromReports(String filename) {
		System.out.println("load jasper from " + "/WEB-INF/reports/" + filename);
		return this.getClass().getClassLoader().getResourceAsStream("/META-INF/reports/" + filename);
	}
	
	public InputStream getStream(JasperPrint jasperPrint, String format){
		System.out.println("format = " + format);
		switch (format) {
		case FORMAT_XLS:
			return getXLSStream(jasperPrint);
		case FORMAT_XLSX:
			return getXLSXStream(jasperPrint);
		case FORMAT_HTML:
			return getHTMLStream(jasperPrint);
		case FORMAT_DOC:
		case FORMAT_DOCX:
			return getDOCXStream(jasperPrint);
		default:
			return getPDFStream(jasperPrint);
		}
	}
	
	private InputStream getDOCXStream(JasperPrint jasperPrint){
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		List<JasperPrint> jasperPrintList = new ArrayList<>();
		jasperPrintList.add(jasperPrint);
		
		JRDocxExporter exporter = new JRDocxExporter();
		
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
		SimpleDocxExporterConfiguration configuration = new SimpleDocxExporterConfiguration();
		exporter.setConfiguration(configuration);

		try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		try {			
			InputStream stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			byteArrayOutputStream.close();
			return stream;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private InputStream getXLSXStream(JasperPrint jasperPrint){
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		List<JasperPrint> jasperPrintList = new ArrayList<>();
		jasperPrintList.add(jasperPrint);
		
		JRXlsxExporter exporter = new JRXlsxExporter();
		
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
		SimpleXlsxExporterConfiguration configuration = new SimpleXlsxExporterConfiguration();
		exporter.setConfiguration(configuration);

		try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		try {			
			InputStream stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			byteArrayOutputStream.close();
			return stream;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private InputStream getXLSStream(JasperPrint jasperPrint){
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		List<JasperPrint> jasperPrintList = new ArrayList<>();
		jasperPrintList.add(jasperPrint);
		
		JRXlsExporter exporter = new JRXlsExporter();
		
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
		SimpleXlsExporterConfiguration configuration = new SimpleXlsExporterConfiguration();
		exporter.setConfiguration(configuration);

		try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		try {			
			InputStream stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			byteArrayOutputStream.close();
			return stream;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private InputStream getPDFStream(JasperPrint jasperPrint){
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
			InputStream stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			byteArrayOutputStream.close();
			return stream;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private InputStream getHTMLStream(JasperPrint jasperPrint){
		System.out.println("generate to html.");
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		HtmlExporter exporter = new HtmlExporter();

		exporter.setConfiguration(createHtmlConfiguration());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(byteArrayOutputStream));

		try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		try {	
			InputStream stream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			byteArrayOutputStream.close();
			return stream;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HtmlExporterConfiguration createHtmlConfiguration() {
		SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
		
		configuration.setHtmlFooter("");
		configuration.setHtmlHeader("");
		
		return configuration;
	}

	public String getFormat(String format) {
		switch (format) {
		case FORMAT_XLS:
			return FORMAT_XLS;
		case FORMAT_XLSX:
			return FORMAT_XLSX;
		case FORMAT_DOC:
			return FORMAT_DOC;
		case FORMAT_DOCX:
			return FORMAT_DOCX;
		case FORMAT_HTML:
			return FORMAT_HTML;
		default:
			return FORMAT_PDF;
		}
	}

}

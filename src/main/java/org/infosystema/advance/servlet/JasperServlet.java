package org.infosystema.advance.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.infosystema.advance.controller.report.JasperViewerController;
import org.infosystema.advance.util.ReportGenerator;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@WebServlet(urlPatterns = {"/jasper"})
public class JasperServlet extends HttpServlet {

	private static final long serialVersionUID = -7626497440520150364L;
	private ReportGenerator generator = new ReportGenerator();
	
	public JasperServlet() {}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String type = req.getParameter("type");
			String k = req.getParameter("k");
			
			if(type == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			if(k == null || !k.equals("inline")) k = "attachment";
			
		    JasperPrint jasperPrint = (JasperPrint)req.getSession().getAttribute(JasperViewerController.REPORT_NAME);
		    
			MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
			String format = generator.getFormat(type);
			InputStream stream = generator.getStream(jasperPrint, format);
			String contentType = mimeTypesMap.getContentType("test." + format);
			DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd_hh-mm");
			
			System.out.println("contentType = " + contentType);
			
			resp.setContentType("application/pdf");
		    resp.setHeader("Content-Disposition", k + "; filename=\"report_" + dateFormat.format(new Date()) + "." + format + "\";");
		    resp.setContentLength(stream.available());
		    OutputStream os = resp.getOutputStream();
		    
		    try {
		        IOUtils.copy(stream, os);
		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		        os.close();
		        stream.close();
		    }
		} catch(Exception e){
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}


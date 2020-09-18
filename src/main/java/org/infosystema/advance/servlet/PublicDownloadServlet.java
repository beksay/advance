package org.infosystema.advance.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.MimetypesFileTypeMap;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.dto.DataResponse;
import org.infosystema.advance.service.AttachmentService;
import org.infosystema.advance.soa.RepositoryServiceFactory;
import org.infosystema.advance.util.Translit;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@WebServlet(urlPatterns = {"/public_download"})
public class PublicDownloadServlet extends HttpServlet {
	
	@EJB
	private AttachmentService service;

	private static final long serialVersionUID = -7626497440520150364L;
	
	public PublicDownloadServlet() {}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String idString = req.getParameter("id");
			
		    Integer id = new Integer(idString);
		    InputStream stream = null;
		    String contentType = null;
		    String fileName = null;
		    
		    if(id.intValue() == 0) {
		    	stream = this.getClass().getClassLoader().getResourceAsStream("META-INF/watsapp.png");
		    	contentType = "application/png";
		    	fileName = "watsapp.png";
		    }
		    else {
		    	Attachment attachment = service.findById(id, false);

				DataResponse response = RepositoryServiceFactory.getInstance().getService().getData(attachment.getRepositoryName());
				stream = response.getData().getInputStream();
				MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
				contentType = mimeTypesMap.getContentType(attachment.getFileName());
				fileName = attachment.getFileName();
		    }
		    
			resp.setContentType(contentType);
		    resp.setHeader("Content-Disposition", "attachment; filename=\"" + Translit.translit(fileName) + "\";");
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

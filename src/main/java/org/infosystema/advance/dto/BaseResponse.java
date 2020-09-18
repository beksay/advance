package org.infosystema.advance.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@XmlRootElement(name="response")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BaseResponse extends AbstractResponse {

	private static final long serialVersionUID = -2787102103110567642L;
	
	public BaseResponse() {}
	
	public BaseResponse(Integer code){
		super(code);
	}
	
	public BaseResponse(Integer code, String comment){
		super(code, comment);
	}

}


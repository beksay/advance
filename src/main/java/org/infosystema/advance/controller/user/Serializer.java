package org.infosystema.advance.controller.user;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.commons.codec.binary.Base64;
import org.infosystema.advance.annotation.Logged;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Named
@RequestScoped
@Logged
public class Serializer {
	
	public String serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return Base64.encodeBase64String(b.toByteArray());
    }

    public Object deserialize(String value) throws IOException, ClassNotFoundException {
    	byte[] bytes = Base64.decodeBase64(value);
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }
}

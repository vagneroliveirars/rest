package br.com.geladaonline.services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Application;

/**
 * This class sets up the JAX-RS application
 *  
 * @author vagner
 *
 */
public class ApplicationJAXRS extends Application {

	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("jersey.config.server.provider.packages", "br.com.geladaonline.services");
		
		return properties;
	}
	
}
package br.com.geladaonline.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

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
	
	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		singletons.add(new JettisonFeature());
		singletons.add(new MultiPartFeature());
		return singletons;
	}
	
}
package br.com.geladaonline.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

/**
 * This class sets up the JAX-RS application
 *  
 * @author vagner
 *
 */
public class ApplicationJAXRS extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(TesteService.class);
		
		return classes;
	}	
	
}

package br.com.geladaonline.services;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.jettison.JettisonFeature;

/**
 * This class sets up the JAX-RS application
 *  
 * @author vagner
 *
 */
public class ApplicationJAXRS extends Application {
	
	public ApplicationJAXRS() {
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setTitle("API cervejaria");
		beanConfig.setDescription("This API manages all the beers");
		beanConfig.setVersion("0.0.1");
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/cervejaria/services");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setResourcePackage("br.com.geladaonline.services");
		beanConfig.setScan(true);
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();

		// Swagger classes
		resources.add(ApiListingResource.class);
		resources.add(SwaggerSerializers.class);
		
		return resources;
	}

	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("jersey.config.server.provider.packages", "br.com.geladaonline.services");
		properties.put("jersey.config.server.wadl.generatorConfig", WADLConfig.class.getCanonicalName());
		return properties;
	}
	
	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		singletons.add(new JettisonFeature());
		return singletons;
	}
	
}
package br.com.geladaonline.client;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jettison.JettisonFeature;

import br.com.geladaonline.model.rest.Cervejas;

public class Client {

	public static void main(String[] args) {
		SslConfigurator sslConfigurator = SslConfigurator
				.newInstance()
				.trustStoreFile(System.getProperty("user.dir") + "/src/main/java/br/com/geladaonline/client/server.keystore")
				.trustStorePassword("cervejaria");
		
		SSLContext context = sslConfigurator.createSSLContext();
		
		javax.ws.rs.client.Client client = ClientBuilder.newBuilder()
				.sslContext(context)
				.build();
		
		HttpAuthenticationFeature auth = HttpAuthenticationFeature.basic("admin", "123");
		
		client.register(auth).register(JettisonFeature.class);
		
		Cervejas cervejas = client
				.target("https://localhost:8443/cervejaria/services")
				.path("/cervejas")
				.request(MediaType.APPLICATION_JSON)
				.get(Cervejas.class);
						
		System.out.println(cervejas);
	}

}
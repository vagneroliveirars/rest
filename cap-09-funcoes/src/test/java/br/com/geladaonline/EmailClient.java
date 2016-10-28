package br.com.geladaonline;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

public class EmailClient {

	public static void main(String[] args) throws URISyntaxException {
		// Sends a simple e-mail without attachments
		ClientBuilder.newClient()
			.register(MultiPartFeature.class)
			.target("http://localhost:8080/cervejaria/services/email")
			.request()
			.header("To", "vagner.oliveirars@gmail.com")
			.header("Cc", "fake@fake.com")
			.header("Bcc", "fake2@fake.com")
			.header("Subject", "Olá, mundo!")
			.post(Entity.text("Teste de envio de mensagens de email utilizando JAX-RS"));
		
		// Sends an e-mail with attachments
		MultiPart multiPart = new MultiPart();
		URI uriDoArquivo = EmailClient.class.getResource("/Erdinger Weissbier.jpg").toURI();
		File arquivo = new File(uriDoArquivo);
		multiPart.bodyPart("Mensagem com anexos", MediaType.TEXT_PLAIN_TYPE)
			.bodyPart(new FileDataBodyPart("imagem", arquivo));
		
		ClientBuilder
			.newClient()
			.register(MultiPartFeature.class)
			.target("http://localhost:8080/cervejaria/services/email")
			.request()
			.header("To", "alesaudate@gmail.com")
			.header("Cc", "fake@fake.com")
			.header("Bcc", "fake2@fake.com")
			.header("Subject", "Olá, mundo!")
			.post(Entity.entity(multiPart, multiPart.getMediaType()));
	}

}

package br.com.geladaonline.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.MultiPart;

import br.com.geladaonline.model.Anexo;
import br.com.geladaonline.model.Email;

/**
 * This class is a REST service to send e-mails
 * 
 * @author vagner
 *
 */
@Path("/email")
public class EmailService {
	
	/**
	 * Sends a simple e-mail without attachments
	 * 
	 * @param para
	 * @param comCopia
	 * @param comCopiaOculta
	 * @param assunto
	 * @param mensagem
	 * @param httpHeaders
	 */
	@POST
	@Consumes({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
	public void enviarEmailSimples(@HeaderParam("To") String para,
			@HeaderParam("Cc") String comCopia,
			@HeaderParam("Bcc") String comCopiaOculta, 
			@HeaderParam("Subject") String assunto,
			String mensagem,
			@Context HttpHeaders httpHeaders) {

		Email email = new Email()
			.withDestinatario(para)
			.withComCopia(comCopia)
			.withComCopiaOculta(comCopiaOculta)
			.withAssunto(assunto)
			.withMensagem(mensagem, httpHeaders.getMediaType().toString());
		email.enviar();
	
	}
	
	/**
	 * Sends an e-mail with attachments
	 * 
	 * @param para
	 * @param comCopia
	 * @param comCopiaOculta
	 * @param assunto
	 * @param multiPart
	 */
	@POST
	@Consumes("multipart/mixed")
	public void enviarEmailAnexos(
			@HeaderParam("To") String para,
			@HeaderParam("Cc") String comCopia,
			@HeaderParam("Bcc") String comCopiaOculta, 
			@HeaderParam("Subject") String assunto,
			MultiPart multiPart) {
		
		Email email = new Email();
		email.withDestinatario(para)
			.withAssunto(assunto)
			.withComCopia(comCopia)
			.withComCopiaOculta(comCopiaOculta);
		for (BodyPart bodyPart : multiPart.getBodyParts()) {
			String mediaType = bodyPart.getMediaType().toString();
			if (mediaType.startsWith("text/plain") || mediaType.startsWith("text/html")) {
				email = email.withMensagem(bodyPart.getEntityAs(String.class), mediaType);
			}
			else {
				Anexo anexo = new Anexo(bodyPart.getEntityAs(byte[].class), bodyPart.getMediaType().toString());
				email = email.withAnexo(anexo);
			}
		}
		
		email.enviar();
	}

}

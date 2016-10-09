package br.com.geladaonline.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.com.geladaonline.exception.CervejaJaExisteException;
import br.com.geladaonline.model.Cerveja;
import br.com.geladaonline.model.Estoque;
import br.com.geladaonline.model.rest.Cervejas;

/**
 * This class is a REST service for beers
 * 
 * @author vagner
 *
 */
@Path("/cervejas")
@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
public class CervejaService {

	private static Estoque estoque = new Estoque();
	
	private static final int TAMANHO_PAGINA = 10;
	private static Map<String, String> EXTENSOES;
	
	static {
		EXTENSOES = new HashMap<String, String>();
		EXTENSOES.put("image/jpg", ".jpg");
	}
	
	/**
	 * Returns all the beers with pagination
	 * 
	 * @return {@link Cervejas}
	 */
	@GET
	public Cervejas listaTodasAsCervejas(@QueryParam("pagina") int pagina) {
		List<Cerveja> cervejas = estoque.listarCervejas(pagina, TAMANHO_PAGINA);
		
		return new Cervejas(cervejas);
	}
	
	/**
	 * Returns a beer by name
	 * 
	 * @param nomeDaCerveja
	 * @return {@link Cerveja}
	 */
	@GET
	@Path("{nome}")
	public Cerveja encontreCerveja(@PathParam("nome") String nomeDaCerveja) {
		Cerveja cerveja = estoque.recuperarCervejaPeloNome(nomeDaCerveja);
		
		if (cerveja != null) {
			return cerveja;
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
	}
	
	/**
	 * Creates a new beer
	 * 
	 * @param cerveja
	 * @return
	 */
	@POST
	public Response criarCerveja(Cerveja cerveja) {
		try {
			estoque.adicionarCerveja(cerveja);
		} catch (CervejaJaExisteException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}
		
		URI uri = UriBuilder.fromPath("cervejas/{nome}").build(cerveja.getNome());
		
		return Response.created(uri).entity(cerveja).build();
	}
	
	/**
	 * Updates a beer
	 * 
	 * @param nome
	 * @param cerveja
	 */
	@PUT
	@Path("{nome}")
	public void atualizarCerveja(@PathParam("nome") String nome, Cerveja cerveja) {
		encontreCerveja(nome);
		cerveja.setNome(nome);
		estoque.atualizarCerveja(cerveja);
	}
	
	/**
	 * Deletes a beer
	 * 
	 * @param nome
	 */
	@DELETE
	@Path("{nome}")
	public void apagarCerveja(@PathParam("nome") String nome) {
		estoque.apagarCerveja(nome);
	}
	
	@GET
	@Path("{nome}")
	@Produces("image/*")
	public Response recuperaImagem(@PathParam("nome") String nomeDaCerveja) throws IOException {
		InputStream is = CervejaService.class.getResourceAsStream("/" + nomeDaCerveja + ".jpg");
		
		if (is == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
		byte[] dados = new byte[is.available()];
		is.read(dados);
		is.close();
		
		return Response.ok(dados).type("image/jpg").build();
	}
	
	@POST
	@Path("{nome}")
	@Consumes("image/*")
	public Response criaImagem(@PathParam("nome") String nomeDaImagem,
			@Context HttpServletRequest req, byte[] dados) 
		throws IOException, InterruptedException {

		String userHome = System.getProperty("user.home");
		String mimeType = req.getContentType();
		FileOutputStream fos = new FileOutputStream(userHome + File.separator
				+ nomeDaImagem + EXTENSOES.get(mimeType));
		
		fos.write(dados);
		fos.flush();
		fos.close();
		
		return Response.ok().build();
	}
	
}

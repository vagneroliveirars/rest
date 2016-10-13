package br.com.geladaonline.services;

import java.io.IOException;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.geladaonline.model.Cerveja;
import br.com.geladaonline.model.rest.Cervejas;

/**
 * This class is an interface for beers service
 * 
 * @author vagner
 *
 */
@Path("/cervejas")
@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
public interface CervejaService {
	
	/**
	 * Returns all the beers with pagination
	 * 
	 * @return {@link Cervejas}
	 */
	@GET
	public Cervejas listaTodasAsCervejas(@QueryParam("pagina") int pagina);
	
	/**
	 * Returns a beer by name
	 * 
	 * @param nomeDaCerveja
	 * @return {@link Cerveja}
	 */
	@GET
	@Path("{nome}")
	public Cerveja encontreCerveja(@PathParam("nome") String nomeDaCerveja);
	
	/**
	 * Creates a new beer
	 * 
	 * @param cerveja
	 * @return
	 */
	@POST
	public Response criarCerveja(Cerveja cerveja);
	
	/**
	 * Updates a beer
	 * 
	 * @param nome
	 * @param cerveja
	 */
	@PUT
	@Path("{nome}")
	public void atualizarCerveja(@PathParam("nome") String nome, Cerveja cerveja);
	
	/**
	 * Deletes a beer
	 * 
	 * @param nome
	 */
	@DELETE
	@Path("{nome}")
	public void apagarCerveja(@PathParam("nome") String nome);
	
	/**
	 * Returns an image by beer name
	 * 
	 * @param nomeDaCerveja
	 * @return Response with image binary data
	 * @throws IOException
	 */
	@GET
	@Path("{nome}")
	@Produces("image/*")
	public Response recuperaImagem(@PathParam("nome") String nomeDaCerveja) throws IOException;

	/**
	 * Creates a new image in the user home directory
	 * 
	 * @param nomeDaImagem
	 * @param req
	 * @param dados
	 * @return Response
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@POST
	@Path("{nome}")
	@Consumes("image/*")
	public Response criaImagem(@PathParam("nome") String nomeDaImagem,
			@Context HttpServletRequest req, byte[] dados) 
		throws IOException, InterruptedException;

}
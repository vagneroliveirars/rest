package br.com.geladaonline.services;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;

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
@Consumes({MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_XML})
public class CervejaService {

	private static Estoque estoque = new Estoque();
	
	/**
	 * Return all the beers
	 * 
	 * @return {@link Cervejas}
	 */
	@GET
	public Cervejas listaTodasAsCervejas() {
		Cervejas cervejas = new Cervejas();
		cervejas.setCervejas(new ArrayList<>(estoque.listarCervejas()));
		
		return cervejas;
	}
	
	/**
	 * Search a beer by name
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
	
	@PUT
	@Path("{nome}")
	public void atualizarCerveja(@PathParam("nome") String nome, Cerveja cerveja) {
		encontreCerveja(nome);
		cerveja.setNome(nome);
		estoque.atualizarCerveja(cerveja);
	}
	
	@DELETE
	@Path("{nome}")
	public void apagarCerveja(@PathParam("nome") String nome) {
		estoque.apagarCerveja(nome);
	}
	
}

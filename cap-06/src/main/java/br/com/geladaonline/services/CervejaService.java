package br.com.geladaonline.services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import br.com.geladaonline.model.Cerveja;
import br.com.geladaonline.model.Estoque;
import br.com.geladaonline.model.rest.Cervejas;

@Path("/cervejas")
@Consumes({MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_XML})
public class CervejaService {

	private static Estoque estoque = new Estoque();
	
	@GET
	public Cervejas listaTodasAsCervejas() {
		Cervejas cervejas = new Cervejas();
		cervejas.setCervejas(new ArrayList<>(estoque.listarCervejas()));
		
		return cervejas;
	}
	
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
	
}

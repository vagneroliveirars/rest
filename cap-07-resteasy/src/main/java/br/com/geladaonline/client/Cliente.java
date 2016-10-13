package br.com.geladaonline.client;

import javax.ws.rs.client.ClientBuilder;

import org.jboss.resteasy.client.jaxrs.ProxyBuilder;

import br.com.geladaonline.model.rest.Cervejas;
import br.com.geladaonline.services.CervejaService;

/**
 * This class is a REST client that retrieves beers
 * 
 * @author vagner
 *
 */
public class Cliente {

	public static void main(String[] args) {
		ProxyBuilder<CervejaService> proxyBuilder = ProxyBuilder.builder(
				CervejaService.class,
				ClientBuilder.newClient().target(Constants.HOST));
		
		CervejaService service = proxyBuilder.build();
		
		Cervejas cervejas = service.listaTodasAsCervejas(0);
		
		System.out.println(cervejas.getLinks().get(0));
	}

}

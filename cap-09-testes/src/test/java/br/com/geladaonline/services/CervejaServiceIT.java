package br.com.geladaonline.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import br.com.geladaonline.model.Cerveja;
import br.com.geladaonline.model.Cervejas;
import br.com.geladaonline.model.Constants;
import static org.junit.Assert.*;

/**
 * Test class for the beer service
 * 
 * @author vagner
 *
 */
public class CervejaServiceIT extends JerseyTest {
	
	@Override
	protected Application configure() {
		return new ApplicationJAXRS();
	}
	
	/**
	 * Retrieves all the beers
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testeCenarioFeliz() throws UnsupportedEncodingException {
		Cervejas cervejas = target("/cervejas")
				.register(JettisonFeature.class)
				.request().get(Cervejas.class);
		
		assertNotNull(cervejas);
		assertNotNull(cervejas.getLinks());
		assertFalse(cervejas.getLinks().isEmpty());
		assertEquals(2, cervejas.getLinks().size());
		
		Link link1 = cervejas.getLinks().get(0);
		Link link2 = cervejas.getLinks().get(1);
		
		String caminhoCompleto = Constants.CAMINHO_COMPLETO;
		
		assertEquals(caminhoCompleto + "cervejas/Erdinger Weissbier", URLDecoder.decode(link1.getUri().toASCIIString(), "UTF-8"));
		
		assertEquals(caminhoCompleto + "cervejas/Stella Artois", URLDecoder.decode(link2.getUri().toASCIIString(), "UTF-8"));
		
		assertEquals("cerveja", link1.getRel());
		assertEquals("cerveja", link2.getRel());
		
		assertEquals("Erdinger Weissbier", link1.getTitle());
		assertEquals("Stella Artois", link2.getTitle());
		
		Cerveja erdinger = target("cervejas/Erdinger Weissbier")
				.register(JettisonFeature.class)
				.request(MediaType.APPLICATION_XML).get(Cerveja.class);
		
		assertNotNull(erdinger);
		assertEquals("Erdinger Weissbier", erdinger.getNome());
		assertEquals("Erdinger Weissbrau", erdinger.getCervejaria());
		assertEquals("Cerveja de trigo alem�", erdinger.getDescricao());
		assertEquals(Cerveja.Tipo.WEIZEN, erdinger.getTipo());
		
		Cerveja stella = target("cervejas/Stella Artois")
				.register(JettisonFeature.class)
				.request(MediaType.APPLICATION_XML).get(Cerveja.class);
		
		assertNotNull(stella);
		assertEquals("Stella Artois", stella.getNome());
		assertEquals("Artois", stella.getCervejaria());
		assertEquals("A cerveja belga mais francesa do mundo", stella.getDescricao());
		assertEquals(Cerveja.Tipo.LAGER, stella.getTipo());
	}
	
	/**
	 * Creates a new beer
	 */
	@Test
	public void testeInsereCerveja() {
		Cerveja skol = new Cerveja("Skol", "Cerveja brasileira", "Ambev", Cerveja.Tipo.PILSEN);
		
		Response response = target("/cervejas").request().post(Entity.xml(skol));
		
		assertNotNull(response);
		assertEquals(201, response.getStatus());
		assertNotNull(response.getLocation());
		
		assertTrue(response.getLocation().toASCIIString().endsWith("/cervejas/Skol"));
		
		skol = target("/cervejas/Skol")
				.request(MediaType.APPLICATION_XML)
				.get(Cerveja.class);
		
		assertEquals("Skol", skol.getNome());
		assertEquals("Ambev", skol.getCervejaria());
		assertEquals("Cerveja brasileira", skol.getDescricao());
		assertEquals(Cerveja.Tipo.PILSEN, skol.getTipo());
	}

}

package br.com.geladaonline.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is a stock of beers
 * 
 * @author vagner
 *
 */
public class Estoque {
	
	private Map<String, Cerveja> cervejas = new HashMap<String, Cerveja>();
	
	public Estoque() {
		Cerveja primeiraCerveja = new Cerveja("Stella Artois",
				"A cerveja belga mais francesa do mundo", "Artois",
				Cerveja.Tipo.LAGER);
		
		Cerveja segundaCerveja = new Cerveja("Erdinger Weissbier",
				"Cerveja de trigo Alemã", "Erdinger Weissbrau",
				Cerveja.Tipo.WEIZEN);
		
		this.cervejas.put("Stella Artois", primeiraCerveja);
		this.cervejas.put("Erdinger Weissbier", segundaCerveja);
	}
	
	public Collection<Cerveja> listarCervejas() {
		return new ArrayList<Cerveja>(this.cervejas.values());
	}
	
	public void adicionarCerveja(Cerveja cerveja) {
		this.cervejas.put(cerveja.getNome(), cerveja);
	}
	
	public Cerveja recuperarCervejaPeloNome(String nome) {
		return this.cervejas.get(nome);
	}

}

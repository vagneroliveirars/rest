package br.com.geladaonline.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is a stock of beers
 * 
 * @author vagner
 *
 */
public class Estoque {
	
	private Collection<Cerveja> cervejas = new ArrayList<Cerveja>();
	
	public Estoque() {
		Cerveja primeiraCerveja = new Cerveja("Stella Artois",
				"A cerveja belga mais francesa do mundo", "Artois",
				Cerveja.Tipo.LAGER);
		
		Cerveja segundaCerveja = new Cerveja("Erdinger Weissbier",
				"Cerveja de trigo Alemã", "Erdinger Weissbrau",
				Cerveja.Tipo.WEIZEN);
		
		this.cervejas.add(primeiraCerveja);
		this.cervejas.add(segundaCerveja);
	}
	
	public Collection<Cerveja> listarCervejas() {
		return new ArrayList<Cerveja>(this.cervejas);
	}
	
	public void adicionarCerveja(Cerveja cerveja) {
		this.cervejas.add(cerveja);
	}

}

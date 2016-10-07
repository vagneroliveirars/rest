package br.com.geladaonline.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.geladaonline.exception.CervejaJaExisteException;
import br.com.geladaonline.exception.CervejaNaoEncontradaException;

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
		if (this.cervejas.containsKey(cerveja.getNome())) {
			throw new CervejaJaExisteException();
		}
		
		this.cervejas.put(cerveja.getNome(), cerveja);
	}
	
	public Cerveja recuperarCervejaPeloNome(String nome) {
		return this.cervejas.get(nome);
	}
	
	public void atualizarCerveja(Cerveja cerveja) {
		if (!this.cervejas.containsKey(cerveja.getNome())) {
			throw new CervejaNaoEncontradaException();
		}
		cervejas.put(cerveja.getNome(), cerveja);
	}
	
	public void apagarCerveja(String nomeCerveja) {
		this.cervejas.remove(nomeCerveja);
	}

}

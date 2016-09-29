package br.com.geladaonline.model.rest;

import java.util.ArrayList;
import java.util.List;

import br.com.geladaonline.model.Cerveja;

/**
 * This class wraps {@link Cerveja}
 * 
 * @author vagner
 *
 */
public class Cervejas {
	
	private List<Cerveja> cervejas = new ArrayList<Cerveja>();
	
	public List<Cerveja> getCervejas() {
		return cervejas;
	}
	
	public void setCervejas(List<Cerveja> cervejas) {
		this.cervejas = cervejas;
	}

}

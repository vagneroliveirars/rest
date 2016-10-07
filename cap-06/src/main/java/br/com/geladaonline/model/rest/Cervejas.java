package br.com.geladaonline.model.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.geladaonline.model.Cerveja;

/**
 * This class wraps {@link Cerveja}
 * 
 * @author vagner
 *
 */
@XmlRootElement
public class Cervejas {
	
	private List<Cerveja> cervejas = new ArrayList<Cerveja>();
	
	public Cervejas() {}
	
	public Cervejas(List<Cerveja> cervejas) {
		this.cervejas = cervejas;
	}

	@XmlElement(name = "cerveja")
	public List<Cerveja> getCervejas() {
		return cervejas;
	}
	
	public void setCervejas(List<Cerveja> cervejas) {
		this.cervejas = cervejas;
	}

}

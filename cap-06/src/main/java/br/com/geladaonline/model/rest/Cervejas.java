package br.com.geladaonline.model.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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

	@XmlTransient
	public List<Cerveja> getCervejas() {
		return cervejas;
	}
	
	public void setCervejas(List<Cerveja> cervejas) {
		this.cervejas = cervejas;
	}
	
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<Link>();
		
		for (Cerveja cerveja : cervejas) {
			Link link = Link.fromPath("cervejas/{nome}")
					.rel("cerveja")
					.title(cerveja.getNome())
					.build(cerveja.getNome());
			
			links.add(link);
		}
		
		return links;
	}
	
	public void setLinks(List<Link> links) {}

}

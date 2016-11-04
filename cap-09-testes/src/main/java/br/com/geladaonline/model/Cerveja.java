package br.com.geladaonline.model;

import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents a beer
 * 
 * @author vagner
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cerveja {

	private String nome;
	private String descricao;
	private String cervejaria;
	private Tipo tipo;
	
	public Cerveja() {}

	public Cerveja(String nome, String descricao, String cervejaria, Tipo tipo) {
		this.nome = nome;
		this.descricao = descricao;
		this.cervejaria = cervejaria;
		this.tipo = tipo;
	}

	public enum Tipo {
		LAGER, PILSEN, PALE_ALE, INDIAN_PALE_ALE, WEIZEN;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCervejaria() {
		return cervejaria;
	}

	public void setCervejaria(String cervejaria) {
		this.cervejaria = cervejaria;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return this.nome + " - " + this.descricao;
	}
	
	public boolean matchExemplo(Cerveja cerveja) {
		boolean match = true;
		match &= matchRegex(cerveja.nome, this.nome);
		match &= matchRegex(cerveja.descricao, this.descricao);
		match &= matchRegex(cerveja.cervejaria, this.cervejaria);
		match &= this.tipo != null ? matchRegex(cerveja.tipo.name(),
				this.tipo.name()) : true;

		return match;
	}

	private boolean matchRegex(String toCompare, String source) {
		if (source != null) {
			return Pattern.compile(source).matcher(toCompare).find();
		}
		return true;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private Cerveja building;

		public Builder() {
			building = new Cerveja();
			building.nome = "";
			building.descricao = "";
			building.cervejaria = "";
			building.tipo = null;
		}

		public Builder withNome(String nome) {
			building.nome = nome;
			return this;
		}

		public Builder withDescricao(String descricao) {
			building.descricao = descricao;
			return this;
		}

		public Builder withCervejaria(String cervejaria) {
			building.cervejaria = cervejaria;
			return this;
		}

		public Builder withTipo(Tipo tipo) {
			building.tipo = tipo;
			return this;
		}

		public Builder withTipo(String tipo) {
			if (tipo == null || tipo.trim().equals("")) {
				return this;
			}
			building.tipo = Tipo.valueOf(tipo);
			return this;
		}

		public Cerveja build() {
			return building;
		}
	}

}

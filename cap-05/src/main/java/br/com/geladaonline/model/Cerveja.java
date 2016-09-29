package br.com.geladaonline.model;

/**
 * This class represents a beer
 * 
 * @author vagner
 * 
 */
public class Cerveja {

	private String nome;
	private String descricao;
	private String cervejaria;
	private Tipo tipo;

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

}

package br.com.geladaonline.modelo.pessoa;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class represents a person
 * 
 * @author vagner
 *
 */
@XmlSeeAlso({ PessoaFisica.class })
public abstract class Pessoa {

	private Long id;
	private String nome;
	private List<Endereco> endereco;

	@XmlAttribute(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

}

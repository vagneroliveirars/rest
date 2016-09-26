package br.com.geladaonline.parsers;

import java.util.ArrayList;
import java.util.List;

import br.com.geladaonline.modelo.pessoa.Endereco;
import br.com.geladaonline.modelo.pessoa.PessoaFisica;

/**
 * This class is a Parser that creates an instance of {@link PessoaFisica}
 * 
 * @author vagner
 * 
 */
public abstract class Parser {
	
	public static PessoaFisica criarPessoaFisicaTeste() {
		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setId(1l);;
		pessoaFisica.setNome("Alexandre");
		pessoaFisica.setCpf("123.456.789-00");
		
		Endereco endereco = new Endereco();
		endereco.setCep("12345-678");
		endereco.setLogradouro("Rua Um");
		
		List<Endereco> enderecos = new ArrayList<Endereco>();
		enderecos.add(endereco);
		
		pessoaFisica.setEndereco(enderecos);
		
		return pessoaFisica;	
	}

}

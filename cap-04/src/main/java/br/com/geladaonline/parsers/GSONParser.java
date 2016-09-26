package br.com.geladaonline.parsers;

import br.com.geladaonline.modelo.pessoa.PessoaFisica;

import com.google.gson.Gson;

/**
 * This class converts an instance of {@link PessoaFisica} in JSON using a GSON
 * Parser
 * 
 * @author vagner
 * 
 */
public class GSONParser {

	public static void main(String[] args) {
		PessoaFisica pessoaFisica = Parser.criarPessoaFisicaTeste();
		Gson gson = new Gson();
		System.out.println(gson.toJson(pessoaFisica));
	}

}

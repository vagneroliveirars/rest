package br.com.geladaonline.parsers;

import br.com.geladaonline.modelo.pessoa.PessoaFisica;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * This class converts an instance of {@link PessoaFisica} in JSON using a
 * XStream Parser
 * 
 * @author vagner
 *
 */
public class XStreamParser {

	public static void main(String[] args) {
		PessoaFisica pessoaFisica = Parser.criarPessoaFisicaTeste();
		
		XStream xStream = new XStream(new JettisonMappedXmlDriver());
		
		System.out.println(xStream.toXML(pessoaFisica));
	}

}

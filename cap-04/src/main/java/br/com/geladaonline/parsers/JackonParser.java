package br.com.geladaonline.parsers;

import java.io.IOException;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

import br.com.geladaonline.modelo.pessoa.PessoaFisica;

/**
 * This class converts an instance of {@link PessoaFisica} in JSON using a
 * Jackson Parser
 * 
 * @author vagner
 * 
 */
public class JackonParser {

	public static void main(String[] args) throws IOException {
		PessoaFisica pessoaFisica = Parser.criarPessoaFisicaTeste();
		ObjectMapper objectMapper = new ObjectMapper();
		
		AnnotationIntrospector annotationIntrospector = new JaxbAnnotationIntrospector();
		objectMapper.setAnnotationIntrospector(annotationIntrospector);
		System.out.println(objectMapper.writeValueAsString(pessoaFisica));
	}

}

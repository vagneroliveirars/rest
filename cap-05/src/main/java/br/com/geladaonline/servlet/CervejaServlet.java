package br.com.geladaonline.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import br.com.geladaonline.model.Cerveja;
import br.com.geladaonline.model.Estoque;
import br.com.geladaonline.model.rest.Cervejas;

@WebServlet(value = "/cervejas/*")
public class CervejaServlet extends HttpServlet {

	private static final long serialVersionUID = -4310324554946934863L;

	private Estoque estoque = new Estoque();

	private static JAXBContext context;

	static {
		try {
			context = JAXBContext.newInstance(Cervejas.class);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String acceptHeader = req.getHeader("Accept");

		if (acceptHeader == null || acceptHeader.contains("application/xml")) {
			escreveXML(req, resp);
		} else if (acceptHeader == null
				|| acceptHeader.contains("application/json")) {
			escreveJSON(req, resp);
		} else {
			// Accept header has received an unsupported value
			resp.sendError(415);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String identificador = null;
		
		try {
			identificador = obtemIdentificador(req);
		} catch (RecursoSemIdentificadorException e) {
			resp.sendError(400, e.getMessage());
		}
		
		if (identificador != null && estoque.recuperarCervejaPeloNome(identificador) != null ) {
			resp.sendError(409, "Já existe uma cerveja com esse nome");
			return;
		}
		
		try {
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			Cerveja cerveja = (Cerveja) unmarshaller.unmarshal(req.getInputStream());
			cerveja.setNome(identificador);
			estoque.adicionarCerveja(cerveja);
			
			String requestURI = req.getRequestURI();
			resp.setHeader("Location", requestURI);
			resp.setStatus(201);
			
			escreveXML(req, resp);
		} catch (JAXBException e) {
			e.printStackTrace();
			resp.sendError(500, e.getMessage());
		}
	}

	private void escreveXML(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Object objetoAEscrever = localizaObjetoASerEnviado(req);
		
		if (objetoAEscrever == null) {
			resp.sendError(404); // Object not found
			return;
		}

		try {
			resp.setContentType("application/xml;charset=UTF-8");
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(objetoAEscrever, resp.getWriter());
		} catch (JAXBException e) {
			// Internal server error
			e.printStackTrace();
			resp.sendError(500, e.getMessage());
		}
	}

	private void escreveJSON(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Object objetoAEscrever = localizaObjetoASerEnviado(req);
		
		if (objetoAEscrever == null) {
			resp.sendError(404); // Object not found
			return;
		}

		try {
			resp.setContentType("application/json;charset=UTF-8");

			MappedNamespaceConvention con = new MappedNamespaceConvention();

			XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con,
					resp.getWriter());

			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(objetoAEscrever, xmlStreamWriter);
		} catch (JAXBException e) {
			// Internal server error
			e.printStackTrace();
			resp.sendError(500, e.getMessage());
		}
	}

	private String obtemIdentificador(HttpServletRequest req)
			throws RecursoSemIdentificadorException {
		
		String requestUri = req.getRequestURI();

		String[] pedacosDaUri = requestUri.split("/");

		boolean contextoCervejasEncontrado = false;
		for (String contexto : pedacosDaUri) {
			if (contexto.equals("cervejas")) {
				contextoCervejasEncontrado = true;
				continue;
			}
			if (contextoCervejasEncontrado) {
				try {
					return URLDecoder.decode(contexto, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					return URLDecoder.decode(contexto);
				}
			}
		}
		
		throw new RecursoSemIdentificadorException("Recurso sem identificador");
	}
	
	private Object localizaObjetoASerEnviado(HttpServletRequest req) {
		Object objeto = null;

		try {
			String identificador = obtemIdentificador(req);
			objeto = estoque.recuperarCervejaPeloNome(identificador);
		} catch (RecursoSemIdentificadorException e) {
			Cervejas cervejas = new Cervejas();
			cervejas.setCervejas(new ArrayList<>(estoque.listarCervejas()));
			objeto = cervejas;
		}
		return objeto;
	}

}

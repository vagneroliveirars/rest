package br.com.geladaonline.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
		} else if (acceptHeader == null || acceptHeader.contains("application/json")) {
			escreveJSON(req, resp);
		} else {
			// Accept header has received an unsupported value
			resp.sendError(415);
		}
	}

	private void escreveXML(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cervejas cervejas = new Cervejas();
		cervejas.setCervejas(new ArrayList<Cerveja>(this.estoque.listarCervejas()));
		
		try {
			resp.setContentType("application/xml;charset=UTF-8");
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(cervejas, resp.getWriter());
		} catch (JAXBException e) {
			// Internal server error
			e.printStackTrace();
			resp.sendError(500, e.getMessage());
		}
	}
	
	private void escreveJSON(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cervejas cervejas = new Cervejas();
		cervejas.setCervejas(new ArrayList<Cerveja>(this.estoque.listarCervejas()));
		
		try {
			resp.setContentType("application/json;charset=UTF-8");
			
			MappedNamespaceConvention con = new MappedNamespaceConvention();
			
			XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, resp.getWriter());
			
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(cervejas, xmlStreamWriter);
		} catch (JAXBException e) {
			// Internal server error
			e.printStackTrace();
			resp.sendError(500, e.getMessage());
		}
	}

}

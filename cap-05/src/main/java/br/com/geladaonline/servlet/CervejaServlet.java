package br.com.geladaonline.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
		
		try {
			Marshaller marshaller = context.createMarshaller();
			
			resp.setContentType("application/xml;charset=UTF-8");
			
			PrintWriter printWriter = resp.getWriter();
			
			Cervejas cervejas = new Cervejas();
			cervejas.setCervejas(new ArrayList<Cerveja>(this.estoque.listarCervejas()));
			
			marshaller.marshal(cervejas, printWriter);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500, e.getMessage());
		}
	}

}

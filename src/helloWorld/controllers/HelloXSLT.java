
package helloWorld.controllers;

import helloWorld.model.CourseRepository;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
/**
 * implementation class Hello
 */
public class HelloXSLT extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private helloWorld.model.CourseRepository repo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloXSLT() {
        new helloWorld.model.Hello();
		repo = new CourseRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document doc = repo.exportToXMLDocument();
		try {
			// We use classic output format with getPrettyFormat()
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			response.getOutputStream().println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			response.getOutputStream().println("<?xml-stylesheet type=\"text/xsl\" href=\"Transformer\"?>");
			xmlOutputter.output(doc.getRootElement(), response.getOutputStream());
			}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

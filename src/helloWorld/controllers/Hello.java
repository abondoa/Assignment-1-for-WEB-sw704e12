
package helloWorld.controllers;

import helloWorld.model.Course;
import helloWorld.model.CourseRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
public class Hello extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private helloWorld.model.Hello model;
	private helloWorld.model.CourseRepository repo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
        model = new helloWorld.model.Hello();
		repo = new CourseRepository();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Hello World Title");
		request.setAttribute("message", model.getMessage());
	    RequestDispatcher view = request.getRequestDispatcher("/HelloView.jsp");
		request.setAttribute("courses",repo.getCourses());
	    view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

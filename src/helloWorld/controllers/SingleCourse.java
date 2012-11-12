package helloWorld.controllers;


import helloWorld.model.CourseRepository;
import helloWorld.viewModel.SingleCourseHelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.*;
/**
 *  implementation class SingleCourse
 */
public class SingleCourse extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private helloWorld.model.CourseRepository repo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleCourse() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		repo = new CourseRepository();
		String courseId = (String)request.getParameter("id");
		String offset = (String)request.getParameter("offset");
		if(offset == null || offset == "")
		{
			offset = "0";
		}
	    RequestDispatcher view = request.getRequestDispatcher("/SingleCourseView.jsp");
	    
	    int offsetValue = Integer.parseInt(offset);
	    SingleCourseHelper helper = new SingleCourseHelper(repo,courseId,offsetValue);
		request.setAttribute("helper", helper);
	    view.forward(request, response);
	    log(request);
		repo = null;
	}

	private void log(HttpServletRequest request) {
		Logger logger = Logger.getLogger("navigation");
		//PropertyConfigurator.configure("log4jconfig.txt");
		logger.addAppender(new org.apache.log4j.ConsoleAppender(new org.apache.log4j.PatternLayout("[%d{ISO8601}]%5p%6.6r[%t]%x - %C.%M(%F:%L) - %m%n")));
		logger.info("derp");
	    request.getRemoteAddr();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

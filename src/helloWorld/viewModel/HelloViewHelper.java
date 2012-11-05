package helloWorld.viewModel;

import helloWorld.model.Course;
import helloWorld.model.*;
import java.util.Collection;

public class HelloViewHelper {
	
	private CourseRepository courseRepo;
	private helloWorld.model.Hello model;
	
	public HelloViewHelper(Hello model, CourseRepository cr)
	{
		this.model = model;
		courseRepo = cr;
	}
	
	public Collection<Course> getCourses()
	{
		return courseRepo.getCourses();
	}
	
	public String getTitle()
	{
		return "Hello World Title";
	}
	
	public String getMessage()
	{
		return model.getMessage();
	}
}

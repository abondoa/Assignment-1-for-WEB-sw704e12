package helloWorld.viewModel;

import helloWorld.model.Course;
import helloWorld.model.CourseRepository;

public class SingleCourseHelper {

	private CourseRepository courseRepo;
	private String id;
	private int offset;
	private Course course;
	
	public SingleCourseHelper(CourseRepository courseRepo, String id, int offset) {
		super();
		this.courseRepo = courseRepo;
		this.id = id;
		this.offset = offset;
		course = null;
	}
	
	public Course getCourse()
	{
		_load();
		return course;
	}
	
	private void _load() {
		if (course == null)
		{
			course = courseRepo.getCourse(id,offset);
		}
	}

	public String previousLink()
	{
		_load();
		return "http://localhost:8080/WEHelloWorld/SingleCourse?offset=-1&id=" + course.id;
	}

	public String nextLink()
	{
		_load();
		return "http://localhost:8080/WEHelloWorld/SingleCourse?offset=1&id=" + course.id;
	}
}

package helloWorld.model;

import java.util.Collection;

public class Course {
	public String id;
	public String title;
	public String credits;
	public String level;
	public String restrictions;
	public Collection<Section> sections;
	
	public Course(
			String id,
			String title,
			String credits,
			String level,
			String restrictions,
			Collection<Section> sections) {

		this.id = id;
		this.title = title;
		this.credits = credits;
		this.level = level;
		this.restrictions = restrictions;
		this.sections = sections;
	}
}

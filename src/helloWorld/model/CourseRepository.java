package helloWorld.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class CourseRepository {
	private SAXBuilder sax;
	private Document document;
	private Element root;
	private Map<String,Course> map;
	
	public CourseRepository() {
		sax = new SAXBuilder();
		try {
			document = sax.build(new File("C:\\uwm.xml"));
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root = document.getRootElement();
		map = new HashMap<String,Course>();
	}
	
	public Course getCourse(String id) {
		if(map.containsKey(id)) {
			return map.get(id);
		}
		
		for(Element courseElement : root.getChildren("course_listing")) {
			if(!map.containsKey(courseElement.getChildText("course"))){
				Course current = new Course(
						courseElement.getChildText("course"), 
						courseElement.getChildText("title"), 
						courseElement.getChildText("credits"), 
						courseElement.getChildText("level"), 
						courseElement.getChildText("restrictions"), 
						parseSections(courseElement)
						);
			
				map.put(courseElement.getChildText("course"),current);
				if(courseElement.getChildText("course").equals(id)) {
					return current;
				}
			}
		}
		
		throw new IllegalArgumentException("No course with id: "+id+" exists");
	}
	
	public Collection<Course> getCourses() {
		for(Element courseElement : root.getChildren("course_listing")) {
			if(!map.containsKey(courseElement.getChildText("course"))){
				Course current = new Course(
						courseElement.getChildText("course"), 
						courseElement.getChildText("title"), 
						courseElement.getChildText("credits"), 
						courseElement.getChildText("level"), 
						courseElement.getChildText("restrictions"), 
						parseSections(courseElement)
						);
			
				map.put(courseElement.getChildText("course"),current);
			}
		}
		
		return map.values();
	}
	
	public Document exportToXMLDocument() {
		Collection<Course> courses = getCourses();
		Element root = new Element("course_list");
		Document document = new Document(root);
		
		for(Course course : courses) {
			Element courseElement = new Element("course");
			courseElement.setAttribute(new Attribute("id", course.id));
			courseElement.addContent(new Element("title").addContent(course.title));
			courseElement.addContent(new Element("credits").addContent(course.credits));
			courseElement.addContent(new Element("level").addContent(course.level));
			courseElement.addContent(new Element("restrictions").addContent(course.restrictions));
			
			if(!course.sections.isEmpty()) {
				Element sectionsElement = new Element("sections");
				for(Section section : course.sections) {
					Element sectionElement = new Element("section");
					sectionElement.setAttribute(new Attribute("id",section.id));
					sectionElement.addContent(new Element("days").addContent(section.days));
					sectionElement.addContent(new Element("time_start").addContent(section.timeStart));
					sectionElement.addContent(new Element("time_end").addContent(section.timeEnd));
					sectionElement.addContent(new Element("instructor").addContent(section.instructor));
					sectionElement.addContent(new Element("comment").addContent(section.comment));
					
					sectionsElement.addContent(sectionElement);
				}
				courseElement.addContent(sectionsElement);
			}
			
			root.addContent(courseElement);
		}
		return document;
	}

	private Collection<Section> parseSections(Element courseElement) {
		ArrayList<Section> result = new ArrayList<Section>();
		for(Element sectionElement : courseElement.getChildren("section_listing")) {
			Section current = new Section(
					sectionElement.getChildText("section"), 
					sectionElement.getChildText("days"), 
					sectionElement.getChild("hours").getChildText("start"), 
					sectionElement.getChild("hours").getChildText("end"), 
					sectionElement.getChildText("instructor"), 
					sectionElement.getChildText("comments")
					);
		
			result.add (current);
		}
		return result;
	}
}

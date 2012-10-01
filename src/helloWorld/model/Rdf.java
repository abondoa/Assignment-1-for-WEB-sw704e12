package helloWorld.model;

import java.util.Collection;

public class Rdf {

	private String content; 
	private  String uri = "http://someurl.fake/#";
	public String getRdf()
	{
		
		String header = "<rdf:RDF	xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"	xmlns:cd=\"http://www.recshop.fake/cd#\">";
		String footer = "</rdf:RDF>";
		return header + content + footer;
	}
	
	
	public Rdf createStatement(String subject, String predicate, String object)
	{
		
		content += "<rdf:Description rdf:about=\" "+ subject + "\">";
		content += "<" + predicate + ">" + object + "</" + predicate + ">";
		return this;
	}
	
	public Rdf createSectionCollection( Collection<Section> c )
	{
		content += "<sw7:Section rdf:parseType=\"collection\">";
		for(Section s : c)
		{
			content += "<rdf:Description rdf:about=\""+ uri + s.id  +"\">";
			content += "<sw7:timeStart rdf:about=\""+ uri + s.timeStart +"\">";
			content += "<sw7:timeStart rdf:about=\""+ uri + s.timeEnd +"\">";
			content += "</rdf:Description>";
		}
		content += "</sw7:Section>";
		return this;
	}
	
	public void loadFromXml()
	{
		CourseRepository repo  = new CourseRepository();
		
		for(Course c : repo.getCourses())
		{
			createStatement(uri + c.id, "sw7:title", c.title).createSectionCollection(c.sections);
			
			
			
		}
	}
}

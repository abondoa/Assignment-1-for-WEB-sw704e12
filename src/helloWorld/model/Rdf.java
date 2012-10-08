package helloWorld.model;

import java.util.Collection;

public class Rdf {

	private String content = ""; 
	private  String uri = "http://someurl.fake/#";
	public String getRdf()
	{
		
		String header = "<rdf:RDF	xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" 	xmlns:sw7=\""+uri+"\">\n";
		String footer = "</rdf:RDF>\n";
		return header + content + footer;
	}
	
	
	public Rdf createStatement(String subject, String predicate, String object)
	{
		
		content += "<rdf:Description rdf:about=\" "+ subject + "\">\n";
		content += "\t<" + predicate + ">" + object + "</" + predicate + ">\n";
		content += "</rdf:Description>\n";
		return this;
	}
	
	public Rdf createSectionCollection( Collection<Section> c )
	{
		content += "\t<sw7:Section rdf:parseType=\"collection\">\n";
		for(Section s : c)
		{
			content += "\t\t<rdf:Description rdf:about=\""+ uri + s.id  +"\">\n";
			content += "\t\t\t<sw7:timeStart rdf:about=\""+ uri + s.timeStart +"\">\n";
			content += "\t\t\t<sw7:timeStart rdf:about=\""+ uri + s.timeEnd +"\">\n";
			content += "\t\t</rdf:Description>\n";
		}
		content += "\t</sw7:Section>\n";
		return this;
	}
	
	public void loadFromXml()
	{
		CourseRepository repo  = new CourseRepository();
		
		for(Course c : repo.getCourses())
		{
			content += "<rdf:Description rdf:about=\" "+ uri + c.id + "\">\n";
			content += "\t<" + "sw7:title" + ">" + c.title + "</" + "sw7:title" + ">\n";
			createSectionCollection(c.sections);
			content += "</rdf:Description>\n";
			
			
		}
	}
}

<%@page import="java.util.Collection"%>
<%@page import="helloWorld.viewModel.HelloViewHelper"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
HelloViewHelper helper = (helloWorld.viewModel.HelloViewHelper)request.getAttribute("helper"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= helper.getTitle() %> </title>
</head>
<body>
<%= helper.getMessage() %> 
<ul>
<%
for(helloWorld.model.Course course : helper.getCourses()) {
	out.print("<li>");
	out.print("<b>"+course.title+"</b>");
	out.print(" - "+course.credits+" ECTS<br/>");
	out.print(""+course.restrictions+"");
	if(!course.sections.isEmpty()) {
		out.print("<ul>");
		for(helloWorld.model.Section section : course.sections) {
			out.print("<li>");
			out.print("<b>"+section.id+"</b>");
			out.print(" - "+section.instructor+"");
			out.print(" - "+section.days+"");
			out.print(" - "+section.timeStart+"");
			if(section.timeEnd != null && section.timeEnd.length() > 0) {
				out.print("-"+section.timeEnd+"");
			}
			if(section.comment != null && section.comment.length() > 0) {
				out.print("<br/>"+section.comment+"");
			}
			out.print("</li>");
		}
		out.print("</ul>");
	}
	out.print("</li>");
}
%>
</ul>
</body>
</html>
package helloWorld.model;

public class Section {
	public String id;
	public String days;
	public String timeStart;
	public String timeEnd;
	public String instructor;
	public String comment;
	
	public Section(String id,
	String days,
	String timeStart,
	String timeEnd,
	String instructor,
	String comment) {
		this.id = id;
		this.days = days;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.instructor = instructor;
		this.comment = comment;
	}
}

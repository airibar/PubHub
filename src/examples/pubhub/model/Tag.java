package examples.pubhub.model;

public class Tag {
private String isbn13;
private String tag_name;

// First Constructor given the tag and isbn13

public Tag(String isbn13, String tag_name) {
	this.isbn13 = isbn13;
	this.tag_name = tag_name;
}
// Default Constructor

public Tag() {
	this.isbn13 = null;
	this.tag_name = null;
}
// getters and setters.
public String getIsbn13() {
	return isbn13;
}
public void setIsbn13(String isbn13) {
	this.isbn13 = isbn13;
}
public String getTag_name() {
	return tag_name;
}
public void setTag_name(String tag_name) {
	this.tag_name = tag_name;
}

}

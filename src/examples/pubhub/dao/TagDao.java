package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.BandT;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public interface TagDao {

// A method to add a tag to a book, given the tag name 
//and a reference to a book (either a Book reference variable
// or just an ISBN-13)
	//  1- Add Tag name and isbn13
public boolean checkTagName(String tag_name,String isbn13);
	//  1- Add Tag name and isbn13
public boolean addTagName(String tag_name,String isbn13);
//1- add Tag giving  Book and isbn13
public boolean addTagNameByBook(Book book,String isbn13);
//2- Remove Tag name and isbn13
public boolean removeTagName(String tag_name,String isbn13);
//2- Remove Tage giving Book and isbn13
public boolean removeTagNameByBook(Book book,String isbn13);

// Update a Tag giving book and tag_name
public boolean updateTag(Book book, String tag_name, String new_tag_name);
// Update a Tag giving a Tag
public boolean updateTag(Tag tag);

//retrieve all books that have an specific tag
public List<Book> getAllBooks(String tag_name);
//retrieve all tags that have an specific Book
public List<BandT> getAllTagsForBook(String isbn13);

}

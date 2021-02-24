package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.BandT;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class TagDaoImpl implements TagDao{
	
	// Connection
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	

	@Override
	public boolean addTagName(String isbn13,String tag_name) {
	 int response=0;
		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "INSERT INTO public.\"Book_Tags\"(\r\n"
					+ "	isbn_13, tag_name)\r\n"
					+ "	VALUES (?, ?);";	// Our SQL query
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, isbn13);   // add isbn_13 to query
			stmt.setString(2, tag_name); // add tagname to query
			response=stmt.executeUpdate();
			// Queries the database
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		   if (response==1) {
			   return true; // tag was saved
		   }else {
			   return false; // duplicate tags 
		   }
			
	}

	@Override
	public boolean addTagNameByBook(Book book, String tag_name) {
		int response=0;
		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "INSERT INTO public.\"Book_Tags\"(\r\n"
					+ "	isbn_13, tag_name)\r\n"
					+ "	VALUES (?, ?);";	// Our SQL query
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, book.getIsbn13());   // add isbn_13 to query
			stmt.setString(2, tag_name); // add tagname to query
			response=stmt.executeUpdate();
			// Queries the database
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		   if (response==1) {
			   return true; // tag was saved
		   }else {
			   return false; // duplicate tags 
		   }
			

	}

	@Override
	public boolean removeTagName(String tag_name, String isbn13) {
		int response=0;
		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "DELETE FROM public.\"Book_Tags\"\r\n"
					+ "	WHERE isbn_13 = ? and tag_name = ?;";	// Our SQL query
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, isbn13);   // add isbn_13 to query
			stmt.setString(2, tag_name); // add tagname to query
			response=stmt.executeUpdate();
			// Queries the database
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		   if (response==1) {
			   return true; // deleted
		   }else {
			   return false; // no deleted
		   }
	}

	@Override
	public boolean removeTagNameByBook(Book book, String tag_name) {
		int response=0;
		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "DELETE FROM public.\"Book_Tags\"\r\n"
					+ "	WHERE isbn_13 = ? and tag_name = ?;";	// Our SQL query
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, book.getIsbn13());   // add isbn_13 to query
			stmt.setString(2, tag_name); // add tagname to query
			response=stmt.executeUpdate();
			// Queries the database
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		   if (response==1) {
			   return true; // Borro
		   }else {
			   return false; // no Borro
		   }

	}

	@Override
	public boolean updateTag(Book book, String tag_name, String new_tag_name) {
		// TODO Auto-generated method stub
		//SET isbn_13=?, tag_name=?
		//	WHERE WHERE isbn_13 = ? and tag_name = ?;
		int response=0;
		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "UPDATE public.\"Book_Tags\""
					+  "SET isbn_13=?, tag_name= ? "
					+ "	WHERE isbn_13 = ? and tag_name = ?;";	// Our SQL query
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, book.getIsbn13());   //  isbn_13 to query
			stmt.setString(2, new_tag_name); // add new tagname to query
			stmt.setString(3, book.getIsbn13());
			stmt.setString(4, tag_name); // isbn
			response=stmt.executeUpdate();
			// Queries the database
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		   if (response==1) {
			   return true; // tag was saved
		   }else {
			   return false; // duplicate tags 
		   }


	}

	@Override
	public boolean updateTag(Tag tag) {
		// TODO Auto-generated method stub
				
		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "UPDATE public.\"Book_Tags\"\r\n"
					+ "		SET tag_name=?\r\n"
					+ "		WHERE isbn_13 = ?;";	// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			stmt.setString(1, tag.getTag_name());
			stmt.setString(2, tag.getIsbn13());
			stmt.executeQuery();			// Queries the database
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
		return true;
	}

	
	@Override
	public List<Book> getAllBooks(String tag_name) {
		List<Book> books = new ArrayList<>();
		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			if(tag_name.length()==0) {
				String sql = "select b.isbn_13,author,title,publish_date,price,content from \"books\" as b where isbn_13  in (\r\n"
						+ "select bt.isbn_13 from \"Book_Tags\" as bt);";
				stmt = connection.prepareStatement(sql);
			}else {
			String sql = "select isbn_13,author,title,publish_date,price,content from public.\"books\" where isbn_13 in (\r\n"
					+ "SELECT isbn_13\r\n"
					+ "	FROM public.\"Book_Tags\" where tag_name like ?);";	// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			stmt.setString(1, tag_name);
			}
			ResultSet rs = stmt.executeQuery();			// Queries the database
			while(rs.next()) {
				Book book = new Book();
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));
				
				books.add(book);
			}
			connection.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
		return books;
	}
	@Override
	public List<BandT> getAllTagsForBook(String isbn13) {
		//		SELECT isbn_13, tag_name
		//	FROM public."Book_Tags" where isbn_13 = '1111111111111'
		
		List<BandT> bandt = new ArrayList<BandT>();
			try {
				connection = DAOUtilities.getConnection();	// Get our database connection from the manager
				String sql = "select bt.isbn_13,b.title,b.author,b.publish_date,price,string_agg(tag_name,', ') as at from \"books\" as b , \"Book_Tags\" as bt\r\n"
						+ "where b.isbn_13 = ? \r\n"
						+ "group by bt.isbn_13,b.title,b.author,b.publish_date,price";	// Our SQL query
				stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
				stmt.setString(1, isbn13);
				ResultSet rs = stmt.executeQuery();			// Queries the database
				while(rs.next()) {
					BandT allTag = new BandT();
					allTag.setTitle(rs.getString("title"));
					allTag.setIsbn13(rs.getString("isbn_13"));
					allTag.setAuthor(rs.getString("author"));
					allTag.setMyTag(rs.getString("at"));
								
					bandt.add(allTag);
				}
				connection.close();
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
			
			return bandt;

	}

	@Override
	public boolean checkTagName(String tag_name, String isbn13) {
		// TODO Auto-generated method stub
		Tag tag = new Tag();
		boolean tagExist = false;
		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT isbn_13, tag_name\r\n"
					+ "		FROM public.\"Book_Tags\" where tag_name = ? and isbn13 = ?;";	// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			stmt.setString(2, isbn13);
			stmt.setString(1, tag_name);
			ResultSet rs = stmt.executeQuery();			// Queries the database
			while(rs.next()) {
			
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTag_name(rs.getString("tag_name"));
				tagExist = true;	
				}
			connection.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
		
		return tagExist;
	}

	

}

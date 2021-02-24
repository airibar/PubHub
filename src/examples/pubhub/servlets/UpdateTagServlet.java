package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDao;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class UpdateTagServlet
 */
@WebServlet("/UpdateTag")
public class UpdateTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TagDao dao = DAOUtilities.getTagsDAO();
		String accion = request.getParameter("accion");
		Tag tag = new Tag();
		tag.setIsbn13(request.getParameter("isbn13"));
		tag.setTag_name(request.getParameter("tagname"));
		// go to tags general list 
		// verify tag exists, if so delete and re insert new one. doing this since
		// primary and foreign ket are together
		boolean existTag = dao.checkTagName(tag.getTag_name(), tag.getIsbn13());
			
		if (accion.equals("Delete"))
		{
			dao.removeTagName(tag.getTag_name(),tag.getIsbn13());
		}
		if (accion.equals("Update"))
		{
			dao.updateTag(tag);
		}
		if (accion.equals("Add"))
		{
			dao.addTagName(tag.getIsbn13(),tag.getTag_name());
		}
		request.getSession().setAttribute("tag", tag);
		request.getSession().setAttribute("isbn13", tag.getIsbn13());
		request.getSession().setAttribute("Accion",accion);
		request.getRequestDispatcher("BookPublishing").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

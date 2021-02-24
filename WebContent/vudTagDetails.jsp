	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
		
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>PUBHUB <small>Tag Details - ${tag.isbn13 }</small></h1>
		<hr class="book-primary">
		
		<form action="UpdateTag" method="Post" class="form-horizontal">
		  
		  <input type="hidden" class="form-control" id="isbn13" name="isbn13" required="required" value="${tag.isbn13 }" />
		  <input type="hidden" class="form-control" id="accion" name="accion" required="required" value="${Accion}" />
		  
		  <div class="form-group">
		    <label for="title" class="col-sm-4 control-label">Tag</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="title" name="tagname" placeholder="Title" required="required" value="${tag.tag_name }" />
		    </div>
		  </div>
		      
		   <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-info">${Accion}</button>
		    </div>
		  </div>
  
		</form>

	  </div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />

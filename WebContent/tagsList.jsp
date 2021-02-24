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
	
		<h1>PUBHUB <small>Tags in This Book </small></h1>
		<hr class="book-primary">

		<table class="table table-striped table-hover table-responsive pubhub-datatable">
			<thead>
				<tr>${title}</tr>
				<tr>
					<td>Tags:</td>
					<td></td>
					<td>
					<form action="VudTag" method="Post">
							<input type="hidden" name="isbn13" value="${tag.isbn13}">
							<input type="hidden" name="tagname" value="${tag.tag_name}">
							<input type="hidden" name="Accion" value="Add">
							<button class="btn btn-success">Add Tag</button>
						</form>
					</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tag" items="${tags}">
					<tr>
						<td><c:out value="${tag.myTag}" /></td>
						<td><form action="VudTag" method="Post">
								<input type="hidden" name="isbn13" value="${tag.isbn13}">
								<input type="hidden" name="tagname" value="${tag.myTag}">
								<input type="hidden" name="Accion" value="View">
								<button class="btn btn-success">View</button>
						</form></td>
						<td><form action="VudTag" method="Post">
								<input type="hidden" name="isbn13" value="${tag.isbn13}">
								<input type="hidden" name="tagname" value="${tag.myTag}">
								<input type="hidden" name="Accion" value="Delete">
								<button class="btn btn-success">Delete</button>
						</form></td>
						
					</tr>
				</c:forEach>
					
			</tbody>
		</table>

	  </div>
	</header>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>menu.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript">
function search(){
    var userInput = document.getElementById('inputTitle').value;
    window.location = "menu/" + userInput;
}
</script>
</head>
<body>
	<div class="container">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h1>List of all DVDs</h1>
			<div>
	        	<div>
	            	<h2>DVDs</h2>
	            	<form action="" method="POST">
	            	<input type="text" name="title" placeholder="search title" id="inputTitle"/>
	            	<input type="submit" class="btn btn-secondary" value="Search"/>
	            	</form>
	            	<div >${msg}</div>
	            	<table class="table table-striped">
	                	<thead>
	                    	<tr>
	                        	<th>ID</th>
	                        	<th>Title</th>
	                        	<th>Release Date</th>
	                        	<th>MPAA Rating</th>
	                        	<th>Director</th>
	                        	<th>Studio</th>
	                        	<th>User rating/Note</th>
	                        	<th>Edit</th>
	                        	<th>Delete</th>
	                    	</tr>
	                	</thead>
	                	<tbody>
							<!-- loop -->
							<c:forEach items="${dvd}" var="ListItem">
							<tr >
								<td>${ListItem.id}</td>
	 							<td>${ListItem.title}</td>
	 							<td>${ListItem.releaseDate}</td>
	 							<td>${ListItem.mpaaRating}</td>
	 							<td>${ListItem.directorName}</td>
	 							<td>${ListItem.studio}</td>
	 							<td>${ListItem.note}</td>
	 							<td><a href="edit_dvd.htm?id=${ListItem.id}">Edit</a></td>
	 							<td><a href="delete_dvd.htm?id=${ListItem.id}">Delete</a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<p><a href="add_dvd.htm">Add a new DVD</a></p>
			</div>
			<div class="col-md-2"></div>
		</div>
</body>
</html>

<!-- http://localhost:8080/DVDLibrary/menu.htm -->
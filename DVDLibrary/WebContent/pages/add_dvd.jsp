<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add DVD</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="jumbotron">
					<h2>Add new DVD</h2>
					<form method="POST" action="">
						<br/>
						ID:<br/> 
						<input type="text" name="id" value="${nextId}"/> <br/>
						<hr>
						Title:<br/> 
						<input type="text" name="title"/> <br/>
						<hr>
						Release Date:<br/> <input type="date" name="releaseDate"/> <br/>
						<hr>
						MPAA Rating:<br/> <input type="text" name="mpaaRating"/> <br/>
						<hr>
						Director name:<br/> <input type="text" name="directorName"/> <br/>
						<hr>
						Studio: <br/><input type="text" name="studio"/> <br/>
						<hr>
						Your rating(?/10)/ Note: <br/><input type="text" name="note"/> <br/>
						<hr>
						<input type="submit" value="Submit" class="btn btn-success"> <br/>
					</form>
				</div>
			</div>
			<div class="col-md-2"></div>
	</div>
</body>
</html>
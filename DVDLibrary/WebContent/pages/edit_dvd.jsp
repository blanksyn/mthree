<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>edit.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>

	<div class="container">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="jumbotron">
					<h2>Edit existing DVD</h2>
					<form method="POST" action="">
						<br/>
				
						Title:<br/> 
						<input type="text" name="title" value="${dvd.title}"/> <br/>
					
						Release Date:<br/> 
						<input type="date" name="releaseDate" value="${dvd.releaseDate}" placeholder="dd/mm/yyyy"/> <br/>
						
						MPAA Rating:<br/> 
						<input type="text" name="mpaaRating" value="${dvd.mpaaRating}"/> <br/>
						
						Director name:<br/> 
						<input type="text" name="directorName" value="${dvd.directorName}"/> <br/>
					
						Studio: <br/>
						<input type="text" name="studio" value="${dvd.studio}"/> <br/>
				
						Your rating(?/10)/ Note: <br/>
						<input type="text" name="note" value="${dvd.note}"/> <br/>
						
						<input type="submit" value="Confirm changes" class="btn btn-success"> <br/>
					</form>
				</div>
			</div>
			<div class="col-md-2"></div>
	</div>
</body>
</html>
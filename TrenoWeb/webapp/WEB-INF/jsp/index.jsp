<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

        <!-- <img src="resources/P.png">  -->

		<h1>Benvenuto!</h1>
		<h3>Fai l'accesso al sito</h3>
		
		<br>
		
		<form action="/TrenoWeb/home">
			<label for="fname">Username:</label><br>
			<input type="text" id="username" name="username" value="..."><br>
			<label for="lname">Password:</label><br>
			<input type="text" id="password" name="password" value="..."><br><br>
			<input type="submit" value="Submit">
		</form> 
		
</body>
</html>
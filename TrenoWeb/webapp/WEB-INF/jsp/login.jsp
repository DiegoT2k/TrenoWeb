<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	${message}
	
	<form:form method="POST" 
	        action="postLogin"       
	        modelAttribute="loginVO"> 
	     <table>
	        <tr>
	            <td><form:label path="username">Username</form:label></td>
	            <td><form:input path="username"/></td>   
	        </tr>
	        <tr>
	            <td><form:label path="password">Password</form:label></td>
	            <td><form:password path="password"/></td>
	        </tr>

	        <tr>
	            <td><input type="submit" value="Submit"/></td>
	        </tr>
	    </table>
	</form:form>


</body>
</html>
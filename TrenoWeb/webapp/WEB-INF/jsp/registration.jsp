<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrazione</title>
</head>
<body>

<jsp:include page="menu.jsp"></jsp:include>	

<h1>Registrazione</h1>
    
    	<form:form method="POST" 
	        action="postRegistrazione"       
	        modelAttribute="registrationVO"> 
	     <table>
	        <tr>
	            <td><form:label path="nome">Nome</form:label></td>
	            <td><form:input path="nome" placeholder="..."/></td>   
	        </tr>
	        <tr>
	            <td><form:label path="cognome">Cognome</form:label></td>
	            <td><form:input path="cognome" placeholder="..."/></td>
	        </tr>
	        <tr>
	            <td><form:label path="username">Username</form:label></td>
	            <td><form:input path="username" placeholder="..."/></td>
	        </tr>
	     	<tr>
	            <td><form:label path="email">Email</form:label></td>
	            <td><form:input path="email" placeholder="..."/></td>
	        </tr>
    	    <tr>
	            <td><form:label path="password">Password</form:label></td>
	            <td><form:input path="password" placeholder="..."/></td>
	        </tr>
	        <tr>
	            <td><input type="submit" value="Registrati"/></td>
	        </tr>
	    </table>
	</form:form>
	
	
</body>
</html>
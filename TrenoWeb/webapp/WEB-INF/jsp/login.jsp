<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>

	<link rel="stylesheet" type="text/css" href="<c:url value='/style/login.css' />" />  
	<meta charset="UTF-8">
	<title>Login</title>

</head>

<body>

<jsp:include page="menu.jsp"></jsp:include>	

<div class="container">

	<h1>Login</h1>
	
	<c:if test="${not empty errorMessage}">
    	<p style="color:red;">${errorMessage}</p>
	</c:if>

	<form:form method="POST" 
	        action="postLogin"       
	        modelAttribute="loginVO">
<!--  
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
 -->
 			<input type="hidden" name="origin" value="${origin}">
			<div class="form-group">
				<form:label path="username">Username</form:label>
				<form:input path="username" placeholder="Inserisci il tuo username" required="true" />
				<form:errors path="username" class="error"/>
				<h2 class="error">${error_username}</h2>
			</div>

			<div class="form-group">

				<form:label path="password">Password</form:label>
				<form:password path="password"
					placeholder="Inserisci la tua password" required="true" minlength="8" maxlength="20"/>
				<form:errors path="password" class="error" />
				<h2 class="error">${error_password}</h2>
			</div>

			 <div class="form-group">
            <input type="submit" value="Login"/>
        </div>

	</form:form>
</div>

</body>
</html>
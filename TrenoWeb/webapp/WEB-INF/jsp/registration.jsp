<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<title>Registrazione</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/style/registration.css' />" />

</head>

<body>

<jsp:include page="menu.jsp"></jsp:include> 

<div class="container">
    <h1>Registrazione</h1> 
    <form:form method="POST" 
        action="postRegistrazione"       
        modelAttribute="registrationVO"> 
        <div class="form-group">
            <form:label path="nome">Nome</form:label>
            <form:input path="nome" placeholder="Inserisci il tuo nome"/>
            <form:errors path="nome" class="error"/>
        </div>
        <div class="form-group">
            <form:label path="cognome">Cognome</form:label>
            <form:input path="cognome" placeholder="Inserisci il tuo cognome"/>
            <form:errors path="cognome" class="error"/>
        </div>
        <div class="form-group">
            <form:label path="username">Username</form:label>
            <form:input path="username" placeholder="Inserisci il tuo username"/>
            <form:errors path="username" class="error"/>
        </div>
        <div class="form-group">
            <form:label path="email">Email</form:label>
            <form:input path="email" placeholder="Inserisci la tua email"/>
            <form:errors path="email" class="error"/>
        </div>
        <div class="form-group">
            <form:label path="password">Password</form:label>
            <form:password path="password" placeholder="Inserisci la tua password"/>
            <form:errors path="password" class="error"/>
        </div>
        <div class="form-group">
            <form:label path="confirmPassword">Conferma la Password</form:label>
            <form:password path="confirmPassword" placeholder="Conferma la tua password"/>
            <form:errors path="confirmPassword" class="error"/>
        </div>
        <div class="form-group">
            <input type="submit" value="Registrati"/>
        </div>
    </form:form>
</div>

</body>
</html>
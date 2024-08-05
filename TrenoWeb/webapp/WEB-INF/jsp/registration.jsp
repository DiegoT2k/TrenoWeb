<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrazione</title>
<style>
    .error {
        color: red;
    }
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        color: #333;
        margin: 0;
        padding: 0;
    }

    .container {
        width: 60%;
        margin: 0 auto;
        padding: 20px;
        background: #fff;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        margin-top: 50px;
    }

    h1, h2 {
        text-align: center;
        color: #333;
    }

    .form-group {
        margin-bottom: 20px;
    }

    .form-group label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    .form-group input[type="text"],
    .form-group input[type="password"],
    .form-group input[type="email"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 16px;
    }

    .form-group input[type="submit"] {
        width: 100%;
        padding: 10px;
        border: none;
        border-radius: 4px;
        background-color: #4CAF50;
        color: #fff;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .form-group input[type="submit"]:hover {
        background-color: #45a049;
    }

    .error {
        color: red;
        font-size: 14px;
    }
</style>
</head>
<body>

<div class="container">
    <h1>Registration Form</h1> 
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
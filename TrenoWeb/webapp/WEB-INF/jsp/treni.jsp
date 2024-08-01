<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina treni</title>
</head>
<body>

<h1>Tabella dei treni:</h1>

<!-- ${listaTreni}  -->

<c:forEach var="item" items="${listaTreni}"> 
    <h4>${item}</h4> 
</c:forEach> 

</body>
</html>
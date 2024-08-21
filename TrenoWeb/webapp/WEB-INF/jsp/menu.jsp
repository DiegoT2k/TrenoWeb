<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>

    <meta charset="UTF-8">
    <title>Menu di Navigazione</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/style/menu.css' />" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />

</head>

<body>

    <nav>
   
    <div class="menu">
  
    	<a href="<c:url value='/home' />">
    	<i class="fa-solid fa-home"></i>
    	 Home</a>
    	
        <a href="<c:url value='/treni' />">
        <i class="fa-solid fa-table"></i>
         Treni</a>
        
        <a href="<c:url value='modulo' />">
        <i class="fa-solid fa-plus"></i>
         Crea Treno</a>

      

		<c:choose>
			<c:when test="${sessionScope.utente == null}">
				<a href="<c:url value='registration' />" class="registration">
				<i class="fa-solid fa-address-card"></i> Registrati</a>
        		
        		<a href="<c:url value='/login' />" class="login">
        		<i class="fa-solid fa-right-to-bracket"></i> Login</a>
			</c:when>
			<c:otherwise>
				<a href="<c:url value='/profilo' />" class="profilo">
				<i class="fa-solid fa-user"></i> Ciao, ${username}</a>
				
				<a href="<c:url value='logout' />" class="logout">
				<i class="fa-solid fa-right-from-bracket"></i> Logout</a>
			</c:otherwise>
		</c:choose>
		
	</div>
	</nav>
</body>

</html>


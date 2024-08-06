<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Menu di Navigazione</title>
    
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
    
    <style>
        /* Stili di base per il corpo della pagina */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px; /* Stacco dai bordi della pagina */
            background-color: #f8f9fa;
        }

        /* Stili per il contenitore del menu */
        .menu {
            background-color: #6c757d; /* Colore di sfondo grigio */
            overflow: hidden;
            border-radius: 8px; /* Angoli arrotondati */
            margin: 0 auto;
            padding: 10px; /* Stacco interno */
            max-width: 800px; /* Larghezza massima del menu */
            box-shadow: 0 4px 8px rgba(0,0,0,0.1); /* Ombra leggera */
        }

        /* Stili per i link nel menu */
        .menu a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
            border-radius: 4px; /* Angoli leggermente arrotondati per i link */
        }

        /* Stili per i link attivi e al passaggio del mouse */
        .menu a:hover {
            background-color: #5a6268;
        }

        /* Stili per i contenitori dei link */
        .menu .dropdown {
            float: left;
            overflow: hidden;
        }

        /* Stili per i link all'interno dei dropdown */
        .menu .dropdown .dropbtn {
            font-size: 16px;
            border: none;
            outline: none;
            color: white;
            padding: 14px 20px;
            background-color: inherit;
            margin: 0;
            border-radius: 4px; /* Angoli leggermente arrotondati per i dropdown */
        }

        /* Stili per il contenuto dei dropdown */
        .menu .dropdown-content {
            display: none;
            position: absolute;
            background-color: #6c757d;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
            border-radius: 4px; /* Angoli leggermente arrotondati per il contenuto dei dropdown */
        }

        /* Stili per i link all'interno dei dropdown */
        .menu .dropdown-content a {
            float: none;
            color: white;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
            border-radius: 4px; /* Angoli leggermente arrotondati per i link nei dropdown */
        }

        /* Stili per i link al passaggio del mouse all'interno dei dropdown */
        .menu .dropdown-content a:hover {
            background-color: #5a6268;
        }

        /* Mostra il contenuto del dropdown al passaggio del mouse */
        .menu .dropdown:hover .dropdown-content {
            display: block;
        }
  .menu {
    display: flex;
    justify-content: space-between;
}

.menu a {
    padding: 10px;
    text-decoration: none;
}

.profilo {
	margin-left: auto;
}

.logout {
    margin-left: auto; /* Spinge il logout a destra */
}

.login {
	margin-left: auto;
}



    </style>
</head>


<body>

    <!-- Contenitore del menu di navigazione -->
    <nav>
   
    <div class="menu">
  
    	<a href="<c:url value='/home' />">
    	<!-- icona home -->
    	<i class="fa-solid fa-home"></i>
    	 Home</a>
    	
        <a href="<c:url value='/treni' />">
        <i class="fa-solid fa-table"></i>
         Treni</a>
        
        <a href="<c:url value='modulo' />">
        <i class="fa-solid fa-plus"></i>
         Crea Treno</a>

        <!-- Puoi aggiungere altri link o dropdown qui -->

<!-- 		Mostra login/logout a seconda dello stato dell'utente -->
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


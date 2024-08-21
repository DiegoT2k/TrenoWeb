<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Modulo di creazione treni</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/style/modulo.css' />" />
</head>

<body>

<jsp:include page="menu.jsp"></jsp:include>	

<div class="crea">

	<h1>Crea il tuo treno personalizzato:</h1>
	
	<ul>
		<li>H - Locomotiva</li>
	 	<li>P - Vagone Passeggeri</li>
		<li>C - Vagone Cargo</li>
	 	<li>R - Vagone Ristorante</li>
	</ul>

	
    <form action="/TrenoWeb/crea" method="post">
        <label for="inputString">Inserisci una stringa:</label>
        <input type="text" id="sigla" name="sigla" required>
        <input type="hidden" id="id_utente" name="id_utente" value="${id_utente}">
        <br><br>
        <label for="selectOption">Seleziona un'opzione:</label>
        <select id="fabbrica" name="fabbrica" required>
            <option value="IT">Italo</option>
            <option value="TN">Trenord</option>
        </select>
        <br><br>
        <input type="submit" value="Invia">
    </form>

    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>
    </div>
</body>
</html>
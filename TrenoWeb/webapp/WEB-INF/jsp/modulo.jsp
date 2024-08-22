<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Modulo di creazione treni</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/style/modulo.css' />" />
	<script src="<c:url value='/js/modulo.js' />" defer></script>
</head>

<body>

<jsp:include page="menu.jsp"></jsp:include>	

<div class="crea">

	<h1>Crea il tuo treno personalizzato</h1>
	
	<button id="infoButton">Info Treno</button>
	
	<div id="infoPopup" class="popup">
        <div class="popup-content">
            <span class="close"></span>
            <h2>Istruzioni per creare il treno:</h2>
            <p>Inserisci una stringa che rappresenta la configurazione del treno. Ogni lettera rappresenta un tipo di vagone:</p>
            
            <strong>H</strong> - Locomotiva<br>
            <strong>P</strong> - Vagone Passeggeri<br>
            <strong>C</strong> - Vagone Cargo<br>
            <strong>R</strong> - Vagone Ristorante
            
            <p>1- Deve essere presente obbligatoriamente una locomotiva in testa</p>
            <p>2- Può esistere una seconda locomotiva ma solo in coda al treno</p>
            <p>3- Se sono presenti vagoni cargo non possono esserci vagone passeggeri, e viceversa</p>
            <p>4- E' valido un solo vagone ristorante, e non può esistere assieme ai treni cargo</p>
            <p>5- Il treno non può avere più di 20 vagoni</p>
        </div>
    </div>
	<ul>
		<li>H - Locomotiva</li>
	 	<li>P - Vagone Passeggeri</li>
		<li>C - Vagone Cargo</li>
	 	<li>R - Vagone Ristorante</li>
	</ul>

    <c:if test="${not empty errorMessage}">
        <h5 style="color:red;">${errorMessage}</h5>
    </c:if>
	
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


    </div>
</body>
</html>
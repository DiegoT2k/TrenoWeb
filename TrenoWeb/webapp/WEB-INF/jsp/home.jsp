<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/style/home.css' />" />
</head>
<body>

	<jsp:include page="menu.jsp"></jsp:include>


	<h1>La Fabbrica dei Treni</h1>
<!-- Se è loggato mostra messaggio di benvenuto con l'username altrimenti un benvenuto generico -->
	<c:choose>
		<c:when test="${not empty username}">
			<h2>Benvenuto, ${username}!</h2>
		</c:when>
		<c:otherwise>
			<h2>${message}</h2>
		</c:otherwise>
	</c:choose>
	
	<h2>Il tuo sito di fiducia per creare treni in modo semplice ed efficace</h2>
	
	<p>Sei un appassionato di treni o un professionista del settore ferroviario? 
	Allora sei nel posto giusto! Sulla Fabbrica dei Treni, puoi dare vita ai tuoi treni ideali, personalizzando ogni dettaglio, dai vagoni alle locomotive. 
	Con il nostro strumento intuitivo e facile da usare, puoi progettare treni unici per qualsiasi esigenza, che sia un progetto reale 
	o un sogno da appassionato.</p>
	
	<h4>Perché scegliere La Fabbrica dei Treni?</h4>
	<ul>
		<li>Semplice ed Efficace: Grazie alla nostra interfaccia user-friendly, progettare il tuo treno perfetto non è mai stato così facile. 
		Basta qualche click per selezionare i componenti che desideri.</li>
		
		<li>Personalizzazione: Che tu stia cercando un treno per trasporto merci, un treno passeggeri di lusso, o una locomotiva storica, 
		Crea Treno ti permette di configurare ogni aspetto del tuo treno.</li>
		
		<li>Biblioteca di Componenti: Abbiamo una vasta gamma di componenti ferroviari reali e personalizzati tra cui scegliere.</li>
		
		<li>Grafica: I tuoi treni prenderanno vita con immagini grafiche 2D!</li>
		
		<li>Votazione: Puoi condividere ed esprimere pareri con la comunità, assegnando un sistema di votazione a stelle ai treni creati dagli altri utenti!</li>
		
		<li>Acquisto biglietti: Si possono comprare anche i biglietti dei treni.</li>
		
	</ul>

	
</body>
</html>
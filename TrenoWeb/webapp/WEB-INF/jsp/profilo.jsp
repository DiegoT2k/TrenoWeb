<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <title>Profilo Utente</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/style/profilo.css' />" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
	
</head>

<body>
<jsp:include page="menu.jsp"></jsp:include> 
    <header>
        
        <h1>Benvenuto, ${utente.username}</h1>

    </header>
     
    <section>
        <h3>Scheda Utente</h3>
        <p><strong>Nome:</strong> ${utente.nome}</p>
        <p><strong>Cognome:</strong> ${utente.cognome}</p>
        <p><strong>Email:</strong> ${utente.email}</p>
    </section>

    <section>
        <h2>Treni Creati</h2>
        <c:if test="${not empty errorMessage}">
        	<div class="error">
            <c:out value="${errorMessage}" />
        	</div>
	    </c:if>
	    
    	<c:if test="${not empty successMessage}">
        	<div>
            	<c:out value="${successMessage}" />
        	</div>
    	</c:if>
        <c:choose>
            <c:when test="${not empty treniCreati}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID Treno</th>
                            <th>Fabbrica</th>
                            <th>Valutazione Media</th>
                            <th>Peso Totale</th>
                            <th>Prezzo Totale</th>
                            <th>Lunghezza Totale</th>
                            <th>Sigla</th>
                            <th>Azioni</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="treno" items="${treniCreati}">
                            <tr>
                                <td>${treno.id_treno}</td>
                                <td>${treno.fabbrica}</td>
                                <td><span class="emoji">&#x2B50;</span> ${treno.voto}</td>
                                <td>${treno.peso} ton</td>
                                <td>${treno.prezzo} €</td>
                                <td>${treno.lunghezza} m</td>
                                <td>${treno.sigla}</td>
                                <td>
                                <div class="bottoni">
	                                	<form action="<c:url value='/${treno.id_treno}' />" method="get">
	                            			<button type="submit">Visualizza</button>
	                        			</form>                                
	                                
	                                    <form action="<c:url value='/modificaTreno/${treno.id_treno}' />" method="get" style="display:inline;">
	                                        <button type="submit">Modifica Treno</button>
	                                    </form>
	                                    
	                                	<form action="<c:url value='/duplicaTreno' />" method="post" style="display:inline;">
	    									<input type="hidden" name="idTreno" value="${treno.id_treno}"/>
	    									<button type="submit" onclick="return confirm('Sei sicuro di voler duplicare questo treno?');">Duplica Treno</button>
										</form>
										
										<form action="<c:url value='/invertiSigla' />" method="post" style="display:inline;">
	                                        <input type="hidden" name="idTreno" value="${treno.id_treno}"/>
	                                        <button type="submit">Inverti Sigla</button>
	                                    </form>
	                                    
	                                    <form action="<c:url value='/gestisciTreno' />" method="post" style="display:inline;">
	                                        <input type="hidden" name="idTreno" value="${treno.id_treno}"/>
	                                        <input type="hidden" name="azione" value="elimina"/>
	                                        <button type="submit" onclick="return confirm('Sei sicuro di voler eliminare questo treno?');">Elimina Treno</button>
	                                    </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Non hai ancora creato nessun treno.</p>
            </c:otherwise>
        </c:choose>
    </section>
    
    <section class="crea">
        <form action="<c:url value='/modulo' />" method="get">
            <button type="submit">Crea Nuovo Treno</button>
        </form>
    </section>

</body>
</html>

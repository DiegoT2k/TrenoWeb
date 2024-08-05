<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profilo Utente</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/style.css' />" />
</head>
<body>
    <header>
        <h1>Benvenuto, ${utente.username}</h1>
    </header>

    <section>
        <h2>Profilo Utente</h2>
        <p><strong>Nome:</strong> ${utente.nome}</p>
        <p><strong>Cognome:</strong> ${utente.cognome}</p>
        <p><strong>Email:</strong> ${utente.email}</p>
    </section>

    <section>
        <h2>Treni Creati</h2>
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
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="treno" items="${treniCreati}">
                            <tr>
                                <td>${treno.idTreno}</td>
                                <td>${treno.fabbrica}</td>
                                <td>${treno.mediaVoti}</td>
                                <td>${treno.pesoTotale}</td>
                                <td>${treno.prezzoTotale}</td>
                                <td>${treno.lunghezzaTotale}</td>
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
    
    <section>
        <form action="<c:url value='/modulo' />" method="get">
            <button type="submit">Crea Nuovo Treno</button>
        </form>
    </section>

</body>
</html>
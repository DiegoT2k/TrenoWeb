<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifica Treno</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/styles.css' />"> <!-- Link al tuo CSS -->
</head>
<body>
    <h1>Modifica Treno</h1>

    <form action="<c:url value='/modificaTreno/${treno.id_treno}' />" method="post">
        <input type="hidden" name="_method" value="PUT"/> <!-- Usato per simulare il metodo PUT -->

        <div>
            <label for="fabbrica">Fabbrica:</label>
            <input type="text" id="fabbrica" name="fabbrica" value="${treno.fabbrica}" required>
        </div>

        <div>
            <label for="idUtente">ID Utente:</label>
            <input type="number" id="idUtente" name="id_utente" value="${treno.id_utente.id_utente}" required>
        </div>

        <div>
            <label for="peso">Peso:</label>
            <input type="number" id="peso" name="peso" value="${treno.peso}" step="0.01" required>
        </div>

        <div>
            <label for="prezzo">Prezzo:</label>
            <input type="number" id="prezzo" name="prezzo" value="${treno.prezzo}" step="0.01" required>
        </div>

        <div>
            <label for="lunghezza">Lunghezza:</label>
            <input type="number" id="lunghezza" name="lunghezza" value="${treno.lunghezza}" step="0.01" required>
        </div>

        <div>
            <button type="submit">Aggiorna Treno</button>
        </div>
    </form>

    <div>
        <a href="<c:url value='/profilo' />">Torna al Profilo</a>
    </div>
</body>
</html>
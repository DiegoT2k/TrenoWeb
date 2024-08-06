<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modulo di creazione treni</title>
</head>
<body>

<jsp:include page="menu.jsp"></jsp:include>	

	<h1>Crea il tuo treno personalizzato:</h1>
	
    <form action="/TrenoWeb/crea" method="post">
        <label for="inputString">Inserisci una stringa:</label>
        <input type="text" id="sigla" name="sigla" required>
        <input type="hidden" id="id_utente" name="id_utente" value="${id_utente}">
        <br><br>
        <label for="selectOption">Seleziona un'opzione:</label>
        <select id="fabbrica" name="fabbrica" required>
            <option value="IT">IT</option>
            <option value="TN">TN</option>
        </select>
        <br><br>
        <input type="submit" value="Invia">
    </form>

    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>
    
</body>
</html>
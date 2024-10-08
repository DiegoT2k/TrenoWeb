<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>Modifica Treno</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/style/modulo.css' />" />
    
</head>

<body>

	<jsp:include page="menu.jsp" /> 
	
    <h1>Modifica Treno</h1>

    <form action="<c:url value='/modificaTreno/${trenoVO.idTreno}' />" method="post">
        <input type="hidden" name="idTreno" value="${trenoVO.idTreno}"/> <!-- Hidden field for idTreno -->

        <div>
            <label for="sigla">Sigla:</label>
            <input type="text" id="sigla" name="sigla" value="${trenoVO.sigla}" required>
        </div>

        <div>
            <button type="submit">Aggiorna Treno</button>
        </div>
    </form>
    
    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>
    
</body>
</html>
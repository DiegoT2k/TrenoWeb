<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<title>Pagina treni</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/style/treni.css' />" />
    <script src="<c:url value='/js/treni.js' />"></script>

</head>

<body>

<jsp:include page="menu.jsp"></jsp:include>    

<h1>Tabella dei treni</h1>

    <!-- Bottone per mostrare/nascondere i filtri -->
    <button id="toggle-filters">Filtro</button>
    
	<div id="filters" style="display: none;">
    
    <form:form method="POST" 
        action="filtro"       
        modelAttribute="filtroVO"> 
        <div class="form-group">
		    <form:label path="pesoMin">Peso min:</form:label>
		    <form:input path="pesoMin"/>
        </div>
        <div class="form-group">
		    <form:label path="pesoMax">Peso max:</form:label>
		    <form:input path="pesoMax" />
        </div>
        <div class="form-group">
		    <form:label path="lunghezzaMin">Lunghezza min:</form:label>
		    <form:input path="lunghezzaMin" />
        </div>
        <div class="form-group">
            <form:label path="lunghezzaMax">Lunghezza max:</form:label>
            <form:input path="lunghezzaMax"/>
        </div>
        <div class="form-group">
            <form:label path="prezzoMin">Prezzo min:</form:label>
            <form:input path="prezzoMin"/>
        </div>
        <div class="form-group">
            <form:label path="prezzoMax">Prezzo max:</form:label>
            <form:input path="prezzoMax"/>
        </div>
        <div class="form-group">
	        <form:label path="sigla">Cerca sigla:</form:label>
	        <form:input path="sigla"/>
	    </div>
        <div class="form-group">
	        <form:label path="username">Cerca username:</form:label>
	        <form:input path="username"/>
	    </div>
	    <div class="form-group">
            <form:input type="hidden" path="sortField" id="sortField" name="sortField" />
            <form:input type="hidden" path="sortOrder" id="sortOrder" name="sortOrder" />
        </div>

        
        <div>
        	<button type="button" id="sortLunghezza" onclick="setSort('lunghezza', 'ASC')">Ordina per lunghezza</button>
        	<button type="button" id="sortPeso" onclick="setSort('peso', 'ASC')">Ordina per peso</button>
        	<button type="button" id="sortPrezzo" onclick="setSort('prezzo', 'ASC')">Ordina per prezzo</button>
    	</div>
    	
 	    <div class="form-group">
            <input type="submit" class="bottoni" value="Cerca"/>
        </div>
        
    </form:form>

    </div>



<table border="1">
    <thead>
        <tr>
            <th>ID_Treno</th>
            <th>Utente</th>
            <th>Treno</th>
            <th>Sigla</th>
            <th>Fabbrica</th>
            <th>Peso tot</th>
            <th>Lunghezza tot</th>
            <th>Prezzo tot</th>   
            <th>Valutazione</th>     
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${listaTreni}">
            <tr>
                <td>${item.id_treno}</td>
                <td>${item.id_utente}</td>
                <td>
                <form action="<c:url value='/${item.id_treno}' />" method="get">
                      <button type="submit">Visualizza</button>
                </form> 
                </td>
                <td>${item.sigla}</td>
                <td>${item.fabbrica}</td>
                <td>${item.peso} ton</td>
                <td>${item.lunghezza} m</td>
                <td>${item.prezzo} €</td>
                <td><span class="emoji">&#x2B50;</span> ${item.voto}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>

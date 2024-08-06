<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>Pagina treni</title>

<!-- <link rel="stylesheet" type="text/css" href="webapp/WEB-INF/css/table.css">  -->

<style>

    h1{
        text-align:center;
        color: blue;
    }
    
    table{
        width: 100%;
        border-collapse: collapse;
        margin-top: 10px;
    }
    
    td, th {
        border: 1px solid black;
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #dddddd;
    }
    
    .modifica{
        background-color: #73ba4c;
    }
    
    .elimina{
        background-color: #de3f23;
    }
    
    .vota{
        background-color: #dec223;
    }
    
    .emoji {
        font-size: 1.2em; /* Regola la dimensione dell'emoji se necessario */
        color: gold; /* Cambia il colore se desiderato */
    }

    /* Stili per il popup */
    #voteForm {
        display: none;
        position: fixed;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        padding: 20px;
        border-radius: 10px;
        background-color: #f2f2f2;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        z-index: 1000;
    }

    /* Stili per lo sfondo scuro dietro il popup */
    #overlay {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        z-index: 500;
    }

    /* Stili per i pulsanti all'interno del popup */
    #voteFormContent button {
        margin: 5px;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    #voteFormContent button[type="submit"] {
        background-color: #73ba4c;
        color: white;
    }

    #voteFormContent button[type="button"] {
        background-color: #de3f23;
        color: white;
    }
    
    .selected {
        background-color: #4CAF50; /* Colore di esempio */
        color: white;
    }

</style>
</head>
<body>

<jsp:include page="menu.jsp"></jsp:include>    

<h1>Tabella dei treni:</h1>

    <!-- Bottone per mostrare/nascondere i filtri -->
    <button id="toggle-filters">Filtro</button>
    
	<div id="filters" class="mt-3" style="display: none;">
    
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
            <input type="submit" value="Cerca"/>
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
                <td>${item.peso}</td>
                <td>${item.lunghezza}</td>
                <td>${item.prezzo}</td>
                <td><span class="emoji">&#x2B50;</span> ${item.voto}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<script>
	$(document).ready(function(){
	    $("#toggle-filters").click(function(){
	        $("#filters").toggle();
	    });
	});
	
   function setSort(field, order) {
        document.getElementById('sortField').value = field;
        document.getElementById('sortOrder').value = order;
        
        // Rimuovi la classe 'selected' da tutti i bottoni
        document.getElementById('sortLunghezza').classList.remove('selected');
        document.getElementById('sortPeso').classList.remove('selected');
        document.getElementById('sortPrezzo').classList.remove('selected');

        // Aggiungi la classe 'selected' al bottone cliccato
        if (field === 'lunghezza') {
            document.getElementById('sortLunghezza').classList.add('selected');
        } else if (field === 'peso') {
            document.getElementById('sortPeso').classList.add('selected');
        } else if (field === 'prezzo') {
            document.getElementById('sortPrezzo').classList.add('selected');
        }
    }
</script>

</body>
</html>

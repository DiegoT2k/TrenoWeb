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
            <input type="submit" value="Cerca"/>
        </div>
    </form:form>

    </div>

   <div>
        <button>Ordina per lunghezza</button>
        <button>Ordina per peso</button>
        <button>Ordina per prezzo</button>
    </div>

<table border="1">
    <thead>
        <tr>
            <th>ID_Treno</th>
            <th>Utente</th>
            <th>Treno</th>
            <th>Sigla</th>
            <th>Fabbrica</th>
            <th>Posti</th>
            <th>Peso tot</th>
            <th>Lunghezza tot</th>
            <th>Prezzo tot</th>   
            <th>Valutazione</th>     
            <th>#</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${listaTreni}">
            <tr>
                <td>${item.id_treno}</td>
                <td>${item.id_utente}</td>
                <td><button type="button" class="modifica" onclick="document.location='home'">Mostra</button></td>
                <td>${item.sigla}</td>
                <td>${item.fabbrica}</td>
                <td>${item.biglietti} <button type="button" class="vota">Acquista</button></td>
                <td>${item.peso}</td>
                <td>${item.lunghezza}</td>
                <td>${item.prezzo}</td>
                <td><span class="emoji">&#x2B50;</span> ${item.voto}</td>
                <td><button type="button" class="vota" onclick="openVoteForm('${item.id_treno}')">Vota</button></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- Sfondo scuro dietro il popup -->
<div id="overlay" onclick="closeVoteForm()"></div>

<!-- Modulo di valutazione popup -->
<div id="voteForm">
    <h3>Vota il Treno</h3>
    <form id="voteFormContent" method="post" action="addVoto">
        <input type="hidden" id="trenoId" name="trenoId">
        <label for="rating">Valutazione:</label>
        <input type="number" id="rating" name="rating" min="1" max="5" required>
        <button type="submit">Invia</button>
        <button type="button" onclick="closeVoteForm()">Annulla</button>
    </form>
</div>

<script>
function openVoteForm(trenoId) {
    document.getElementById('trenoId').value = trenoId;
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('voteForm').style.display = 'block';
}

function closeVoteForm() {
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('voteForm').style.display = 'none';
}

$(document).ready(function(){
    $("#toggle-filters").click(function(){
        $("#filters").toggle();
    });
});
</script>

</body>
</html>

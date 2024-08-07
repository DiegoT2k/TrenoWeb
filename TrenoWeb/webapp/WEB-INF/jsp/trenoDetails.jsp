<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dettagli Treno</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
    
    <style>
      
    .vota{
        background-color: #dec223;
    }
   
   .bottoni{
   		padding-top:30px;
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


	/* stile per il bottone di biglietto comprato */
  	.overlay2 {
	    position: fixed;
	    top: 0;
	    left: 0;
	    width: 100%;
	    height: 100%;
	    background: rgba(0, 0, 0, 0.5); 
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    color: white;
	    font-size: 20px;
	    z-index: 1000; 
	    visibility: hidden; 
  	 }
  	 
	  .overlay-content2 {
	    background: blue;
	    padding: 20px;
	    border-radius: 10px;
	    text-align: center;
	  }

    .close-button {
      background: red;
      color: white;
      border: none;
      border-radius: 5px;
      padding: 5px 10px;
      cursor: pointer;
      font-size: 16px;
      margin-top: 10px; 
    }

</style>

</head>
<body>

	<jsp:include page="menu.jsp"></jsp:include>  
 
    <h1>Dettagli Treno</h1>
    <p><strong>ID Treno:</strong> ${trenoCompleto.id_treno}</p>
    <p><strong>Sigla:</strong> ${trenoCompleto.sigla}</p>
    <p><strong>Fabbrica:</strong> ${trenoCompleto.fabbrica.sigla}</p>
    <p><strong>Voto:</strong> ${trenoCompleto.voto}</p>
<%--     <p><strong>Peso:</strong> ${trenoCompleto.peso}</p>
    <p><strong>Prezzo:</strong> ${trenoCompleto.prezzo}</p>
    <p><strong>Lunghezza:</strong> ${trenoCompleto.lunghezza}</p> --%>
    <p><strong>Biglietti disponibili:</strong> ${trenoCompleto.biglietti}</p>
    <p><strong>Utente:</strong> ${trenoCompleto.id_utente.username}</p>

    <form action="<c:url value='/acquista/${trenoCompleto.id_treno}' />" method="get" style="display:inline;">
        <button type="submit">Acquista Biglietti</button>
    </form>

    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>
    
	  <div id="overlay2" class="overlay2">
	  
	  <button class="close-button" onclick="closeOverlay()">X</button>
	    <div class="overlay-content2" id="overlayMessage"></div>
	  </div>
	  
	  <script type="text/javascript">
	    <c:if test="${not empty buyMessage}">
	      document.getElementById('overlayMessage').innerText = '${buyMessage}';
	      document.getElementById('overlay2').style.visibility = 'visible';
	    </c:if>
	  </script>

	<button type="button" class="vota" onclick="openVoteForm('${trenoCompleto.id_treno}')">Vota</button>
	<div class="train-image" id="trainImageContainer"></div>

	<div class="bottoni">
	    <a href="<c:url value='/treni' />">Tutti i treni</a><br>
	    <a href="<c:url value='/profilo' />">I tuoi treni</a><br>
	    <a href="<c:url value='/modificaTreno/${trenoCompleto.id_treno}' />">Modifica Treno</a>
    </div>
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
    
	    function closeOverlay() {
	        document.getElementById('overlay2').style.visibility = 'hidden';
	    }
    
        const sigla = "${treno.sigla}";
        console.log(sigla);
        const trainImageContainer = document.getElementById('trainImageContainer');
        const imageBasePath = '<c:url value="webapp/WEB-INF/img/" />'; // Base path delle immagini

        function loadTrainImages(sigla) {
            sigla.split('').forEach(letter => {
                const img = document.createElement('img');
                img.src = imageBasePath + letter + ".png";
                img.alt = letter;
                trainImageContainer.appendChild(img);
            });
        }

        loadTrainImages(sigla);


        function openVoteForm(trenoId) {
            document.getElementById('trenoId').value = trenoId;
            document.getElementById('overlay').style.display = 'block';
            document.getElementById('voteForm').style.display = 'block';
        }

        function closeVoteForm() {
            document.getElementById('overlay').style.display = 'none';
            document.getElementById('voteForm').style.display = 'none';
        }

        </script>
    
</body>
</html>

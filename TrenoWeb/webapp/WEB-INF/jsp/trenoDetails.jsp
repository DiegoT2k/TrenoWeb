<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>Dettagli Treno</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/style/trenoDetails.css' />" />    
	
	
</head>

<body>

	<jsp:include page="menu.jsp"></jsp:include>  
 
    <h1>Dettagli Treno</h1>
	<div class="train-image" id="trainImageContainer"></div>
    <p><strong>ID Treno:</strong> ${trenoCompleto.id_treno}</p>
    <p><strong>Sigla:</strong> ${trenoCompleto.sigla}</p>
    <p><strong>Fabbrica:</strong> ${trenoCompleto.fabbrica.sigla}</p>
    <p><strong>Voto:</strong> ${trenoCompleto.voto}</p>
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


<%-- 	<div class="bottoni">
	    <a href="<c:url value='/treni' />">Tutti i treni</a><br>
	    <a href="<c:url value='/profilo' />">I tuoi treni</a><br>
	    <a href="<c:url value='/modificaTreno/${trenoCompleto.id_treno}' />">Modifica Treno</a>
    </div> --%>
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

    const imageMap = {
    			'H': 'locomotivaIt.png',
        		'P': 'passeggeriIt.png',
        		'R': 'passeggeriIt.png',
        		'C': 'cargoIT.png'
   		};
    
    const sigla = "${trenoCompleto.sigla}";

    const trainImageContainer = document.getElementById('trainImageContainer');
    const imageBasePath = '<c:url value="resources/" />'; // Base path delle immagini

    loadTrainImages(sigla);
    
    function loadTrainImages(sigla) {
    	console.log(sigla);
    	let locomotiveCount = 0;
    	
        sigla.split('').forEach(letter => {
            const img = document.createElement('img');
         
            if (letter === 'H') {
            	locomotiveCount++;
            	
            	if(locomotiveCount === 2) {
            		img.src = imageBasePath + 'locomotivaIt-Inverti.png';
            	}  else {
                	img.src = imageBasePath + imageMap['H'];
                }
            }  else {
            	img.src = imageBasePath + imageMap[letter];
         }

            img.alt = letter;
            
            trainImageContainer.appendChild(img);
        });
    }
    
    function closeOverlay() {
        document.getElementById('overlay2').style.visibility = 'hidden';
    }

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

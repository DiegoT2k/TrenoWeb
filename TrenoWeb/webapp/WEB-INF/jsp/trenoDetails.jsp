<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dettagli Treno</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
    
</head>
<body>
    <h1>Dettagli Treno</h1>
    <p><strong>ID Treno:</strong> ${treno.id_treno}</p>
    <p><strong>Sigla:</strong> ${treno.sigla}</p>
    <p><strong>Fabbrica:</strong> ${treno.fabbrica}</p>


	<div class="train-image" id="trainImageContainer"></div>


    <a href="<c:url value='/profilo' />">Torna all'elenco treni dell'utente</a>
    <a href="<c:url value='/modificaTreno/${treno.id_treno}' />">Modifica Treno</a>
    
    <script>
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
    </script> 
    
</body>
</html>

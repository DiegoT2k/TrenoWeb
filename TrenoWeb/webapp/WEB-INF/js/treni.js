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
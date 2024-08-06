<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
</head>
<body>

	<jsp:include page="menu.jsp"></jsp:include>

<!-- Se è loggato mostra messaggio di benvenuto con l'username altrimenti un benvenuto generico -->
	<c:choose>
		<c:when test="${not empty username}">
			<h1>Benvenuto, ${username}!</h1>
		</c:when>
		<c:otherwise>
			<h1>${message}</h1>
		</c:otherwise>
	</c:choose>
	
	<h2>Il tuo sito di fiducia per creare treni in modo semplice ed efficace</h2>
	
	<p>Descrizione lunga del sito:
	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ut magna dictum, tincidunt dolor malesuada, cursus orci. Nunc sit amet augue et neque imperdiet dictum. Praesent diam ex, fringilla sed ex id, malesuada eleifend purus. 
	Donec ultricies maximus diam, quis laoreet dolor. In dapibus ante et mollis efficitur. 
	Pellentesque pellentesque libero enim, eu maximus erat vehicula sit amet. Fusce ac mi ante. Phasellus ullamcorper, nunc a sollicitudin pellentesque, lacus erat feugiat tellus, at porta dolor est nec ex. In non fringilla magna, ut feugiat nunc. 
	Vivamus malesuada scelerisque erat eget convallis. </p>
	
	<p> Proin feugiat enim id enim mattis rutrum. Phasellus nec condimentum felis.
	Proin vel massa eget lorem porttitor elementum. 
	Nulla ut lacinia eros. In hac habitasse platea dictumst. 
	Proin sapien eros, cursus ut augue in, lobortis lobortis risus. 
	Pellentesque eu efficitur nibh, id consectetur massa. Aliquam erat volutpat. Nam et magna et mauris cursus cursus. 
	Donec ac nibh aliquam, faucibus urna ac, ornare ipsum. Etiam eu tincidunt est, nec ultricies urna.</p>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrazione</title>
</head>
<body>
 <h1>Registrazione</h1>
    <form action="/TrenoWeb/checkRegistrazione">
        <div>
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" placeholder="Inserisci Nome"/>
        </div>
        <div>
            <label for="cognome">Cognome:</label>
            <input type="text" id="cognome" name="cognome" placeholder="Inserisci Cognome"/>
        </div>
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" placeholder="Inserisci Username"/>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" placeholder="Inserisci Email"/>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="text" id="password" name="password" placeholder="Inserisci Password"/>
        </div>
        <div>
            <button type="submit">Registrati</button>
        </div>
    </form>
</body>
</html>
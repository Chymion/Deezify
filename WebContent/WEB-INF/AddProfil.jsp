<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

    <style>
        <%@ include file="style.css"%>
    </style>

    <head>
        <title> Ajouter un profil </title>
    </head>

    <body>
        <%@ include file="Menu.jsp"%>
        <div class="formulaire">
       		<p></p>
    		Nom : <input type="text" name="nom" required /><br>
			<p></p>
			Prenom: <input type="text" name="prenom" required /><br>
			<p></p>
			Pseudo: <input type="text" name="pseudo" required /><br>
			<p></p>
			Anniversaire: <input type="date" name="dateAnniv" required /><br>
			<p></p>
			Mot de passe: <input type="password" name="pseudo" required /><br>
			<input type="button" value="Valider" class="boutonSub" >
    	</div>
    </body>

</html>
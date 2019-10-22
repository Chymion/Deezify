<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

    <style>
        <%@ include file="style.css"%>
    </style>

    <head>
        <title> Connection </title>
    </head>

    <body>
        <%@ include file="Menu.jsp"%>
       	<div class="titre">
    		<p> Connexion :</p>
    	</div>
    	 <div class="formulaire">
       		<p></p>
    		Pseudo : <input type="text" name="pseudo" required /><br>
			<p></p>
			Mot de passe : <input type="password" name="modDePasse" required /><br>
			<p></p>
			<input type="button" value="Valider" class="boutonSub" >
		</div>
    </body>

</html>
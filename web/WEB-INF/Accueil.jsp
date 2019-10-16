<%--
  Created by IntelliJ IDEA.
  User: mateo
  Date: 15/10/2019
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title> Accueil </title>
    </head>

    <body>
        <p> bonjour ${ auteur.prenom } ${ auteur.nom }</p>
        <p> ${ auteur.actif ? 'Vous êtes actif !' : 'Vous ête inactif!' } </p>
    </body>

</html>

<%--
  Created by IntelliJ IDEA.
  User: mateo
  Date: 17/10/2019
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ page import="model.Utilisateur" %>

    <style>
        <%@ include file="../style.css"%>
    </style>

    <head>
        <title> Recommandations </title>
    </head>
	<% Utilisateur utilisateur = (Utilisateur)session.getAttribute( "utilisateur" ); %>
    <body>
        <%@ include file="../Menu.jsp"%>
        <p class="sousTitre"> Profil </p>
        <p class="sousTitre2"> nom : <% out.print(utilisateur.getNom(  )); %> </p>
        <p class="sousTitre2"> prenom : <% out.print(utilisateur.getPrenom(  )); %> </p>
        <p class="sousTitre2"> Pseudo : <% out.print(utilisateur.getPseudo(  )); %> </p>
        <p class="sousTitre2"> Email : <% out.print(utilisateur.getEmail(  )); %> </p>
        <%@ include file="Lecteur.jsp"%>	
    </body>

</html>

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
        <p class="sousTitre3"> Profil </p>
        <p class="sousTitre2"> Nom : <font style="color:white"> <% out.print(utilisateur.getNom(  )); %> </font> </p>
        <p class="sousTitre2"> Prénom : <font style="color:white"> <% out.print(utilisateur.getPrenom(  )); %> </font> </p>
        <p class="sousTitre2"> Pseudo : <font style="color:white"> <% out.print(utilisateur.getPseudo(  )); %> </font> </p>
        <p class="sousTitre2"> Email : <font style="color:white"> <% out.print(utilisateur.getEmail(  )); %> </font> </p>
        <%@ include file="Lecteur.jsp"%>	
    </body>

</html>

<%--
  Created by IntelliJ IDEA.
  User: mateo
  Date: 17/10/2019
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <style>
        <%@ include file="style.css"%>
    </style>

    <head>
        <title> Ma Musique </title>
    </head>

    <body>
        <%@ include file="Menu.jsp"%>
        <p class="sousTitre"> Ma Musique </p>
        <p class="sousTitre2"> Titres </p>
        <p class="sousTitre2"> Artistes </p>
        <p class="sousTitre2"> Albums </p>
        <p class="sousTitre2"> Playlists </p>
        <%@ include file="Lecteur.jsp"%>
    </body>

</html>

<%--
  Created by IntelliJ IDEA.
  User: mateo
  Date: 17/10/2019
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <style>
        <%@ include file="style.css"%>
    </style>

    <head>
        <title> Explorer </title>
    </head>

    <body>
        <%@ include file="Menu.jsp"%>
        <p class="sousTitre"> Recherche </p>
        <form id="conteneurRecherche" method="post" action="/DeezifyWeb/Explorer">
            <input  type="text"
                    class="textField"
                    id="recherche"
                    name="recherche"/>
            <button> rechercher </button>
        </form>
        <%
            String recherche = (String) request.getAttribute("recherche");
            if (request.getAttribute("recherche") != null)
                out.println(recherche);
        %>
    </body>

</html>

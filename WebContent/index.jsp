<%--
  Created by IntelliJ IDEA.
  User: mateo
  Date: 15/10/2019
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <style>
        <%@ include file="style.css"%>
    </style>

    <head>
        <title> Accueil </title>
    </head>

    <body>
   
        <%@ include file="Menu.jsp"%>
        <p class="bienvenue">
            Bienvenue sur Deezify !
        </p>
        <p class="paragrapheBienvenue">
            La <font color="orange">(future)</font> meilleure plateforme de streaming musical !
        </p>
        
      
        <%request.getSession(  ).invalidate(  );%>
       
  
      
        
       
        
    </body>

</html>

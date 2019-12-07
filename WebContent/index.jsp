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
   
        
        <%--
  Created by IntelliJ IDEA.
  User: mateo
  Date: 16/10/2019
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<header>
    <div id="conteneurRight">
    
        	<form action="Connexion"  method="post">
            <input class="connexion"  type="submit"value="Connexion" name="connexion"/>
            </form>
        
    </div>
    
    <h1> <font color="orange"> ▶︎ </font> Deezify </h1>
    
    <div id="conteneur">
        <div id="boutonMenu">
            <a href="/DeezifyWeb/Accueil"> Accueil </a>
        </div>
        <div id="boutonMenu">
            <a href="/DeezifyWeb/Recommandations"> Recommandations </a>
        </div>
        
        <div id="boutonMenu">
            <a href="/DeezifyWeb/Explorer"> Explorer </a>
        </div>
        
    </div>
    
</header>

        
        
        <p class="bienvenue">
            Bienvenue sur Deezify !
        </p>
        <p class="paragrapheBienvenue">
            La <font color="orange">(future)</font> meilleure plateforme de streaming musical !
        </p>
        
      
        <%request.getSession(  ).invalidate(  );%>
       
  
      
        
       
        
    </body>

</html>

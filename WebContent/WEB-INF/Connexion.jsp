<%--
  Created by IntelliJ IDEA.
  User: mateo
  Date: 17/10/2019
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<style>
    <%@ include file="../style.css"%>
</style>

<head>
    <title> Connexion </title>
</head>

<body>
    <%@ include file="../Menu.jsp"%>
    <p class="sousTitre"> Connexion </p>
    <div id="conteneurConnexion">
    
    	
    
        <form id="conteneurConnexion2" method="post" action="Connexion">
            <p>
                <label for="pseudo"> Pseudo : </label>
                <input  type="text"
                        class="field"
                        id="pseudo"
                        name="pseudo"/>
            </p>
            <p>
                <label for="password"> Mot de passe : </label>
                <input  type="text"
                        class="field"
                        id="password"
                        name="password"/>
            </p>
            <br />
            <% 
    	
    			if ( request.getAttribute( "message" ) != null ){
    		
    			    if ( ((String)request.getAttribute( "message" )).equals( "Échec de la connexion, cet utilisateur n'existe pas" ) )
    			    	out.print((String)request.getAttribute( "message" )); 
    			    else {                 
    			        
    			        this.getServletContext().getRequestDispatcher( "/Accueil" ).forward( request, response );
    			    }
    			    
    			}
    	
    		%>
            <button> Connexion </button>
            <p> vous n'avez pas encore de compte ? <a class="lienInscription" href="/DeezifyWeb/Inscription"> Inscrivez vous </a> </p>
        </form>
    </div>
</body>

</html>

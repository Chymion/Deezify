<%--
  Created by IntelliJ IDEA.
  User: mateo
  Date: 16/10/2019
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" 
	
%>

<%@page import = "java.util.ArrayList" 
import="java.util.HashMap" 
import="java.util.List" 
import="java.util.Map"
import="model.Musique"
import="model.Utilisateur"  %>
<!DOCTYPE html>
<header>

	
    <div id="conteneurRight">
    	
    	<% 
    	
    	
    	if (request.getSession().getAttribute( "utilisateur" ) != null)
    	    out.print( "<h3> Bienvenue <strong>" + ((Utilisateur)request.getSession().getAttribute( "utilisateur" )).getPseudo(  ) + "</strong> !! </h3>" );
    	
    	
    	
    	if (request.getParameter( "deconnexion" ) != null){ 
    	    request.getSession().removeAttribute( "utilisateur" );
    	    request.getSession().removeAttribute( "tabPlaylist" );
    	    request.getSession().removeAttribute( "supprimerPlaylist" );
    	    request.getSession().removeAttribute( "tabPlaylist" );
    	    request.getSession().removeAttribute( "nomListeMaMusique" );
    	    request.getSession().removeAttribute( "tabMusiqueMaMusique" );
    	    request.getSession().removeAttribute( "tabPlaylistUtilisateur" );
    	    response.sendRedirect( request.getContextPath(  ) + "/Accueil" );
    	    
    	    }
    	
    	if (request.getSession().getAttribute( "utilisateur" ) == null){ %>
    
       
        	<form action="Connexion"  method="post">
        	<p>
            <input class="connexion"  type="submit"value="Connexion" name="connexion"/>
            </p>
            </form>
        
        
        <% }else{ %>
        
        	
            <form action="${ sessionScope.nomPage }"  method="post">
            <p>
            <input class="connexion" type="submit" value="Deconnexion" name="deconnexion"/>
            </p>
            </form>
            
       
        
        <%  }%>
        
    </div>
    
    <h1> <font color="orange"> ▶︎ </font> Deezify </h1>
    
    
    
    
    
    <div id="conteneur">
    
        <div id="boutonMenu">
            <a href="/DeezifyWeb/Accueil"> Accueil </a>
        </div>
        
        <%if (request.getSession().getAttribute( "utilisateur" ) != null){%>
        
        <div id="boutonMenu">
            <a href="/DeezifyWeb/Recommandations"> Recommandations </a>
        </div>
        
        <%}if (request.getSession().getAttribute( "utilisateur" ) != null){%>
        
        <div id="boutonMenu">
            <a href="/DeezifyWeb/Musique"> Ma Musique </a>
        </div>
        
         <%}%>
        
        <div id="boutonMenu">
            <a href="/DeezifyWeb/Explorer"> Explorer </a>
        </div>
        
        <%if (request.getSession().getAttribute( "utilisateur" ) != null){%>
        
        <div id="boutonMenu">
            <a href="/DeezifyWeb/Profil"> Profil </a>
        </div>
        
        <%}%>
      
        
    </div>
    
</header>
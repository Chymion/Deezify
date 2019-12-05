<%--
  Created by IntelliJ IDEA.
  User: mateo
  Date: 15/10/2019
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@page import = "java.util.ArrayList" 
import="java.util.HashMap" 
import="java.util.List" 
import="java.util.Map"
import="model.ListeMusique"
import="model.Playlist"
import="model.Album"  %>
    <style>
        <%@ include file="style.css"%>
    </style>

    <head>
        <title> Playlist </title>
    </head>

    <body>
        <%@ include file="Menu.jsp"%>
        
        
<%-- Affichage des Playlists --%>
	<h2>Playlists</h2>

    <%
		 
        List<ListeMusique> tabPlaylist = (ArrayList<ListeMusique>) request.getAttribute( "tabPlaylist" );
    	ListeMusique ligneActuelle = null;
		        
	%>
  
  	
  	<div class="box">
	
  		 <%   
  		 for(int k = 0; k < tabPlaylist.size() ; k++ ){
  		     
  		    ligneActuelle = tabPlaylist.get( k );
  		     
  			out.print("<div class = \"elem\">");
  			
  			out.print("<form action=\"ListeMusique\" method=\"post\">");
  			out.print("<button type=\"submit\" name=\"nomListe\" value=\"" +  ligneActuelle.getNomListe(  )  + "\" class=\"sousTitre\">");
  			out.print(ligneActuelle.getNomListe(  ) );
  			out.print("</button>");
  			out.print("</form>");
  			
  			out.print("<br/>");
  			
  			out.print("<td>");
			out.print("<form action=\"ListeMusique\" method=\"post\">");
			out.print("<button type=\"submit\" name=\"nomListe\" value=\"" +  ligneActuelle.getNomListe(  )   + "\" class=\"sousTitre\">");
			out.print("<img src=\"" + "\" class=\"lienGenre\" ");
			out.print("</button>");
			out.print("</form>");
			
			out.print("</div>");
  		   
  		 }
  		 %> 
  	
	</div>  		 
  	
<%-- Affichage des Albums --%>

	<h2>Albums</h2>

	<%
		 
	List<ListeMusique> tabAlbum = (ArrayList<ListeMusique>) request.getAttribute( "tabAlbum" );
    	ligneActuelle = null;
		        
	%>
  
  	
  	<div class="box">
		
  		 <%   
  		 for(int k = 0; k < tabAlbum.size() ; k++ ){
  		     
  		    ligneActuelle = tabAlbum.get( k );
  		     
  			out.print("<div class = \"Elem\">");
  			
  			out.print("<form action=\"ListeMusique\" method=\"post\">");
  			out.print("<button type=\"submit\" name=\"nomListe\" value=\"" +  ligneActuelle.getNomListe(  )  + "\" class=\"sousTitre\">");
  			out.print(ligneActuelle.getNomListe(  ));
  			out.print("</button>");
  			out.print("</form>");
  			
  			out.print("<br/>");
  			
  			out.print("<td>");
			out.print("<form action=\"ListeMusique\" method=\"post\">");
			out.print("<button type=\"submit\" name=\"nomListe\" value=\"" +  ligneActuelle.getNomListe(  )  + "\" class=\"sousTitre\">");
			out.print("<img src=\"" + "\" class=\"lienGenre\" ");
			out.print("</button>");
			out.print("</form>");
			
			out.print("</div>");
  		   
  		 }
  		 %> 
	</div>  		 
   
</body>

</html>

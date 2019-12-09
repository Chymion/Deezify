<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import = "java.util.ArrayList" 
import="java.util.HashMap" 
import="java.util.List" 
import="java.util.Map"
import="model.Musique"  %>


<html>

    <style>
        <%@ include file="../style.css"%>
    </style>

    <head>
        <title> Playlist </title>
    </head>

    <body>
        <%@ include file="../Menu.jsp"%>
        
        
        <%
               
        ArrayList<Musique> tabMusique = (ArrayList<Musique>) request.getAttribute( "tabMusique" );
        String nomListe = (String) request.getAttribute( "nomListe" );
        
    	Musique ligneActuelle = null;
        
    	out.print( "<h1>" + nomListe + "</h1>" );
        %>
        
        
        
        <div class="box">
	
  		 <%   
  		
  		 for(int k = 0; k < tabMusique.size() ; k++ ){
  		     
  		    ligneActuelle = tabMusique.get( k );
  		     
  		    
  		    /*Affichage des informations sur la musique*/
  		    
  			out.print("<div class = \"elemMusique\">");
  			
  			out.print("<form action=\"ListeMusique\" method=\"post\">");
  			
  			// Nom de la musique
  			out.print(" <form id=\"conteneurLecteur\" method=\"post\" action=\"ListeMusique\"> "+
  					"<input type=\"submit\" value=\"" + tabMusique.get(k).getNomMusique(  ) + "\" class=\"sousTitre\" name=\"music\" /></form>");
  			
  			
  			out.print("<br/>");
  			
  			// Nom artiste
 
  			out.print("Artiste : " + ligneActuelle.getArtiste(  ).getNom(  ) + "<br/>");
  			
  			
  			out.print("<br/>");
  			
  			//Date de sortie
			
			out.print("Date de sortie : " + ligneActuelle.getDate(  ));
			
			
			out.print("<br/>");
  			
  			//Duree
			
			out.print("Durée : " + ligneActuelle.getDuree(  ));
  			
  			
			out.print("<br/>");
			out.print("</form>");
			
			out.print("</div>");
			
			
  		   
  		 }
        
  		 %> 
  	
	</div>
	
	<%@ include file="Lecteur.jsp"%> 		 
        
	</body>

</html>

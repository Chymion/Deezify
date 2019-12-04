<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import = "java.util.ArrayList" 
import="java.util.HashMap" 
import="java.util.List" 
import="java.util.Map"  %>


<html>

    <style>
        <%@ include file="style.css"%>
    </style>

    <head>
        <title> Playlist </title>
    </head>

    <body>
        <%@ include file="Menu.jsp"%>
        
        
        <%
        
        List<Map<String, String>> tabListe = (List<Map<String, String>>) request.getAttribute( "tabListe" );
        String nomListe = (String) request.getAttribute( "nomListe" );
        ArrayList<String> tabNomMusique = (ArrayList<String>) request.getAttribute( "tabNomMusique" );
        
    	Map<String, String> ligneActuelle = null;
        
    	out.print( "<h1>" + nomListe + "</h1>" );
        %>
        
        
        
        <div class="box">
	
  		 <%   
  		 int i = 0;
  		 for(int k = 0; k < tabListe.size() && i < (tabNomMusique.size()+2) ; k++ ){
  		     
  		    ligneActuelle = tabListe.get( k );
  		     
  		    
  		    /*Affichage des informations sur la musique*/
  		    
  			out.print("<div class = \"elemMusique\">");
  			
  			out.print("<form action=\"ListeMusique\" method=\"post\">");
  			
  			// Nom de la musique
  			out.print("<span name=\"nomListe\" value=\"" +  ligneActuelle.get( "nom" )  + "\" class=\"sousTitre\">");
  			out.print(ligneActuelle.get( "nom" ));
  			out.print("</span>");
  			
  			
  			out.print("<br/>");
  			
  			// Nom artiste
 
  			out.print("Artiste : " + ligneActuelle.get( "nomArtiste" ) + "<br/>");
  			
  			
  			out.print("<br/>");
  			
  			//Date de sortie
			
			out.print("Date de sortie : " + ligneActuelle.get( "date" ));
			
			
			out.print("<br/>");
  			
  			//Duree
			
			out.print("Durée : " + ligneActuelle.get( "duree" ));
  			
  			
			out.print("<br/>");
			
			//Bouton Play/pause
			
			
			out.print(" <form id=\"conteneurLecteur\" method=\"post\" action=\"ListeMusique\"> "+
			"<input type=\"submit\" value=\"" + tabNomMusique.get(i) + "\" class=\"boutonMusique\" name=\"music\" /></form>");
		
			out.print("</form>");
			
			out.print("</div>");
			
			
  		   i++;
  		 }
  		 %> 
  	
	</div>  		 
        
  
	</body>

</html>

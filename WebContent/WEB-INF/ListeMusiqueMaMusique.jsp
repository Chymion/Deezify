<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import = "java.util.ArrayList" 
import="java.util.HashMap" 
import="java.util.List" 
import="java.util.Map"
import="model.Musique"
import="model.Playlist"  %>


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
         
        ArrayList<Musique> tabMusique = new ArrayList<Musique>();
        ArrayList<Playlist> tabPlaylist = new ArrayList<Playlist>();
        
        
        
        // Si on vient de servletMusique
        if (request.getAttribute( "tabMusique" ) != null){
        	tabMusique = (ArrayList<Musique>) request.getAttribute( "tabMusique" );
        	String nomListe = (String) request.getAttribute( "nomListe" );
        	if (request.getAttribute( "nomListe" ) != null)
        		out.print( "<h1>" + nomListe + "</h1>" );
        }
     
        
        
        
        
        if (!tabMusique.isEmpty(  ))
  		    	out.print( "<h2> Musiques </h2>" );
        %>
        
        <div class="box">
	
  		<%   
  		 
  			
  		 
  		 	
  			Musique ligneActuelleMusique = null;		
  		 	for(int k = 0; k < tabMusique.size() ; k++ ){
  		     
  		    	ligneActuelleMusique = tabMusique.get( k );
  		     
  		    
  		    	/*Affichage des informations sur la musique*/
  		    
  				out.print("<div class = \"elemMusique\">");
  			
  				out.print("<form action=\"" + request.getSession(  ).getAttribute( "nomPage" ) + "\" method=\"post\">");
  			
  				// Nom de la musique
  				out.print(" <form id=\"conteneurLecteur\" method=\"post\" action=\"" + request.getSession(  ).getAttribute( "nomPage" ) + "\"> "+
  					"<input type=\"submit\" value=\"" + tabMusique.get(k).getNomMusique(  ) + "\" class=\"sousTitre\" name=\"music\" /></form>");
				

  			
  				// Nom artiste
 
  				out.print("<form action=/DeezifyWeb/Artiste>Artiste : <input name=\"artiste\" type=submit value=\""+ligneActuelleMusique.getArtiste(  ).getNom(  )+"\" /></form>");
  			

  			
  				//Date de sortie
			
				out.print("Date de sortie : " + ligneActuelleMusique.getDate(  ));
			
			
				out.print("<br/>");
  			
  				//Duree
			
				out.print("Durée : " + ligneActuelleMusique.getDuree(  ));
  			
  			
				out.print("<br/>");
				out.print("</form>");
			
				out.print("</div>");
			
			
  		   
  		 }
  		 	
  		 	
  		 	%>
  		 	</div>
	
	<%@ include file="Lecteur.jsp" %> 		 
        
	</body>

</html>

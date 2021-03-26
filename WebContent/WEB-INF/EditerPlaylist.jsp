<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page 
import="java.util.ArrayList"
import="model.Playlist" %>







<html>

    <style>
        <%@ include file="../style.css"%>
    </style>

    <head>
        <title> Ajouter une musique </title>
    </head>

    <body>
        <%@ include file="../Menu.jsp"%>
        
        
        <p class="sousTitre3"> Musique à ajouter </p>
        
        <div class="box">
        
        <%
        
        	//On commence par afficher les musiques d'ont il possède pas
        	
        	ArrayList<Musique> tabMusique = new ArrayList<Musique>();
        
       		if (request.getAttribute( "tabMusiqueAjouter" ) != null)
        	tabMusique = (ArrayList<Musique>) request.getAttribute( "tabMusiqueAjouter" );
        
       		Musique ligneActuelleMusique = null;		
  		 	for(int k = 0; k < tabMusique.size() ; k++ ){
  		     
  		    	ligneActuelleMusique = tabMusique.get( k );
  		     
  		    
  		    	/*Affichage des informations sur la musique*/
  		    
  				out.print("<div class = \"elemMusique\">");
  			
  				out.print("<form action=\"" + request.getSession(  ).getAttribute( "nomPage" ) + "\" method=\"post\">");
  			
  				// Nom de la musique
  				out.print(" <form id=\"conteneurLecteur\" method=\"post\" action=\"" + request.getSession(  ).getAttribute( "nomPage" ) + "\"> "+
  					"<input type=\"submit\" value=\"" + tabMusique.get(k).getNomMusique(  ) + "\" class=\"sousTitre\" name=\"boutonAjouter\" /></form>");
  			
  			
  			
  				// Nom artiste
 
  			  	out.print("<form action=/DeezifyWeb/Artiste>Artiste : <input name=\"artiste\" type=submit class=\"boutonArtiste\" value=\""+ligneActuelleMusique.getArtiste(  ).getNom(  )+"\" /></form>");

  			
  			
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
  		
  		<p class="sousTitre3"> Musique à supprimer </p>
  		
  		<div class="box">
  		
  		
  		
  		<%
        
        	//On commence par afficher les musiques d'ont il possède 
        	
        	tabMusique = new ArrayList<Musique>();
        
       		if (request.getAttribute( "tabMusiqueSupprimer" ) != null)
        	tabMusique = (ArrayList<Musique>) request.getAttribute( "tabMusiqueSupprimer" );
        
       		ligneActuelleMusique = null;		
  		 	for(int k = 0; k < tabMusique.size() ; k++ ){
  		     
  		    	ligneActuelleMusique = tabMusique.get( k );
  		     
  		    
  		    	/*Affichage des informations sur la musique*/
  		    
  				out.print("<div class = \"elemMusique\">");
  			
  				out.print("<form action=\"" + request.getSession(  ).getAttribute( "nomPage" ) + "\" method=\"post\">");
  			
  				// Nom de la musique
  				out.print(" <form id=\"conteneurLecteur\" method=\"post\" action=\"" + request.getSession(  ).getAttribute( "nomPage" ) + "\"> "+
  					"<input type=\"submit\" value=\"" + tabMusique.get(k).getNomMusique(  ) + "\" class=\"sousTitre\" name=\"boutonSupprimer\" /></form>");
  			
  			
  			
  				// Nom artiste
  				
  				out.print("<form action=/DeezifyWeb/Artiste>Artiste : <input name=\"artiste\" class=\"boutonArtiste\" type=submit value=\""+ligneActuelleMusique.getArtiste(  ).getNom(  )+"\" /></form>");

 
  			
  			
  			
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
        
        
        <%@ include file="Lecteur.jsp"%>
        
    </body>

</html>
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
        // Si on vient de servletExplorer par la barre de recherche
        else {
            
            if (request.getAttribute( "tabMusiqueRecherche" ) != null) {
            request.getSession(  ).setAttribute( "estEnModeRecherche", true );
        	tabMusique = (ArrayList<Musique>) request.getAttribute( "tabMusiqueRecherche" );
        	tabPlaylist = (ArrayList<Playlist>) request.getAttribute( "tabPlaylistRecherche" );
            }
        	// Si la recherche n'a donné aucun résultat, on en informe l'utilisateur
            if (tabMusique.isEmpty(  ) && tabPlaylist.isEmpty(  ))
    		    out.print( "<h3> Aucun résultat trouvé </h3>" );  
        
            
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
  			
  			
  				out.print("<br/>");
  			
  				// Nom artiste
 
  				out.print("<form action=/Deezify_web/Artiste>Artiste : <input name=\"artiste\" type=submit value=\""+ligneActuelleMusique.getArtiste(  ).getNom(  )+"\" /></form>");
  			
  			
  				out.print("<br/>");
  			
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
  		 	<% if (!tabPlaylist.isEmpty(  ))
  	  		    out.print( "<h2> Playlists et Albums </h2>" ); %>
  		 	<div class = "box">
  		 	<%
  		 
  		Playlist ligneActuellePlaylist = null;	 
  		for(int k = 0; k < tabPlaylist.size() ; k++ ){
 		     
  		    	ligneActuellePlaylist = tabPlaylist.get( k );
  		     
  				out.print("<div class = \"elem\">");
  			
  				out.print("<form action=\"ListeMusique\" method=\"post\">");
  				out.print("<button type=\"submit\" name=\"nomListe\" value=\"" +  ligneActuellePlaylist.getNomListe(  )  + "\" class=\"sousTitre\">");
  				out.print(ligneActuellePlaylist.getNomListe(  ) );
  				out.print("</button>");
  				out.print("</form>");
  			
  				out.print("<br/>");
  			
  				out.print("<td>");
				out.print("<form action=\"ListeMusique\" method=\"post\">");
				out.print("<button type=\"submit\" name=\"nomListe\" value=\"" +  ligneActuellePlaylist.getNomListe(  )   + "\" class=\"sousTitre\">");
				out.print("<img src=\"" +ligneActuellePlaylist.getImage()+ "\" class=\"lienGenre\" ");
				out.print("</button>");
				out.print("</form>");
			
				out.print("</div>");
  		   
  		 }
        
  		 %> 
  	
	</div>
	
	<%@ include file="Lecteur.jsp"%> 		 
        
	</body>

</html>

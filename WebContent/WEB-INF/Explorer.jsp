<%--
  Created by IntelliJ IDEA.
  User: mateo
  Date: 17/10/2019
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "java.util.ArrayList" 
import="java.util.HashMap" 
import="java.util.List" 
import="java.util.Map"
import="model.EnsembleGenre"
import="model.Genre"  %>

<html>

    <style>
        <%@ include file="../style.css"%>
    </style>

    <head>
        <title> Explorer </title>
    </head>

    <body>
   		
    	<%@ include file="../Menu.jsp"%>
        
        <p class="sousTitre3"> Recherche </p>
        
        <form id="conteneurRecherche" method="post" action="Recherche">
        
	        <input  type="text" class="textField" id="recherche" name="recherche"/>
	  		<button class="buttonExplorer"> Rechercher </button>
	            
        </form>

  
	    <%
			HttpSession httpSession = request.getSession();
			EnsembleGenre ensembleGenre = (EnsembleGenre) session.getAttribute( "ensembleGenre" );
	        ArrayList<Genre> tabGenre = ensembleGenre.getTabGenre(  );
	    
	    	Genre ligneActuelle = null;
	    
		%>
	  
  	
    	
	  	<div class="box">
				
	  		 <%   
		  		 for(int k = 0; k < tabGenre.size() ; k++ ){
		  		     
		  		    ligneActuelle = tabGenre.get( k );
		  		    
		  			out.print("<div class = \"elem\">");
		  			
		  			out.print("<form action=\"Playlist\" method=\"post\">");
		  			out.print("<button type=\"submit\" name=\"genre\" value=\"" +  ligneActuelle.getNom(  )  + "\" class=\"sousTitre\">");
		  			out.print(ligneActuelle.getNom(  ) );
		  			out.print("</button>");
		  			out.print("</form>");
		  			
		  			//out.print("<br/>");
		  			
		  			out.print("<td>");
					out.print("<form action=\"Playlist\" method=\"post\">");
					out.print("<button type=\"submit\" name=\"genre\" value=\"" +  ligneActuelle.getNom(  )   + "\" class=\"sousTitre\">");
					out.print("<img src=\"" + ligneActuelle.getUrl(  ) + "\" class=\"lienGenre\" ");
					out.print("</button>");
					out.print("</form>");
					
					out.print("</div>");
		  		   
		  		 }
	  		 %> 
	  		 
		</div>  		 
  
   		<%@ include file="Lecteur.jsp"%>
   
	</body>

</html>

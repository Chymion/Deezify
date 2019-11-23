<%--
  Created by IntelliJ IDEA.
  User: mateo
  Date: 17/10/2019
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "java.util.ArrayList" 
import="java.util.HashMap" 
import="java.util.List" 
import="java.util.Map"  %>

<html>

    <style>
        <%@ include file="style.css"%>
    </style>

    <head>
        <title> Explorer </title>
    </head>

    <body>
   		
         <%@ include file="Menu.jsp"%>
        
        <p class="sousTitre"> Recherche </p>
        <form id="conteneurRecherche" method="post" action="Explorer">
            <input  type="text"
                    class="textField"
                    id="recherche"
                    name="recherche"/>
            <button class="buttonExplorer"> rechercher </button>
            
        </form>
        
<%-- Affichage des Genres --%>

  
    <%
		 
		List<Map<String, String>> tabGenre = (List<Map<String, String>>) request.getAttribute( "tabGenre" );
    	Map<String, String> ligneActuelle = null;
    
	%>
  
  	
    	
  	<div class="box">
			
  		 <%   
  		 for(int k = 0; k < tabGenre.size() ; k++ ){
  		     
  		    ligneActuelle = tabGenre.get( k );
  		     
  			out.print("<div class = \"elem\">");
  			
  			out.print("<form action=\"Playlist\" method=\"post\">");
  			out.print("<button type=\"submit\" name=\"genre\" value=\"" +  ligneActuelle.get( "nom" )  + "\" class=\"sousTitre\">");
  			out.print(ligneActuelle.get( "nom" ));
  			out.print("</button>");
  			out.print("</form>");
  			
  			out.print("<br/>");
  			
  			out.print("<td>");
			out.print("<form action=\"Playlist\" method=\"post\">");
			out.print("<button type=\"submit\" name=\"genre\" value=\"" +  ligneActuelle.get( "nom" )  + "\" class=\"sousTitre\">");
			out.print("<img src=\"" + ligneActuelle.get( "lien" ) + "\" class=\"lienGenre\" ");
			out.print("</button>");
			out.print("</form>");
			
			out.print("</div>");
  		   
  		 }
  		 %> 
  		 
	</div>  		 
  
   
</body>

</html>

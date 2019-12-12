<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%@page import="model.Artiste" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Artiste</title>
	</head>
	<style>
    <%@ include file="../style.css"%>
	</style>
	<body>
		<%@ include file="../Menu.jsp"%>
        <div class="aff<ichage">
        	        	
       		
       		
       		<%
       		
       		// Création d'un artiste 
       		Artiste a = new Artiste((String) request.getAttribute("artiste"));
       		
       		out.print("<h1>Voici quelques informations sur "+a.getNom()+"</h1>");
       		
       		//Nom 
       		out.print("Nom: "+a.getNom());
       		
       		out.print("</br>");
       		
       		// Déscription
       		out.print("Description: "+a.getDescription());
       		
       		out.print("</br>");
       		
       		// Image
       		out.print("<img src=\""+a.getImage()+"\"");
       		
       		%>
       		
       		
       		
       		
    	</div>
		<%@ include file="Lecteur.jsp"%>






	</body>
	</html>
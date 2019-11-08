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
            <button> rechercher </button>
            
        </form>
        
<%-- Affichage des Images 'genre' --%>

<section>
  <table class="genre">
 
 <c:forEach  items="${tabGenre}" var="genre" >
  	
  	
  		<tr>
  		    <th>
      			<c:out value="${ genre['nom'] } " ></c:out>
  			</th>
  		</tr>
  		
  		<tr>
  			<td>
      			<img src="${ genre['lien'] }" class="lienGenre">
  			</td>
  		</tr>
  	

</c:forEach>
	
  </table>   
   </section>     
    </body>

</html>

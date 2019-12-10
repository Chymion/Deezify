<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<style>
		<%@ include file="../style.css"%>
	</style>

	<head>
		<title> Inscription </title>
	</head>
	
	<body>
	
		<%@ include file="../Menu.jsp"%>
		<% 
		
		if (request.getAttribute( "resultat" ) != null){
		    	out.print( "<h3>" + (String)request.getAttribute( "resultat" ) + "</h3>" ); 
		    	request.removeAttribute( "resultat" );
		}
		
		%>
		<p class="sousTitre"> Inscription </p>
		<div id="conteneurConnexion">
			<form id="conteneurConnexion2" method="post" action="Inscription">
				<p>
					<label for="prenom"> Prenom : </label>
					<input  type="text"
							class="field"
							id="prenom"
							name="prenom"/>
				</p>
				<p>
					<label for="nom"> Nom : </label>
					<input  type="text"
							class="field"
							id="nom"
							name="nom"/>
				</p>
				<p>
					<label for="pseudo"> Pseudo : </label>
					<input  type="text"
							class="field"
							id="pseudo"
							name="pseudo"/>
				</p>
				<p>
					<label for="email"> Email : </label>
					<input  type="email"
							class="field"
							id="email"
							name="email"/>
				</p>
				<p>
					<label for="password"> Mot de passe : </label>
					<input  type="password"
							class="field"
							id="password"
							name="motDePasse"/>
				</p>
				<br />
				<button type="submit" name="inscription" > Inscription </button>
			</form>
			
		</div>
		
		
	</body>
	
</html>
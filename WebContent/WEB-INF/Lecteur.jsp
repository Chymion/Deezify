<%--
  Created by IntelliJ IDEA.
  User: mateo
  Date: 22/10/2019
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer>

    <form id="conteneurLecteur" method="post" action=${ sessionScope.nomPage }>
    
    	<%  
    		// Il suffit de vérifier si la variable count est présente ou pas pour lancer le bouton Play/Pause
    		if ( request.getSession().getAttribute( "count" ) != null  ){
	    		out.print("<input type=\"submit\"value=\"Play/pause\" class=\"boutonMusique\" name=\"boutonPlay\" />");
	    		out.print("<input class=\"slider\" type=\"range\" name=\"volume\" min=\"0\" max=\"100\" />");
    		}
    	
    	%>
    
        
    </form>
    
</footer>

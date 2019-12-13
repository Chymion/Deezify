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
    		// Il suffit de vérifier si la variable count est présente ou pas pour lancer le bouton Play/Pause et le volume
    		if ( request.getSession().getAttribute( "count" ) != null  ){
    			
    			out.print("<div class=\"conteneurPitch\">");
	    		out.print("<input type=\"submit\"value=\"<\" class=\"boutonMusique\" name=\"boutonSlower\" />");
    			out.print("<input type=\"submit\"value=\">\" class=\"boutonMusique\" name=\"boutonFaster\" />");
	    		out.print("</div>");

    			out.print("<input type=\"submit\"value=\"Play/Pause\" class=\"boutonPlayPause\" name=\"boutonPlay\" />");
	    		
    			out.print("<div class=\"conteneurVolume\">");
	    		out.print("<input type=\"submit\"value=\"-\" class=\"boutonMusique\" name=\"boutonLow\" />");
	    		out.print("<input type=\"submit\"value=\"+\" class=\"boutonMusique\" name=\"boutonUp\" />");
	    		out.print("</div>");
    		}
    	
    		
    	%>
    
        
    </form>
    
</footer>
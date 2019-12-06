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
    		// Il suffit de vérifier si la variable boutonPresent est présente ou pas
    	
    		Boolean b = (Boolean)request.getSession().getAttribute( "boutonPresent" );
    		
    		
    		if ( b != null && b  ){
	    		out.print("<input type=\"submit\"value=\"Play/pause\" class=\"boutonMusique\" name=\"boutonPlay\" />");  
    		}
    	%>
    
        
    </form>
    
</footer>

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
    	
    	if (request.getAttribute("blod") != null)
    	{
    		
    
    	if ((boolean)(request.getAttribute("blod")) == true)
    	{
    		out.print("<input type=\"submit\"value=\"Play/pause\" class=\"boutonMusique\" name=\"boutonPlay\" />");
    	}
    	else
    	{
    		
    	}
    	}
    
    	%>
    
        
    </form>
    
</footer>

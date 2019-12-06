package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletRecommandation")
public class ServletRecommandation extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String nomPage="Recommandations";
        HttpSession session = request.getSession();
        session.setAttribute("nomPage", nomPage);

        this.getServletContext().getRequestDispatcher("/WEB-INF/Recommandation.jsp").forward(request, response);
    }


}

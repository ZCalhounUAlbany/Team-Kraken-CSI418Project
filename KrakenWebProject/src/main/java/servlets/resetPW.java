package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.Connection;


public class resetPW extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
        String username = request.getParameter("un");
        String o_password = request.getParameter("opw");
        String n_password = request.getParameter("npw");

        try {
            //Connection con = sqlmethods.getConnection();
            if (sqlmethods.verifyUser(username,o_password)){
                sqlmethods.updatepw(username,n_password);
                request.setAttribute("popout","true");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage","Sorry, Invalid username or password");
                request.getRequestDispatcher("/views/resetpassword.jsp").forward(request, response);
            }
        } catch (Exception e) {
            out.println("sorry,some error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/resetpassword.jsp").forward(request, response);
    }
}

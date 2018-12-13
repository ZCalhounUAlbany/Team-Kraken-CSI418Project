package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class search extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5356033511716867297L;
	
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String category = request.getParameter("category");
        String search_input = request.getParameter("search_input");
        sqlmethods search_sql = new sqlmethods();
        ArrayList<String> parameter = new ArrayList<String>();
        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        ArrayList<String> value = new ArrayList<String>();
        parameter.add(category);
        value.add(search_input);
        try {
            results = search_sql.searchTable(parameter, value);
            request.setAttribute("results", results);
            request.getRequestDispatcher("/views/searchresult.jsp").forward(request, response);
        }catch (SQLException e){
            out.println("sorry,some error occured. Please return back to the search page");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("do-get version");
        out.println("parameter " + request.getParameter("category") +
                "\nvalue " + request.getParameter("search_input"));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  Secind servlet which gets numbers.
 * @author Paulina Czempiel
 * @version 1.0
 */
@WebServlet(name = "Second", urlPatterns = {"/Second"})
public class Second extends HttpServlet {
    
    Connection connection;
    /**
	 * 
	 */
	private static final long serialVersionUID = 6711027430460211589L;
	
	@Override
	public void init() {
		connection = First.getConnection();
	}
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      response.setContentType("text/html; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();
	
		
		 out.println("<html>\n<body>\n<h1>History</h1>\n");
		
		 try {
			Statement stmt = connection.createStatement();
			String sql = "select word,word_to_check,anagrams from anagrams";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String word1 = rs.getString("word");
				String word2 = rs.getString("word_to_check");
				String anagrams = rs.getString("anagrams");
				out.println("<br> <br> Word: " + word1 + " Word 2: " + word2 + " Anagrams for  given word: " + anagrams);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 	
		 HttpSession session = request.getSession();
			Date time = new Date(session.getLastAccessedTime());
			
			 out.println("<br> Last time visited: " + time);
		 
		 Cookie[] cookies = request.getCookies();
		 String timesVisited = "1";
		 for (Cookie cookie : cookies) {
			 if (cookie.getName().equals("timesVisited")) {
              timesVisited = cookie.getValue();
              break;
			 }
		 }
      
      out.println("<br> <br> Times Visited this site: " + timesVisited);
		 out.println("</body>\n</html>");
		 
		 int visits = Integer.parseInt(timesVisited);
		 visits++;
		 
		 Cookie cookie = new Cookie("timesVisited", Integer.toString(visits));
         response.addCookie(cookie);
		
	}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  Second servlet which gets numbers.
 * @author Paulina Czempiel
 * @version 1.0
 */
@WebServlet(name = "Second", urlPatterns = {"/Second"})
public class Second extends HttpServlet {
    
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
		
        out.println("<html>\n<body>\n<h1>History</h1>\n");
		
		
        
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

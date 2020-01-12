/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.Cookie;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import mathapplication.exception.MathNoDataException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mathapplication.view.MathResponseCreator;
import mathapplication.model.MathModel;
import javax.servlet.annotation.WebServlet;


/**
 *  * First servlet which links with model and saves output.
 * @author Paulina Czempiel
 * @version 1.0
 */

@WebServlet(name = "First", urlPatterns = {"/First"})
public class First extends HttpServlet {

    private static MathModel model;
    private static Connection connection;
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    @Override
	public void init() {
		model = new MathModel();
		if(connection == null) {
			Context initContext;
			try {
				initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:comp/env");
				DataSource ds = (DataSource) envContext.lookup("jdbc/numbers");
				connection = ds.getConnection();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
     /*
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
        MathModel mathModel;
        MathResponseCreator creator;
        HttpSession session = request.getSession(true);
        Object sessionCalculator = session.getAttribute("calculate");
        Object sessionCreator = session.getAttribute("creator");
        if (sessionCalculator == null) {
            mathModel = new MathModel();
        } else {
            mathModel = (MathModel) sessionCalculator;
        }
        if (sessionCreator == null) {
            creator = new MathResponseCreator();
        } else {
            creator = (MathResponseCreator) sessionCreator;
        }
        useCookies(creator, request, response);
        this.performCalculation(mathModel, creator, request);
        try (PrintWriter out = response.getWriter()) {
            creator.createResponse();
            out.println(creator.getResponse());
        }
        catch(NullPointerException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "NullPointerException!");
        }
        session.setAttribute("calculate", mathModel);
        session.setAttribute("creator", creator);
        
    }
    
     /**
     * Calculates given number - average, median and standard deviation. Invokes
     * proper methods both in the view and in the model of this application.
     *
     * @param mathmodel reference to model, responsible for calculation.
     * @param creator reference to view, responsible for html page look.
     * @param text2 String, containing numbers to count.
     */
    private void performCalculation(MathModel mathmodel, MathResponseCreator creator, HttpServletRequest request) {
        try {
            String numbers = request.getParameter("text2");
            creator.setLabel7("");
            mathmodel.setNumbers(numbers);
            mathmodel.count();
            creator.setText3(String.valueOf(mathmodel.getAverage()));
            creator.setText4(String.valueOf(mathmodel.getMedian()));
            creator.setText5(String.valueOf(mathmodel.getStandardDeviation()));
            mathmodel.clearResults();
            mathmodel.clearArray();
        }  catch(NullPointerException | MathNoDataException e){
                creator.setLabel7("Wrong input!");
            }
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "Insert into numbers (average,median,standarddeviation) values (?,?,?)");

		stmt.setDouble(1, mathmodel.getAverage());
		stmt.setDouble(2, mathmodel.getMedian());
		stmt.setDouble(3, mathmodel.getStandardDeviation());
		
		stmt.executeUpdate();	
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    /**
     * Reads saved cookies o determine, how many times was calculation used.
     * Displays this information on the view.
     *
     * @param creator reference to view, responsible for html page look
     * @param request servlet request
     * @param response servlet response
     */
 private void useCookies(MathResponseCreator creator, HttpServletRequest request, HttpServletResponse response) {
        int calculationNumber = 0;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("calculation")) {
                calculationNumber = Integer.parseInt(cookie.getValue());
                break;
            }
        }
        creator.setCalculation(calculationNumber);
        calculationNumber++;
        Cookie cookie = new Cookie("convertions", String.valueOf(calculationNumber));
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
	 * Gets the numbers.
	 *
	 * @return the numbers.
	 */
        public static Connection getConnection() {
		if(connection == null) {
			Context initContext;
			try {
				initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:comp/env");
				DataSource ds = (DataSource) envContext.lookup("jdbc/numbers");
				connection = ds.getConnection();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return connection;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MathModel;
import model.MathNoDataException;
import view.MathResponseCreator;

/**
 * First servlet which links with model and saves output.
 *
 * @author Paulina Czempiel
 * @version 2.0
 */
@WebServlet(name = "First", urlPatterns = {"/First"})
public class First extends HttpServlet {

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
            throws ServletException, IOException, MathNoDataException {
        response.setContentType("text/html;charset=UTF-8");
        MathModel mathModel;
        MathResponseCreator creator;
        HttpSession session = request.getSession(true);
        Object sessionModel = session.getAttribute("calculate");
        Object sessionCreator = session.getAttribute("creator");
        if (sessionModel == null) {
            mathModel = new MathModel();
        } else {
            mathModel = (MathModel) sessionModel;
        }
        if (sessionCreator == null) {
            creator = new MathResponseCreator();
        } else {
            creator = (MathResponseCreator) sessionCreator;
        }
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
    private void performCalculation(MathModel mathmodel, MathResponseCreator creator, HttpServletRequest request) throws MathNoDataException {
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
        }  catch(NullPointerException e){
                creator.setLabel7("Wrong input!");
            }
       
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
        try {
            processRequest(request, response);
        } catch (MathNoDataException ex) {
            Logger.getLogger(First.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (MathNoDataException ex) {
            Logger.getLogger(First.class.getName()).log(Level.SEVERE, null, ex);
        }
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

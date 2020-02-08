/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MathModel;
import view.MathResponseCreator;
import exception.MathNoDataException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;

/**
 * Servlet that realizes count
 * @author Paulina Czempiel
 * @version 3.0
 */
@WebServlet(name = "Convert", urlPatterns = {"/Convert"})
public class Convert extends HttpServlet {

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
        
        MathModel model;
        MathResponseCreator view;

        HttpSession session = request.getSession(true);
        Object sessionModel = session.getAttribute("model");
        Object sessionView = session.getAttribute("view");
        
        if (sessionModel == null)
        {
            model = new MathModel();
        } else {model = (MathModel) sessionModel;
        }
        
        if (sessionView == null)
        {
            view = new MathResponseCreator();
        } else {
            view = (MathResponseCreator) sessionView;
        }
        
        view.setAverage("");
        view.setMedian("");
        view.setStandard("");
        view.setMessage("");
        view.setHistory("");
        
        String number = request.getParameter("number");
        useCookies(view, request, response);
        try{
            this.realizeConversion(model, view, number);
        
        try (PrintWriter out = response.getWriter()) {
            view.createPage();
            out.println(view.getPage());
        }
        session.setAttribute("model", model);
        session.setAttribute("view", view); 
        }
        catch (NullPointerException e)
        {
        }
    }
    
    /**
     * Method to realize counting
     * @param model logic of count
     * @param view of applicaion
     * @param number is value to count
     */
    @SuppressWarnings("unused")
    private void realizeConversion(MathModel model, MathResponseCreator view, String number) throws MathNoDataException
    {
        try{
            model.setNumbers(number);
            model.count();
            view.setAverage(String.valueOf(model.getAverage()));
            view.setMedian(String.valueOf(model.getMedian()));
            view.setStandard(String.valueOf(model.getStandard()));
            String history;
            history = model.updateHistory();

        } catch (NullPointerException | MathNoDataException e )
        {
            view.setMessage(e.getMessage());
        }
        
    }
    
    /**
     * Reads saved cookies to keep information about number used count
     * @param view of apllication 
     * @param request servlet request
     * @param response servlet response
     */
    private void useCookies(MathResponseCreator view, HttpServletRequest request, HttpServletResponse response)
    {
        int numberOfConversions = 0;
        if (request.getCookies() !=null){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("convertions")) {
                numberOfConversions = Integer.parseInt(cookie.getValue());
                break;
            }
        }
        numberOfConversions++;
        }
        Cookie cookie = new Cookie("convertions", String.valueOf(numberOfConversions));
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
        try {
            processRequest(request, response);
        } catch (MathNoDataException ex) {
            Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
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

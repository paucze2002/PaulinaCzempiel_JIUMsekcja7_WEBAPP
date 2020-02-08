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
import java.util.List;

/**
 * Servlet for get history of count
 * @author Paulina Czempiel
 * @version 3.0
 */
@WebServlet(name = "History", urlPatterns = {"/History"})
public class History extends HttpServlet {

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
        MathModel model;
        MathResponseCreator view;
        HttpSession session = request.getSession(true);
        Object sessionModel = session.getAttribute("model");
        Object sessionView = session.getAttribute("view");
        
        if (sessionModel == null) {
            model = new MathModel();
        } else {
            model = (MathModel) sessionModel;
        }
        if (sessionView == null) {
            view = new MathResponseCreator();
        } else {
            view = (MathResponseCreator) sessionView;
        }
        
        view.setHistory(this.prepareHistory(model, view));
        
        try (PrintWriter out = response.getWriter()) {
            view.createPage();
            out.println(view.getPage());
        }
        session.setAttribute("model", model);
        session.setAttribute("view", view); 
    }
    
    /**
     * Prepares history of conversion as one String object
     * @param model logic of conversion, contains history of conversion
     * @param view of application
     * @return ready to show on page history
     */
    private String prepareHistory(MathModel model, MathResponseCreator view)
    {
        List <String> history = model.getHistory();
        String hisAsString = "";
        if (history.isEmpty())
        {
            hisAsString = "Your count history is empty";
        }
        else {
            hisAsString += "Your count history: <br>";
            for(String el: history)
            {
                hisAsString += (el + "<br>");
            }
        }
        return hisAsString;
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

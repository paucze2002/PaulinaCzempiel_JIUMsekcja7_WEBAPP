/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 * View create response to servlet request for html page
 * @author Paulina Czempiel
 * @version 3.0
 */
public class MathResponseCreator {
 
    /**
     * Contains result
     */
    private String average;
    
    /**
     * Contains result
     */
    private String median;
    
    /**
     * Contains result
     */
    private String standard;
    
    
    /**
     * Contains response to servlet
     */
    private String response;
    
    /**
     * Conatins optional message
     */
    private String message;

    /**
     * Contains history of count
     */
    private String history;
    
 
    
    /**
     * Argumentless constructor
     */
    
    public MathResponseCreator()
    {
        average = "";
        median = "";
        standard = "";
        message = "";
        history = "";
        response = "";
    }
    
    /**
     * Creates response from serverlet
     */
    public void createPage()
    {
        response = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "     <head>\n"
                + "         <title>AVERAGE MEDIAN STANDARD DEVIATION COUNT</title>\n"
                + "         <meta charset=\"UTF-8\">\n"
                + "         <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "     </head>\n"
                + "     <body>\n"
                + "     <div id=\"view\">\n"
                + "         <h1 id=\"label1\">Please enter numbers to count, results: average, median and standard deviation! </h1>\n"
                + "         <form action=\"Convert\" method=\"POST\">\n"
                + "             <input name=\"number\" type=\"text\" value=\"\" name=\"number\" >\n"
                + "             <button name=\"h2d\" type=\"submit\">Count</button>\n"
                + "         </form>"
                + "         <h2 id=\"label2\">Results: " + average  + " "+ median + " "+  standard +"</h2>\n"
                + "         <form action=\"History\" method=\"POST\">\n"
                + "         <input name=\"history\" type=\"submit\" value=\"History\" />\n"
                + "         </form>\n"
                + "     <h3 id=\"historyList\">" + history + "</h3>\n"
                + "     <div id=\"message\"> " + message + "</div>\n"
                + "     </div>\n"
                + "     </body>\n"
                + "</html>";
    }
    
    /**
     * Returns response as html page
     * @return response
     */
    public String getPage()
    {
        return response;
    }
    
    /**
     * Setter of result
     * @param val new value of result
     */
    public void setAverage(String val)
    {
        this.average = val;
    }
    
    /**
     * Setter of result
     * @param val new value of result
     */
    public void setMedian(String val)
    {
        this.median = val;
    }
    
    /**
     * Setter of result
     * @param val new value of result
     */
    public void setStandard(String val)
    {
        this.standard = val;
    }
    
    /**
     * Setter of optional message
     * @param mes new message in response
     */
    public void setMessage(String mes)
    {
        this.message = mes;
    }
    
    /**
     * Setter of history respresented as one string
     * @param his history of count
     */
    public void setHistory(String his)
    {
        this.history = his;
    }
    
}


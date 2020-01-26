package view;

/**
 * Class for creating the response to servlet request
 * Returns it as a HTML page
 *
 * @author Paulina Czempiel
 * @version 2.0
 */
public class MathResponseCreator {
    
    /**
     * Contains label displayed above second field text.
     */
    private String label3;
    
    /**
     * Contains label displayed above third field text.
     */
    private String label4;
    
    /**
     * Contains label displayed above forth field text.
     */
    private String label5;
    
    /**
     * Contains label displayed above fifth field text.
     */
    private String label6;
    
       /**
     * Contains error message.
     */
    private String label7;
    
    
    /**
     * Contains second field text value.
     */
    private String text2;
    
     /**
     * Contains average.
     */
    private String text3;
    
    /**
     * Contains median.
     */
    private String text4;
    
     /**
     * Contains standard deviation.
     */
    private String text5;
    
    /**
     * Response from servlet, containing html page.
     */
    private String response;
    
    /**
     * Contains number of calculations performed by user.
     */
    private int calculationNumber;
    
    /**
     * Argumentless constructor, initializes all fields
     */
    public MathResponseCreator() {
        label3 = "Values";
        label7 = "";
        text2 = "";
        text3 = "";
        text4 = "";
        text5 = "";
        response = "";
        calculationNumber = 0;
    }
    
    /**
     * Sets new label value.
     *
     * @param newValue of the label
     */
    public void setLabel7(String newValue) {
        label7 = newValue;
    }
    
    
    /**
     * Sets new text field value.
     *
     * @param newValue of the text field.
     */
    public void setText3(String newValue) {
        text3 = newValue;
    }
    
    /**
     * Sets new text field value.
     *
     * @param newValue of the text field.
     */
    public void setText4(String newValue) {
        text4 = newValue;
    }
    
    /**
     * Sets new text field value.
     *
     * @param newValue of the text field.
     */
    public void setText5(String newValue) {
        text5 = newValue;
    }
    
    /**
     * Sets number of calculations.
     *
     * @param calculation new convertions number.
     */
    public void setCalculation(int calculation) {
        calculationNumber = calculation;
    }

    /**
     * Returns text.
     *
     * @return text of a text.
     */
    public String getText2(){
        return text2;
    }
    
    /**
     * Creates response from servlet.
     */
    public void createResponse() {
        response = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>MathApplication Calculator</title>\n"
                + "        <link rel=\"stylesheet\" href=\"styles.css\"> \n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div id=\"view\">\n"
                + "            <h3 id=\"label1\"> Welcome! Please type numbers to count (after spacebar):</h3>\n"
                + "            <form action=\"Calculate\" method=\"POST\">\n"
                + "                <input id=\"text2\" type=\"text\" value=\"" + text2 + "\" name=\"text2\">\n"
                + "                <input id=\"calculate\" type=\"submit\" value=\"Calculate\" />\n"
                + "            </form>\n"
                + "            <h5 id=\"label4\">Mean:</h5>\n"
                + "            <input id=\"text3\" type=\"text\" value=\"" + text3 + "\" name=\"text3\" readonly>\n"
                + "            <h5 id=\"label5\">Median:</h5>\n"
                + "            <input id=\"text4\" type=\"text\" value=\"" + text4 + "\" name=\"text4\" readonly>\n"
                + "            <h5 id=\"label6\">Standard deviation:</h5>\n"
                + "            <input id=\"text5\" type=\"text\" value=\"" + text5 + "\" name=\"text5\" readonly>\n"
                + "            <h3 id=\"cookie\"> You calculated " + calculationNumber + " numbers on this page. Thank you!</h3>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>\n";
    }
    
    /**
     * Returns html page.
     *
     * @return servlet response
     */
    public String getResponse() {
        return response;
    }
}


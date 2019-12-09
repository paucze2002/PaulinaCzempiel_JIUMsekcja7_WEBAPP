/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.controler;

import mathapplication.model.MathModel;
import mathapplication.exception.MathNoDataException;

import org.json.*;

/**
 * It's purpose is to obtain information from message sent by client,
 * invoke proper methods from calculator and create response to client.
 * 
 *
 * @author Paulina Czempiel
 * @version 1.0
 */
public class MathAnalyzer {

    /**
    * Used to invoke methods responsible for logic of the application.
     */
    private MathModel mathmodel;
    
    /**
     * JSONObject
     */
    private JSONObject response;
    
    
    /**
     * String containing message from server
     */
    private String message;
    
    /**
     * Argumentless constructor. Creates new objects of MathModel and MainPanel
     */
    public MathAnalyzer() {
        this.mathmodel = new MathModel();
        this.response = new JSONObject();
    }
    
    /**
     * Method that calls appriopiate methods in mathmodel to calculate numbers.
     * It creates response to command, that invoked this method, as well.
     * It also catches exceptions, that might have encountered during calculation.
     * 
     * @param text String array contain text to calculate
     * @throws MathNoDataException when there is no data to count
     */
    public void calculate(String[] text) throws MathNoDataException  {
        try {
            mathmodel.setNumbers(text);
            mathmodel.clearResults();
            mathmodel.count();
            response.put("text7", mathmodel.getAverage());
            response.put("text8", mathmodel.getMedian());
            response.put("text9", mathmodel.getStandardDeviation());
        
        } catch(MathNoDataException e){
            response.put("label3", "There is no entered value!");
        } catch(NullPointerException e){
            response.put("label3", "There is null!");
        }
    }
    
    /**
     * Method that erases content of texts fields, notification label and
     * strings representing saved numbers. It creates proper response to
     * command, that invoked this method, as well.
     */
    public void erase() {

        response.put("text2", "0");
        response.put("text6", "0");
        response.put("text17", "0");
        response.put("text12", "0");
        response.put("text15", "0");
        response.put("text16", "0");
        response.put("label1", "");
        mathmodel.setNumbers(null);
    }
    
    /**
     * Method that returns server response for client command. First it
     * checks if the received messege is a HELP command. If no, then it converts
     * messege to JSONObject, to obtain all command it contains. After that,
     * it invokes methods stated in message to create response as well.
     * Then it returns response as String to JSON format.
     * 
     * @return String contaings response.
     */
     public String getResponse() throws MathNoDataException {
        if (message.equals("") || message == null) {
            return "";
        } else if (message.toUpperCase().equals("HELP")) {
            String resp = "{\"method\":\"calculate\",\"text2\":\"<value>\"}|";
            resp += "{\"method\":\"erase\"}|";
            return resp;
        }
        try {
            JSONObject jsonMessage = new JSONObject(message);
            if (jsonMessage.has("method")) {
                String method = jsonMessage.getString("method");
                switch (method) {
                    case "calculate":
                        String text2 = jsonMessage.getString("text1");
                        String text6 = jsonMessage.getString("text6");
                        String text12 = jsonMessage.getString("text12");
                        String text17 = jsonMessage.getString("text17");
                        String text15 = jsonMessage.getString("text15");
                        String text16 = jsonMessage.getString("text16");
                        String[] array = {text2, text6, text12, text17, text15, text16};
                        calculate(array);
                        break;
                    case "erase":
                        erase();
                        break;
                    default:
                        response.put("incorrectRequest", "Incorrect method name!");
                        break;
                }
            } else {
                response.put("incorrectRequest", "No method specified!");
            }
        } catch (JSONException e) {
        }
        String resp = response.toString();
        response = new JSONObject();
        return resp;
    }
     
     /**
     * Returns acknowledgement in JSON format.
     *
     * @return String containing acknowledgement in JSON format.
     */
    public String getAck() {
        JSONObject jsonAck = new JSONObject();
        jsonAck.put("acknowledgement", "true");
        return jsonAck.toString();
    }
    
    /**
     * Setts passed argument as message received from client.
     *
     * @param message String containing message from client.
     */
    public void setMassage(String message) {
        this.message = message;
    }
}


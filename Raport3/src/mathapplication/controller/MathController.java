/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.controller;


import mathapplication.client.MathApplicationClient;
import mathapplication.view.MainPanel;
import org.json.*;
import javax.swing.JFrame;

/**
 * This class mediates between graphic interface of this application and class
 * that connects to server. It creates requests to server, based on user
 * commands coming from view. Then it analyzes responses from server, to apply
 * them on view.
 *
 * @author Paulina Czempiel
 * @version 1.0
 */
public class MathController {

    /**
     * Used to send requests to server.
     */
    private MathApplicationClient client;

    /**
     * Used to create request to server in JSON format.
     */
    private JSONObject jsonRequest;

    /**
     * Used to communicate with Graphic Interface.
     */
    private MainPanel view;

    /**
     * One argument constructor. Initializes variables. Creates new MainWindow
     * class object and initializes it, used as Graphic User Interface.
     *
     * @param client reference to object, that allows communication with server.
     */
    public MathController(MathApplicationClient client) {
        this.client = client;
        jsonRequest = new JSONObject();
        view = new MainPanel(this);
        JFrame mainWindow = new JFrame();
        mainWindow.setBounds(200, 200, 700, 500);
        mainWindow.setLayout(null);
        mainWindow.setContentPane(view);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
    }

    /**
     * Creates request and sends it to server. Request will invoke erase method
     * on server.
     */
    public void erase() {
        jsonRequest.put("method", "erase");
        client.sendRequest(jsonRequest.toString());
        //Creating new object to remove previous request.
        jsonRequest = new JSONObject();
    }

    /**
     * Creates request and sends it to server. Request will invoke convert
     * method on server.
     */
    public void calculate() {
        jsonRequest.put("method", "");
        jsonRequest.put("text1", view.getText1());
        jsonRequest.put("text2", view.getText2());
        jsonRequest.put("text3", view.getText3());
        jsonRequest.put("text4", view.getText4());
        jsonRequest.put("text5", view.getText5());
        jsonRequest.put("text6", view.getText6());
        client.sendRequest(jsonRequest.toString());
        jsonRequest = new JSONObject();
    }

    /**
     * Analyzes response from server. Checks all possible attributes. If they
     * occur in response, then they value will be respectively passed to view.
     *
     * @param response String containing response from server.
     */
    public void analyzeResponse(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            if (jsonResponse.has("incorrectRequest")) {
                errorOccurred("Communication error, between client and server occurred");
            }
            if (jsonResponse.has("label3")) {
                view.setLabel3(jsonResponse.getString("label3"));
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if passed argument contains attribute "acknowledgement" with value
     * "true" in JSON format.
     *
     * @param ack checked String
     * @return true if ack contains checked value, otherwise false.
     */
    public boolean checkAck(String ack) {
        JSONObject jsonAck = new JSONObject(ack);
        return jsonAck.has("acknowledgement")
                && jsonAck.getString("acknowledgement").equals("true");
    }

    /**
     * Invoked if in some part of program error has occured. Sets passed
     * argument, as text in label4 in MainWindow.
     *
     * @param message contains error message
     */
    public void errorOccurred(String message) {
        view.setLabel3(message);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.client;

import mathapplication.controller.MathController;
import java.io.*;
import java.net.Socket;
import java.util.Properties;

/**
 * Class that implements client and communicates with server. It creates
 * connection with server, via socket, which is used for later communication.
 *
 * @author Paulina Czempiel
 * @version 1.0
 */
public class MathApplicationClient{

    /**
     * Represents socket, that connects with server.
     */
    private Socket clientSocket;

    /**
     * Formated output stream.
     */
    private PrintWriter output;

    /**
     * Buffered input stream.
     */
    private BufferedReader input;

    /**
     * Port number, at which server is placed.
     */
    private int PORT;

    /**
     * IP address, at which server is placed.
     */
    private String address;

    /**
     * Used to report errors that occurred during communication with server.
     */
    private MathController mathcontroller;

    /**
     * Contains information about connection with server. True if it was
     * succesful.
     */
    private boolean serverConnection;

    /**
     * Argumentless constructor. Reads IP address and port of server, from
     * conf.properties file and connects with server.
     */
    MathApplicationClient() {
        mathcontroller = new MathController(this);
        serverConnection = false;
        PORT = 0;
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream("conf.properties")) {
            properties.load(in);
            address = properties.getProperty("address");
            PORT = Integer.parseInt(properties.getProperty("PORT"));
        } catch (IOException | NumberFormatException e) {
            mathcontroller.errorOccurred("Error in configuration files!");
        }
        try {
            clientSocket = new Socket(address, PORT);
            if (clientSocket != null && clientSocket.isConnected()) {
                serverConnection = true;
            }
            output = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    clientSocket.getOutputStream())), true);
            input = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
        } catch (IOException e) {
            mathcontroller.errorOccurred("Application can't connect to the server!");
        }
    }

    /**
     * Sends message (passed as argument) to server. After sending it, waits for
     * acknowledgemnet message. Than obtains response and sends it to the
     * controller.
     *
     * @param request String containg message, that is going to be sent to
     * server.
     */
    public void sendRequest(String request) {
        try {
            if (!serverConnection) {
                return;
            }
            output.println(request);
            String ack = input.readLine();
            if (mathcontroller.checkAck(ack)) {
                String response = input.readLine();
                mathcontroller.analyzeResponse(response);
            } else {
               mathcontroller.errorOccurred("Server does not respond!");
            }

        } catch (IOException e) {
            mathcontroller.errorOccurred("Can't connect with server!");

        }
    }

    /**
     * Main method of the program. Crates new client object.
     *
     * @param args command line arguments, not used in this project.
     */
    public static void main(String[] args) {
        MathApplicationClient client = new MathApplicationClient();
    }

}

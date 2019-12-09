/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.server;

import mathapplication.controler.MathAnalyzer;
import java.io.*;
import java.net.*;
import mathapplication.exception.MathNoDataException;

/**
 *  Class that realizes single connection with client.
 * 
 * @author Paulina Czempiel
 * @version 1.0
 */
public class SingleConnection implements Closeable {
    
  /**
     * Represents socket, that connects with client.
     */
    private Socket socket;

    /**
     * Buffered input stream.
     */
    private BufferedReader input;

    /**
     * Formated output stream.
     */
    private PrintWriter output;

    /**
     * Used to sent received command to model and to obtain proper response,
     * that has to be sent to client.
     */
    private MathAnalyzer mathanalyzer;
    
    /**
     * One argument constructor. Creates streams.
     * 
     * @param socket represents connection with client.
     * @param mathanalyzer represents controller connected with model.
     */
    public SingleConnection(Socket socket, MathAnalyzer mathanalyzer) {
        
        try {
            this.mathanalyzer = mathanalyzer;
            this.socket = socket;
            this.output = new PrintWriter(
                new BufferedWriter(
                    new OutputStreamWriter(
                        socket.getOutputStream())), true);
            this.input = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
    /**
     * Communicates with client in infinite loop.First, it reads command from
     * clients, then it sends acknowledgement message and after that a proper
     * response.
     * @throws mathapplication.exception.MathNoDataException
     */
    public void handleConnection() throws MathNoDataException {
        try {
            while (true) {
                String str = input.readLine();
                output.println(mathanalyzer.getAck());
                mathanalyzer.setMassage(str);
                String response = mathanalyzer.getResponse();
                output.println(response);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    
    /**
     * Closes socket.
     *
     * @throws IOException if socket closing wasn't possible.
     */
    @Override
    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }
}

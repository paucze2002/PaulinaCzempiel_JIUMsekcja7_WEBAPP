/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.server;

import mathapplication.controler.MathAnalyzer;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import mathapplication.exception.MathNoDataException;

/**
 *  Main class, that implements server of the application.
 * 
 * @author Paulina Czempiel
 * @version 1.0
 */
public class MathApplicationServer implements Closeable{
    /**
     * Port number, at which port will be opened.
     */
    private int PORT;
    
    /**
     * Socket, that waits for connections from clients.
     */
    private ServerSocket serverSocket;
    
    /**
     * One argument constructor. Reads port from conf.properties file
     * and creates socket server.
     */
    MathApplicationServer(){
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream("conf.properties")) {
            properties.load(in);
            PORT = Integer.parseInt(properties.getProperty("PORT"));
        } catch(IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * The main method of the program.Creates new socket for every connection
 made with client.
     *
     * @param args command line arguments, not used in this project.
     * @throws mathapplication.exception.MathNoDataException
     */
    public static void main(String args[]) throws MathNoDataException {

        MathAnalyzer mathanalyzer = new MathAnalyzer();
        try (MathApplicationServer server = new MathApplicationServer()) {
            while (true) {
                Socket socket = server.serverSocket.accept();
                SingleConnection singleConnection = new SingleConnection(socket, mathanalyzer);
                singleConnection.handleConnection();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Closes socket.
     *
     * @throws IOException if socket closing was not possible.
     */
    @Override
    public void close() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }
    }
}

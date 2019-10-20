/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.view;


/**
 * Class responsible for the user interface: display and
 * presentation of data (int tables).
 *
 * @author Paulina Czempiel
 */
public class MathView{

    /**
     * Shows hello message on the screen.
     */
    public void printHelloMessage() {
        System.out.println("Hello! This program can calculate average, median and standard deviation.");
        System.out.println("Enter the amount of data to calculate: ");
    }

    /**
     * Shows message prompting to enter data.
     */
    public void printDataRequest() {
        System.out.println("Enter data to be counted: ");
    }

    /**
     * Shows message that an exeption has been caught
     * and paremeter.
     *
     * @param message message to display error content.
     */
    public void printError(String message) {
        System.out.println("An exception was cought while executing the program: " + message);
    }

    /**
     * Display array of ints and information about its size.
     *
     * @param array Array to display.
     */
   public  void printIntArray(int[] array) {
        System.out.println("Contents od an " + array.length + " array:");
        for (int i = 0; i < array.length; ++i) {
            System.out.println(array[i]);
        }
    }
    public void printCountValues(double[] result){
        System.out.println("Average: "); 
        System.out.println(result[0]);
        System.out.println("Median: ");
        System.out.println(result[1]);
        System.out.println("Standard deviation: ");
        System.out.println(result[2]);
    
    }
}
 
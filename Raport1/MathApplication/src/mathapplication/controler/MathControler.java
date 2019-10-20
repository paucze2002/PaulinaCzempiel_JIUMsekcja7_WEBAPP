/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.controler;
import java.util.*;
import mathapplication.view.MathView;
import mathapplication.model.MathModel;
import mathapplication.nodataexception.MathNoDataException;

/**
 * A controller is a class that has only one static method: main, which
 * runs the rest of the program and exchanges information between elements
 * application (model and view).
 *
 * @author Paulina Czempiel
 */
public class MathControler {

    /**
     * Main method. Run at program startup. Główan metoda programu. Creates
     * model and view objects and run their methods. Reads
     * data entered by the user and transfers them to the said objects
     * (their methotds)
     *
     * @param args paramethers passed through the system that generates our
     * application. It take amount of numbers to count.
     */
    public static void main(String[] args) {
        MathView view = new MathView();
        MathModel model = new MathModel();
        Scanner scanner = new Scanner(System.in);
        int[] intArray;
        int amount;
        // reads the amount and then numbers to count
        try {
            if (args.length == 0) {
                do {
                view.printHelloMessage();
                amount = scanner.nextInt();
                }
                while(amount < 0);
                view.printDataRequest();
            } 
            else {
                amount = args.length;
            }
            intArray = new int[amount];
            for (int i = 0; i < amount; ++i) {
                if (args.length == 0) {
                    intArray[i] = scanner.nextInt();
                } 
                else {
                    intArray[i] = Integer.parseInt(args[i]);
                }
            }
            double[] results = {0.0, 0.0, 0.0};
           model.count(intArray, results);
           view.printIntArray(intArray);
           view.printCountValues(results);
        } 
        catch (NumberFormatException | NoSuchElementException | IllegalStateException | MathNoDataException e) {
            view.printError(e.getMessage());
        }
    }
}


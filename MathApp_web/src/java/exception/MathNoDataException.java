/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 * Exception of values that contains invalid input
 * @author Paulina Czempiel
 * @version 1.0
 */
public class MathNoDataException extends Exception{
    
    /**
     * Argumentless constructor. Starts automatically. Empty body.
     */
    MathNoDataException() {
    }
    /**
     * Unary constructor. Gets message of exetpion.
     * Constructor calls parent constryctor,
     * and gives providet argument.
     *
     * @param str messege which describes problem
     */
    public MathNoDataException(String str) {
        super(str);
    }
}

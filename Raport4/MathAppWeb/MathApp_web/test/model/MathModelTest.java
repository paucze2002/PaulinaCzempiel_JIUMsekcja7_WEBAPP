/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static org.junit.Assert.*;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Before;
import exception.MathNoDataException;
import org.junit.Test;
/**
 *  Class test MathModel - method count
 * 
 * @author Paulina Czempiel
 * @version 1.0
 */ 
@SuppressWarnings("unused")
public class MathModelTest {
    
    /**
     * Tested object
     */
    MathModel mathmodel;
    
    /**
     * Creates new model before every test
     */
    @Before
    public void setup(){
        mathmodel = new MathModel();
        mathmodel.clearResults();
        mathmodel.clearArray();
    }
    

    /**
     * Test of count method, of class MathModel.
     * Test null array - no data to count.
     */
    @Test
    public void testCountInputNoData() {
        System.out.println("No input data test" );
        try{
            mathmodel.count();
            fail("Incorrect input! An exception should have benn thrown");
        } 
        catch(NullPointerException | MathNoDataException e){
            
        }
    }
    
     /**
     * Test of count method, of class MathModel.
     * Test correct input. Median.
     */
    @Test
    public void testCountCorrectDataMedian() {
        System.out.println("Correct input test." );
        try{
            String tmp = "3.0 3.0 3.0";
            mathmodel.setNumbers(tmp);
            mathmodel.count();
            assertEquals("Median should have been 3.", 3.0, mathmodel.getMedian(), 0);
        }
        catch(NullPointerException | MathNoDataException e){
            fail("Counting fails.");
        }
    }
    
      /**
     * Test of count method, of class MathModel.
     * Test correct input. Average
     */
    @Test
    public void testCountCorrectDataAverage() {
        System.out.println("Correct input test." );
        try{
            String tmp = "3.0 3.0 3.0";
            mathmodel.setNumbers(tmp);
            mathmodel.count();
            assertEquals("Average should have been 3", 3.0, mathmodel.getAverage(), 0);
        }
        catch(NullPointerException | MathNoDataException e){
            fail("Counting fails.");
        }
    }
    
     /**
     * Test of count method, of class MathModel.
     * Test correct input. Standard Deviation.
     */
    @Test
    public void testCountCorrectDataStandardDeviation() {
        System.out.println("Correct input test." );
        try{
            String tmp = "3.0 3.0 3.0";
            mathmodel.setNumbers(tmp);
            mathmodel.count();
            assertEquals("Median should have been 0.", 0.0, mathmodel.getStandard(), 0);
        }
        catch(NullPointerException | MathNoDataException e){
            fail("Counting fails.");
        }
    }
    
}

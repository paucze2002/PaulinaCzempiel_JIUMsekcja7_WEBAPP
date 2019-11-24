/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.model;

import mathapplication.nodataexception.MathNoDataException;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.BeforeClass;


/**
 *  Class test MathModel - method count
 * 
 * @author Paulina Czempiel
 * @version 1.0
 */ 
@SuppressWarnings("unused")
public class MathModelTest {
    
    /**
     * Messege before test
     * 
     */
    @BeforeClass
    public static void message(){
        System.out.println("Test of method \"count\" from MathModel class - exception");
    }
    /**
     * Test of count method, of class MathModel.
     * Test null array - no data to count.
     */
    @Test
    public void testCountInputNoData() {
        System.out.println("No input data test" );
        try{        
            MathModel instance = new MathModel();
            int[] array = null;
            ArrayList<Double> results = new ArrayList<>();        
            instance.count(array, results);
            fail("Incorrect input! An exception should have benn thrown");
        } 
        catch(NullPointerException | MathNoDataException e){
            
        }
    }
    
     /**
     * Test of count method, of class MathModel.
     * Test not empty results list.
     */
    @Test
    public void testinputCountNull() {
        System.out.println("Wrong input data test - not empty restults list." );
        try{        
            MathModel instance = new MathModel();
            int[] array = {3, 3, 3};
            ArrayList<Double> results = new ArrayList<>();
            results.add(5.0);
            instance.count(array, results);
            fail("Incorrect input! An exception should have benn thrown.");
        }
        catch(NullPointerException | MathNoDataException e){
        }
    }
    
     /**
     * Test of count method, of class MathModel.
     * Test correct input. Nothing about results.
     */
    @Test
    public void testCountCorrectData() {
        System.out.println("Correct input test." );
        try{        
            MathModel instance = new MathModel();
            int[] array = {3, 3, 3};
            ArrayList<Double> results = new ArrayList<>();
            instance.count(array, results);
            assertEquals("There should be 3 results.", results.size(), 3);
        }
        catch(NullPointerException | MathNoDataException e){
            fail("Counting fails.");
        }
    }
    
     /**
     * Test of count method, of class MathModel.
     * Test null array and not empty result list.
     */
    @Test
    public void testCountInputWrongData() {
        System.out.println("Wrong input test - empty array and not empty results list." );
        try{        
            MathModel instance = new MathModel();
            int[] array = {};
            ArrayList<Double> results = new ArrayList<>();
            results.add(1.4);
            results.add(55.0);
            results.add(193.2);
            instance.count(array, results);
            fail("Incorrect input! An exception should have benn thrown");
        }
        catch(NullPointerException | MathNoDataException e){
            
        }
    }
    
     /**
     * Test of count method, of class MathModel.
     * Test correct input and output.
     */
    @Test
    public void testCount() {
        System.out.println("Correct input and output test." );
        try{        
            MathModel instance = new MathModel();
            int[] array = {3, 3, 3};
            ArrayList<Double> results = new ArrayList<>();
            ArrayList<Double> checker = new ArrayList<>();
            checker.add(3.0);
            checker.add(3.0);
            checker.add(0.0);
            instance.count(array, results);
            assertEquals("This count should have benn correct", results, checker);
        }
        catch(NullPointerException | MathNoDataException e){   
        }
    }
    
}



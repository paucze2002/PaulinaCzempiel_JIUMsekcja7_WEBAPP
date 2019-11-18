/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.model;

import java.util.ArrayList;
import mathapplication.nodataexception.MathNoDataException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author zizip
 */
public class MathModelTest {
    
    public MathModelTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of count method, of class MathModel.
     * @throws java.lang.Exception
     */
    @Test
    public void testCount() throws Exception {
        System.out.println("Test of method \"count\" from MathModel class" );
        try{
            int[] array = null;
            ArrayList<Double> results = null;
            MathModel instance = new MathModel();
            instance.count(array, results);
        } catch(MathNoDataException e){
            
        }
        //TODO better tests, only public methods? change model? 
        try{
            int[] array = {5, 1, 7};
            ArrayList<Double> results = null;            
            MathModel instance = new MathModel();
            instance.count(array, results);
            fail("Wrong data");
        } catch(Exception e){
        }
         try{
            int[] array = {13, 0, 3,4};
            ArrayList<Double> results = null;            
            MathModel instance = new MathModel();
            instance.count(array, results);
            fail("Wrong data");
        } catch(Exception e){
        }
        //assertEquals();
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.model;


import java.util.*;
import mathapplication.nodataexception.MathNoDataException;

/**
 * Class responsible for data processing (logic).
 *
 * @author Paulina Czempiel
 * @version 2.0
 */
public class MathModel {
    /**
     * Private method which counts numbers in array of ints.
     * @param array array that should be count
     * @return sum which sum of the numbers in array
     */
    @TestAnnotation(authorFirstName = "Paulina", authorLastName = "Czempiel", authorBrief = "sum")
    private double countSum(int[] array){
        int sum = 0;
        for (int i = 0; i < array.length; ++i) {
            sum += array[i];
        }
      return sum;
    }
    /**
     * Private method which counts median.
     * @param array from which should be calculate median
     * @return median from array
     */
    @TestAnnotation(authorFirstName = "Paulina", authorLastName = "Czempiel", authorBrief = "median")
    private double countMedian(int[] array){
        Arrays.sort(array);
        int totalElements = array.length;
        double median;
        if(array.length % 2 == 0){
            int sumMiddleElements = array[totalElements  / 2] + array[totalElements / 2 - 1];
            median = ((double) sumMiddleElements) / 2;
        }
        else {
            median = array[totalElements /2];
        }
        return median;
    }
    /**
     * Private method which counts deviation.
     * @param array from which should be calculate devation
     * @param average from array
     * @return deviation from array
     */
    @TestAnnotation(authorFirstName = "Paulina", authorLastName = "Czempiel", authorBrief = "standard deviation")
    private double countDeviation(int[] array, double average){
        double variance = 0.0;
        for (int i = 0; i < array.length; i++){
            variance += Math.pow(array[i] - average, 2);
        }
        return Math.sqrt(variance/array.length);
    }
    /**
     * Private method which counts deviation.
     * @param array from which should be calculate devation
     * @param results array - average, median and standard deviation
     * @throws mathapplication.nodataexception.MathNoDataException which is thrown when there is no data
     */
    @TestAnnotation(authorFirstName = "Paulina", authorLastName = "Czempiel", authorBrief = "main function of program - counting average, median and standard deviation")
    public void count(int[] array, ArrayList<Double> results) throws MathNoDataException {
        //if list is not empty
        if (!results.isEmpty()){
            throw new MathNoDataException("Wrong data");
        }
        //if array is empty
        if (array.length == 0) {
           throw new MathNoDataException("No data");
        } 
        
        else {
            try{
                //average
                results.add(countSum(array)/ array.length);
                //results[0] = (countSum(array)/ array.length);
                //median
                results.add(countMedian(array));
                //results[1] = countMedian(array);
                //standardDevation
                //results[2] = countDeviation(array, results[0]);
                results.add(countDeviation(array, results.get(0)));
                if(results.size()!= 3){
                    throw new MathNoDataException("Wrong counting");
                }
            }  catch(NullPointerException e){
                    System.out.print("Caught NullPointerException");}
        }
    }
         
}


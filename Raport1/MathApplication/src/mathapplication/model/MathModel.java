/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.model;

import java.util.Arrays;
import mathapplication.nodataexception.MathNoDataException;

/**
 * Class responsible for data processing (logic).
 *
 * @author Paulina Czempiel
 */
public class MathModel {

    /**
     * Private method which counts numbers in array of ints.
     *
     * @param array Tablica w której dwa sąsiednie elementy mają być zamienione
     * miejscami.
     * @param first Indeks pierwszego elementu do zamienienia. first+1 to indeks
     * drugiego elementu.
     */
    double countSum(int[] array){
        int sum = 0;
        for (int i = 0; i < array.length; ++i) {
            sum += array[i];
        }
      return sum;
    }
    
    double countMedian(int[] array){
        Arrays.sort(array);
        int totalElements = array.length;
        double median;
        if(array.length % 2 == 0){
            int sumMiddleElements = array[totalElements  / 2] + array[totalElements / 2 - 1];
            median = ((double) sumMiddleElements) / 2;
        }
        else {
            median = (double) array[totalElements /2];
        }
        return median;
    }
 
    double countDeviation(int[] array, double average){
        double variance = 0.0;
        for (int i = 0; i < array.length; i++){
            variance += Math.pow(array[i] - average, 2);
        }
        return Math.sqrt(variance/array.length);
    }
    
    public void count(int[] array, double[] results) throws MathNoDataException {
        //if array is empty
        if (array.length == 0) {
           throw new MathNoDataException("NO DATA");
        } 
        else {
            //average
            results[0] = (countSum(array)/ array.length);
            //median
            results[1] = countMedian(array);
            //standardDevation
            results[2] = countDeviation(array, results[0]);
        }
    }
         
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import exception.MathNoDataException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



/**
 * Class that is ressponsible for logic of count
 * @author Paulina Czempiel
 * @version 3.0
 */
public class MathModel {
  
   /**
     * ArrayList of all results.
     */
    private List<Double> results;
    
    /**
     * history of count
     */
    private List<String> conversionHistory;
    
    /**
    * Array of numbers to calculate
    */
    private List<Double> array;
    //private double[] array;
    
    /**
     * Argumentless constructor.
     */
    public MathModel() {
        results = new ArrayList();
        array = new ArrayList();
        conversionHistory = new LinkedList();
    }
    
    /**
     * Sets new values to String containg numbers to calculate 
     * @param num String of numbers to parse
     */
    public void setNumbers(String num) {
        
        clearArray();
        String[] numbers = num.split(" ");
        for(String i : numbers){
            array.add(Double.parseDouble(i));
        }
    }
    
    /**
     * Testing input.
     */
    public void writeArray() {
        System.out.println(array.get(0));
        System.out.println(array.get(1));
        System.out.println(array.get(2));

    }
    
    /**
     * Clears array of results.
     */
    public void clearResults() {
       // results.forEach((i) -> {
        //    results.remove(i);
       // });
       results = new ArrayList();
        
    }
    
    /**
     * Clears array of results.
     */
    public void clearArray() {
        array = new ArrayList();
    }
    /**
     * Returns average.
     * 
     * @return average.
     */
     public double getAverage() {
        return results.get(0);
    }
    
     /**
     * Returns median.
     * 
     * @return median.
     */
     public double getMedian() {
        return results.get(1);
    }
    
     /**
     * Returns standard deviation.
     * 
     * @return standard deviation.
     */
     public double getStandard() {
        return results.get(2);
    }
     
     /**
     * Private method which counts average.
     *
     */
     private void countAverage(){
         double sum = 0.0;
         for(int i = 0; i < array.size(); i++){
             sum += array.get(i);
         }
         double average = sum/array.size();
         results.add(0, average); 
    }
    /**
     * Private method which counts median.
     *
     */
    private void countMedian(){
        Collections.sort(array);
        int middle = array.size() / 2;
        middle = middle > 0 && middle % 2 == 0 ? middle - 1 : middle;
        double median = array.get(middle);
        results.add(1, median);
    }
    /**
     * Private method which counts deviation.
     *
     */
    private void countDeviation(){
        double variance = 0.0;
        for (int i = 0; i < array.size(); i++){
            variance += Math.pow(array.get(i) - results.get(0), 2);
        }
        double standardDeviation = Math.sqrt(variance/array.size());
        results.add(2, standardDeviation );
    }
    
    /**
     * Method to calculate.
     * 
     * @throws MathNoDataException when there is no data
     */
    public void count() throws MathNoDataException {
        clearResults();
        //if list of result is not empty
        if(!(results.isEmpty())) {
            clearResults();
            //throw new MathNoDataException("Wrong data!");
        }
        //if array is empty
        if(array.isEmpty()) {
           throw new MathNoDataException("No data!");
        }
       else{
            try{
                countAverage();
                countMedian();
                countDeviation();
                if(results.size() != 3){
                   throw new MathNoDataException("Wrong counting!");
                }
            }   catch(NullPointerException e){
                System.out.println("Caught NullPointerException!");
            }
        }
    }
    
    /**
     * getter
     * @return conversion history
     */
    public List<String> getHistory()
    {
        return conversionHistory;
    }
   
    /**
     * Interface used in lambda expression, to create new history.
     * @author Paulina Czempiel
     * @version 1.0
    */
    @FunctionalInterface
    public interface HistoryInterface {
    /**
     * Creates history of count
     * @param array numbers calculated
     * @param results average, median and st.dev
     * @return 
     */
        String historyCreator(List<Double> array, List<Double> results);
    }
    
    /**
     * Lambda expression using interface HistoryInterface
     * @return calculation to create history
     */
    public String updateHistory()
    {
        HistoryInterface history = (@SuppressWarnings("LocalVariableHidesMemberVariable") List<Double> array, @SuppressWarnings("LocalVariableHidesMemberVariable") List<Double> results) -> 
        {
            return array + " --> " + results;
        };
        String newHis = "";
        if(!array.isEmpty() && !results.isEmpty())
        {   
            newHis= history.historyCreator(array, results);
            conversionHistory.add(newHis);
        }
        return newHis;
    }
}



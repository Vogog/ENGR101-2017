// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2017T1
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;

/** The program contains several methods for analysing the readings of the temperature levels over the course of a day.
 *  There are several things about the temperature levels that a user may be interested in: 
 *    The average temperature level.
 *    How the temperatures rose and fell over the day.
 *    The maximum and the minimum temperature levels during the day.
 */
public class TemperatureAnalyser{

    /** Constructor: set up user interface */
    public TemperatureAnalyser(){
        UI.initialise();
        UI.addButton("Analyse", this::doAnalyse );
        UI.addButton("Quit", UI::quit );
    }

    /* doAnalyse reads a sequence of temperature levels from the user and prints out
     *    average, maximum, and minimum level and plots all the levels
     *    by calling appropriate methods
     */
    public void doAnalyse(){
        UI.clearPanes();
        ArrayList<Double> listOfNumbers = UI.askNumbers("Enter levels, end with 'done': ");
        if (listOfNumbers.size() != 0) {
            this.printAverage(listOfNumbers);
            this.plotLevels(listOfNumbers);

            UI.printf("Maximum level was:  %f\n", this.maximumOfList(listOfNumbers));
            UI.printf("Minimum level was:  %f\n", this.minimumOfList(listOfNumbers));
        }
        else {
            UI.println("No readings");
        }
    }

    /** Print the average level
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the sum, which 
     *     needs to be initialised to an appropriate value.
     *  CORE
     */
    public void printAverage(ArrayList<Double> listOfNumbers) {
        double sum = 0;
        double numOfNumbers = listOfNumbers.size();
        for (double num : listOfNumbers) {
            sum = sum+num;
        }
        double average = sum/numOfNumbers;
        UI.println("The average of the temperature levels is: " + average);
        

    }

    /**
     * Plot a bar graph of the sequence of levels,
     * using narrow rectangles whose heights are equal to the level.
     * [Core]
     *   - Plot the bars.
     * [Completion]
     *   - Draws a horizontal line for the x-axis (or baseline) without any labels.
     *   - Any level greater than 400 should be plotted as if it were just 400, putting an
     *         asterisk ("*") above it to show that it has been cut off.
     * [Challenge:] 
     *   - The graph should also have labels on the axes, roughly every 50 pixels.
     *   - The graph should also draw negative temperature levels correctly.
     *   - Scale the y-axis and the bars so that the largest numbers and the smallest just fit on the graph.
     *     The numbers on the y axis should reflect the scaling.
     *   - Scale the x-axis so that all the bars fit in the window.
     */
    public void plotLevels(ArrayList<Double> listOfNumbers) {
        int base = 420;              //base of the graph
        int left = 50;               //left of the graph
        int step = 25;               //distance between plotted points

        for (double num : listOfNumbers) {
            if (num>400) {
                UI.fillRect(left,(base-400),step,400);
                UI.drawString("*",left+10,base-405);
                left = left+step+2;
            }
            
            
            else {
                UI.fillRect(left,(base-num),step,num);
                left = left+step+2;
            }
        }
        UI.drawLine(50,base,(left+5),base);
        

        UI.println("Finished plotting");
    }
    
    /**
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the maximum, which
     *     needs to be initialised to an appropriate value.
     *  COMPLETION
     */
    public double maximumOfList(ArrayList<Double> listOfNumbers) {
        double max = Double.MIN_VALUE;
        for (double num : listOfNumbers) {
            if (num>max) {
                max = num;
            }
        }
        return max;

    }

    /** Find and return the minimum level in the list
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the minimum, which
     *     needs to be initialised to an appropriate value.
     *  COMPLETION
     */
    public double minimumOfList(ArrayList<Double> listOfNumbers) {
        double min = Double.MAX_VALUE;
        for (double num : listOfNumbers) {
            if (num<min) {
                min = num;
            }
        }
        return min;

    }
}

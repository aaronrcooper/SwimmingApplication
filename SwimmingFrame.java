/**********************************
 *  Author: Aaron Cooper
 *  Class name:SwimmingFrame
 *  Purpose: Creates the frame class for the application
 *********************************/
package swimmingapplication;
//Imports
import javax.swing.*;

public class SwimmingFrame extends JFrame{
    private JButton startWorkout;
    private JButton addSet;
    //Combo Boxes for set collection
    private final int setRepeatMax = 24;
    private JComboBox timesRepeatedSelector;
    private JComboBox distanceSelector;
    private JComboBox strokeSelector;
    private JComboBox deleteSet;
    //Label to keep track of the workout
    private JLabel workoutLabel;
    //Distances Radio Button
    private JRadioButton meters;
    private JRadioButton yards;
    private String[] distanceArr = {"25","50", "75", "100", "125", "150", "175",
        "200", "225", "250", "275", "300", "350", "400", "450", "500", "550",
        "600", "700", "800", "900", "1000", "1100", "1200", "1300", "1400",
        "1500", "1600", "1650"};
    private String[] strokeArr = {"Freestyle", "Backstroke", "Butterfly",
        "Breaststroke", "Easy"};
    private String[] timesRepeatedArr = new String[setRepeatMax];
    //Default Constructor
    SwimmingFrame()
    {
        DrawGUI();
        this.setVisible(true);
    }
    //Loops through each set
    private void startSet()
    {
        
    }
    //populates the string array
    private void fillTimesRepeated()
    {
        String temp = "";
        for (int i = 1; i <= setRepeatMax; i++) 
        {
            temp = Integer.toString(i);
            timesRepeatedArr[i-1] = temp;
        }
    }
    //Draws the GUI
    private void DrawGUI()
    {
        startWorkout = new JButton("Start Workout");
        addSet = new JButton("Add Set");
        distanceSelector = new JComboBox();
    }
    
}

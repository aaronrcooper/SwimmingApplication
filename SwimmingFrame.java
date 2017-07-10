/**********************************
 *  Author: Aaron Cooper
 *  Class name:SwimmingFrame
 *  Purpose: Creates the frame class for the application
 *********************************/
package swimmingapplication;
//Imports
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.*;

public class SwimmingFrame extends JFrame{
    //Gets and sets the screensize
    private Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    
    //Buttons for workout manupulation
    private JButton startWorkout;
    private JButton addSet;
    private JButton deleteSet;
    
    //Combo Boxes for set collection
    private final int setRepeatMax = 24;
    private JComboBox timesRepeatedSelector;
    private JComboBox distanceSelector;
    private JComboBox strokeSelector;
    
    //Label to keep track of the workout
    private JLabel workoutLabel;
    private JLabel timerLabel;
    
    //Panels to hold groups of buttons
    private JPanel topPanel;
    private JPanel manipulateWorkoutPanel;
    private JPanel setWorkoutElementsPanel;
    private JPanel distanceUnitsPanel;
    private JPanel timerPanel;
    
    //Distances Radio Buttons
    private JRadioButton meters;
    private JRadioButton yards;
    
    //ComboBox values
    private String[] distanceArr = {"25","50", "75", "100", "125", "150", "175",
        "200", "225", "250", "275", "300", "350", "400", "450", "500", "550",
        "600", "700", "800", "900", "1000", "1100", "1200", "1300", "1400",
        "1500", "1600", "1650"};
    private String[] strokeArr = {"Freestyle", "Backstroke", "Butterfly",
        "Breaststroke", "Easy"};
    private String[] timesRepeatedArr = new String[setRepeatMax];
    
    //Variables to store workout information to be passed into Set class
    private String stroke;
    private String distance;
    private String timesRepeated;
    private String interval;
    
    //Creates a list to store the set
    private List<Set> workoutList;
    //Default Constructor
    SwimmingFrame()
    {
        DrawGUI();
        this.setVisible(true);
    }
    //Adds the set to a list
    private void addSetToList()
    {
        workoutList.add(new Set(stroke, distance, timesRepeated, interval));
        workoutLabel.setText(workoutLabel.getText()+ " " + timesRepeated + " x " + distance + " " + stroke + " on "
                + interval + "\n");
    }
    //Loops through each set
    private void loopSet()
    {
        for (Set currentSet : workoutList)
        {
            currentSet.loopSet();
        }
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
        fillTimesRepeated();
        
        //Resizes the screen
        screensize.height = (int) (screensize.height * 0.75);
        screensize.width = (int) (screensize.width * 0.75);
        
        //Creates the panels to hold button groups
        topPanel = new JPanel();
        manipulateWorkoutPanel = new JPanel();
        setWorkoutElementsPanel = new JPanel();
        distanceUnitsPanel = new JPanel();
        timerPanel = new JPanel();
        
        //Creates the start workout and add set buttons
        startWorkout = new JButton("Start Workout");
        addSet = new JButton("Add Set");
        deleteSet = new JButton("Delete Set");
        
        //Creates the combo boxes to select the workout components
        timesRepeatedSelector = new JComboBox(timesRepeatedArr);
        distanceSelector = new JComboBox(distanceArr);
        strokeSelector = new JComboBox(strokeArr);
        
        //Creates the radio buttons
        yards = new JRadioButton("Yards");
        meters = new JRadioButton("Meters");
        
        //Creates the workout label that displays the workout for the user
        workoutLabel = new JLabel("Workout:\n");
        //Creates the timer label
        timerLabel = new JLabel("Time: ");
        //Adds the appropriate items to each panel
        setWorkoutElementsPanel.add(timesRepeatedSelector);
        setWorkoutElementsPanel.add(distanceSelector);
        setWorkoutElementsPanel.add(strokeSelector);
        manipulateWorkoutPanel.add(addSet);
        manipulateWorkoutPanel.add(deleteSet);
        manipulateWorkoutPanel.add(startWorkout);
        distanceUnitsPanel.add(yards);
        distanceUnitsPanel.add(meters);
        topPanel.add(setWorkoutElementsPanel);
        topPanel.add(distanceUnitsPanel);
        topPanel.add(manipulateWorkoutPanel);
        //Sets the layout of the application
        this.setLayout(new BorderLayout());
        //Adds the elements to the GUI
        this.add(topPanel, BorderLayout.NORTH);
        this.add(workoutLabel, BorderLayout.CENTER);
        this.add(timerLabel, BorderLayout.SOUTH);
        //Sets the default closing operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screensize);
    }
    
}

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.*;

public class SwimmingFrame extends JFrame{
    //Gets and sets the screensize
    private Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    
    //Buttons for workout manupulation
    private JButton startWorkoutbtn;
    private JButton addSetbtn;
    private JButton deleteSetbtn;
    
    //Combo Boxes for set collection
    private final int setRepeatMax = 24;
    private JComboBox timesRepeatedSelector;
    private JComboBox distanceSelector;
    private JComboBox strokeSelector;
    
    //Label to keep track of the workout
    private JLabel workoutLabel;
    private JLabel timerLabel;
    private JLabel repeatedLabel;
    private JLabel distanceLabel;
    private JLabel strokeLabel;
//    private JLabel minutesLabel;
//    private JLabel secondsLabel;
    private JLabel intervalLabel;
    private JLabel countdownLabel;
    
    //Panels to hold groups of buttons
    private JPanel topPanel;
    private JPanel manipulateWorkoutPanel;
    private JPanel setWorkoutElementsPanel;
    private JPanel distanceUnitsPanel;
    private JPanel timerPanel;
    private JPanel workoutPanel;
    
    //Distances Radio Buttons
    private JRadioButton meters;
    private JRadioButton yards;
    
    //Text Fields
    private JTextField minutesTxt;
    private JTextField secondsTxt;
    
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
    private String intervalMins;
    private String intervalSecs;
    
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
        workoutList.add(new Set(stroke, distance, timesRepeated, intervalMins, intervalSecs));
        workoutLabel.setText(workoutLabel.getText()+ " " + timesRepeated + " x " + distance + " " + stroke + " on "
                + interval + "\n");
    }
    private void deleteLastSet()
    {
        workoutList.remove(workoutList.size()-1);
    }
    //Loops through each set
    private void loopSets()
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
        //Fills the times repeated array
        fillTimesRepeated();
        
        //Resizes the screen
        screensize.height = (int) (screensize.height * 0.75);
        //screensize.width = (int) (screensize.width * 0.75);
        
        //Creates the panels to hold button groups
        topPanel = new JPanel();
        manipulateWorkoutPanel = new JPanel();
        setWorkoutElementsPanel = new JPanel();
        distanceUnitsPanel = new JPanel();
        timerPanel = new JPanel();
        workoutPanel = new JPanel();
        
        //Creates the start workout and add set buttons
        startWorkoutbtn = new JButton("Start Workout");
        addSetbtn = new JButton("Add Set");
        deleteSetbtn = new JButton("Delete Set");
        
        //Creates the combo boxes to select the workout components
        timesRepeatedSelector = new JComboBox(timesRepeatedArr);
        distanceSelector = new JComboBox(distanceArr);
        strokeSelector = new JComboBox(strokeArr);
        
        //Creates the radio buttons
        yards = new JRadioButton("Yards");
        meters = new JRadioButton("Meters");
        
        //Creates the text fields
        minutesTxt = new JTextField("Minutes");
        secondsTxt = new JTextField("Seconds");
        
        //Creates the labels for the buttons and the workout and time labels
        workoutLabel = new JLabel("Workout:\n");
        timerLabel = new JLabel("Time: ");
        repeatedLabel = new JLabel("Times Repeated: ");
        distanceLabel = new JLabel("Distance: ");
        strokeLabel = new JLabel("Stroke: ");
        intervalLabel = new JLabel("Interval: ");
        countdownLabel = new JLabel();
        
        //Creates text fields
        minutesTxt = new JTextField("Minutes");
        secondsTxt = new JTextField("Seconds");
        
        //Adds the appropriate items to each panel
        setWorkoutElementsPanel.add(repeatedLabel);
        setWorkoutElementsPanel.add(timesRepeatedSelector);
        setWorkoutElementsPanel.add(distanceLabel);
        setWorkoutElementsPanel.add(distanceSelector);
        setWorkoutElementsPanel.add(strokeLabel);
        setWorkoutElementsPanel.add(strokeSelector);
        setWorkoutElementsPanel.add(intervalLabel);
        setWorkoutElementsPanel.add(minutesTxt);
        setWorkoutElementsPanel.add(secondsTxt);
        manipulateWorkoutPanel.add(addSetbtn);
        manipulateWorkoutPanel.add(deleteSetbtn);
        manipulateWorkoutPanel.add(startWorkoutbtn);
        distanceUnitsPanel.add(yards);
        distanceUnitsPanel.add(meters);
        
        //Adds elements to the top panel
        topPanel.add(setWorkoutElementsPanel);
        topPanel.add(distanceUnitsPanel);
        topPanel.add(manipulateWorkoutPanel);
        
        //Adds elements to the timer panel
        timerPanel.add(timerLabel);
        
        //Adds elements to the workout label
        workoutPanel.add(workoutLabel);
        
        //Action listeners
        startWorkoutbtn.addActionListener(new ActionHandler());
        addSetbtn.addActionListener(new ActionHandler());
        deleteSetbtn.addActionListener(new ActionHandler());
        minutesTxt.addFocusListener(new FocusHandler());
        secondsTxt.addFocusListener(new FocusHandler());
        //Radio button handlers
        yards.addItemListener(new RadioButtonHandler());
        meters.addItemListener(new RadioButtonHandler());

        
        //Sets the layout of the application
        this.setLayout(new BorderLayout());
        
        //Adds the elements to the GUI
        this.add(topPanel, BorderLayout.NORTH);
        this.add(workoutPanel, BorderLayout.WEST);
        this.add(timerPanel, BorderLayout.CENTER);
        
        //Sets the default closing operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screensize);
    }
        //Private inner class to handle button actions
        private class ActionHandler implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Action for add set button
                if(e.getSource() == addSetbtn)
                {
                    try
                    {
                        //Checks to see if the input of the minutes/seconds are integers
                        String temp = minutesTxt.getText();
                        int tempInt= Integer.parseInt(temp);
                        temp = secondsTxt.getText();
                        tempInt = Integer.parseInt(temp);
                        //Sets the interval to a string in a minutes:seconds format
                        interval = minutesTxt.getText() + ":" + secondsTxt.getText();
                        //Adds the set to a List
                        addSetToList();                        
                    }
                    catch(NumberFormatException err)
                    {
                        String errorMessage = "Please input a proper value into"
                                + " the minutes or seconds text box";
                        JOptionPane.showMessageDialog(null, errorMessage );
                    }

                }
                //action for delete set button
                if(e.getSource() == deleteSetbtn)
                {
                    //deletes the last set input
                    deleteLastSet();
                }
                //action for start workout button
                if(e.getSource() == startWorkoutbtn)
                {
                    //Starts workout
                    loopSets();
                }
            }
        }
        //Private inner class to handle radio button state changes
        private class RadioButtonHandler implements ItemListener
        {
            @Override
            public void itemStateChanged(ItemEvent ie) 
            {
                //Action for the yards radio button state being set
                if (ie.getSource() == yards) 
                {
                    //Determines action if the state is SELECTED
                    if (ie.getStateChange() == ItemEvent.SELECTED) 
                    {
                        meters.setSelected(false);
                    }
                }
                //Action for the meters radio button state being set
                if (ie.getSource() == meters) 
                {
                    //Determines action if the state is SELECTED
                    if (ie.getStateChange() == ItemEvent.SELECTED) 
                    {
                        yards.setSelected(false);
                    }
                }
            }
        }
        private class FocusHandler implements FocusListener
        {

            @Override
            public void focusGained(FocusEvent fe) {                
                if (fe.getSource() == minutesTxt) 
                {
                    minutesTxt.selectAll();

                }
                //Selects all text in order to minimize accidental input error
                if (fe.getSource() == secondsTxt) 
                {
                    secondsTxt.selectAll();
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                //Places minutes into the text field if there is no input
                if (fe.getSource() == minutesTxt) 
                {
                    if (minutesTxt.getText() == null) 
                    {
                        minutesTxt.setText("Minutes");
                    }
                }
                //Places seconds into the text field if there is no input
                if (fe.getSource() == secondsTxt) 
                {
                    if (secondsTxt.getText() == "") 
                    {
                        secondsTxt.setText("Seconds");
                    }
                }
            }
            
        }

}

/*
*Author:Aaron Cooper
*Name:Set
*Purpose: Class to determine class length
*/
package swimmingapplication;

//Imports
import java.util.Timer;
import java.util.TimerTask;

public class Set {

    //Instance variables
    private String stroke;
    private String distance;
    private String repeated;
    private int repeatedInt;
    private String intervalMins;
    private String intervalSecs;
    private int intervalInt;
    private Timer timer;
    private int delay;
    private int period;
    //Constructor
    Set(String pStroke, String pDistance, String pRepeated, String pIntervalMins, String pIntervalSecs){
        //Sets all of the instance variables to the values passed
        this.stroke = pStroke;
        this.distance = pDistance;
        this.repeated = pRepeated;
        this.repeatedInt = Integer.parseInt(pRepeated);
        this.intervalMins = pIntervalMins;
        this.intervalSecs = pIntervalSecs;
        //this.intervalInt = Integer.parseInt(this.interval);
        //Creates a delay period, a time period and a timer object in order to
        //track time
        delay = 1000;
        period = 1000;
        timer = new Timer();
    }
    //Loops through the sets
    public void loopSet() 
    {
        //Schedules a new Timer task using an anonymous inner class
        timer.scheduleAtFixedRate(new TimerTask()
        {
            int minutes = Integer.parseInt(getIntervalMins());
            int seconds = Integer.parseInt(getIntervalSecs());         
            //Run method
            public void run()
            {
                while (repeatedInt !=0)
                {
                    //Loops through until the seconds and minutes are depleted
                    while (minutes >0 && seconds >0)
                    {
                        //Loops the seconds variable back to the beginning of the next minute
                        if ((seconds == 0))
                        {
                            seconds = 60;
                            minutes--;
                        }
                        //Decreases one second
                        seconds--;
                    }
                    //Loops downward
                    repeatedInt--;
                }
                
            }
        }, delay, period);  //Sets the delay and period to 1 second (1000ms)
    }

    /**
     * @return the stroke
     */
    public String getStroke() {
        return stroke;
    }

    /**
     * @return the distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * @return the repeated
     */
    public String getRepeated() {
        return repeated;
    }

    /**
     * @return the repeatedInt
     */
    public int getRepeatedInt() {
        return repeatedInt;
    }

    /**
     * @return the intervalInt
     */
    public int getIntervalInt() {
        return intervalInt;
    }
    /**
     * @return the intervalMins
     */
    public String getIntervalMins() {
        return intervalMins;
    }

    /**
     * @return the intervalSecs
     */
    public String getIntervalSecs() {
        return intervalSecs;
    }
}

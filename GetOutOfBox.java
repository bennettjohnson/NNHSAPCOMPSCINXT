import lejos.nxt.*;
import java.util.ArrayList;
/**
 * Code to get out of the box.
 * 
 * Works by rotating the NXT Robot 360 degrees about its Center Of Mass and getting light value
 * from the light sensor every degree and adding it to an Arraylist.
 * 
 * The NXT Robot will then rotate index number of degrees where index is the index of the
 * highest light value.
 * 
 * @version(v0.1a)
 * @author(Bennett Johnson)
 */
public class GetOutOfBox
{
    /*
     * Comments:
     *  LeJOS JAR File Path: C:\Program Files\leJOS NXJ\lib\nxt\classes.jar
     *  980 Taycho degrees to rotate entire NXT Robot
     */
    
    //Instance Variables
    private LightSensor light;
    
    private ArrayList<Integer> lightVals;
    
    private int fullNXTRotation = 980;
    private int maxLightVal = 0;
    private int maxLightValIndex;
    
    private static final double ROTATE = 3.14225;
    /**
     * Constructor for class GetOutOfBox.
     */
    public GetOutOfBox()
    {
        // Initialize all instance variables.
        this.light = new LightSensor(SensorPort.S2);
        this.lightVals = new ArrayList<Integer>();
        
        // Set Preferences.
        this.light.setFloodlight(true);
    }
    /**
     * Populate the ArrayList with light values every degree of that the NXT Robot is turned.
     */
    private void populateArray()
    {
        // Output to terminal.
        System.out.println("Populating Array.");
        
        // Start the motors in opposite directions to rotate the NXT Robot.
        Motor.A.forward();
        Motor.C.backward();
        
        // Get light value from the light sensor every degree that the NXT Robot turns.
        while(Motor.A.getTachoCount() <= this.fullNXTRotation && Motor.C.getTachoCount() <= this.fullNXTRotation)
        {
            System.out.println(this.light.readValue());
            this.lightVals.add(this.light.readValue());
        }
        
        
        // Stop the motors on the NXT Robot.
        Motor.A.stop();
        Motor.C.stop();
        
        // Reset Tacho Count
        Motor.A.resetTachoCount();
        Motor.C.resetTachoCount();
        
        System.out.println("done");
    }
    /**
     * Returns the index of the highest light value in the lightVals ArrayList.
     * 
     * @return  The Tacho degree of the highest light value in the lightVals ArrayList.
     */
    private void getHighPointIndex()
    {
        // Output to terminal.
        System.out.println("Getting High Point Index.");
        
        // Loop Through lightVals
        for(int i : this.lightVals)
        {
            // Check if I is larger than the largest light value, if so, set the largest light value to I.
            if(i > this.maxLightVal)
            {
                this.maxLightVal = i;
            }
        }
        System.out.println(this.maxLightVal);
        // Return the index of the largest light value in the ArrayList lightVals.
        this.maxLightValIndex = this.lightVals.indexOf(this.maxLightVal) + 30;
    }
    /**
     * Rotate the NXT Robot to face the maximum light value.
     */
    private void goToLightSource()
    {
        // Output to terminal.
        Motor.A.resetTachoCount();
        Motor.C.resetTachoCount();
        System.out.println("Going to light source.");
        
        // Start the motors in opposite directions to rotate the NXT Robot.
        Motor.A.forward();
        Motor.C.backward();
        
        // Do nothing while the robot is not facing the max light val.
        while(Motor.A.getTachoCount() < this.maxLightValIndex)
        {
            System.out.println("Not at light source.");
        }
        
        // Stop motors on the NXT Robot.
        Motor.A.stop();
        Motor.C.stop();
        
        // Reset Tacho Count.
        Motor.A.resetTachoCount();
        Motor.C.resetTachoCount();
    }
    /**
     * Call all steps to get out of box.
     */
    public void getOut()
    {
        // Call methods.
        populateArray();
        getHighPointIndex();
        goToLightSource();
        
        Motor.A.resetTachoCount();
        Motor.C.resetTachoCount();
        Motor.A.forward();
        Motor.C.forward();
        
        
        
        while(Motor.C.getTachoCount() < 355 * ROTATE )
        {}
        Motor.A.stop();
        Motor.C.stop();
        //Button.waitForAnyPress();
    }
}

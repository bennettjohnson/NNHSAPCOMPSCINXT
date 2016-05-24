import lejos.nxt.*;

/**
 * Write a description of class RunOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RunOver
{
    /** description of instance variable x (add comment for each instance variable) */
    private static final double ROTATE = 18.5;

    /**
     * Default constructor for objects of class RunOver
     */
    public RunOver()
    {

    }

    /**
     * An example of a method - replace this comment with your own
     *    that describes the operation of the method
     *
     * @pre        preconditions for the method
     *            (what the method assumes about the method's parameters and class's state)
     * @post    postconditions for the method
     *            (what the method guarantees upon completion)
     * @param    y    description of parameter y
     * @return    description of the return value
     */
    public void run()
    {
        Motor.A.resetTachoCount();
        Motor.C.resetTachoCount();
        
        while (Motor.A.getTachoCount() < 270)
        {
            Motor.A.forward();
            Motor.C.backward();
        }
        Motor.A.stop();
        Motor.C.stop();

        Motor.A.resetTachoCount();
        Motor.C.resetTachoCount();

        Motor.A.setSpeed(495);
        Motor.C.setSpeed(360);
        while (Motor.C.getTachoCount() < 360 * ROTATE)
        {
            if (Motor.C.getTachoCount() > 360 * ROTATE/2)
            {
                Motor.A.setSpeed(510);
            }
            Motor.A.forward();
            Motor.C.forward();
        }

        Motor.A.stop();
        Motor.C.stop();
    }

}

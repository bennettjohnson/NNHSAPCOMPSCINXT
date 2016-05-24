import lejos.nxt.*;

public class readVal
{
    public static void main(String[] args)
    {
        LightSensor light = new LightSensor(SensorPort.S2);
        light.setFloodlight(false);
        while(true)
            System.out.println(light.readValue());
    }
}

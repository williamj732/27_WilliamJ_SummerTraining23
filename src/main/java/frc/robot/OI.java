package frc.robot;

import frc.robot.commands.ToggleIntake;
import harkerrobolib.joysticks.XboxGamepad;
import harkerrobolib.util.Constants;

public class OI {
    private static OI instance; 
    private XboxGamepad driver;
    private OI(){
        driver = new XboxGamepad(Constants.DRIVER_ID);
    }
    private void initBindings() {
        driver.getButtonX().whileTrue(new ToggleIntake());
    }
    public XboxGamepad getDriver(){
        return driver;
    }
    public static OI getInstance(){
        if(instance == null){
           instance = new OI();  
        }
        return instance;
    }
}
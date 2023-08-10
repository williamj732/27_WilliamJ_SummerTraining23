package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import harkerrobolib.wrappers.HSTalon;

public class Intake extends SubsystemBase{
    private static Intake instance;

    private HSTalon master;
    private HSTalon follower;
    private DoubleSolenoid intakePiston;

    private Intake() {
        master = new HSTalon(RobotMap.Intake.MASTER_ID);
        follower = new HSTalon(RobotMap.Intake.FOLLOWER_ID);

        master.setInverted(RobotMap.Intake.MASTER_INVERT);
        follower.setInverted(RobotMap.Intake.FOLLOWER_INVERT);

        follower.follow(master);
        
        intakePiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.Intake.PISTON_FORWARD_ID, RobotMap.Intake.PISTON_REVERSE_ID);
    }

    public void setOutput(double power) {  
        master.set(ControlMode.PercentOutput, power);
    }


    public void toggleState() {
        if (intakePiston.get() == DoubleSolenoid.Value.kOff) {
                intakePiston.set(DoubleSolenoid.Value.kReverse);
        } else {
            intakePiston.toggle();
        }
    }

    public static Intake getInstance() {
        if (instance == null) {
            instance = new Intake();
        }
        return instance;
    }
}

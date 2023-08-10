package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import harkerrobolib.wrappers.HSTalon;

public class Elevator extends SubsystemBase{
    private static Elevator instance;

    private HSTalon master;
    private VictorSPX follower1;
    private VictorSPX follower2;
    private VictorSPX follower3;

    private Elevator() {
        master = new HSTalon(RobotMap.Elevator.MASTER_ID);
        follower1 = new VictorSPX(RobotMap.Elevator.FOLLOWER1_ID);
        follower2 = new VictorSPX(RobotMap.Elevator.FOLLOWER2_ID);
        follower3 = new VictorSPX(RobotMap.Elevator.FOLLOWER3_ID);

        master.setInverted(RobotMap.Elevator.MASTER_INVERT);
        follower1.setInverted(RobotMap.Elevator.FOLLOWER1_INVERT);
        follower2.setInverted(RobotMap.Elevator.FOLLOWER2_INVERT);
        follower3.setInverted(RobotMap.Elevator.FOLLOWER3_INVERT);

        follower1.follow(master);
        follower2.follow(master);
        follower3.follow(master);

        master.setNeutralMode(NeutralMode.Brake);
        follower1.setNeutralMode(NeutralMode.Brake);
        follower2.setNeutralMode(NeutralMode.Brake);
        follower3.setNeutralMode(NeutralMode.Brake);

        master.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        master.setSelectedSensorPosition(0);
        master.configForwardSoftLimitThreshold(18000);
        master.configReverseSoftLimitThreshold(1000);
        master.configReverseSoftLimitEnable(true);
        master.configForwardSoftLimitEnable(true);
    }

    public void setElevatorPower (double speed) {
        master.set(ControlMode.PercentOutput,speed, DemandType.ArbitraryFeedForward,0.13);
    }

    public static Elevator getInstance() {
        if (instance == null) {
            instance = new Elevator();
        }
        return instance;
    }
}
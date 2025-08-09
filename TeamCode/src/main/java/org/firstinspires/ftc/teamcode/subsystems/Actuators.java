package org.firstinspires.ftc.teamcode.subsystems;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorGroup;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;

import org.firstinspires.ftc.teamcode.util.configurations;

public class Actuators extends Subsystem {
    public static final Actuators INSTANCE = new Actuators();
    private Actuators() {}
    public MotorEx Act1;
    public MotorEx Act2;
    public MotorGroup Actuators;
    public String Act1Name = "Act1";
    public String Act2Name = "Act2";



    public Command actuatorsUp(){
        return new SetPower(Actuators, configurations.actuatorUpPower, this);
    }
    public Command actuatorsDown(){
        return new SetPower(Actuators, configurations.actuatorDownPower, this);
    }
    public Command actuatorsStop(){
        return new SetPower(Actuators, configurations.actuatorStopPower, this);
    }

    @Override
    public void initialize(){
        Act1 = new MotorEx(Act1Name);
        Act2 = new MotorEx(Act2Name);
        Actuators = new MotorGroup(Act1, Act2);
    }


}

package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;
import org.firstinspires.ftc.teamcode.util.configurations;

public class Elbows extends Subsystem {
    public static final Elbows INSTANCE = new Elbows();
    private Elbows() {}

    public Servo rightElbow;

    public String elbow_2 = "elbow_2";


    public Command elbowDown(){
        return new ServoToPosition(rightElbow, configurations.rightElbowUpPosition, this);
    }

    public Command elbowUp(){
        return new ServoToPosition(rightElbow, configurations.rightElbowDownPosition, this);
    }

    @Override
    public void initialize(){
        rightElbow = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, elbow_2);
    }
}

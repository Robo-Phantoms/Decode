package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;



import org.firstinspires.ftc.teamcode.util.configurations;

    public class Claw extends Subsystem {
        public static final Claw INSTANCE = new Claw();
        private Claw() {}

        public Servo clawServo;
        public Servo pivot;
        public String pivotName = "pivot";
        public String name = "claw";

        public Command openClawCommand(){
            return new ServoToPosition(clawServo, configurations.clawOpenPosition, this);
        }

        public Command closeClawCommand(){
            return new ServoToPosition(clawServo, configurations.clawClosePosition, this);
        }

        public Command notSpunPosition(){
            return new ServoToPosition(pivot, configurations.notSpun, this);
        }
        public Command spunPosition(){
            return new ServoToPosition(pivot, configurations.spun, this);
        }

        @Override
        public void initialize(){
            clawServo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class,name);
            pivot = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, pivotName);
            pivot.setDirection(Servo.Direction.REVERSE);
            pivot.setPosition(configurations.notSpun);
        }

    }

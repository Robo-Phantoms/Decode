package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "CWT")
public class CompliantWheelTest extends OpMode {

    private DcMotorEx compliantWheelLeft;
    private DcMotorEx compliantWheelRight;
    @Override
    public void init(){
        compliantWheelLeft = hardwareMap.get(DcMotorEx.class, "left");
        compliantWheelRight = hardwareMap.get(DcMotorEx.class, "right");
    }

    @Override
    public void loop(){
        if (gamepad1.a){
            compliantWheelLeft.setPower(-1);
            compliantWheelRight.setPower(1);
        }
    }
}

package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.core.command.utility.LambdaCommand;
import com.rowanmcalpin.nextftc.core.command.utility.conditionals.PassiveConditionalCommand;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;
import com.rowanmcalpin.nextftc.ftc.driving.DifferentialTankDriverControlled;
import com.rowanmcalpin.nextftc.ftc.hardware.Drivetrain;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorGroup;

import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name="teleop")
public class teleop extends NextFTCOpMode {

    public teleop() {
        super(Claw.INSTANCE, Elbows.INSTANCE, Slides.INSTANCE, Actuators.INSTANCE);
    }

    public String frontLeftName = "leftFront";
    public String frontRightName = "rightFront";
    public String backLeftName = "leftBack";
    public String backRightName = "rightBack";

    public MotorEx leftFront;
    public MotorEx rightFront;
    public MotorEx leftBack;
    public MotorEx rightBack;
    public Command driverControlled;
    //public LambdaCommand strafeLeft;
    public MotorGroup leftMotors;
    public MotorGroup rightMotors;


    boolean isClawOpen = false;
    boolean isElbowUp = false;


    @Override
    public void onInit() {
        leftFront = new MotorEx(frontLeftName);
        leftBack = new MotorEx(backLeftName);
        rightBack = new MotorEx(backRightName);
        rightFront = new MotorEx(frontRightName);

        // Change the motor directions to suit your robot.
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);

        leftMotors = new MotorGroup(leftFront, leftBack);
        rightMotors = new MotorGroup(rightFront, rightBack);



    }

    @Override
    public void onStartButtonPressed(){
        driverControlled = new DifferentialTankDriverControlled(leftMotors, rightMotors, gamepadManager.getGamepad1());
        driverControlled.invoke();
        /*strafeLeft = new LambdaCommand()
                .setUpdate(() -> {
                    leftFront.setPower(0.75);
                    leftBack.setPower(-0.75);
                    rightFront.setPower(0.75);
                    rightBack.setPower(-0.75);
                })
                .setStop(interrupted -> {
                    leftFront.setPower(0);
                    leftBack.setPower(0);
                    rightFront.setPower(0);
                    rightBack.setPower(0);
                });*/

        setGamePad1Commands();
        setGamePad2Commands();
    }
    public void setGamePad2Commands(){
        //Claw Toggle
        gamepadManager.getGamepad2().getX().setPressedCommand(() ->
                new SequentialGroup(
                        new InstantCommand(() -> {
                            isClawOpen = !isClawOpen;
                        }),
                        new PassiveConditionalCommand(
                                () -> isClawOpen,
                                () -> Claw.INSTANCE.openClawCommand(),
                                () -> Claw.INSTANCE.closeClawCommand()
                        )
                )
        );

        gamepadManager.getGamepad2().getB().setPressedCommand(() ->
                new SequentialGroup(
                        new InstantCommand(() -> {
                            isElbowUp = !isElbowUp;
                        }),
                        new PassiveConditionalCommand(
                                () -> isElbowUp,
                                () -> Elbows.INSTANCE.elbowUp(),
                                () -> Elbows.INSTANCE.elbowDown()
                        )
                ));

        gamepadManager.getGamepad2().getA().setPressedCommand(() ->
                new ParallelGroup(Elbows.INSTANCE.elbowDown(), Claw.INSTANCE.spunPosition())
        );
        gamepadManager.getGamepad2().getY().setPressedCommand(() ->
                new ParallelGroup(Elbows.INSTANCE.elbowUp(), Claw.INSTANCE.notSpunPosition())
        );

        gamepadManager.getGamepad2().getDpadUp().setPressedCommand(Slides.INSTANCE::slidesUp);
        gamepadManager.getGamepad2().getDpadDown().setPressedCommand(Slides.INSTANCE::slidesDown);
    }

    public void setGamePad1Commands(){
        gamepadManager.getGamepad1().getLeftTrigger().setPressedCommand(
                value -> Actuators.INSTANCE.actuatorsDown()
        );
        gamepadManager.getGamepad1().getLeftTrigger().setReleasedCommand(
                value  -> Actuators.INSTANCE.actuatorsStop()
        );
        gamepadManager.getGamepad1().getRightTrigger().setPressedCommand(
                value -> Actuators.INSTANCE.actuatorsUp()
        );
        gamepadManager.getGamepad1().getRightTrigger().setReleasedCommand(
                value  -> Actuators.INSTANCE.actuatorsStop()
        );
        //gamepadManager.getGamepad1().getLeftBumper().setHeldCommand(() -> strafeLeft);
    }
    @Override
    public void onUpdate(){
        if (gamepad1.right_bumper) {
            leftFront.setPower(0.75);
            leftBack.setPower(-0.75);
            rightFront.setPower(0.75);
            rightBack.setPower(-0.75);
        } else if (gamepad1.left_bumper) {
            leftFront.setPower(-0.75);
            leftBack.setPower(0.75);
            rightFront.setPower(-0.75);
            rightBack.setPower(0.75);
        }
        if(gamepad1.a){
           leftFront.setPower(-0.3);
           leftBack.setPower(-0.3);
           rightFront.setPower(-0.3);
           rightBack.setPower(-0.3);
        }
        if(gamepad1.y){
            leftFront.setPower(0.3);
            leftBack.setPower(0.3);
            rightFront.setPower(0.3);
            rightBack.setPower(0.3);
        }
    }
}

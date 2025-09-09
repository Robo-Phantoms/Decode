package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.driving.DifferentialTankDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;
import org.firstinspires.ftc.teamcode.util.*;


@TeleOp(name="TeleopWithOnlyDrive")
public class TeleopWithOnlyDrive extends NextFTCOpMode {

    public TeleopWithOnlyDrive(){
        addComponents(
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    private MotorEx leftFront;
    private MotorEx rightFront;
    private MotorEx leftBack;
    private MotorEx rightBack;
    private MotorGroup leftMotors;
    private MotorGroup rightMotors;

    @Override
    public void onInit() {
        leftFront = new MotorEx("leftFront").reversed();
        rightFront = new MotorEx("rightFront");
        leftBack = new MotorEx("leftBack").reversed();
        rightBack = new MotorEx("rightBack");
        leftMotors = new MotorGroup(leftFront, leftBack);
        rightMotors = new MotorGroup(rightFront, rightBack);
    }

    @Override
    public void onStartButtonPressed() {
        Command driverControlled = new DifferentialTankDriverControlled(leftMotors, rightMotors, Gamepads.gamepad1().leftStickY(), Gamepads.gamepad1().rightStickY());        driverControlled.schedule();

        Gamepads.gamepad2().leftBumper()
                .whenTrue(() -> ExtraCommands.strafeLeft(leftFront, rightFront, leftBack, rightBack))
                .whenBecomesFalse(() -> ExtraCommands.stop(leftFront, rightFront, leftBack, rightBack));
        Gamepads.gamepad2().rightBumper()
                .whenTrue(() -> ExtraCommands.strafeRight(leftFront, rightFront, leftBack, rightBack))
                .whenBecomesFalse(() -> ExtraCommands.stop(leftFront, rightFront, leftBack, rightBack));
        Gamepads.gamepad2().y()
                .whenTrue(() -> ExtraCommands.forward(leftFront, rightFront, leftBack, rightBack))
                .whenBecomesFalse(() -> ExtraCommands.stop(leftFront, rightFront, leftBack, rightBack));
        Gamepads.gamepad2().a()
                .whenTrue(() -> ExtraCommands.backward(leftFront, rightFront, leftBack, rightBack))
                .whenBecomesFalse(() -> ExtraCommands.stop(leftFront, rightFront, leftBack, rightBack));
    }
}

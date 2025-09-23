package org.firstinspires.ftc.teamcode.opModes;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.driving.DifferentialTankDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;

import org.firstinspires.ftc.teamcode.util.ExtraCommands.*;
import org.firstinspires.ftc.teamcode.util.Subsystems.*;


@TeleOp(name="Teleop")
public class Teleop extends NextFTCOpMode {

    public Teleop(){
        addComponents(
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE,
                new SubsystemComponent(Intake.INSTANCE));
    }

    private MotorEx leftFront = new MotorEx("leftFront");
    private MotorEx rightFront = new MotorEx("rightFront").reversed();
    private MotorEx leftBack = new MotorEx("leftBack");
    private MotorEx rightBack = new MotorEx("rightBack").reversed();
    private MotorGroup leftMotors = new MotorGroup(leftFront, leftBack);
    private MotorGroup rightMotors = new MotorGroup(rightFront, rightBack);

    @Override
    public void onStartButtonPressed() {
        Command driverControlled = new DifferentialTankDriverControlled(leftMotors, rightMotors, Gamepads.gamepad1().leftStickY(), Gamepads.gamepad1().rightStickY());
        driverControlled.schedule();

        Gamepads.gamepad1().leftBumper()
                .whenTrue(() -> DriveCommands.strafeLeft(leftFront, rightFront, leftBack, rightBack))
                .whenBecomesFalse(() -> DriveCommands.stop(leftFront, rightFront, leftBack, rightBack));

        Gamepads.gamepad1().rightBumper()
                .whenTrue(() -> DriveCommands.strafeRight(leftFront, rightFront, leftBack, rightBack))
                .whenBecomesFalse(() -> DriveCommands.stop(leftFront, rightFront, leftBack, rightBack));

        Gamepads.gamepad1().y()
                .whenTrue(() -> DriveCommands.forward(leftFront, rightFront, leftBack, rightBack))
                .whenBecomesFalse(() -> DriveCommands.stop(leftFront, rightFront, leftBack, rightBack));

        Gamepads.gamepad1().a()
                .whenTrue(() -> DriveCommands.backward(leftFront, rightFront, leftBack, rightBack))
                .whenBecomesFalse(() -> DriveCommands.stop(leftFront, rightFront, leftBack, rightBack));

        Gamepads.gamepad2().rightStickY().inRange(-0.1, 0.1)
                .whenFalse(() -> Intake.INSTANCE.intakeArtifact(gamepad2.right_stick_y).schedule())
                .whenTrue(() -> Intake.INSTANCE.stopIntake().schedule()
                );
    }
}

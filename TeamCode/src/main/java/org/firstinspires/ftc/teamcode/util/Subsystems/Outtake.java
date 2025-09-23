package org.firstinspires.ftc.teamcode.util.Subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.controllable.RunToVelocity;
import dev.nextftc.hardware.impl.MotorEx;

public class Outtake implements Subsystem {
    public static Outtake INSTANCE = new Outtake();
    private Outtake() {}

    private MotorEx leftFlyWheel = new MotorEx("leftFlyWheel").reversed();
    private MotorEx rightFlyWheel = new MotorEx("rightFlyWheel");
    private MotorGroup flyWheelOuttake = new MotorGroup(leftFlyWheel, rightFlyWheel);
    public static double kp, ki, kd;
    public static double kv, ka, ks;

    private ControlSystem controller = ControlSystem.builder()
            .velPid(kp, ki, kd)
            .basicFF(kv, ka, ks)
            .build();

    public Command shootArtifact = new RunToVelocity(controller, 0).requires(this);
    public Command stopShooting = new RunToVelocity(controller, 0).requires(this);

    @Override
    public void periodic(){
        flyWheelOuttake.setPower(controller.calculate(flyWheelOuttake.getState()));
    }

}

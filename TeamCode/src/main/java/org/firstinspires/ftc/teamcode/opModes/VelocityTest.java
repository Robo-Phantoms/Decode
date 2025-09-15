package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;

@Config
@TeleOp(name = "VelocityTest")
public class VelocityTest extends NextFTCOpMode {

    public VelocityTest(){
        addComponents(
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE
        );
    }
    private MotorEx compliantWheelLeft = new MotorEx("left").reversed();
    private MotorEx compliantWheelRight = new MotorEx("right");

    private MotorGroup Wheels = new MotorGroup(compliantWheelLeft, compliantWheelRight);
    public static double kp, ki, kd;
    public static double kv, ka, ks;
    public static double targetVel;
    public static ControlSystem controller = ControlSystem.builder()
            .velPid(kp, ki, kd)
            .basicFF(kv, ks, ka)
            .build();

    @Override
    public void onStartButtonPressed(){
        Gamepads.gamepad1().a().whenTrue(() -> controller.setGoal(new KineticState(0.0, targetVel, 0.0)));
    }

    @Override
    public void onUpdate(){
        KineticState currentState = new KineticState(Wheels.getCurrentPosition(), Wheels.getVelocity());
        double power = controller.calculate(currentState);
        Wheels.setPower(power);

        ActiveOpMode.telemetry().addData("currentPos", Wheels.getCurrentPosition());
        ActiveOpMode.telemetry().addData("currentVel", Wheels.getVelocity());
        ActiveOpMode.telemetry().addData("power", power);
        ActiveOpMode.telemetry().update();

    }
}

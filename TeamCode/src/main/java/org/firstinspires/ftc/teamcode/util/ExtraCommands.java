package org.firstinspires.ftc.teamcode.util;

import dev.nextftc.hardware.controllable.Controllable;

/**
 * @Param Use this class for extra commands that can't be put in subsystems
 */

public class ExtraCommands {
    public static double STRAFE_POWER = 0.75;

    public static void strafeLeft(Controllable lfMotor, Controllable rfMotor, Controllable lbMotor, Controllable rbMotor) {
        rfMotor.setPower(STRAFE_POWER);
        lfMotor.setPower(-STRAFE_POWER);
        lbMotor.setPower(STRAFE_POWER);
        rbMotor.setPower(-STRAFE_POWER);
    }

    public static void strafeRight(Controllable lfMotor, Controllable rfMotor, Controllable lbMotor, Controllable rbMotor) {
        rfMotor.setPower(-STRAFE_POWER);
        lfMotor.setPower(STRAFE_POWER);
        lbMotor.setPower(-STRAFE_POWER);
        rbMotor.setPower(STRAFE_POWER);
    }

    public static void stop(Controllable lf, Controllable rf, Controllable lb, Controllable rb) {
        rf.setPower(0);
        lf.setPower(0);
        lb.setPower(0);
        rb.setPower(0);
    }
}
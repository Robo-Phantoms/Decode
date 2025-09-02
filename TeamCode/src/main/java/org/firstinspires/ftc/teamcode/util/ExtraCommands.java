package org.firstinspires.ftc.teamcode.util;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.LambdaCommand;
import dev.nextftc.hardware.controllable.Controllable;

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
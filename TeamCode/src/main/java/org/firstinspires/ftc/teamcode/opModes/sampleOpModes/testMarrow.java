package org.firstinspires.ftc.teamcode.opModes.sampleOpModes;

import com.skeletonarmy.marrow.TimerEx;
import com.skeletonarmy.marrow.nextftc.RetryCommand;

import org.firstinspires.ftc.teamcode.util.Subsystems.*;

import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

public class testMarrow extends NextFTCOpMode {

    public testMarrow(){
        addComponents(
                new SubsystemComponent(Intake.INSTANCE, Outtake.INSTANCE),
                BulkReadComponent.INSTANCE
        );
    }
    TimerEx timer = new TimerEx();
    boolean isArtifactIntaken = timer.isMoreThan(2);
    int intakeRetries = 2;
    @Override
    public void onStartButtonPressed(){
        new RetryCommand(
                Intake.INSTANCE.intakeArtifact(1.0),
                () -> isArtifactIntaken,
                intakeRetries
        ).schedule();
    }
}

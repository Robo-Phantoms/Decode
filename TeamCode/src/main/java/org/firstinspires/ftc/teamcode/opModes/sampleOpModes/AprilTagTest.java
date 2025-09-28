package org.firstinspires.ftc.teamcode.opModes.sampleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp(name="AprilTag Test", group="Concept")
public class AprilTagTest extends OpMode {

    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal vision;

    private double myTagPoseX;
    private double myTagPoseY;
    private double myTagPoseZ;
    private double myTagPosePitch;
    private double myTagPoseRoll;
    private double myTagPoseYaw;
    private double myTagPoseRange;
    private double myTagPoseBearing;
    private double myTagPoseElevation;

    @Override
    public void init() {
        WebcamName webCam = hardwareMap.get(WebcamName.class, "Webcam");
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();

        vision = new VisionPortal.Builder()
                .setCamera(webCam)
                .addProcessor(aprilTagProcessor)
                .enableLiveView(true)
                .build();

        telemetry.addLine("AprilTag OpMode initialized");
        telemetry.update();
    }

    @Override
    public void init_loop(){
        telemetry.addData("Vision Portal Status", vision.getCameraState());
    }
    @Override
    public void loop() {
        List<AprilTagDetection> detections = aprilTagProcessor.getDetections();

        if (!detections.isEmpty()) {
            for (AprilTagDetection detection : detections) {
                telemetry.addData("Tag ID", detection.id);

                if (detection.metadata != null) {
                    myTagPoseX = detection.ftcPose.x;
                    myTagPoseY = detection.ftcPose.y;
                    myTagPoseZ = detection.ftcPose.z;

                    myTagPosePitch = detection.ftcPose.pitch;
                    myTagPoseRoll = detection.ftcPose.roll;
                    myTagPoseYaw = detection.ftcPose.yaw;

                    myTagPoseRange = detection.ftcPose.range;
                    myTagPoseBearing = detection.ftcPose.bearing;
                    myTagPoseElevation = detection.ftcPose.elevation;
                }
            }
        } else {
            telemetry.addLine("No tags detected");
        }

        telemetry.update();
    }


}

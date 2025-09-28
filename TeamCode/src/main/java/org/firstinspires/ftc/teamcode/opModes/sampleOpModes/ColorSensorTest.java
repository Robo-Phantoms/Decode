package org.firstinspires.ftc.teamcode.opModes.sampleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

public class ColorSensorTest extends OpMode {

    private NormalizedColorSensor colorSensor;
    public static double redValues = 0;
    public static double blueValues = 0;
    public static double greenValues = 0;
    @Override
    public void init() {
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color");
    }
    @Override
    public void loop() {
        telemetry.addData("Light Detected", ((OpticalDistanceSensor) colorSensor).getLightDetected());
        NormalizedRGBA colors = colorSensor.getNormalizedColors();

        double red = colors.red;
        double blue = colors.blue;
        double green = colors.green;

        telemetry.addData("Red", "%.3f", red);
        telemetry.addData("Green", "%.3f", green);
        telemetry.addData("Blue", "%.3f", blue);
        telemetry.update();

        if (red > redValues && blue > blueValues && green > greenValues) {
            telemetry.addLine("Purple Detected!");
        }
    }
}

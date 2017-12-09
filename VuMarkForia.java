package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by thebiteffect on 12/7/2017.
 */

public class VuMarkForia {

    // Lots of Vuforia inits
    static VuforiaTrackables relicTrackables;
    static VuforiaTrackable relicTemplate;
    VuforiaLocalizer vuforia;


    public VuMarkForia(HardwareMap hardwareMap, String key, VuforiaLocalizer.CameraDirection cameraDirection, boolean showViewport) {
        // This is basically an init
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        if (showViewport) {
            parameters.cameraMonitorViewIdParent = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        }

        parameters.vuforiaLicenseKey = key;

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters); // Create the Vuforia instance.

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);

    }

    public VuMarkForia(HardwareMap hardwareMap, String key, VuforiaLocalizer.CameraDirection cameraDirection) {
        new VuMarkForia(hardwareMap, key, cameraDirection, false);
    }

    public VuMarkForia(HardwareMap hardwareMap, String key) {
        new VuMarkForia(hardwareMap, key, VuforiaLocalizer.CameraDirection.BACK, false);
    }

}

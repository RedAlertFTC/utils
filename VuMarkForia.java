package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Deprecated // Never worked in the first place...
public class VuMarkForia {

    // Lots of Vuforia inits
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;
    VuforiaLocalizer vuforia;
    OpenGLMatrix lastLocation = null;

    public VuMarkForia(HardwareMap hardwareMap, String key, VuforiaLocalizer.CameraDirection cameraDirection, boolean showViewport) {
        // This is basically an init
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        if (showViewport) {
            parameters.cameraMonitorViewIdParent = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        }

        parameters.vuforiaLicenseKey = key;

        parameters.cameraDirection = cameraDirection;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters); // Create the Vuforia instance.

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // could possibly help in debugging

    }

    public VuMarkForia(HardwareMap hardwareMap, String key, VuforiaLocalizer.CameraDirection cameraDirection) {
        new VuMarkForia(hardwareMap, key, cameraDirection, false);
    }

    public VuMarkForia(HardwareMap hardwareMap, String key) {
        new VuMarkForia(hardwareMap, key, VuforiaLocalizer.CameraDirection.BACK);
    }

    public void init() {
        relicTrackables.activate();
    }

    public RelicRecoveryVuMark getCurrentVuMark() {
        return RelicRecoveryVuMark.from(relicTemplate);
    }

    public OpenGLMatrix getRelativeLocation() {
        lastLocation = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();
        return lastLocation;
    }

}

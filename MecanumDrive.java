package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by thebiteffect on 11/8/17.
 */

public class MecanumDrive {

    public DcMotor motorLeftA,
            motorRightA,
            motorLeftB,
            motorRightB;
    public String[] motorNames;
    int targetRightA,
            targetRightB,
            targetLeftA,
            targetLeftB;
    double powerRightA,
            powerRightB,
            powerLeftA,
            powerLeftB,

    velocityDrive,
            strafeDrive,
            rotationDrive;

    public MecanumDrive(boolean fieldOrientate, String[] mapValues) {
        motorNames = mapValues;
    }

    public MecanumDrive() {
        new MecanumDrive(false, new String[]{"motorLeftA", "motorRightA", "motorLeftB", "motorRightB"});
    }

    public void InitMotors(HardwareMap hardwareMap) {
        motorLeftA = hardwareMap.dcMotor.get("motorLeftA");
        motorRightA = hardwareMap.dcMotor.get("motorRightA");
        motorLeftB = hardwareMap.dcMotor.get("motorLeftB");
        motorRightB = hardwareMap.dcMotor.get("motorRightB");

        motorLeftA.setDirection(DcMotor.Direction.FORWARD);
        motorRightA.setDirection(DcMotor.Direction.REVERSE);
        motorLeftB.setDirection(DcMotor.Direction.FORWARD);
        motorRightB.setDirection(DcMotor.Direction.REVERSE);

        setMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        powerLeftA = 0;
        powerRightA = 0;
        powerLeftB = 0;
        powerRightB = 0;
    }

    public void setMotorMode(DcMotor.RunMode mode) {
        motorLeftA.setMode(mode);
        motorRightA.setMode(mode);
        motorLeftB.setMode(mode);
        motorRightB.setMode(mode);
    }

    public void update(double velocity, double strafe, double rotation) {

        powerRightA = - velocity + rotation + strafe;
        powerRightB = velocity + rotation - strafe;
        powerLeftA = - velocity - rotation - strafe;
        powerLeftB = velocity - rotation + strafe;

        motorRightA.setPower(powerRightA);
        motorRightB.setPower(powerRightB);
        motorLeftA.setPower(powerLeftA);
        motorLeftB.setPower(powerLeftB);

    }

    public void updateTarget(int velocity, int strafe, int rotation) {

        targetRightA = - velocity + rotation + strafe;
        targetRightB = velocity + rotation - strafe;
        targetLeftA = - velocity - rotation - strafe;
        targetLeftB = velocity - rotation + strafe;

        motorRightA.setTargetPosition(targetRightA);
        motorRightB.setTargetPosition(targetRightB);
        motorLeftA.setTargetPosition(targetLeftA);
        motorLeftB.setTargetPosition(targetLeftB);

    }
    public void stop() {
        motorRightA.setPower(0);
        motorRightB.setPower(0);
        motorLeftA.setPower(0);
        motorLeftB.setPower(0);
    }


}

package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive {

    public DcMotor motorLeftA,
            motorRightA,
            motorLeftB,
            motorRightB;
    public String[] motorNames;
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

        motorLeftA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        powerLeftA = 0;
        powerRightA = 0;
        powerLeftB = 0;
        powerRightB = 0;
    }

    public void update(double velocity, double strafe, double rotation) {

        strafe = -strafe;
        rotation = -rotation;

        powerRightA = velocity + rotation + strafe;
        powerRightB = velocity + rotation - strafe;
        powerLeftA = velocity - rotation - strafe;
        powerLeftB = velocity - rotation + strafe;

        motorRightA.setPower(powerRightA);
        motorRightB.setPower(powerRightB);
        motorLeftA.setPower(powerLeftA);
        motorLeftB.setPower(powerLeftB);

    }

    public void stop() {
        motorRightA.setPower(0);
        motorRightB.setPower(0);
        motorLeftA.setPower(0);
        motorLeftB.setPower(0);
    }


}

package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import static android.os.SystemClock.sleep;

/**
 * Created by zacha on 1/25/2018.
 */

@TeleOp(name = "Encoder Tester", group = "DEBUG")
public class TestEncoder extends OpMode {
    DcMotor motorWithEncoder;
    boolean lastYInput = false, thisYInput = false;
    boolean lastAInput = false, thisAInput = false;

    @Override
    public void init() {
        motorWithEncoder = hardwareMap.dcMotor.get("Motor With Encoder");
        motorWithEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addLine("Use gamepad1 for everything.");
        telemetry.addLine("Press Start to Continue...");
        telemetry.update();
    }

    @Override
    public void start() {
        telemetry.clearAll();
    }

    @Override
    public void loop() {
        // Telemetry Tutorial
        telemetry.addLine("Press A to reset encoders.");
        telemetry.addLine("Press Y to reverse motor input.");
        telemetry.addLine("Use right joystick Y-axes or right trigger and bumper.");
        telemetry.addData("encoderCount", motorWithEncoder.getCurrentPosition());
        telemetry.update();

        // Actual TeleOp
        thisAInput = gamepad1.a;
        thisYInput = gamepad1.a;

        if (gamepad1.right_bumper) motorWithEncoder.setPower(gamepad1.right_trigger / 0.25);
        else if (!gamepad1.right_bumper) motorWithEncoder.setPower(gamepad1.left_stick_y / 0.25);
        else throw new IllegalStateException("ERROR: It seems all logic as we know it is dead...");

        if (thisYInput && !lastYInput)
            if (motorWithEncoder.getDirection() == DcMotor.Direction.REVERSE)
                motorWithEncoder.setDirection(DcMotor.Direction.FORWARD);
            else if (motorWithEncoder.getDirection() == DcMotor.Direction.FORWARD)
                motorWithEncoder.setDirection(DcMotor.Direction.REVERSE);
            else throw new IllegalStateException("No DcMotor Direction!");

        if (thisAInput && !lastAInput) {
            telemetry.clearAll();
            telemetry.addLine("Resetting encoder.");
            telemetry.update();
            motorWithEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            sleep(1000);
            motorWithEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.clearAll();
        }

        lastAInput = thisAInput;
        lastYInput = thisYInput;
    }

    @Override
    public void stop() {
        super.stop();
    }
}

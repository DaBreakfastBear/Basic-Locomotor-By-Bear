package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="FirstMotorCode")
public class FirstMotorCode extends LinearOpMode {

    private DcMotor Bob;

    @Override
    public void runOpMode() {
        Bob = hardwareMap.get(DcMotor.class, "sally");

        telemetry.addData("Status:", "wheee");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            double drivePower = -gamepad1.left_stick_y;

            Bob.setPower(drivePower);

            telemetry.addData("Joystick Position", drivePower);
            telemetry.addData("Motor POwer sent", Bob.getPower());
            telemetry.update();
        }
    }
}
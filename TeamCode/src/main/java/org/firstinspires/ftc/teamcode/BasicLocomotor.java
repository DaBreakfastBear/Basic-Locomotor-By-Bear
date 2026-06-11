package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "BasicLocomotor", group = "charms")
public class BasicLocomotor extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException{

        //naming stuff
        //front is before in timeline
        //right is light and left is dark
        // 0-4 is timeline
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "riddle");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "voldemort");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "harry");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "masterofDeath");

        //reverse the left side for some wierd reason
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {

            double y = -gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0);
            double frontLeftPotter = (y+x+rx) / denominator;
            double backLeftPotter = (y-x+rx) / denominator;
            double frontRightPotter = (y-x-rx) / denominator;
            double backRightPotter = (y+x-rx) /denominator;

            frontLeft.setPower(frontLeftPotter);
            backLeft.setPower(backLeftPotter);
            frontRight.setPower(frontRightPotter);
            backRight.setPower(backRightPotter);
        }


    }

}


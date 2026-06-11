package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "SquareGrowth", group = "charms")
public class SquareGrowth extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException{

        //naming stuff
        //front is before in timeline
        //right is light and left is dark
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "riddle");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "voldemort");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "harry");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "masterofDeath");

        //reverse the left side for some wierd reason
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        IMU repero = hardwareMap.get(IMU.class, "granger");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.LEFT, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

        repero.initialize(parameters);

        telemetry.addData("esta", "bien");
        telemetry.update();


        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.options) {
                repero.resetYaw();
            }

            double rawy = -gamepad1.left_stick_y;
            double rawx = gamepad1.left_stick_x;
            double rawRx = gamepad1.right_stick_x;

            double y = (rawy * Math.abs(rawy));
            double x = (rawx * Math.abs(rawx));
            double rx = (rawRx * Math.abs(rawRx));

            double robotHeading = repero.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            double rotX = x * Math.cos(-robotHeading) - y * Math.sin(-robotHeading);
            double rotY = y * Math.sin(-robotHeading) + x * Math.cos(-robotHeading);

            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1.0);
            double frontLeftPotter = (rotY+rotX+rx) / denominator;
            double backLeftPotter = (rotY-rotX+rx) / denominator;
            double frontRightPotter = (rotY-rotX-rx) / denominator;
            double backRightPotter = (rotY+rotX-rx) /denominator;

            double speedMultiplier = 1.0;

            if (gamepad1.right_trigger > 0.5){
                speedMultiplier = 0.5;
            }

            frontLeft.setPower(frontLeftPotter * speedMultiplier);
            backLeft.setPower(backLeftPotter * speedMultiplier);
            frontRight.setPower(frontRightPotter * speedMultiplier);
            backRight.setPower(backRightPotter *speedMultiplier);

            telemetry.addData("Robot ANgle(degrees)", Math.toDegrees(robotHeading));
            telemetry.update();

        }


    }

}
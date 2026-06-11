package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@TeleOp(name = "LocomotorIMU", group = "charms")
public class LocomotorIMU extends LinearOpMode{


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


        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));


        repero.initialize(parameters);


        telemetry.addData("esta", "bien");
        telemetry.update();




        waitForStart();


        while (opModeIsActive()) {

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            if (gamepad1.start) {
                repero.resetYaw();
            }

            double robotHeading = repero.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);


            double rotX = x * Math.cos(-robotHeading) - y * Math.sin(-robotHeading);
            double rotY = y * Math.cos(-robotHeading) + x * Math.sin(-robotHeading);



            rotX = rotX * 1.1;


            double speedMultiplier = 1.0;


            if (gamepad1.right_trigger > 0.5){
                speedMultiplier = 0.5;
            }

            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1.0);
            double frontLeftPotter = (rotY+rotX+rx) / denominator;
            double backLeftPotter = (rotY-rotX+rx) / denominator;
            double frontRightPotter = (rotY-rotX-rx) / denominator;
            double backRightPotter = (rotY+rotX-rx) /denominator;


            frontLeft.setPower(frontLeftPotter * speedMultiplier);
            backLeft.setPower(backLeftPotter *speedMultiplier);
            frontRight.setPower(frontRightPotter * speedMultiplier);
            backRight.setPower(backRightPotter * speedMultiplier);


            telemetry.addData("Robot ANgle(degrees)", Math.toDegrees(robotHeading));
            telemetry.addData("Status of rotX", rotX);
            telemetry.addData("Status of rotY,", rotY);
            telemetry.update();


        }




    }


}

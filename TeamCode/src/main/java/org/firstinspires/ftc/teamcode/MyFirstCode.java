package org.firstinspires.ftc.teamcode;
// this is importing stuff
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="MyFirstCode")
public class MyFirstCode extends LinearOpMode {

    @Override
    public void runOpMode() {
        // declare servo
        Servo myServo;
        myServo = hardwareMap.get(Servo.class, "servo_zero");

        telemetry.addData("Status", "Initialized! Press Play.");
        telemetry.update();

        boolean isOpen = false;
        boolean lastGamepad1A = false;

        //robot pauses until i hit start on controller
        waitForStart();

        //runs until i hit stop
        while (opModeIsActive()) {
            //if I press a then servo moves to position0
            if (gamepad1.a && !lastGamepad1A) {

                if (isOpen) {
                    myServo.setPosition(0.44);
                    isOpen = false;
                }else {
                    myServo.setPosition(0.56);
                    isOpen = true;
                }
            }
            lastGamepad1A = gamepad1.a;

            if (isOpen) {
                telemetry.addData("to the left!", "yay!");
            } else {
                telemetry.addData("to the right!", "wheeee");
            }

            //show driver when is being pressed
            telemetry.addData("Servo Status", "Running");
            telemetry.update();
        }
    }
}
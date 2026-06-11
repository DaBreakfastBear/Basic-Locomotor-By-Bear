package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="something")
public class MyFirstCode extends LinearOpMode {
    public enum ArmState {
        LEFT,
        MIDDLE,
        RIGHT
    }

    @Override
    public void runOpMode() {
        Servo GOATEDservo;
            GOATEDservo = hardwareMap.get(Servo.class, "servo_zero");
        ArmState currentPosition = ArmState.LEFT;

        telemetry.addData("Status", "Lets go baby!");
        telemetry.update();

        boolean isOpen = false;
        boolean lastGamepad1A = false;

            waitForStart();
            while (opModeIsActive()) {
                if (gamepad1.a && !lastGamepad1A){
                    isOpen = !isOpen;
                    if(isOpen) {
                        currentPosition = ArmState.RIGHT;
                    }
                    else {
                        currentPosition = ArmState.LEFT;
                    }
                }
                lastGamepad1A = gamepad1.a;

                if (gamepad1.b)
                    currentPosition = ArmState.MIDDLE;

                if (currentPosition == ArmState.RIGHT){
                    GOATEDservo.setPosition(0.44);
                    telemetry.addData("status", "quack quack");
                    telemetry.update();
                }
                else if (currentPosition == ArmState.MIDDLE){
                    GOATEDservo.setPosition(0.5);
                    telemetry.addData("status" ,"im a child");
                    telemetry.update();
                }
                else if (currentPosition == ArmState.LEFT){
                    GOATEDservo.setPosition(0.56);
                    telemetry.addData("status","schoolbus is cool");
                    telemetry.update();

                }



            }
    }
        }
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


public class MovementMethods extends LinearOpMode{


    // double ServoSpd = 0.5;
    // double ServoAmt = 0.0;




    @Override
    public void runOpMode(){
    }




    HardwareFluffy robot       = new HardwareFluffy();
    private ElapsedTime runtime = new ElapsedTime();




    public void Drive(double Left, double Right) {

        robot.leftDrive.setPower(Left);
        robot.rightDrive.setPower(Right);

    }





    public void ServoMove(double ServoSpd, double ServoAmt){

        if (gamepad1.right_bumper)
             ServoAmt += ServoSpd;
        else if (gamepad1.left_bumper)
            ServoAmt -= ServoSpd;

        // Move both servos to new position.  Assume servos are mirror image of each other.
        ServoAmt = Range.clip(ServoAmt, -0.5, 0.5);
        robot.leftClaw.setPosition(robot.MID_SERVO + ServoAmt);
        robot.rightClaw.setPosition(robot.MID_SERVO - ServoAmt);
    }







    public void DriveTime(double Left, double Right, int Time, int Step) {
        robot.leftDrive.setPower(Left);
        robot.rightDrive.setPower(Right);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < Time)) {
            telemetry.addData("Path", "Leg", Step, ": %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
    }



    public void ArmMove(double Up, double Down){
        if (gamepad1.y)
            robot.leftArm.setPower(Up);
        else if (gamepad1.a)
            robot.leftArm.setPower(Down);
        else
            robot.leftArm.setPower(0.0);
    }







}


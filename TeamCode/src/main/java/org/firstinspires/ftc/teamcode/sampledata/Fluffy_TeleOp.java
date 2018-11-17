package org.firstinspires.ftc.teamcode.sampledata;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Fluffy: TeleOp")
public abstract class Fluffy_TeleOp extends SuperFluffy{

   public void runOpMode() {
       initialization(false);
       waitForStart();
       while(opModeIsActive()) {

           double left = gamepad1.left_stick_x;
           double right = gamepad1.right_stick_x;

           tankDrive(left, right);
           armLift(gamepad1.y, gamepad1.a);
       }




   }



}

package org.firstinspires.ftc.teamcode.sampledata;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Fluffy: TeleOp", group="Pushbot")
public abstract class Fluffy_TeleOp extends SuperFluffy{

    @Override
    public void initialization(boolean autonomous) {
        super.initialization(false);

        double left = gamepad1.left_stick_x;
        double right = gamepad1.right_stick_x;

        tankDrive(left, right);
        armLift(gamepad1.y, gamepad1.a);



    }
}

package org.firstinspires.ftc.teamcode.sampledata;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Fluffy: TeleOp")
public class Fluffy_TeleOp extends SuperFluffy{

public void runOpMode() {

    initialization(false);
    waitForStart();
    while (opModeIsActive()) {
        float gpleft = gamepad1.left_stick_y;
        float gpright = gamepad1.right_stick_y;
        boolean gpa = gamepad1.a;
        boolean gpb = gamepad1.b;//YOU CAN DO IT I BELEVE IN YOU
        boolean gpx = gamepad1.x;
        boolean gpy = gamepad1.y;
        boolean gplb = gamepad1.left_bumper;
        boolean gprb = gamepad1.right_bumper;
        boolean gpa2 = gamepad2.a;
        boolean gpb2 = gamepad2.b;
        boolean gpx2 = gamepad2.x;
        boolean gpy2 = gamepad2.y;
        boolean gplb2 = gamepad2.left_bumper;
        boolean gprb2 = gamepad2.right_bumper;
        boolean gpupd = gamepad1.dpad_up;
        boolean gpdownd = gamepad1.dpad_down;
        boolean gpleftd = gamepad1.dpad_left; 
        boolean gprightd = gamepad1.dpad_right;
        float gprt = gamepad1.right_trigger;
        float gplt = gamepad1.left_trigger;
        float gpleft2 = gamepad2.left_stick_y;


        tankDrive(gpleft, gpright);
        armLift(gpa, gpy);
        crServo(gpx, gpb);
        colArm(gpleftd,gprightd);
        //colArmTrigger(gprt,gplt);
        armActuator(gpupd,gpdownd);

    }
}
}

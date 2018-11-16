package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name="Fluffy: Crater", group="Pushbot")
public abstract class Fluffy_Crater extends SuperFluffy {
    @Override
    public void runOpMode() {
init();
    initialization(true);
    waitForStart();



        autoArm(4);
        driveForwardEncoders(12,autoPower);
        markerDrop();
        driveBackwardEncoders(12, autoPower);






    }
}

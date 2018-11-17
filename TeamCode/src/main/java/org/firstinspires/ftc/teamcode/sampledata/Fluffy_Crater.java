package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Fluffy: Crater")
public abstract class Fluffy_Crater extends SuperFluffy {

    public void runOpMode() {

    initialization(true);


    waitForStart();



        autoArm(4);
        driveForwardEncoders(12,autoPower);
        markerDrop();
        driveBackwardEncoders(12, autoPower);






    }
}

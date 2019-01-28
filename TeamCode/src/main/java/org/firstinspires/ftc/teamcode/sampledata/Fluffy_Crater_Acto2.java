package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name="Fluffy: Crater Encoderized")
public class Fluffy_Crater_Acto2 extends SuperFluffy {

    public void runOpMode() {

    initialization(true);
    //goldDetect();

    waitForStart();

    //tfod.deactivate(); //Stop TFLite

        autoArm(actoTime);
        driveForwardEncoders(500, .8);
        rotateLeft(35, .6);
        driveForwardEncoders(2000, .8);
        rotateLeft(35, .6);// You the manlyest man
        driveForwardEncoders(1000, .8);
        markerDrop();
        sleep(500);
        markerReset();
        driveBackwardEncoders(4000, .8);
    }
}

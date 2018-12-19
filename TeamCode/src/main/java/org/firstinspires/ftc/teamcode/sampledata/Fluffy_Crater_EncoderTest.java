package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Fluffy: Crater Encoder")
public class Fluffy_Crater_EncoderTest extends SuperFluffy {

    public void runOpMode() {

    initialization(true);
    //goldDetect();

    waitForStart();

    //tfod.deactivate(); //Stop TFLite

     //   autoArm(actoTime);
        driveForwardEncoders(1000,.6);
       // rotateLeft(45,.5);
    }
}

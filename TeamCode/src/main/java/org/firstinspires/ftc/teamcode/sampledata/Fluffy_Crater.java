package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name="Fluffy: Crater")
public class Fluffy_Crater extends SuperFluffy {

    public void runOpMode() {

    initialization(true);
    goldDetect();

    waitForStart();

    tfod.deactivate(); //Stop TFLite

        autoArm(4);

      //  driveTime(1500, .8, .8); //Drive away from lander briefly// Go have brakefast you need your vitamins
        rotateLeft(-90, .6); //Rotate towards depot
        driveTime(4500, .8, .8); //Drive towards depot
        markerDrop(); //Drop marker
        rotateRight(160, .6); //Rotate towards crater
        driveTime(6000, .8, .8); //Drive towards crater





    }
}

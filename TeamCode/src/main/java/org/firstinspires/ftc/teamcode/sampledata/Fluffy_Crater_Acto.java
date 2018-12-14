package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Fluffy: Crater Actobotics")
public class Fluffy_Crater_Acto extends SuperFluffy {

    public void runOpMode() {

    initialization(true);
    goldDetect();

    waitForStart();

    tfod.deactivate(); //Stop TFLite

        autoArm(11);

      //  driveTime(1500, .8, .8); //Drive away from lander briefly
        rotateLeft(-90, .6); //Rotate towards depot
        driveTime(4500, .8, .8); //Drive towards depot
        markerDrop(); //Drop marker
        rotateRight(160, .6); //Rotate towards crater
        driveTime(6000, .8, .8); //Drive towards crater





    }
}

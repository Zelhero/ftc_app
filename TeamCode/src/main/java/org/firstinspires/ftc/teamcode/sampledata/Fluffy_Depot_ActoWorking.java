package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="DEPOT Fluffy: Depot ")
public class Fluffy_Depot_ActoWorking extends SuperFluffy {

    public void runOpMode() {

    initialization(true); // have some water gotta stay hydrated
    //goldDetect();

    waitForStart();

    //tfod.deactivate(); //Stop TFLite

        autoArm(actoTime);
        rotateLeft(15,.6);
        driveTime(300, -.8, -.8); //Drive from lander
        rotateRight(0,.6);
        driveTime(1100, -.5, -.5); //

        markerDrop(); //Drop marker
        markerReset();

        //rotateLeft(45,.6);
        driveTime(2000, .6, .8); //Drive from lander

    }
}
// Eat ur beans
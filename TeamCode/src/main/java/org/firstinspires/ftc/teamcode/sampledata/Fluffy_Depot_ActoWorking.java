package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Fluffy: Depot RUN")
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
        driveTime(1000, -.5, -.5); //

        markerDrop(); //Drop marker
        markerReset();

        driveTime(1000, .8, .8); //Drive from lander

    }
}
// Eat ur beans
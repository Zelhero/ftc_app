package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="RUN THISFluffy: Crater Actobotics")
public class Fluffy_Crater_Acto extends SuperFluffy {

    public void runOpMode() {

    initialization(true); // have some water gotta stay hydrated
    //goldDetect();

    waitForStart();

    //tfod.deactivate(); //Stop TFLite

        autoArm(actoTime);
        rotateLeft(15,.6);
        driveTime(750, -.8, -.8); //Drive from lander
        sleep(1000);
        rotateLeft(75,.6);
        sleep(1000);
        driveTime(2200, -.5, -.5); //Drive from lander

        markerDrop(); //Drop marker
        markerReset();

        driveTime(2200, .8, .8); //Drive from lander

    }
}
// Eat ur beans
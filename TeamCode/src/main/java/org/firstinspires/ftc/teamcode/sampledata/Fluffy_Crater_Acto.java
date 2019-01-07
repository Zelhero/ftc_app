package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="RUN THISFluffy: Crater Actobotics")
public class Fluffy_Crater_Acto extends SuperFluffy {

    public void runOpMode() {

    initialization(true);
    //goldDetect();

    waitForStart();

    //tfod.deactivate(); //Stop TFLite

        autoArm(actoTime);
        driveTime(200, -.8, .8); //twist
        driveTime(2000, .8, .8); //Drive from lander
        markerDrop(); //Drop marker
        markerReset();
    }
}

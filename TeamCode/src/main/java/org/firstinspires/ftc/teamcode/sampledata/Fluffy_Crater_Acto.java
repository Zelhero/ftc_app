package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Fluffy: Crater Actobotics")
public class Fluffy_Crater_Acto extends SuperFluffy {

    public void runOpMode() {

    initialization(true);
    //goldDetect();

    waitForStart();

    //tfod.deactivate(); //Stop TFLite

        autoArm(actoTime);
        driveTime(500, .8, -.8); //Drive away from lander briefly
        driveTime(500, -.8, -.8); //Drive away from lander briefly
        driveTime(500, -.8, .8); //Drive away from lander briefly
//driveTime(4500, .8, .8); //Drive towards depot
        driveTime(2000, -.8, -.8); //Drive towards crater
        markerDrop(); //Drop marker
        driveTime(2000, .8, .8); //Drive towards crater





    }
}

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
      //  driveTime(400, .8, -.8); //Twist from lander
        driveTime(300, -.8, -.8); //Drive from lander
       // driveTime(450, -.8, .8); //Twist back to face corner
        driveTime(1300, -.8, -.8); //Drive towards depot
        markerDrop(); //Drop marker
       // driveTime(1300, .8, .8); //Drive towards crater
        markerReset();
    }
}

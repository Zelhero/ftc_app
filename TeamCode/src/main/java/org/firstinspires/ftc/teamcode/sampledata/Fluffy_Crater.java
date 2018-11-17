package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Fluffy: Crater")
public class Fluffy_Crater extends SuperFluffy {

    public void runOpMode() {

    initialization(true);


    waitForStart();



        autoArm(4);
        driveTime(1500, -.8, -.8);
        markerDrop();
        driveTime(1500, .8, .8);






    }
}

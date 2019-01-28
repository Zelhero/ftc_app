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

   markerDrop();




    }
}

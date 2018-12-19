package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Recharged Orange on 10/18/2018.
 */

@Disabled
@Autonomous (name = "RedLineTest")

public class RedLineTest extends superClass {

    public void runOpMode() {

        initialization(true);

        waitForStart(); //driver hits play


        driveForwardEncoders(500, .89);
        driveright(1900, 1);
        rotateLeft(45, .1);
        driveForwardEncoders(2500, 1);
        sleep(1000);
        //drop of team marker
        driveBackwardEncoders(6000, 1);
    }
}
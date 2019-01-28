package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name="Fluffy: Depot")
public class Fluffy_Depot extends SuperFluffy {

    public void runOpMode() {

        initialization(true);

        while(!opModeIsActive()) {
            goldDetect();
        }

        waitForStart();

        tfod.deactivate(); //Stop TFLite

        autoArm(actoTime); //Lower from lander
        rotateRight(15, .5); //Adjust angle for landing


        if (goldMineralPos == 1) { //Left
            rotateLeft(-15, .6); //Rotate towards gold
            driveForwardEncoders(500, .8); //Run into gold

            rotateRight(30, .6); //Rotate to face depot
            driveForwardEncoders(3000, .8);//Drive to depot
            markerDrop();

            rotateRight(45, .6); //Rotate to face crater
            driveForwardEncoders(3000, .8);// Drive towards crater
        }
        else if (goldMineralPos == 2) { //Center
            driveTime(750, -.8, -.8);
            sleep(500);
            driveTime(750, .8, .8);
        }
        else if (goldMineralPos == 3) { //Right
            rotateLeft(-30, .6);
            driveTime(1000, -.8, -.8);
        }


        driveTime(1500, -.8, -.8); //Drive forward to depot
        markerDrop(); //Drop marker in depot
        rotateRight(135, .6); //Turn to crater
        driveTime(5000, -.8, -.8); //Drive into crater


    }
}
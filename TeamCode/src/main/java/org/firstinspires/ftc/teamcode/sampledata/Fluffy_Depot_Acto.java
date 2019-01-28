package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name="Fluffy: Depot Acto")
public class Fluffy_Depot_Acto extends SuperFluffy {

    public void runOpMode() {

        initialization(true);// You look nice today
        goldDetect();

        waitForStart();

        tfod.deactivate(); //Stop TFLite

        autoArm(actoTime); //Lower from lander

        if (goldMineralPos == 1) { //Left
            rotateLeft(-30, .6); //Rotate towards gold
            driveTime(1000, -.8, -.8); //Drive towards Gold
            rotateRight(30, .6); //Rotate to face depot
            driveTime(500, -.8, -.8); //Drive towards depot
            markerDrop();
            rotateRight(45, .6); //Rotate to face depot
            driveTime(3000, -.8, -.8); //Drive towards depot
        }
        else if (goldMineralPos == 2) { //Center
            driveTime(750, -.8, -.8); // You look tired Go take a nap
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
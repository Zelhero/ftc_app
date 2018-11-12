package org.firstinspires.ftc.teamcode.sampledata;

public abstract class Fluffy_Crater extends SuperFluffy {





    @Override
    public void initialization(boolean autonomous) {
        super.initialization(true);

        autoArm(4);
        driveForwardEncoders(12,autoPower);
        markerDrop();
        driveBackwardEncoders(12, autoPower);






    }
}

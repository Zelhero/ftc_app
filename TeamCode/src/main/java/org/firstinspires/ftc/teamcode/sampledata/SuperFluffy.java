package org.firstinspires.ftc.teamcode.sampledata;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

/**
 * Created by Recharged Orange on 10/9/2018. Modified by Polar Technics on 11/9/18
 */

public abstract class SuperFluffy extends LinearOpMode {

    public DcMotor left;
    public DcMotor right;
    public DcMotor arm;
    //  public DcMotor rightBack;
    public DcMotor sweeper;

    public Servo servo;
    public CRServo REVServo;

    public BNO055IMU imu;
    public RevTouchSensor touchSensor;
    public float imuStartingPosition;
    public DigitalChannel beam;

    public  ElapsedTime     runtime = new ElapsedTime();




    double lefttarget = 0;
    double righttarget = 0;

    public double autoPower = .8;
    public long actoTime = 10;
    public long mk2Time = 4;

    public int goldMineralPos; //Left = 1, Center = 2 and Right = 3



    //TFLite / CV stuff
        private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
        private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
        private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
        private static final String VUFORIA_KEY = " ATgwoQH/////AAAAGQZIyN7blEnstZsu3+dMPasofG+vAgBRDotB5Uj24jIrGTwIbWR87Yyl7Bt+jGz9GmNtQvGxooXkEaxOgeWlNP6O2mbhOJxuIu5IuJGoFARnOeR7ImW+GwEVeWrsLrns0f0oJ+++ATKCQM34yPxSyUtXhRwip2FgdpFiBHrpHMHTjQjtRFw3YxJx2Ba8whMUO4/adth2pQWYOgnBDhugtb2c/FCWFeYH3/RB4e5FClYIUs3VN/qC0q9DTJ5gPfKVkj9iuAJFlRXOkFuAOR1yo4uzwUA6EyhGdQY+Qs/7Q1+FTLNcq0P9YYEasujFcwYlZP/3HrdS7z7o5OInyrUUMQabYyiWhjbIFnYYNEqigNuP";
        public VuforiaLocalizer vuforia;
        public TFObjectDetector tfod;


    Orientation lastAngles = new Orientation();
    double globalAngle, power = .30, correction;

    public void initialization(boolean autonomous) {

        telemetry.addLine("drive Init");
        telemetry.update();
        initDrive();
/*
        telemetry.addLine("sweeper init");
        telemetry.update();
        initSweeper();
*/
        telemetry.addLine("servo");
        telemetry.update();
        initServo();

        telemetry.addLine("beam");
        telemetry.update();
        //initBeam();

        telemetry.addLine("init touch");
        telemetry.update();
        //initTouch();

        if (autonomous) {
            telemetry.addLine("imu init");
            telemetry.update();
            initiate_imu();

            telemetry.addLine("vuforia init");
            telemetry.update();
            initVuforia();

            telemetry.addLine("tfod init");
            telemetry.update();
            initTfod();

            left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        } else {
            left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }
        telemetry.addLine("Finished init - ready to go captain!");
        telemetry.update();
    }

    public void initDrive() {

        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");
        arm = hardwareMap.dcMotor.get("arm");


        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        left.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setDirection(DcMotorSimple.Direction.REVERSE);


    }

    public void initSweeper() {
        sweeper = hardwareMap.dcMotor.get("sweeper");
        sweeper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void initServo() {
        servo = hardwareMap.servo.get("servo");
       //CONTINUOUS SERVO ENABLE
        REVServo = hardwareMap.crservo.get("REVServo");
    }

    public void initBeam() {
        beam = hardwareMap.digitalChannel.get("beam");
    }

    public void initTouch() {
        touchSensor = hardwareMap.get(RevTouchSensor.class, "touchSensor");

    }

    public void initiate_imu() {

        telemetry.addLine("Initiating IMU");
        telemetry.update();

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        while (opModeIsActive()) {

            imuStartingPosition = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES).firstAngle;

        }
    }

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }





    public void driveForwardEncoders(double distance, double power) {
        left.setPower(power);
        right.setPower(power);


        lefttarget = (left.getCurrentPosition() + distance);
        righttarget = (right.getCurrentPosition() + distance);

        while (left.getCurrentPosition() < lefttarget
                && right.getCurrentPosition() < righttarget
                && opModeIsActive()) {

            telemetry.addData("left", left.getCurrentPosition());
            telemetry.addData("rightfront", right.getCurrentPosition());

            telemetry.update();
        }
        left.setPower(0);
        right.setPower(0);

    }


    public void driveBackwardEncoders(double distance, double power) {

        lefttarget = (left.getCurrentPosition() - distance);
        righttarget = (right.getCurrentPosition() - distance);


        while (left.getCurrentPosition() > righttarget
                && right.getCurrentPosition() > lefttarget
                && opModeIsActive()) {
            left.setPower(-power);
            right.setPower(-power);


            telemetry.addData("left", left.getCurrentPosition());
            telemetry.addData("right", right.getCurrentPosition());


            telemetry.update();

        }
        left.setPower(0);
        right.setPower(0);
    }

    public void driveleft(double distance, double power) {
        right.setPower(power);
        left.setPower(power);

        lefttarget = (left.getCurrentPosition() + distance);
        righttarget = (right.getCurrentPosition() - distance);

        while (right.getCurrentPosition() > righttarget
                && left.getCurrentPosition() < lefttarget
                && opModeIsActive()) {

            telemetry.addData("left", left.getCurrentPosition());
            telemetry.addData("right", right.getCurrentPosition());


            telemetry.update();

        }
        left.setPower(0);
        right.setPower(0);
    }

    public void driveright(double distance, double power) {
        right.setPower(power);
        left.setPower(-power);


        lefttarget = (left.getCurrentPosition() + distance);
        righttarget = (right.getCurrentPosition() - distance);


        while (right.getCurrentPosition() < lefttarget
                && left.getCurrentPosition() > righttarget
                && opModeIsActive()) {

            telemetry.addData("left", left.getCurrentPosition());
            telemetry.addData("right", right.getCurrentPosition());

            telemetry.update();
        }
        left.setPower(0);
        right.setPower(0);
    }

    public void rotateLeft(double targetAngle, double power) {
        left.setPower(power);
        right.setPower(-power);

        while (opModeIsActive() && getAngle() < targetAngle) {
            telemetry.addData("curentAngle", getAngle());
            telemetry.addData("targetAngle", targetAngle);
            telemetry.update();
        }
        left.setPower(0);
        right.setPower(0);

    }

    public void rotateRight(double targetAngle, double power) {
        left.setPower(power);
        right.setPower(-power);

        while (opModeIsActive() && getAngle() > targetAngle) {
            telemetry.addData("curentAngle", getAngle());
            telemetry.addData("targetAngle", targetAngle);
            telemetry.update();
        }
        left.setPower(0);
        right.setPower(0);
    }


    public void resetAngle() {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    public double getAngle() {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

    //Begin Polar Technics methods

    public void armLift(boolean up, boolean down) {

        if (up)
            arm.setPower(1);
        else if (down)
            arm.setPower(-1);
        else
            arm.setPower(0.0);
    }

    public void tankDrive(double leftX, double rightX) {

        left.setPower(leftX);
        right.setPower(rightX);
    }

    public void autoArm(long time){
        time = time * 1000;
        arm.setPower(-1);
        sleep(time);
        arm.setPower(0);
        runtime.reset();
    }


    public void markerDrop(){

            servo.setPosition(0);
            sleep(1500);
            servo.setPosition(1);

    }

    public void driveTime(long timeinmilli, double l, double r){


        tankDrive(l, r);
        sleep(timeinmilli);
        tankDrive(0, 0);

    }
    public void crServo(boolean up, boolean down) {

        if (up)
            REVServo.setPower(1);
        else if (down)
            REVServo.setPower(-1);
        else
            REVServo.setPower(0.0);
    }

    public void goldDetect(){
          tfod.activate();


            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        if (updatedRecognitions.size() == 3) {
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            int silverMineral2X = -1;
                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();
                                } else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();
                                } else {
                                    silverMineral2X = (int) recognition.getLeft();
                                }
                            }
                            if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                                if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Left");
                                    goldMineralPos = 1;
                                } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Right");
                                    goldMineralPos = 3;
                                } else {
                                    telemetry.addData("Gold Mineral Position", "Center");
                                    goldMineralPos = 2;
                                }
                            }
                        }
                        telemetry.update();
                    }
                }
            }
        }



//FINAL BRACKET
}

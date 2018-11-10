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

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by Recharged Orange on 10/9/2018. Modified by Polar Technics on 11/9/18
 */

public abstract class SuperFluffy extends LinearOpMode {

    public DcMotor left;
    public DcMotor right;
    public DcMotor rightFront;
    public DcMotor rightBack;
    public DcMotor sweeper;

    public Servo servo;
    public CRServo REVServo;

    public BNO055IMU imu;
    public RevTouchSensor touchSensor;
    public float imuStartingPosition;
    public DigitalChannel beam;

    double lefttarget = 0;
    double righttarget = 0;


    Orientation lastAngles = new Orientation();
    double globalAngle, power = .30, correction;

    public void initialization(boolean autonomous) {

        telemetry.addLine("drive Init");
        telemetry.update();
        initDrive();

        telemetry.addLine("sweeper init");
        telemetry.update();
        initSweeper();

        telemetry.addLine("servo");
        telemetry.update();
        initServo();

        telemetry.addLine("beam");
        telemetry.update();
        initBeam();

        telemetry.addLine("init touch");
        telemetry.update();
        initTouch();

        if (autonomous) {
            telemetry.addLine("imu init");
            telemetry.update();
            initiate_imu();


            left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        } else {
            left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }
        telemetry.addLine("Finished init - ready to go captain!");
        telemetry.update();
    }

    public void initDrive() {

        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");


        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        left.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setDirection(DcMotorSimple.Direction.REVERSE);


    }

    public void initSweeper() {
        sweeper = hardwareMap.dcMotor.get("sweeper");
        sweeper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void initServo() {
        servo = hardwareMap.servo.get("servo");
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
        righttarget = (rightBack.getCurrentPosition() - distance);


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
        rightBack.setPower(0);
        rightFront.setPower(0);
    }

    public void driveleft(double distance, double power) {
        right.setPower(-power);
        left.setPower(power);
        rightFront.setPower(power);
        rightBack.setPower(-power);

        lefttarget = (left.getCurrentPosition() - distance);
        righttarget = (right.getCurrentPosition() + distance);

        while (right.getCurrentPosition() > righttarget
                && left.getCurrentPosition() < lefttarget
                && opModeIsActive()) {

            telemetry.addData("left", left.getCurrentPosition());
            telemetry.addData("right", right.getCurrentPosition());


            telemetry.update();

        }
        left.setPower(0);
        right.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
    }

    public void driveright(double distance, double power) {
        right.setPower(power);
        left.setPower(-power);


        lefttarget = (left.getCurrentPosition() + distance);
        righttarget = (rightBack.getCurrentPosition() - distance);


        while (right.getCurrentPosition() < lefttarget
                && left.getCurrentPosition() > righttarget
                && opModeIsActive()) {

            telemetry.addData("left", left.getCurrentPosition());
            telemetry.addData("right", right.getCurrentPosition());
            telemetry.addData("right back", rightBack.getCurrentPosition());
            telemetry.addData("right front", rightFront.getCurrentPosition());

            telemetry.update();
        }
        left.setPower(0);
        right.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
    }

    public void rotateLeft(double targetAngle, double power) {
        left.setPower(-power);
        right.setPower(-power);
        rightBack.setPower(power);
        rightFront.setPower(power);

        while (opModeIsActive() && getAngle() < targetAngle) {
            telemetry.addData("curentAngle", getAngle());
            telemetry.addData("targetAngle", targetAngle);
            telemetry.update();
        }
        left.setPower(0);
        right.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);

    }

    public void rotateRight(double targetAngle, double power) {
        left.setPower(power);
        right.setPower(power);
        rightBack.setPower(-power);
        rightFront.setPower(-power);

        while (opModeIsActive() && getAngle() > targetAngle) {
            telemetry.addData("curentAngle", getAngle());
            telemetry.addData("targetAngle", targetAngle);
            telemetry.update();
        }
        left.setPower(0);
        right.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
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
}

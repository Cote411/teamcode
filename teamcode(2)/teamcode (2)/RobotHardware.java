package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.Set;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;


public class RobotHardware
{
     /* Public OpMode members. */
     public DcMotorEx  Motor1;
     public DcMotorEx  Motor2;
     public DcMotorEx  Motor3;
     public DcMotorEx  Motor4;
     public Servo servo1;
     public DcMotorEx  shooter;
     public DcMotor  intake;
     public DcMotor  arm;
     public DcMotor tape;
     public Servo servo2;
     public Servo servo3;

     //Make sensor
     BNO055IMU imu;
     //public ColorSensor colorsensor;

     private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
     private static final String LABEL_FIRST_ELEMENT = "Quad";
     private static final String LABEL_SECOND_ELEMENT = "Single";
     static String target;
     private static final String VUFORIA_KEY =
            " AeyDIMr/////AAABmUPT8BZ+R0Qxt8YkcRu3awMUuwOSPrS70JbahNYk2Mk7kBWqEjxJOcBPyz9HoACQQ9sakbNDJe2xueXsn8xRNsMnJG+IxGQnNNBS1HHMSp3QfGFxg830arlRELC991FjtxF8piAaSO8Cy1bRzVeYjDAoarrtIpZp6becH5+T7bCqVZ6dU6zDq1RWgYvWHMNT75IgXObHk0ufYYHyCm28ZeTGWeop/EDpT2fFwsjXzQFjWOI0T9h2xCrk98xQaAAYBbMuw95s0Vv3NMNLwtre9KPCCPgw07hzD9qDaPvT/GD1w5Q5v+gQV07bmxS9WyjA7aNrenddk7M3bUdBgLJJhOMQR5/U5jU1IC7hp1DsRzEW ";
            
     private VuforiaLocalizer vuforia;
     
     private TFObjectDetector tfod;

     /* local OpMode members. */
     HardwareMap hardwareMap;

     /* Initialize standard Hardware interfaces */
     public void init(HardwareMap hardwareMap) {
        // Save reference to Hardware map

        // Define and Initialize Motors
        Motor1 = hardwareMap.get(DcMotorEx.class, "Port0"); // FL
        Motor2 = hardwareMap.get(DcMotorEx.class, "Port1"); // BL
        Motor3 = hardwareMap.get(DcMotorEx.class, "Port2"); // FR
        Motor4 = hardwareMap.get(DcMotorEx.class, "Port3"); // BR
        shooter = hardwareMap.get(DcMotorEx.class, "shoot");
        intake = hardwareMap.get(DcMotor.class, "intake");
        arm = hardwareMap.get(DcMotor.class, "arm");
        servo1 = hardwareMap.get(Servo.class, "push");
        servo2 = hardwareMap.get(Servo.class, "grab1");
        servo3 = hardwareMap.get(Servo.class, "grab2");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        tape = hardwareMap.get(DcMotor.class, "tape");

        //define

       
        //colorsensor = hardwareMap.get(ColorSensor.class, "Color");



        // Set all motors to zero power
         Motor1.setPower(0);
         Motor2.setPower(0);
         Motor3.setPower(0);
         Motor4.setPower(0);
        shooter.setPower(0);
        intake.setPower(0);
        arm.setPower(0);
        tape.setPower(0);

        //Set Motor Mode
         Motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         Motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         Motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
         Motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

         Motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         Motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         Motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         Motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //set 0 power behavior

         Motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         Motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         Motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         Motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        servo1.setPosition(1.0);
        servo2.setPosition(1.0);
        servo3.setPosition(0.0);
        
        //

     }
}
 

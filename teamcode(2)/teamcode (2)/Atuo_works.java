package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import java.util.Locale;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import java.util.Locale;


@Autonomous(name="Big Score - Auto")

public class Atuo_works extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";
    static String target;
    private static final String VUFORIA_KEY =
            " AeyDIMr/////AAABmUPT8BZ+R0Qxt8YkcRu3awMUuwOSPrS70JbahNYk2Mk7kBWqEjxJOcBPyz9HoACQQ9sakbNDJe2xueXsn8xRNsMnJG+IxGQnNNBS1HHMSp3QfGFxg830arlRELC991FjtxF8piAaSO8Cy1bRzVeYjDAoarrtIpZp6becH5+T7bCqVZ6dU6zDq1RWgYvWHMNT75IgXObHk0ufYYHyCm28ZeTGWeop/EDpT2fFwsjXzQFjWOI0T9h2xCrk98xQaAAYBbMuw95s0Vv3NMNLwtre9KPCCPgw07hzD9qDaPvT/GD1w5Q5v+gQV07bmxS9WyjA7aNrenddk7M3bUdBgLJJhOMQR5/U5jU1IC7hp1DsRzEW ";
    private VuforiaLocalizer vuforia;
     private TFObjectDetector tfod;
     double velocity0 = 0.5;
     int velocity1 = 2;
     
     
     
     // The IMU sensor object
    BNO055IMU imu;
    
    
    double degrees;
    
    
    

    // State used for updating telemetry
    Orientation angles; 
    Acceleration gravity;

    RobotHardware robot = new RobotHardware();
    

    //TensorFlow detect = new TensorFlow();


    @Override
    public void runOpMode() {
        
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        
        
        
        
        robot.init(hardwareMap);
        //composeTelemetry();
       
        robot.Motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.Motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.Motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.Motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //robot.Run();
        
        //waitForStart();
        //robot.Motor1.setPower(1.0);
    
        initVuforia();
        initTfod();

        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 1.78 or 16/9).

            // Uncomment the following line if you want to adjust the magnification and/or the aspect ratio of the input images.
            tfod.setZoom(2.0, 16.0/9.0);
            
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        //waitForStart();
        if(!opModeIsActive()){
            while(!opModeIsActive()){
               // for(int a = 0; a < 400000; a++){
                        if (tfod != null) {
                            // getUpdatedRecognitions() will return null if no new information is available since
                            // the last time that call was made.
                            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                            if (updatedRecognitions != null) {
                              telemetry.addData("# Object Detected", updatedRecognitions.size());
                              if (updatedRecognitions.size() == 0 ) {
                                  // empty list.  no objects recognized.
                                  telemetry.addData("TFOD", "No items detected.");
                                  telemetry.addData("Target Zone", "A");
                                  target = "None";
                              } else {
                                  // list is not empty.
                                  // step through the list of recognitions and display boundary info.
                                  int i = 0;
                                  for (Recognition recognition : updatedRecognitions) {
                                      telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                                      telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                              recognition.getLeft(), recognition.getTop());
                                      telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                              recognition.getRight(), recognition.getBottom());
        
                                      // check label to see which target zone to go after.
                                      if (recognition.getLabel().equals("Single")) {
                                          telemetry.addData("Target Zone", "B");
                                          target = recognition.getLabel();
                                      } else if (recognition.getLabel().equals("Quad")) {
                                          telemetry.addData("Target Zone", "C");
                                          target = recognition.getLabel();
                                           
                                      } else {
                                          telemetry.addData("Target Zone", "UNKNOWN");
                                      }
                                  }
                              }
        
                              telemetry.update();
                            }
                       // }
                       
                    
                }
            }
        }
            if (tfod != null) {
                tfod.shutdown();
            }
            
        
            // angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            // degrees = AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle));
        
        // telemetry.addData("heading: ", degrees);
        //     telemetry.update();
        
        waitForStart();
        // while(opModeIsActive()){
           
        // }
        
        
        MoveTest();
        //SecondTest();
        
        stop();
        
        //DistanceTest();
        
        // telemetry.addData("Target Position: ", robot.Motor1.getTargetPosition());
        // telemetry.addData("Current Position: ", robot.Motor1.getCurrentPosition());
        // telemetry.update();
        // sleep(6000);
        
        //MoveStart();
    }
    
    
   
    
    
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
      tfodParameters.minResultConfidence = 0.9f;
      tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
      tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
        
    //waitForStart();
   
    public void backwardsSlow(int time){
        robot.Motor1.setVelocity(538);
        robot.Motor2.setVelocity(538);
        robot.Motor3.setVelocity(-538);
        robot.Motor4.setVelocity(-538);
        sleep(time);
        robot.Motor1.setVelocity(0);
        robot.Motor2.setVelocity(0);
        robot.Motor3.setVelocity(0);
        robot.Motor4.setVelocity(0);
        
    }
    
     public void backwardsFast(int time){
        robot.Motor1.setVelocity(538 * 2);
        robot.Motor2.setVelocity(538 * 2);
        robot.Motor3.setVelocity(-538 * 2);
        robot.Motor4.setVelocity(-538 * 2);
        sleep(time);
        robot.Motor1.setVelocity(0);
        robot.Motor2.setVelocity(0);
        robot.Motor3.setVelocity(0);
        robot.Motor4.setVelocity(0);
     }

    public void forwardFast(int time, double speed){
        robot.Motor1.setVelocity(-538 * speed);
        robot.Motor2.setVelocity(-538 * speed);
        robot.Motor3.setVelocity(538 * speed);
        robot.Motor4.setVelocity(538 * speed);
        sleep(time);
        robot.Motor1.setVelocity(0);
        robot.Motor2.setVelocity(0);
        robot.Motor3.setVelocity(0);
        robot.Motor4.setVelocity(0);
        
    }
    
    public void forwardSlow(int time){
        //sleep(1500);
        robot.Motor1.setVelocity(-538);
        robot.Motor2.setVelocity(-538);
        robot.Motor3.setVelocity(538);
        robot.Motor4.setVelocity(538);
        sleep(time);
        
        robot.Motor1.setVelocity(0);
        robot.Motor2.setVelocity(0);
        robot.Motor3.setVelocity(0);
        robot.Motor4.setVelocity(0);
        
    }
   
    
    public void turnRight(){
        while (degrees > -80.0||degrees < -85){
            
            robot.Motor1.setVelocity(-538 * 1.5);
            robot.Motor2.setVelocity(-538 * 1.5);
            robot.Motor3.setVelocity(-538 * 1.5);
            robot.Motor4.setVelocity(-538 * 1.5);
            
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            double degrees5 = AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle));
            telemetry.addData("heading: ", degrees5);
            degrees = degrees5;
            telemetry.update();
            
           
        }
            robot.Motor1.setVelocity(0);
            robot.Motor2.setVelocity(0);
            robot.Motor3.setVelocity(0);
            robot.Motor4.setVelocity(0);
    }
    public void turnAround(double bigAngle, double smallerAngle){
        while (degrees > bigAngle||degrees < smallerAngle){
            
            robot.Motor1.setVelocity(-538 * 1);
            robot.Motor2.setVelocity(-538 * 1);
            robot.Motor3.setVelocity(-538 * 1);
            robot.Motor4.setVelocity(-538 * 1);
            
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            double degrees5 = AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle));
            telemetry.addData("heading: ", degrees5);
            degrees = degrees5;
            telemetry.update();
            
           
        }
            robot.Motor1.setVelocity(0);
            robot.Motor2.setVelocity(0);
            robot.Motor3.setVelocity(0);
            robot.Motor4.setVelocity(0);
            sleep(200);
    }
    
    public void turnLeft(double bigAngle, double smallerAngle){
         while (degrees > bigAngle || degrees < smallerAngle){
            
            robot.Motor1.setVelocity(538);
            robot.Motor2.setVelocity(538);
            robot.Motor3.setVelocity(538);
            robot.Motor4.setVelocity(538);
            
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            double degrees5 = AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle));
            telemetry.addData("heading: ", degrees5);
            degrees = degrees5;
            telemetry.update();
            
           
        }
            robot.Motor1.setVelocity(0);
            robot.Motor2.setVelocity(0);
            robot.Motor3.setVelocity(0);
            robot.Motor4.setVelocity(0);
    }
    
    
    public void strafLeft(int time, double speed){
        robot.Motor1.setVelocity(538 * speed);
        robot.Motor2.setVelocity(-538 * speed);
        robot.Motor3.setVelocity(-538 * speed);
        robot.Motor4.setVelocity(538 * speed);
        sleep(time);
        robot.Motor1.setVelocity(0);
        robot.Motor2.setVelocity(0);
        robot.Motor3.setVelocity(0);
        robot.Motor4.setVelocity(0);
        
    }
    public void strafRight(int time, double speed){
        robot.Motor1.setVelocity(-538 * speed);
        robot.Motor2.setVelocity(538 * speed);
        robot.Motor3.setVelocity(538 * speed);
        robot.Motor4.setVelocity(-538 * speed);
        sleep(time);
        robot.Motor1.setVelocity(0);
        robot.Motor2.setVelocity(0);
        robot.Motor3.setVelocity(0);
        robot.Motor4.setVelocity(0);
        
    }
     public void zeroAlign(){
         while (degrees > -0.001||degrees < -1){
            
            robot.Motor1.setVelocity(-538 * 0.5);
            robot.Motor2.setVelocity(-538 * 0.5);
            robot.Motor3.setVelocity(-538 * 0.5);
            robot.Motor4.setVelocity(-538 * 0.5);
           
            
            
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            double degrees5 = AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle));
            telemetry.addData("heading: ", degrees5);
            degrees = degrees5;
            telemetry.update();
            
           
        }
            robot.Motor1.setVelocity(0);
            robot.Motor2.setVelocity(0);
            robot.Motor3.setVelocity(0);
            robot.Motor4.setVelocity(0);
        
    }
        
    //waitForStart();
   
    public void shoot(){
        
        
        
        robot.shooter.setVelocity(28 * 66);
        sleep(800);
        robot.servo1.setPosition(0.0);
        sleep(500);
        robot.servo1.setPosition(1.0);
        sleep(500);
        robot.servo1.setPosition(0.0);
        sleep(500);
        robot.servo1.setPosition(1.0);
        sleep(500);
        robot.servo1.setPosition(0.0);
        sleep(500);
        robot.servo1.setPosition(1.0);
        sleep(500);
        robot.shooter.setVelocity(0);
        
       
        
    }
        
        
    
    public void MoveTest(){
       
        if(target.equals("None")){
           
            
            //Shoot
            //zeroAlign();
            forwardFast(2300, 2);
            sleep(100);
            //zeroAlign();
            //line up and shoot
            shoot();
            forwardSlow(1600);
            turnRight();
            sleep(200);
            //Move to Box
            forwardFast(1400, 2);
            sleep(300);
            //Drop Wobble Goal
            robot.arm.setPower(0.1);
            sleep(1400);
            robot.servo2.setPosition(0.5);
            robot.servo3.setPosition(0.5);
            sleep(500);
            //robot.arm.setPower(-0.1);
            //backwardsSlow(500);
            robot.arm.setPower(0);
            sleep(500);
            //strafRight(4200, 1.25);
            //Move to Second Wobble Goal
            turnAround(-174, -179);
            forwardFast(1850, 2);
            sleep(100);
            //zeroAlign();
            //turnAround(177, 175);
            //Pickup Second Wobble Goal
            robot.servo2.setPosition(1.0);
            robot.servo3.setPosition(0.0);
            sleep(800);
            //Move Back to Box
            backwardsFast(1800);
            sleep(100);
            turnLeft(-87, -92);
            //forwardFast(1200);
            //Drop Second Wobble Goal
            robot.servo2.setPosition(0.5);
            robot.servo3.setPosition(0.5);
            //Park
            backwardsSlow(400);
            
        }
        
      if(target.equals("Single")){
        shoot();
        forwardFast(1420, 2);
        turnRight();
        //Move to Box
        forwardSlow(1000);
        sleep(300);
        robot.arm.setPower(0.1);
        sleep(1400);
        //Drop Wobble Goal
        robot.servo2.setPosition(1.0);
        sleep(1000);
        robot.arm.setPower(-0.1);
        //backwardsSlow(1700);
        robot.arm.setPower(0);
        strafRight(1620, 1);
        sleep(1000);
        // turnLeft();
        // backwardsFast(1620);
        // sleep(1000);
     }
      if(target.equals("Quad")){     //1 resol = 537.6 ticks
           /* shoot();
        forwardFast(1420);
        turnRight();
        forwardSlow(1000);
        sleep(300);
        robot.arm.setPower(0.1);
        sleep(1400);
        robot.servo2.setPosition(1.0);
        sleep(1000);
        robot.arm.setPower(-0.1);
        backwardsSlow(1700);
        robot.arm.setPower(0);
        strafRight(1620);
        sleep(1000);*/
           
        shoot();
        forwardFast(2755, 2);
        turnRight();
        //Move to Box
        forwardFast(1505, 2);
        sleep(300);
        sleep(300);
        robot.arm.setPower(0.1);
        sleep(1400);
        //Drop Wobble Goal
        robot.servo2.setPosition(1.0);
        sleep(1000);
        robot.arm.setPower(-0.1);
        //Park
        backwardsSlow(1000);
        robot.arm.setPower(0);
        strafRight(2655, 2);
        sleep(1000);
        
        
      }
     }     
        public void SecondTest(){
            robot.Motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
            robot.Motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
            robot.Motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
            robot.Motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
         
            //robot.Motor1.  
         
         
        }
    }
        

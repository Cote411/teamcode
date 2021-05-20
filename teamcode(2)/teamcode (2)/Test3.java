// package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.hardware.DcMotor;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import com.qualcomm.robotcore.eventloop.opmode.OpMode;
// import com.qualcomm.robotcore.hardware.DcMotorEx;
// import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
// import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
// import org.firstinspires.ftc.robotcore.external.ClassFactory;
// import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
// import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
// import org.firstinspires.ftc.robotcore.external.ClassFactory;
// import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
// import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
// import com.qualcomm.robotcore.hardware.DcMotorEx;
// import com.qualcomm.robotcore.hardware.HardwareMap;
// import com.qualcomm.robotcore.hardware.Servo;
// import com.qualcomm.robotcore.util.ElapsedTime;
// import com.qualcomm.robotcore.hardware.GyroSensor;
// import com.qualcomm.robotcore.hardware.ColorSensor;
// import com.qualcomm.robotcore.eventloop.opmode.Disabled;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import java.util.List;
// import org.firstinspires.ftc.robotcore.external.ClassFactory;
// import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
// import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
// import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
// import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
// @TeleOp

// public class Test3 extends OpMode{
//      private DcMotorEx Motor1;
//      int velocity = -10;
//      public void init(){
//             Motor1 = hardwareMap.get(DcMotorEx.class, "Port0");
//      //Motor1
//      }
//      public void loop(){
          
     
//      Motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//      double motorVelocity = -200;
//      if(gamepad2.right_trigger != 0){
//      Motor1.setVelocity(28 * velocity);
//      //}else{
//         //robot.shooter.setPower(0);
//      }
//      //robot.shooter.getVelocity();
//      telemetry.addData("velocity", Motor1.getVelocity());
//      telemetry.update();
//      }
// }   

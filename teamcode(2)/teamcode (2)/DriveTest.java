// package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import com.qualcomm.robotcore.hardware.DcMotorEx;
// import com.qualcomm.robotcore.util.Range;
// import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
// import com.qualcomm.robotcore.eventloop.opmode.OpMode;
// import com.qualcomm.robotcore.hardware.DcMotor;import com.qualcomm.robotcore.hardware.DcMotor;
// import com.qualcomm.hardware.bosch.BNO055IMU;
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
// //@Override
// public class DriveTest extends OpMode{
    
        
    
    
//      RobotHardware robot = new RobotHardware();
      
    
//      public void init() {
         
       
//         robot.init(hardwareMap);
        
//      }
//      public void loop(){
         
//         //int velocity = 39;
        
        
        
    
       
        
//         // double y = -gamepad1.left_stick_y;
//         // double x = gamepad1.left_stick_x;
//         // double rx = gamepad1.right_stick_x;
     
//         // double theta = Math.atan2(y, x);
//         // double hypot = Math.sqrt((x*x)+(y*y));
//         // double pi = Math.PI;
//         // double storeArray [] = new double[4];
//         // double max = 0;
     
//         // double frontLeftPower = (Math.sin(theta + ((1/4) * pi)) * hypot + rx);
//         // double backLeftPower =  (Math.sin(theta - ((1/4) * pi)) * hypot + rx);
//         // double frontRightPower = (Math.sin(theta - ((1/4) * pi)) * hypot + rx);
//         // double backRightPower =  (Math.sin(theta + ((1/4) * pi)) * hypot + rx);
     
//         // if(frontLeftPower > 1 || frontLeftPower > -1 || backLeftPower > 1 || backLeftPower > -1 || frontRightPower > 1 || frontRightPower > -1 || backRightPower > 1 || backRightPower > -1){
//         //     storeArray[0] = Math.abs(frontLeftPower);
//         //     storeArray[1] = Math.abs(backLeftPower);
//         //     storeArray[2] = Math.abs(frontRightPower);
//         //     storeArray[3] = Math.abs(backRightPower);
//         //     for(int i = 0; i < storeArray.length; i++){
//         //         if(storeArray[i] > max)
//         //         max = storeArray[i];
//         //     }
            
//         //     frontLeftPower /= max;
//         //     backLeftPower /= max;
//         //     frontRightPower /= max;
//         //     backRightPower /= max;
//         // }
        
        
        
        
//         // robot.Motor1.setPower(frontLeftPower); // front left backwards
//         // robot.Motor2.setPower(backLeftPower); //back left backwards
//         // robot.Motor3.setPower(frontRightPower); // front right
//         // robot.Motor4.setPower(backRightPower); // back right

        
        
//     //  if(robot.servo1.getPosition() <= 1.0 && robot.servo1.getPosition()!= 0.0){
//     //     if(gamepad1.left_bumper == true)
//     //         robot.servo1.setPosition(0.0);
//     //  }
//     //  if(robot.servo1.getPosition() <= 0.0 && robot.servo1.getPosition()!= 1.0){
//     //     if(gamepad1.left_bumper == true)
//     //         robot.servo1.setPosition(1.0);
//     //  }
    
//     //     if(gamepad2.left_bumper != false){
//     //         robot.servo1.setPosition(0.0);
//     //         //robot.servo1.setPosition(1.0);
            
//     //     }else{
//     //         robot.servo1.setPosition(1.0);
//     //     }

//     //  //robot.shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     
//     //  if(gamepad2.a != false){
//     //     robot.shooter.setVelocity(28 * velocity);
//     //  }else{
//     //     robot.shooter.setVelocity(0);
//     //  }
//     //  //robot.shooter.getVelocity();
//     //     // telemetry.addData("Front Left: ",frontLeftPower);
//     //     // telemetry.addData("Back Left: ", backLeftPower);
//     //     // telemetry.addData("Front Right: ", frontRightPower);
//     //     // telemetry.addData("Back Right: ", backRightPower);
//     //     // telemetry.update();
//     //  //telemetry.addData("velocity", robot.shooter.getVelocity());
//     //  //telemetry.update();
       
//     //  if(gamepad2.right_bumper != false){
//     //     robot.intake.setPower(-1.0);
//     //  }else if(gamepad2.left_trigger != 0){
//     //     robot.intake.setPower(1.0);
//     //  }else{
//     //     robot.intake.setPower(0.0);
//     //  }
    
//     //  if(gamepad1.right_bumper != false){
//     //     robot.servo2.setPosition(1.0);
//     //     //robot.servo2.setPosition(0.0);
//     //  }else{
//     //     robot.servo2.setPosition(0.0);
//     //  }
//     //  if(gamepad1.right_trigger != 0){
//     //     robot.arm.setPower(-0.25);
//     //  }
//     //  else if(gamepad1.left_trigger != 0){
//     //     robot.arm.setPower(0.25);
//     //  }else{
//     //     robot.arm.setPower(0);
//     //  }
    
       
        
//      }
     
     
    
// }
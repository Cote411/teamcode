package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@TeleOp(name = "Drive - TeleOp")


public class Mecanum_Drive_Fixed extends OpMode{
    RobotHardware robot = new RobotHardware();
    
    //@Override
    // public void init(){
     
    //  robot.init(hardwareMap);

    // }
    
    // public void loop(){
    //     if(gamepad2.a != false){
    //         robot.shooter.setVelocity(28 * 68);
    //     }else{
    //         robot.shooter.setVelocity(538 * 0);
    //     }
        
    //     if(gamepad2.right_bumper != false){
    //         robot.servo1.setPosition(0.0);
    //     }else{
    //         robot.servo1.setPosition(1.0);
    //     }
    // }
    
    public void init() {
       
        robot.init(hardwareMap);
        
     }
    
     public void loop(){
        
        
        int velocity = 39;
       double gyrotheta = (robot.imu.getAngularOrientation().firstAngle + (2 * Math.PI) % (2 * Math.PI))- (Math.PI/2);
        double magnitude = Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2));
        double joytheta = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x);
        joytheta += gyrotheta;
        setVelos(magnitude * Math.sin(joytheta), -magnitude * Math.cos(joytheta), 0.8 * gamepad1.right_stick_x);
        
        
    
       
    
        if(gamepad2.left_bumper != false){
            robot.servo1.setPosition(0.0);
            
        }else{
            robot.servo1.setPosition(1.0);
        }

     
     
     if(gamepad2.a != false){
        //robot.shooter.setVelocity(28 * 68);
        fire3x();
     }else if(gamepad2.b != false){
        robot.shooter.setVelocity(28 * 59);
     }else{
        robot.shooter.setVelocity(0);
     }
     
     if(gamepad2.x != false){
         robot.tape.setPower(-1.0);
     }
     else if(gamepad2.y != false){
       robot.tape.setPower(1.0);
     }
     else{
         robot.tape.setPower(0.0);
         
     }
     
       
     if(gamepad2.right_bumper != false){
        robot.intake.setPower(-1.0);
     }else if(gamepad2.left_trigger != 0){
        robot.intake.setPower(1.0);
     }else{
        robot.intake.setPower(0.0);
     }
    
     if(gamepad1.right_bumper != false){
        robot.servo2.setPosition(1.0);
        robot.servo3.setPosition(0.0);
     }else{
        robot.servo2.setPosition(0.0);
        robot.servo3.setPosition(1.0);
     }
     if(gamepad1.right_trigger != 0){
        robot.arm.setPower(-0.25);
     }
     else if(gamepad1.left_trigger != 0){
        robot.arm.setPower(0.25);
     }else{
        robot.arm.setPower(0);
     }
    
       
        
     }
     void fire3x(){
        robot.shooter.setVelocity(28 * 66);
        sleep(750);
        for(int i = 0; i < 3; i++){
            shoot();
        }
        robot.shooter.setVelocity(0);
     }
     
     void shoot(){
        robot.servo1.setPosition(0.0);
        sleep(300);
        robot.servo1.setPosition(1.0);
        sleep(300);
     }
     
     void sleep(int time){
        try{
            Thread.sleep(time);
        }catch(Exception e){}
     }
     
     public void setVelos(double y, double x, double rx){
         
        
        robot.Motor1.setPower((y + x + rx));
        robot.Motor2.setPower((y - x + rx));
        robot.Motor3.setPower(-(y - x - rx));
        robot.Motor4.setPower((y + x - rx));
     }
}
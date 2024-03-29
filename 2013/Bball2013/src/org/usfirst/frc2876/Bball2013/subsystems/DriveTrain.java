// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc2876.Bball2013.subsystems;
import org.usfirst.frc2876.Bball2013.RobotMap;
import org.usfirst.frc2876.Bball2013.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2876.Bball2013.Robot;
/**
 *
 */
public class DriveTrain extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Gyro gyro = RobotMap.driveTrainGyro;
    SpeedController leftJaguar = RobotMap.driveTrainleftJaguar;
    SpeedController rightJaguar = RobotMap.driveTrainrightJaguar;
    RobotDrive robotDrive = RobotMap.driveTrainRobotDrive;
    public Encoder leftEncoder = RobotMap.driveTrainleftEncoder;
    public Encoder rightEncoder = RobotMap.driveTrainrightEncoder;
    private static final double turnKp = 0.1;
    private static final double turnKi = 0;
    private static final double turnKd = 0.30;
    private static final double turnKf = 0.001;
    private static final double driveKp = 0.1;
    private static final double driveKi = 0;
    private static final double driveKd = 0.30;
    private static final double driveKf = 0.001;
    public static final int DRIVE_WHEEL_RADIUS = 3;
    public static final int PULSE_PER_ROTATION = 360;
    public static final double GEAR_RATIO = 26/12;
    public static final int DRIVE_ENCODER_MIN_RATE = 10;
    public static final int DRIVE_ENCODER_MIN_PERIOD = 10;
    public static  final double DRIVE_ENCODER_PULSE_PER_ROT = PULSE_PER_ROTATION*GEAR_RATIO; //pulse per rotation * gear ratio
    public static final double DRIVE_ENCODER_DIST_PER_TICK = ((Math.PI * 2 * DRIVE_WHEEL_RADIUS) / DRIVE_ENCODER_PULSE_PER_ROT);
    PIDController turnPID;
    PIDController drivePID;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public double limitleft, limitright;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    
    //encoder - e4p-360-250-d-h-d-b
    
    public DriveTrain(){
        startEncoder(leftEncoder);
        startEncoder(rightEncoder);
        leftEncoder.setDistancePerPulse(DRIVE_ENCODER_DIST_PER_TICK);
        rightEncoder.setDistancePerPulse(DRIVE_ENCODER_DIST_PER_TICK);
        turnPID = new PIDController(turnKp, turnKi, turnKd, turnKf, gyro, new PIDOutput() {
            public void pidWrite(double output) {
//                limitleft = output;
//                limitright = -output;
                robotDrive.tankDrive(-output, output);
                //System.out.println("output: " + output + "  -  leftjag: " + leftJaguar.get() + "  -  rightJag: " + rightJaguar.get());
            }   
        });
        
        drivePID = new PIDController(driveKp, driveKi, driveKd, driveKf, leftEncoder, new PIDOutput(){
             public void pidWrite(double output) {
//                limitleft = output;
//                limitright = -output;
                robotDrive.tankDrive(-output, output);
                //System.out.println("output: " + output + "  -  leftjag: " + leftJaguar.get() + "  -  rightJag: " + rightJaguar.get());
            }
        });
        
        turnPID.setOutputRange(-1, 1);
  
        turnPID.setInputRange(-90, 90);

        turnPID.setPercentTolerance(2);
        
        LiveWindow.addActuator("DriveTrain", "turnPID", turnPID);
        
        rightEncoder.setMaxPeriod(DRIVE_ENCODER_MIN_PERIOD);
        rightEncoder.setMinRate(DRIVE_ENCODER_MIN_RATE);
        
        leftEncoder.setMaxPeriod(DRIVE_ENCODER_MIN_PERIOD);
        leftEncoder.setMinRate(DRIVE_ENCODER_MIN_RATE);
        
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new Drive(1));
    }
    
    public void init() {
        gyro.reset();
        turnPID.reset();
        turnPID.setSetpoint(0);
        limitleft = limitright = 0;
    }
    
    public void startTurn(double degrees) {
        gyro.reset();
        turnPID.reset();
        turnPID.setSetpoint(degrees);
        turnPID.enable();
        Timer.delay(.2);
        System.out.println("start turning from "
                + RobotMap.roundtoTwo(gyro.pidGet())
                + " to setpoint " + degrees);
    }

  public void startDriveForward(double distance){
      leftEncoder.reset();
      drivePID.reset();
      drivePID.setSetpoint(distance);
      drivePID.enable();
        Timer.delay(.2);
        System.out.println(" Drive setpoint is: "+ distance +" inches");
  }
    
public boolean isDriveFinished() {
        System.out.println("is turn done: " + turnPID.onTarget()
                + " dist:" + RobotMap.roundtoTwo(leftEncoder.getDistance())
                + " out:" + RobotMap.roundtoTwo(drivePID.get())
                + " err:" + RobotMap.roundtoTwo(drivePID.getError()));

        return turnPID.onTarget();
    }

    public void setTurnSetPoint(double degrees)
    {
        gyro.reset();
        turnPID.setSetpoint(degrees);
    }


    public boolean isTurnFinished() {
        System.out.println("is turn done: " + turnPID.onTarget()
                + " deg:" + RobotMap.roundtoTwo(gyro.getAngle())
                + " out:" + RobotMap.roundtoTwo(turnPID.get())
                + " err:" + RobotMap.roundtoTwo(turnPID.getError()));

        return turnPID.onTarget();
    }

    public void endTurn() {
        turnPID.disable();
    }
    
    public void resetGyro() {
        gyro.reset();
    }
    
    public double getRightJag(){
        return rightJaguar.get();
    }
    
    public double getLeftJag(){
        return leftJaguar.get();
    }
    
    public double getGyro() {
        SmartDashboard.putNumber("gyro: ", gyro.getAngle());
        return gyro.getAngle();
    }
    
    private double limitdrive(double joy, double last) {
        double limit = .06;
        double change = joy - last;
        if (change > limit) {
            change = limit;
        } else if (change < -limit) {
            change = -limit;
        }
        double newlimit = last + change;
        return newlimit;
    }
    public void drive(double left, double right) {
        robotDrive.tankDrive(left, right);
    }
    
    public void drive(Joystick left, Joystick right, double sense) {
        if (left.getZ() < 0) {
            robotDrive.tankDrive(left.getY() * sense, right.getY() * sense);
        } else {
            // TODO scale drive so full power can't be hit.
            limitleft = limitdrive(left.getY() / 1.5, limitleft);
            limitright = limitdrive(right.getY() / 1.5, limitright);
            robotDrive.tankDrive(limitleft * sense, limitright * sense);
        }
    }
    
    public double getLeftEncoder() {
        SmartDashboard.putNumber("left encoder: ", leftEncoder.getRaw());
        return leftEncoder.get();
    }
    
    public double getRightEncoder() {
        SmartDashboard.putNumber("right encoder: ", rightEncoder.getRaw());
        return rightEncoder.get();
    }
    
    public double getLeftEncoderDistance() {
        SmartDashboard.putNumber("left encoder distance: ", leftEncoder.getDistance());
        return leftEncoder.getDistance();
    }
    
    public double getRightEncoderDistance() {
        SmartDashboard.putNumber("right encoder distance: ", rightEncoder.getDistance());
        return rightEncoder.getDistance();
    }
    
    
    public void startEncoder(Encoder encoder) { 
       encoder.reset();
       encoder.start();
    }
    
   public void stopEncoder(Encoder encoder) {
       encoder.stop();
    }
} 

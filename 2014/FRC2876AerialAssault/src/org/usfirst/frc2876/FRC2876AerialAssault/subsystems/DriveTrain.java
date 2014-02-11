// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc2876.FRC2876AerialAssault.subsystems;

import org.usfirst.frc2876.FRC2876AerialAssault.RobotMap;
import org.usfirst.frc2876.FRC2876AerialAssault.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Gyro gyro = RobotMap.driveTrainGyro;
    Encoder leftEncoder = RobotMap.driveTrainLeftEncoder;
    Encoder rightEncoder = RobotMap.driveTrainRightEncoder;
    SpeedController leftSpeedController = RobotMap.driveTrainLeftSpeedController;
    SpeedController rightSpeedController = RobotMap.driveTrainRightSpeedController;
    RobotDrive robotDrive21 = RobotMap.driveTrainRobotDrive21;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    GyroPID gyroPID = new GyroPID();

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new Drive());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void tankDrive(double left, double right) {
        robotDrive21.tankDrive(left, right);

    }

    public void arcadeDrive(double throttle, double rotation) {
        robotDrive21.arcadeDrive(throttle, rotation);
    }

    public double getHeading() {
        return gyro.getAngle();
    }

    public void resetEncoders() {

        leftEncoder.reset();
        rightEncoder.reset();
    }

    public void startEncoders() {
        leftEncoder.start();
        rightEncoder.start();
    }

    public void stopEncoders() {
        leftEncoder.stop();
        rightEncoder.stop();
    }

    public void updateDashboard() {
        SmartDashboard.putData("Left Encoder", leftEncoder);
        SmartDashboard.putData("Right Encoder", rightEncoder);
        SmartDashboard.putData("Gyro", gyro);
        if (gyroPID.isEnabled()) {
            SmartDashboard.putData("GyroPID", gyroPID.getPIDController());
        }
    }

    public void driveStraightStart() {
        gyroPID.start();
    }

    public void driveStraightStop() {
        gyroPID.stop();
    }

    private class GyroPID {

        private PIDController gpid = new PIDController(1.0, 0, 0, gyro, new DrivePIDOutput());;

        private class DrivePIDOutput implements PIDOutput {

            public void pidWrite(double output) {
                double base = .7;
                //System.out.println("gryopid output" + Utilities.rnd(output));
                printStatus();
                if (output > 0) {
                    //robotDrive2.tankDrive(base, base);
                    robotDrive21.tankDrive(output + base, base);
                } else if (output < 0) {
                    robotDrive21.tankDrive(base, output + base);
                } else {
                    robotDrive21.tankDrive(base, base);
                }
                //robotDrive2.tankDrive(base + output, base + output);
            }
        }

        public void GyroPID() {
            //gpid = new PIDController(1.0, 0, 0, gyro, new DrivePIDOutput());
            gpid.setOutputRange(-.2, .2);
            gpid.setPercentTolerance(5);
        }

        public PIDController getPIDController() {
            return gpid;
        }

        public void start() {
            gyro.reset();
            gpid.setSetpoint(0);
            gpid.enable();
        }

        public void stop() {
            gpid.disable();
        }

        public boolean isEnabled() {
            return gpid.isEnable();
        }

        private double now = Timer.getFPGATimestamp();
        private double last = now;

        public void printStatus() {
            now = Timer.getFPGATimestamp();
            if (now - last > 1) {
                System.out.println("gpid out: " + gpid.get()
                        + " error: " + gpid.getError()
                        + " l: " + leftSpeedController.get()
                        + " r: " + rightSpeedController.get());
                last = now;
            }

        }
    }
}

// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc2876.Bball2013;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static Gyro driveTrainGyro;
    public static SpeedController driveTrainleftJaguar;
    public static SpeedController driveTrainrightJaguar;
    public static RobotDrive driveTrainRobotDrive;
    public static DigitalInput conveyorHighlmMiddle;
    public static Relay conveyorHighconveyorHighRelay;
    public static DigitalInput conveyorLowlmLow;
    public static Relay conveyorLowconveyorLowRelay;
    public static DigitalInput shooterlmHigh;
    public static DigitalInput shooterlineTracker;
    public static SpeedController shootershootJaguar;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Encoder driveTrainleftEncoder;
    public static Encoder driveTrainrightEncoder;
        //encoder - e4p-360-250-d-h-d-b

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainGyro = new Gyro(1, 2);
        LiveWindow.addSensor("DriveTrain", "Gyro", driveTrainGyro);
        driveTrainGyro.setSensitivity(.007);
        driveTrainleftJaguar = new Jaguar(1, 3);
        LiveWindow.addActuator("DriveTrain", "leftJaguar", (Jaguar) driveTrainleftJaguar);

        driveTrainrightJaguar = new Jaguar(1, 1);
        LiveWindow.addActuator("DriveTrain", "rightJaguar", (Jaguar) driveTrainrightJaguar);

        driveTrainRobotDrive = new RobotDrive(driveTrainleftJaguar, driveTrainrightJaguar);

        driveTrainRobotDrive.setSafetyEnabled(false);
        driveTrainRobotDrive.setExpiration(0.1);
        driveTrainRobotDrive.setSensitivity(0.5);
        driveTrainRobotDrive.setMaxOutput(1.0);

        //conveyorHighlmMiddle = new DigitalInput(1, 2);
        //LiveWindow.addSensor("ConveyorHigh", "lmMiddle", conveyorHighlmMiddle);

        conveyorHighconveyorHighRelay = new Relay(1, 7);
        LiveWindow.addActuator("ConveyorHigh", "conveyorHighRelay", conveyorHighconveyorHighRelay);

        conveyorLowlmLow = new DigitalInput(1, 4);
        LiveWindow.addSensor("ConveyorLow", "lmLow", conveyorLowlmLow);

        //conveyorLowconveyorLowRelay = new Relay(1, 1);
        //LiveWindow.addActuator("ConveyorLow", "conveyorLowRelay", conveyorLowconveyorLowRelay);

        shooterlmHigh = new DigitalInput(1, 3);
        LiveWindow.addSensor("Shooter", "lmHigh", shooterlmHigh);

        shooterlineTracker = new DigitalInput(1, 1);
        LiveWindow.addSensor("Shooter", "lineTracker", shooterlineTracker);

        shootershootJaguar = new Jaguar(1, 2);
        LiveWindow.addActuator("Shooter", "shootJaguar", (Jaguar) shootershootJaguar);


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainleftEncoder = new Encoder(5, 6, false, CounterBase.EncodingType.k4X);
        driveTrainleftEncoder.setMinRate(10);
        driveTrainleftEncoder.setMaxPeriod(10);
        driveTrainleftEncoder.start();
        LiveWindow.addSensor("DriveTrain", "leftEnc", driveTrainleftEncoder);
        
        driveTrainrightEncoder = new Encoder(7, 8, false, CounterBase.EncodingType.k4X);
        driveTrainrightEncoder.setMinRate(10);
        driveTrainrightEncoder.setMaxPeriod(10);
        driveTrainrightEncoder.start();
        LiveWindow.addSensor("DriveTrain", "rightEnc", driveTrainrightEncoder);
    }

    public static double roundtoTwo(double num) {
        return Math.floor(num * 100.0) / 100.0;
    }

    public static int relayValToInt(Relay.Value val) {
        if (Relay.Value.kForward == val) {
            return 1;
        } else if (Relay.Value.kReverse == val) {
            return -1;
        } else if (Relay.Value.kOff == val) {
            return 0;
        }
        return 0;
    }
}

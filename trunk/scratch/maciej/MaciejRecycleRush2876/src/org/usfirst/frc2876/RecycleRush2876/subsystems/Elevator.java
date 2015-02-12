// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2876.RecycleRush2876.subsystems;

import org.usfirst.frc2876.RecycleRush2876.RobotMap;
import org.usfirst.frc2876.RecycleRush2876.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Elevator extends PIDSubsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    AnalogPotentiometer  Potentiometer = RobotMap.elevatorPotentiometer;
    SpeedController elevatorMotor = RobotMap.elevatorElevatorMotor;
    DigitalInput topLimit = RobotMap.elevatorTopLimit;
    DigitalInput bottomLimit = RobotMap.elevatorBottomLimit;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Elevator() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        super("Elevator", 1.0, 0.0, 0.0);
        setAbsoluteTolerance(0.2);
        getPIDController().setContinuous(false);
        LiveWindow.addActuator("Elevator", "PIDSubsystem Controller", getPIDController());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
    }
        
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new ElevatorIdle());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return  Potentiometer.get();
    }
    
	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		if (isAtBottom() || isAtTop()) {
			elevatorMotor.pidWrite(0);
			disable();
			return;
		}
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
		elevatorMotor.pidWrite(output);
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
	}

	// ===============================================================
	// STUFF I ADDED DO NOT CHECKIN
	public enum Position {
		TOP    (10),
		PICKUP (1),
		BOTTOM (0),
		MOVING (-1);
		
		private final double pot;
		Position(double pot) {
			this.pot = pot;
		}
		public double getVoltage() {
			return pot;
		}
	}
	public boolean isAtTop() {
		if (!topLimit.get()) {
			return true;
		}
		double cur = getPosition();		
		return (cur > Position.TOP.getVoltage());
	}
	
	public boolean isAtBottom() {
		if (!bottomLimit.get()) {
			return true;
		}
		double cur = getPosition();		
		return (cur > Position.BOTTOM.getVoltage());
	}

	public void setPosition(double pos) {
		setSetpoint(pos);
	}
	public void setPosition(Position pos) {
		setSetpoint(pos.getVoltage());
	}
	public void moveTop() {
		setPosition(Position.TOP);
	}
	public void moveBottom() {
		setPosition(Position.BOTTOM);
	}
    
    public void motorUp() {
    	elevatorMotor.set(.5);
    }
    
    public void motorOff() {
    	elevatorMotor.set(0);
    }
    
    public void motorDown() {
    	elevatorMotor.set(-.3);
    }
    
  public boolean topMax() {
	return !topLimit.get();  
  }
  
  public boolean bottomMax() {
	  return !bottomLimit.get();
  }

}
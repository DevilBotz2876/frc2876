/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.Pimpbot2011.commands;

import edu.wpi.first.Pimpbot2011.OI;


/**
 *
 * @author maciej
 */
public class ShoulderWithJoystick extends CommandBase {

    public ShoulderWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super("ShoulderWithJoystick");
        requires(shoulder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shoulder.set(oi.getArmSpeed());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
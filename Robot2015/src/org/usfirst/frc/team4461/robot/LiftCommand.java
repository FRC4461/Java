package org.usfirst.frc.team4461.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class LiftCommand {
	
	enum Lift {
		Start,
		BreakRelease,
		Lift,
		BreakStart
	}
	enum LiftAuto {
		Start,
		BreakRelease,
		Lift
	}
	enum Stack {
		Start,
		BreakRelease,
		Lower,
		Stop,
		Lift
	}
	enum Drops {
		Start,
		BreakRelease,
		Lower,
		Terminate
	}
	
		JoystickButton button;
		private Lift TeleState = Lift.Start; 
		private LiftAuto AutoState = LiftAuto.Start;
		private Stack StackState = Stack.Start;
		private Drops DropState = Drops.Start;
	/**
	 * This function is called during operator control within
	 * the switch codes. Used for the Lift mechanism
	 * @param js The joystick used for the button
	 * @param Jbutton The button used to begin Execute() command
	 */
	public LiftCommand(Joystick js, int Jbutton) {
		button = new JoystickButton(js, Jbutton);
	}
	/**
	 * This function is called during operator control to
	 * execute a lifting mechanism for lifting totes and
	 * containers
	 */
	public void Execute() {
		if (button.get()) {
			switch(TeleState) {
			case Start:
			{
				Robot.liftEncoder.reset();
				TeleState = Lift.BreakRelease;
			}
			break;
			case BreakRelease:
			{
				Robot.Collector.set(true);
				Robot.liftEncoder.reset();
				TeleState = Lift.Lift;
			}
			break;
			case Lift:
			{
				if(Robot.liftEncoder.getDistance() >= 13) { //Estimated Teeth separation amount 
					Robot.liftEncoder.reset();
					TeleState = Lift.BreakStart;
				} else {
					Robot.lifting.set(0.3);
				}
			}
			break;
			case BreakStart:
			{
				Robot.Collector.set(false);
				Robot.liftEncoder.reset();
			}
			break;
			}
		}
 	}
	/**
	 * This function is called periodically during autonomous within
	 * the switch codes. Used for lifting mechanisms
	 * @param Switch Determines which lifting mechanism to use.
	 * True is for lifting ONLY. False is for Stacking ONLY
	 * @param length Determines the distance the motors go to
	 * lift the totes and/or containers
	 */
	public void ExecutePeriodic(boolean Switch, int length) {  //Used for Autonomous Routines
		Robot.liftEncoder.reset();
		if (Switch) {
			switch(AutoState) {
			case Start:
			{
				Robot.Lift = false;
				Robot.liftEncoder.reset();
				AutoState = LiftAuto.BreakRelease;
			}
			break;
			case BreakRelease:
			{
				Robot.Collector.set(true);
				Robot.liftEncoder.reset();
				AutoState = LiftAuto.Lift;
			}
			break;
			case Lift:
			{
				if (Robot.liftEncoder.getDistance() >= length) {
				Robot.lifting.set(0.0);
				Robot.liftEncoder.reset();
				Robot.Collector.set(false);
				Robot.Lift = true;
				} else {
					Robot.lifting.set(0.3);
				}
			}
			break;
			}
		} else {
			switch(StackState) {
			case Start:
			{
				Robot.Lift = false;
				Robot.liftEncoder.reset();
				StackState = Stack.BreakRelease;
			}
			break;
			case BreakRelease:
			{
				Robot.Collector.set(true);
				Robot.liftEncoder.reset();
				StackState = Stack.Lower;
			}
			break;
			case Lower:
			{
				if(Robot.liftEncoder.getDistance() <= -5) {   //Estimation
					Robot.liftEncoder.reset();
					StackState = Stack.Stop;
				} else {
					Robot.lifting.set(-0.2);
				}
			}
			break;
			case Stop:
			{
				Robot.liftEncoder.reset();
				Robot.lifting.set(0.0);
				StackState = Stack.Lift;
			}
			case Lift:
			{
				if(Robot.liftEncoder.getDistance() >= 5) {    //Estimation
					Robot.liftEncoder.reset();
					Robot.lifting.set(0.0);
					Robot.Collector.set(false);
					Robot.Lift = true;
				} else {
					Robot.lifting.set(0.2);
				}
			}
			break;
			} 
		}
	}
	/**
	 * This funtion is called during autonomous within the switch codes.
	 * Used solely for the purpose of lowering totes and stacked totes and containers
	 * to the ground
	 */
	public void Drop() {
		switch(DropState) {
		case Start:
		{
			Robot.liftEncoder.reset();
			Robot.Lift = false;
			DropState = Drops.BreakRelease;
		}
		break;
		case BreakRelease:
		{
			Robot.liftEncoder.reset();
			Robot.Collector.set(true);
			DropState = Drops.Lower;
		}
		break;
		case Lower:
		{
			if(Robot.liftEncoder.getDistance() <= -13) {
				Robot.liftEncoder.reset();
				DropState = Drops.Terminate;
			} else {
				Robot.lifting.set(0.3);
			}
		}
		break;
		case Terminate:
		{
			Robot.Collector.set(false);
			Robot.lifting.set(0.0);
			Robot.Lift = true;
		}
		break;
		}
	}
}

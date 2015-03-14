package org.usfirst.frc.team4461.robot;

import org.usfirst.frc.team4461.robot.LiftCommand.Lift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class LiftTeleop {
	JoystickButton liftButton;
	JoystickButton lowerButton;
	JoystickButton continuousLiftButton;
	JoystickButton continuousLowerButton;
	
	boolean liftButtonLastState = false;
	boolean lowerButtonLastState = false;
	int pendingLiftCount;
	
	enum States
	{
		Idle,
		Lifting,
		Lowering,
		Done
	}
	
	States state = States.Idle;
	
	/**
	 * This function is called during operator control within
	 * the switch codes. Used for the Lift mechanism
	 * @param js The joystick used for the button
	 * @param Jbutton The button used to begin Execute() command
	 */
	public LiftTeleop(Joystick js, int liftButtonNumber, int lowerButtonNumber, int continuousLiftButtonNumber, int continuousLowerButtonNumber) {
		liftButton = new JoystickButton(js, liftButtonNumber);
		lowerButton = new JoystickButton(js, lowerButtonNumber);
		continuousLiftButton = new JoystickButton(js, continuousLiftButtonNumber);
		continuousLowerButton = new JoystickButton(js, continuousLowerButtonNumber);
	}
	
	public void ExecutePeriodic()
	{
		if (liftButton.get() && !liftButtonLastState)
		{
			pendingLiftCount++;
		}
		
		if (lowerButton.get() && !lowerButtonLastState)
		{
			pendingLiftCount--;
		}
		
		if (continuousLiftButton.get())
		{
			state = States.Idle;
			pendingLiftCount = 0;
			ReleaseBrake();
			Robot.liftingA.set(-0.3);
			Robot.liftingB.set(-0.3);
		}
		else if (continuousLowerButton.get())
		{
			state = States.Idle;
			pendingLiftCount = 0;
			ReleaseBrake();
			Robot.liftingA.set(0.3);
			Robot.liftingB.set(0.3);
		}
		else if (state == States.Idle && pendingLiftCount > 0)
		{
			state = States.Lifting;
			ReleaseBrake();
			Robot.liftEncoder.reset();
		}
		else if (state == States.Idle && pendingLiftCount < 0)
		{
			state = States.Lowering;
			ReleaseBrake();
			Robot.liftEncoder.reset();
		}
		else if (state == States.Idle)
		{
			Robot.liftingA.set(0.0);
			Robot.liftingB.set(0.0);
			SetBrake();
		}
		else if (state == States.Lifting)
		{
			if(Robot.liftEncoder.getDistance() >= 13) { //Estimated Teeth separation amount 
				Robot.liftEncoder.reset();
				
				// Keep going if we have more pending
				pendingLiftCount--;
				if (pendingLiftCount == 0)
				{
					SetBrake();
					state = States.Idle;
				}
				else if (pendingLiftCount < 0)
				{
					state = States.Lowering;
				}
			} else {
				Robot.liftingA.set(-0.3);
				Robot.liftingB.set(-0.3);
			}
		}
		else if (state == States.Lowering)
		{
			if(Robot.liftEncoder.getDistance() <= -13) { //Estimated Teeth separation amount 
				Robot.liftEncoder.reset();
				
				// Keep going if we have more pending
				pendingLiftCount++;
				if (pendingLiftCount == 0)
				{
					state = States.Idle;
					SetBrake();
				}
				else if (pendingLiftCount > 0)
				{
					state = States.Lifting;
				}
			} else {
				Robot.liftingA.set(0.3);
				Robot.liftingB.set(0.3);
			}
		}
		
		liftButtonLastState = liftButton.get();
		lowerButtonLastState = lowerButton.get();
	}

	private void ReleaseBrake() {
		Robot.Collector.set(true);
	}
	
	private void SetBrake()
	{
		Robot.Collector.set(false);
	}
}

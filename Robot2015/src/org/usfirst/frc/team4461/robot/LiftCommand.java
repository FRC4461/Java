package org.usfirst.frc.team4461.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class LiftCommand {
	
	enum Lift {
		Start,
		BreakRelease,
		Lift,
		BreakStart,
		Stop
	}
	
		JoystickButton button;
		private Lift TeleState = Lift.Start; 
	
	public LiftCommand(Joystick js) {
		button = new JoystickButton(js, 1);
	}
	
	public void Execute() {
		
		if (button.get()) {
			switch(TeleState) {
			case Start:
			{
				Robot.encoderLeft.reset();
			}
			break;
			case BreakRelease:
			{
				
			}
			break;
			case Lift:
			{
				
			}
			break;
			case BreakStart:
			{
				
			}
			break;
			case Stop:
			{
				
			}
			break;
			}
		}
 	}
}

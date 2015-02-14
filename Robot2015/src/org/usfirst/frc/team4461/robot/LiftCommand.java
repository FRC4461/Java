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
					Robot.lifting.set(0.2);
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
	public void ExecutePeriodic() {  //Not Finished; Used for Autonomous Routines
		
	}
}

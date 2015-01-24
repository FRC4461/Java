
package org.usfirst.frc.team4461.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	AutonomousStates state = AutonomousStates.AutoStart;
	RoutineOne state2 = RoutineOne.AutoStart;
	RoutineTwo state3 = RoutineTwo.AutoStart;
	int timeForward1 = 0;
	int timeTurning = 0;
	int timeForward2 = 0;
	
	
	edu.wpi.first.wpilibj.Joystick joystick = new edu.wpi.first.wpilibj.Joystick(0);
	edu.wpi.first.wpilibj.Victor frontRightMotorControl = new edu.wpi.first.wpilibj.Victor(1);
	edu.wpi.first.wpilibj.Victor backRightMotorControl = new edu.wpi.first.wpilibj.Victor(2);
	edu.wpi.first.wpilibj.Victor backLeftMotorControl = new edu.wpi.first.wpilibj.Victor(3);
	edu.wpi.first.wpilibj.Victor frontLeftMotorControl = new edu.wpi.first.wpilibj.Victor(4);
	edu.wpi.first.wpilibj.RobotDrive robotDrive = new edu.wpi.first.wpilibj.RobotDrive(frontLeftMotorControl, backLeftMotorControl, frontRightMotorControl, backRightMotorControl);
	edu.wpi.first.wpilibj.Encoder encoder = new edu.wpi.first.wpilibj.Encoder(1, 2);
	edu.wpi.first.wpilibj.DigitalInput limitSwitch = new edu.wpi.first.wpilibj.DigitalInput(5);
	edu.wpi.first.wpilibj.smartdashboard.SmartDashboard SmartDash = new edu.wpi.first.wpilibj.smartdashboard.SmartDashboard();
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	encoder.setDistancePerPulse(0.02528445);
    	encoder.setReverseDirection(true);
    	SmartDash.putInt("RoutineSwitch", 1);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if (limitSwitch.get() == false) {
    		this.state = AutonomousStates.stopTerminate;
    	} 
    	switch (this.state) {
    	case AutoStart:
    	{
    		encoder.reset();
    		this.state = AutonomousStates.forward;
    	}
    		break;
    	case forward:
    	{
    		if (encoder.getDistance() >= 60) {
    			encoder.reset();
    			this.state = AutonomousStates.stop1;
    			
    		}
    		robotDrive.drive(0.2, 0.0);
    		System.out.println(encoder.get());

    	}
    		break;
    	case stop1:
    	{
    		robotDrive.drive(0.0, 0.0);
    		this.state = AutonomousStates.turnLeft90;
    	}
    		break;
    	case turnLeft90:
    	{
    		robotDrive.drive(0.2, 0.2);
    		timeTurning++;
    		if (timeTurning == 120) {
    			this.state = AutonomousStates.forward2;
    		}
    	}
    		break;
    	case forward2:
    	{
    		robotDrive.drive(0.2, 0.0);
    		timeForward2++;
    		if (timeForward2 == 100) {
    			this.state = AutonomousStates.stopTerminate;
    		}
    	}
    		break;
    	case stopTerminate:
    	{
    		robotDrive.drive(0.0, 0.0);
    	}
    		break;
    	}

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        robotDrive.arcadeDrive(joystick);
    }
    void MoveForward() {
    	robotDrive.drive(0.7, 0.0);
    }
    void MoveBack() {
    	robotDrive.arcadeDrive(-0.7, 0.0);
    }
    void TurnLeft() {
    	robotDrive.drive(0.2, 0.2);
    }
    void Stop() {
    	robotDrive.drive(0.0, 0.0);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void RoutineOnePeriodic() {
    	if (limitSwitch.get() == false) {
    		this.state2 = RoutineOne.TerminateStop;
    	}
    	switch(this.state2) {
    	case AutoStart:
    	{
    		encoder.reset();
    		this.state2 = RoutineOne.Lift1;
    	}
		break;

    	case Lift1:
    	{
    		encoder.reset();
    		this.state2 = RoutineOne.MoveBack1;
    	}
		break;

    	case MoveBack1:
    	{
    		if(encoder.getDistance() <= -24) {
        		encoder.reset();
        		this.state2 = RoutineOne.stop1;
    		} else {
    			MoveBack();
    			System.out.println(encoder.getDistance());
    		}
    	}
		break;

    	case stop1:
    	{
    		Stop();
    		encoder.reset();

    		this.state2 = RoutineOne.TurnRight1;
    	}
		break;

    	case TurnRight1:
    	{
    		encoder.reset();

    		this.state2 = RoutineOne.MoveForward1;
    	}
		break;

    	case MoveForward1:
    	{
    		if (encoder.getDistance() >= 93) {
        		encoder.reset();
        		this.state2 = RoutineOne.stop2;
    		} else {
    			MoveForward();
    		}
    	}
    	break;
    	
    	case stop2:
    	{
    		Stop();
    		encoder.reset();

    		this.state2 = RoutineOne.TurnLeft1;
    	}
		break;

    	case TurnLeft1:
    	{
    		if (encoder.getDistance() >= 10) {
        		encoder.reset();
        		this.state2 = RoutineOne.MoveForward2;
    		} else {
    			TurnLeft();
    	}
    	}
		break;

    	case MoveForward2:
    	{
    		if (encoder.getDistance() >= 24) {
        		encoder.reset();
        		this.state2 = RoutineOne.stop3;
    		} else {
    			MoveForward();
    		}
    	}
		break;

    	case stop3:
    	{
    		Stop();
    		encoder.reset();

    		this.state2 = RoutineOne.Lift2;
    	}
		break;

    	case Lift2:
    	{
    		encoder.reset();

    		this.state2 = RoutineOne.MoveBack2;
    	}
		break;

    	case MoveBack2:
    	{
    		if(encoder.getDistance() <= -24) {
        		encoder.reset();
        		this.state2 = RoutineOne.stop4;
    		} else {
    			MoveBack();
    		}
    	}
		break;

    	case stop4:
    	{
    		Stop();
    		encoder.reset();

    		this.state2 = RoutineOne.TurnRight2;
    	}
		break;

    	case TurnRight2:
    	{
    		encoder.reset();

    		this.state2 = RoutineOne.MoveForward3;
    	}
		break;

    	case MoveForward3:
    	{
    		if (encoder.getDistance() >= 93) {
        		encoder.reset();
        		this.state2 = RoutineOne.stop5;
    		} else {
    			MoveForward();
    		}
    	}
		break;

    	case stop5:
    	{
    		Stop();
    		encoder.reset();

    		this.state2 = RoutineOne.TurnLeft2;
    	}
		break;

    	case TurnLeft2:
    	{
    		if (encoder.getDistance() >= 10) {
        		encoder.reset();
        		this.state2 = RoutineOne.MoveForward4;
    		} else {
    			TurnLeft();
    		}
    	}
		break;

    	case MoveForward4:
    	{
    		if (encoder.getDistance() >= 24) {
        		encoder.reset();
        		this.state2 = RoutineOne.Lift3;
    		} else {
    			MoveForward();
    		}
    	}
		break;

    	case Lift3:
    	{
    		encoder.reset();

    		this.state2 = RoutineOne.MoveBackCenter;
    	}
		break;

    	case MoveBackCenter:
    	{
    		if (encoder.getDistance() <= -105) {
        		encoder.reset();
        		this.state2 = RoutineOne.stop6;
    		} else {
    			MoveBack();
    		}
    	}
		break;

    	case stop6:
    	{
    		Stop();
    		encoder.reset();
    		this.state2 = RoutineOne.Drop;
    	}
		break;

    	case Drop:
    	{
    		encoder.reset();
    		this.state2 = RoutineOne.MoveBackSafety;
    	}
		break;

    	case MoveBackSafety:
    	{
    		if (encoder.getDistance() <= -12) {
        		encoder.reset();
        		this.state2 = RoutineOne.TerminateStop;
    		} else {
    			MoveBack();
    		}
    	}
		break;

    	case TerminateStop:
    	{
    		Stop();
    	}
		break;

    	}
    }
    public void RoutineTwo() {
    	switch (this.state3) {
    	case AutoStart:
    	{
    		encoder.reset();
    		this.state3 = RoutineTwo.forward;
    	}
    	break;
    	case forward:
    	{
    		MoveForward();
    		if (encoder.getDistance() >= 12) {
    			encoder.reset();
    			this.state3 = RoutineTwo.stop;
    		}
    	}
    	break;
    	case stop:
    	{
    		Stop();
    	}
    	break;
    	}
    }
    public void testPeriodic() {
    	switch (SmartDash.getInt("RoutineSwitch", 1)) {
    	case 1:
    	{
    		RoutineOnePeriodic();
    	}
    	break;
    	case 2:
    	{
    		RoutineTwo();
    	}
    	break;
    	}
    }
    
}
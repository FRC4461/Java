
package org.usfirst.frc.team4461.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.RGBImage;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//Routines
		AutonomousStates state = AutonomousStates.AutoStart;
		RoutineOne state2 = RoutineOne.AutoStart;
		RoutineTwo state3 = RoutineTwo.AutoStart;
	
	//Timer
		int timeForward1 = 0;
		int timeTurning = 0;
		int timeForward2 = 0;
	
	//Joy sticks
		edu.wpi.first.wpilibj.Joystick joystick = new edu.wpi.first.wpilibj.Joystick(0);
	
	//Drive Base
		edu.wpi.first.wpilibj.Victor frontRightMotorControl = new edu.wpi.first.wpilibj.Victor(1);
		edu.wpi.first.wpilibj.Victor backRightMotorControl = new edu.wpi.first.wpilibj.Victor(2);
		edu.wpi.first.wpilibj.Victor backLeftMotorControl = new edu.wpi.first.wpilibj.Victor(3);
		edu.wpi.first.wpilibj.Victor frontLeftMotorControl = new edu.wpi.first.wpilibj.Victor(4);
		edu.wpi.first.wpilibj.RobotDrive robotDrive = new edu.wpi.first.wpilibj.RobotDrive(frontLeftMotorControl, backLeftMotorControl, frontRightMotorControl, backRightMotorControl);
	
	//Encoder
		edu.wpi.first.wpilibj.Encoder encoderRight = new edu.wpi.first.wpilibj.Encoder(4, 3);
		edu.wpi.first.wpilibj.Encoder encoderLeft = new edu.wpi.first.wpilibj.Encoder(8, 9);
	
	//Switches and sensors
		edu.wpi.first.wpilibj.DigitalInput limitSwitch = new edu.wpi.first.wpilibj.DigitalInput(5);
		
	//Camera
		//edu.wpi.first.wpilibj.vision.AxisCamera camera = new edu.wpi.first.wpilibj.vision.AxisCamera("10.44.61.11");
	
	//Miscellaneous
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard SmartDash = new edu.wpi.first.wpilibj.smartdashboard.SmartDashboard();
		private int mode = 1;
		private SendableChooser autoSwitch;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	//Encoder
    		encoderRight.setDistancePerPulse(0.006135923);
    		encoderRight.setReverseDirection(true);
    		encoderLeft.setDistancePerPulse(0.006135923);
		//Smartdashboard
    		//SmartDashboard.putNumber("Autonomous Routines: ", mode);
    		autoSwitch = new SendableChooser();
    		autoSwitch.addDefault("Routine One", 1);
    		autoSwitch.addObject("Routine Two", 2);
    		autoSwitch.addObject("Auto States Test", 3);
    		SmartDashboard.putData("Autonomous Routines", autoSwitch);
    
    	edu.wpi.first.wpilibj.CameraServer.getInstance().startAutomaticCapture("axis-00408cdfe00a");
    }
    public void autonomousInit() {
    	//SmartDashboard.getNumber("Autonomous Routines: ");
    	mode = (int) autoSwitch.getSelected();
    }
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch (mode) {
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
    	case 3:
    	{
    		AutoStates();
    	}
    	}
    	
    }
    public void AutoStates() {
    	if (limitSwitch.get() == false) {
    		this.state = AutonomousStates.stopTerminate;
    	} 
    	switch (this.state) {
    	case AutoStart:
    	{
    		encoderRight.reset();
    		encoderLeft.reset();
    		this.state = AutonomousStates.forward;
    	}
    		break;
    	case forward:
    	{
    		if (encoderRight.getDistance() >= 60) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state = AutonomousStates.stop1;
    			
    		}
    		robotDrive.drive(0.2, 0.0);
    		System.out.println(encoderRight.get() + "  |  " + encoderLeft.get());

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
      /*  RGBImage colours = null;
		try {
			colours = new RGBImage();
		} catch (NIVisionException e) {
			e.printStackTrace();
		}
        camera.getImage(colours);
        try {
			colours.getBluePlane();
		} catch (NIVisionException e) {
			e.printStackTrace();
		}

        edu.wpi.first.wpilibj.CameraServer.getInstance().setImage(colours.image); */
    } 
    /**
     * This function tells the robot to move forward during Autonomous. 
     * Used within the switch code for Autonomous for a specified speed
     */
    void MoveForward() {
    	robotDrive.drive(0.7, 0.0);
    }
    /**
     * This function tells the robot to move backwards during Autonomous.
     *  Used within the switch code for Autonomous for a specified speed
     */
    void MoveBack() {
    	robotDrive.arcadeDrive(-0.7, 0.0);
    }
    /**
     * This function tells the robot to turn left during Autonomous.
     * Used within the switch code for Autonomous for a specified
     * curve vector
     */
    void TurnLeft() {
    	robotDrive.drive(0.2, 0.2);
    }
    /**
     * This function tells the robot to turn right during Autonomous.
     * Used within the switch code for Autonomous for a specified
     * curve vector
     */
    void TurnRight() {
    	robotDrive.drive(0.2, -0.2);
    }
    /**
     * This function tells the robot to stop the motors from running
     * during Autonomous. Used within the switch code for safety
     * precautions for the motors
     */
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
    		encoderRight.reset();
    		encoderLeft.reset();
    		this.state2 = RoutineOne.Lift1;
    	}
		break;

    	case Lift1:
    	{
    		encoderRight.reset();
    		encoderLeft.reset();
    		this.state2 = RoutineOne.MoveBack1;
    	}
		break;

    	case MoveBack1:
    	{
    		if(encoderRight.getDistance() <= -24) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state2 = RoutineOne.stop1;
    		} else {
    			MoveBack();
    			System.out.println(encoderRight.getDistance() + "  |  " + encoderLeft.getDistance());
    		}
    	}
		break;

    	case stop1:
    	{
    		Stop();
    		encoderRight.reset();
    		encoderLeft.reset();

    		this.state2 = RoutineOne.TurnRight1;
    	}
		break;

    	case TurnRight1:
    	{		
    		if (encoderLeft.getDistance() >= 17.8678082) {
    			encoderRight.reset();
        		encoderLeft.reset();
        		this.state2 = RoutineOne.MoveForward1;
    		} else {
    			TurnRight();
    	}
    	}
		break;

    	case MoveForward1:
    	{
    		if (encoderRight.getDistance() >= 93) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state2 = RoutineOne.stop2;
    		} else {
    			MoveForward();
    		}
    	}
    	break;
    	
    	case stop2:
    	{
    		Stop();
    		encoderRight.reset();
    		encoderLeft.reset();

    		this.state2 = RoutineOne.TurnLeft1;
    	}
		break;

    	case TurnLeft1:
    	{
    		if (encoderRight.getDistance() >= 17.8678082) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state2 = RoutineOne.MoveForward2;
    		} else {
    			TurnLeft();
    	} 
    	}
		break;

    	case MoveForward2:
    	{
    		if (encoderRight.getDistance() >= 24) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state2 = RoutineOne.stop3;
    		} else {
    			MoveForward();
    		}
    	}
		break;

    	case stop3:
    	{
    		Stop();
    		encoderRight.reset();
    		encoderLeft.reset();

    		this.state2 = RoutineOne.Lift2;
    	}
		break;

    	case Lift2:
    	{
    		encoderRight.reset();
    		encoderLeft.reset();
    		this.state2 = RoutineOne.MoveBack2;
    	}
		break;

    	case MoveBack2:
    	{
    		if(encoderRight.getDistance() <= -24) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state2 = RoutineOne.stop4;
    		} else {
    			MoveBack();
    		}
    	}
		break;

    	case stop4:
    	{
    		Stop();
    		encoderRight.reset();
    		encoderLeft.reset();
    		this.state2 = RoutineOne.TurnRight2;
    	}
		break;

    	case TurnRight2:
    	{
    		if (encoderLeft.getDistance() >= 17.8678082) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state2 = RoutineOne.MoveForward3;
    		} else {
    			TurnRight();
    	}    	}
		break;

    	case MoveForward3:
    	{
    		if (encoderRight.getDistance() >= 93) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state2 = RoutineOne.stop5;
    		} else {
    			MoveForward();
    		}
    	}
		break;

    	case stop5:
    	{
    		Stop();
    		encoderRight.reset();
    		encoderLeft.reset();

    		this.state2 = RoutineOne.TurnLeft2;
    	}
		break;

    	case TurnLeft2:
    	{
    		if (encoderRight.getDistance() >= 17.8678082) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state2 = RoutineOne.MoveForward4;
    		} else {
    			TurnLeft();
    		}
    	}
		break;

    	case MoveForward4:
    	{
    		if (encoderRight.getDistance() >= 24) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state2 = RoutineOne.Lift3;
    		} else {
    			MoveForward();
    		}
    	}
		break;

    	case Lift3:
    	{
    		encoderRight.reset();
    		encoderLeft.reset();
    		this.state2 = RoutineOne.MoveBackCenter;
    	}
		break;

    	case MoveBackCenter:
    	{
    		if (encoderRight.getDistance() <= -105) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state2 = RoutineOne.stop6;
    		} else {
    			MoveBack();
    		}
    	}
		break;

    	case stop6:
    	{
    		Stop();
    		encoderRight.reset();
    		encoderLeft.reset();
    		this.state2 = RoutineOne.Drop;
    	}
		break;

    	case Drop:
    	{
    		encoderRight.reset();
    		encoderLeft.reset();
    		this.state2 = RoutineOne.MoveBackSafety;
    	}
		break;

    	case MoveBackSafety:
    	{
    		if (encoderRight.getDistance() <= -12) {
        		encoderRight.reset();
        		encoderLeft.reset();
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
    		encoderRight.reset();
    		encoderLeft.reset();
    		this.state3 = RoutineTwo.forward;
    	}
    	break;
    	case forward:
    	{
    		MoveForward();
    		if (encoderRight.getDistance() >= 12) {
    			encoderRight.reset();
    			encoderLeft.reset();
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
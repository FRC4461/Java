package org.usfirst.frc.team4461.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
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
		RoutineOne state1 = RoutineOne.AutoStart;
		RoutineTwo state2 = RoutineTwo.AutoStart;
		RoutineThree state3 = RoutineThree.AutoStart;
		RoutineFour state4 = RoutineFour.AutoStart;
		RoutineFive state5 = RoutineFive.AutoStart;
		RoutineSix state6 = RoutineSix.AutoStart;
		RoutineSeven state7 = RoutineSeven.AutoStart;
		RoutineEight state8 = RoutineEight.AutoStart;
	
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
	//Pneumatics
		public static edu.wpi.first.wpilibj.Solenoid Collector = new edu.wpi.first.wpilibj.Solenoid(1);
		
	//Lift
		public static edu.wpi.first.wpilibj.Victor lifting = new edu.wpi.first.wpilibj.Victor(5);  //Lift Command
		
	//Encoder
		edu.wpi.first.wpilibj.Encoder encoderRight = new edu.wpi.first.wpilibj.Encoder(1, 2);
		edu.wpi.first.wpilibj.Encoder encoderLeft = new edu.wpi.first.wpilibj.Encoder(3, 4);
		public static edu.wpi.first.wpilibj.Encoder liftEncoder = new edu.wpi.first.wpilibj.Encoder(5, 6);   //Lift Command
		
	//Switches and sensors
		edu.wpi.first.wpilibj.DigitalInput limitSwitch = new edu.wpi.first.wpilibj.DigitalInput(5);
	
	//Miscellaneous
		edu.wpi.first.wpilibj.smartdashboard.SmartDashboard SmartDash = new edu.wpi.first.wpilibj.smartdashboard.SmartDashboard();
		private int mode = 1;
		private SendableChooser autoSwitch;
		public static boolean Lift = false;   //Lift Command
		LiftCommand CommandLift;   //Lift Command
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	//Lift
    		CommandLift = new LiftCommand(joystick, 1);   //Lift Command
    	
    	//Encoder
    		encoderRight.setDistancePerPulse(0.006135923);
    		encoderRight.setReverseDirection(true);
    		encoderLeft.setDistancePerPulse(0.006135923);
    		encoderLeft.setReverseDirection(false);
    		liftEncoder.setDistancePerPulse(0.002);
    		liftEncoder.setReverseDirection(true);
		//Smartdashboard
    		/**
    		 * Switches between Autonomous Routines before
    		 * autonomousPeriodic() is called. The SmartDashboard
    		 * contains buttons to be able to switch through
    		 * Routines before Autonomous begins.
    		 */
    		autoSwitch = new SendableChooser();
    		autoSwitch.addDefault("Routine One", 1);
    		autoSwitch.addObject("Routine Two", 2);
    		autoSwitch.addObject("Routine Three", 3);
    		autoSwitch.addObject("Routine Four", 4);
    		autoSwitch.addObject("Routine Five", 5);
    		autoSwitch.addObject("Routine Six", 6);
    		autoSwitch.addObject("Routine Seven", 7);
    		autoSwitch.addObject("Routine Eight", 8);
    		SmartDashboard.putData("Autonomous Routines", autoSwitch);
    
    }
    /**
     * This function is called before atonomous
     */
    public void autonomousInit() {
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
    		RoutineTwoPeriodic();
    	}
    	break;
    	case 3:
    	{
    		RoutineThreePeriodic();
    	}
    	break;
    	case 4:
    	{
    		RoutineFourPeriodic();
    	}
    	break;
    	case 5:
    	{
    		RoutineFivePeriodic();
    	}
    	break;
    	case 6:
    	{
    		RoutineSixPeriodic();
    	}
    	break;
    	case 7:
    	{
    		RoutineSevenPeriodic();
    	}
    	break;
    	case 8:
    	{
    		RoutineEightPeriodic();
    	}
    	break;
    	}
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	CommandLift.Execute();   //LiftCommand
        robotDrive.arcadeDrive(joystick);
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
    	robotDrive.drive(-0.7, 0.0);
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
     * This function tells the robot to reset all encoders as to avaoid
     * disturbances from the switch code when using the encoders for
     * percise lengths. Used within the switch code for the left and
     * right encoders mounted on the motors
     */
    void EncoderReset() {
    	encoderRight.reset();
    	encoderLeft.reset();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void RoutineOnePeriodic() {
    	if (limitSwitch.get() == false) {
    		this.state1 = RoutineOne.TerminateStop;
    	}
    	switch(this.state1) {
    	case AutoStart:
    	{
    		encoderRight.reset();
    		encoderLeft.reset();
    		this.state1 = RoutineOne.Lift1;
    	}
		break;

    	case Lift1:
    	{
    		CommandLift.ExecutePeriodic(true, 13);
    		if(Lift) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state1 = RoutineOne.MoveBack1;
    		}
    	}
		break;

    	case MoveBack1:
    	{
    		if(encoderRight.getDistance() <= -24) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state1 = RoutineOne.stop1;
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

    		this.state1 = RoutineOne.TurnRight1;
    	}
		break;

    	case TurnRight1:
    	{		
    		if (encoderLeft.getDistance() >= 17.8678082) {
    			encoderRight.reset();
        		encoderLeft.reset();
        		this.state1 = RoutineOne.MoveForward1;
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
        		this.state1 = RoutineOne.stop2;
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

    		this.state1 = RoutineOne.TurnLeft1;
    	}
		break;

    	case TurnLeft1:
    	{
    		if (encoderRight.getDistance() >= 17.8678082) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state1 = RoutineOne.MoveForward2;
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
        		this.state1 = RoutineOne.stop3;
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

    		this.state1 = RoutineOne.Lift2;
    	}
		break;

    	case Lift2:
    	{
    		CommandLift.ExecutePeriodic(false, 13);
    		if(Lift) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state1 = RoutineOne.MoveBack2;
    		}
    	}
		break;

    	case MoveBack2:
    	{
    		if(encoderRight.getDistance() <= -24) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state1 = RoutineOne.stop4;
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
    		this.state1 = RoutineOne.TurnRight2;
    	}
		break;

    	case TurnRight2:
    	{
    		if (encoderLeft.getDistance() >= 17.8678082) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state1 = RoutineOne.MoveForward3;
    		} else {
    			TurnRight();
    	}    	}
		break;

    	case MoveForward3:
    	{
    		if (encoderRight.getDistance() >= 93) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state1 = RoutineOne.stop5;
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

    		this.state1 = RoutineOne.TurnLeft2;
    	}
		break;

    	case TurnLeft2:
    	{
    		if (encoderRight.getDistance() >= 17.8678082) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state1 = RoutineOne.MoveForward4;
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
        		this.state1 = RoutineOne.Lift3;
    		} else {
    			MoveForward();
    		}
    	}
		break;

    	case Lift3:
    	{
    		CommandLift.ExecutePeriodic(false, 13);
    		if(Lift) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state1 = RoutineOne.MoveBackCenter;
    		}
    	}
		break;

    	case MoveBackCenter:
    	{
    		if (encoderRight.getDistance() <= -105) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state1 = RoutineOne.stop6;
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
    		this.state1 = RoutineOne.Drop;
    	}
		break;

    	case Drop:
    	{
    		CommandLift.Drop();
    		if(Lift) {
    			EncoderReset();
    			this.state1 = RoutineOne.MoveBackSafety;
    		}
    	}
		break;

    	case MoveBackSafety:
    	{
    		if (encoderRight.getDistance() <= -12) {
        		encoderRight.reset();
        		encoderLeft.reset();
        		this.state1 = RoutineOne.TerminateStop;
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
    public void RoutineTwoPeriodic() {
    	switch(this.state2) {
    	case AutoStart:
    	{
    		EncoderReset();
    		this.state2 = RoutineTwo.R2Lift1;
    	}
    	break;
    	case R2Lift1:
    	{
    		CommandLift.ExecutePeriodic(true, 13);
    		if(Lift) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state2 = RoutineTwo.R2MoveBack1;
    		}
    	}
    	break;
    	
    	case R2MoveBack1:
    	{
    		if(encoderRight.getDistance() <= -24) {
    			EncoderReset();
    			this.state2 = RoutineTwo.R2Stop1;
    		} else {
    			MoveBack();
    		}
    	}
    	break;
    	
    	case R2Stop1:
    	{
    		Stop();
    		EncoderReset();
    		this.state2 = RoutineTwo.R2TurnLeft;     			
    		}
    	break;
    	
    	case R2TurnLeft:
    	{
    		if(encoderRight.getDistance() >= 17.8678082) {
    			EncoderReset();
    			this.state2 = RoutineTwo.R2MoveForward1;
    		} else {
    			TurnLeft();
    		}
    	}
    	break;
    	case R2MoveForward1:
    	{
    		if(encoderRight.getDistance() >=93) {
    			EncoderReset();
    			this.state2 = RoutineTwo.R2Stop2;
    		} else {
    			MoveForward();
    		}
    	}
    	break;
    	case R2Stop2:
    	{
    		Stop();
    		EncoderReset();
    		this.state2 = RoutineTwo.R2TurnRight;
    	}
    	break;
    	case R2TurnRight:
    	{
    		if(encoderLeft.getDistance() >=17.8678082) {
    			EncoderReset();
    			this.state2 = RoutineTwo.R2MoveForward2;
    		} else {
    			TurnRight();
    		}
    	}
    	break;
    	case R2MoveForward2:
    	{
    		if(encoderRight.getDistance() >=24) {
    			EncoderReset();
    			this.state2 = RoutineTwo.R2Stop3;
    		} else {
    			MoveForward();
    		}
    	}
    	break;
    	case R2Stop3:
    	{
    		Stop();
    		EncoderReset();
    		this.state2 = RoutineTwo.R2Lift2;
    	}
    	break;
    	case R2Lift2:
    	{
    		CommandLift.ExecutePeriodic(false, 13);
    		if(Lift) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state2 = RoutineTwo.R2MoveBack2;
    		}
    	}
    	break;
    	case R2MoveBack2:
    	{
    		if(encoderRight.getDistance() <= -105) {
    			EncoderReset();
    			this.state2 = RoutineTwo.R2Stop4;
    		} else {
    			MoveForward();
    		}
    	}
    	break;
    	case R2Stop4:
    	{
    		Stop();
    		EncoderReset();
    		this.state2 = RoutineTwo.R2Drop;
    	}
    	break;
    	case R2Drop:
    	{
    		CommandLift.Drop();
    		if(Lift) {
    			EncoderReset();
    			this.state2 = RoutineTwo.R2MoveBack3;
    		}
    	}
    	break;
    	case R2MoveBack3:
    	{
    		if(encoderRight.getDistance() <= -12) {
    			EncoderReset();
    			this.state2 = RoutineTwo.TerminateStop;
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
    public void RoutineThreePeriodic() {
    	switch (this.state3) {
    	case AutoStart:
    	{
    		EncoderReset();
    		this.state3 = RoutineThree.Lift1;
    	}
    	break;
    	case Lift1:
    	{
    		CommandLift.ExecutePeriodic(true, 13);
    		if(Lift) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state3 = RoutineThree.MoveBack1;
    		}
    	}
    	break;
    	case MoveBack1:
    	{
    		if(encoderRight.getDistance() >= 24) {
    			EncoderReset();
    			this.state3 = RoutineThree.Stop1;
    		}
    	}
    	break;
    	
    	case Stop1:
    	{
    		Stop();
    		EncoderReset();
    		this.state3 = RoutineThree.TurnLeft;

    	}
    	break;
    	
    	case TurnRight:
    	{
    		if(encoderLeft.getDistance() >=17.8678082) {
    			EncoderReset();
    			this.state3 = RoutineThree.MoveForward1;
    		} else {
    			TurnRight();
    		}

    	}
    	break;
    	case MoveForward1:
    	{
    		if(encoderRight.getDistance() >=24) {
    			EncoderReset();
    			this.state3 = RoutineThree.Stop2;
    		} else {
    			MoveForward();
    		}

    	}
    	break;
    	case Stop2:
    	{
    		Stop();
    		EncoderReset();
    		this.state3 = RoutineThree.TurnRight;

    	}
    	break;
    	case TurnLeft:
    	{
    		if(encoderRight.getDistance() >=17.8678082) {
    			EncoderReset();
    			this.state3 = RoutineThree.MoveForward2;
    		} else {
    			TurnLeft();
    		}

    	}
    	break;
    	case MoveForward2:
    	{
    		if(encoderRight.getDistance() >=24) {
    			EncoderReset();
    			this.state3 = RoutineThree.Stop3;
    		} else {
    			MoveForward();
    		}

    	}
    	break;
    	case Stop3:
    	{
    		Stop();
    		EncoderReset();
    		this.state3 = RoutineThree.Lift2;

    	}
    	break;
    	case Lift2:
    	{
    		CommandLift.ExecutePeriodic(false, 13);
    		if(Lift) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state3 = RoutineThree.MoveBack2;
    		}
    	}
    	break;
    	case MoveBack2:
    	{
    		if(encoderRight.getDistance() <= -105) {
    			EncoderReset();
    			this.state3 = RoutineThree.Stop4;
    		} else {
    			MoveBack();
    		}

    	}
    	break;
    	case Stop4:
    	{
    		Stop();
    		EncoderReset();
    		this.state3 = RoutineThree.Drop;

    	}
    	break;
    	case Drop:
    	{
    		CommandLift.Drop();
    		if(Lift) {
    			EncoderReset();
    			this.state3 = RoutineThree.MoveBack3;
    		}
    	}
    	break;
    	case MoveBack3:
    	{
    		if(encoderRight.getDistance() <= -12) {
    			EncoderReset();
    			this.state3 = RoutineThree.TerminateStop;
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
    public void RoutineFourPeriodic() {
    	switch (this.state4) {
    	case AutoStart:
    	{
    		EncoderReset();
    		this.state4 = RoutineFour.Lift;
    	}
    	break;
    	case Lift:
    	{
    		CommandLift.ExecutePeriodic(true, 13);
    		if(Lift) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state4 = RoutineFour.MoveBack1;
    		}
    	}
    	break;
    	case MoveBack1:
    	{
    		EncoderReset();
    		this.state4 = RoutineFour.Stop;
    	}
    	break;
    	case Stop:
    	{
    		EncoderReset();
    		this.state4 = RoutineFour.Drop;
    	}
    	break;
    	case Drop:
    	{
    		CommandLift.Drop();
    		if(Lift) {
    			EncoderReset();
    			this.state4 = RoutineFour.MoveBack2;
    		}
    	}
    	break;
    	case MoveBack2:
    	{
    		EncoderReset();
    		this.state4 = RoutineFour.TerminateStop;
    		
    	}
    	break;
    	case TerminateStop:
    	{
    		Stop();
    	}
    	break;
    	}
    }
    public void RoutineFivePeriodic() {
    	switch (this.state5) {
    	case AutoStart:
    	{
    		EncoderReset();
    		this.state5 = RoutineFive.Lift;
    	}
    	break;
    	case Lift:
    	{
    		CommandLift.ExecutePeriodic(true, 13);
    		if(Lift) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state5 = RoutineFive.MoveBack1;
    		}
    	}
    	break;
    	case MoveBack1:
    	{
    		if(encoderRight.getDistance() <= -24) {
    			EncoderReset();
    			this.state5 = RoutineFive.Stop1;
    		} else {
    			MoveBack();
    		}
    	}
    	break;
    	case Stop1:
    	{
    		Stop();
    		EncoderReset();
    		this.state5 = RoutineFive.TurnLeft;
    	}
    	break;
    	case TurnLeft:
    	{
    		if(encoderRight.getDistance() >= 17.8678082) {
    			EncoderReset();
    			this.state5 = RoutineFive.MoveForward1;
    		} else {
    			TurnLeft();
    		}
    	}
    	break;
    	case MoveForward1:
    	{
    		if(encoderRight.getDistance() >= 93) {
    			EncoderReset();
    			this.state5 = RoutineFive.Stop2;
    		} else {
    			MoveForward();
    		}
    	}
    	break;
    	case Stop2:
    	{
    		Stop();
    		EncoderReset();
    		this.state5 = RoutineFive.TurnRight;
    	}
    	break;
    	case TurnRight:
    	{
    		if(encoderLeft.getDistance() >= 17.8678082) {
    			EncoderReset();
    			this.state5 = RoutineFive.MoveForward2;
    		} else {
    			TurnRight();
    		}
    	}
    	break;
    	case MoveForward2:
    	{
    		if(encoderRight.getDistance() >= 24) {
    			EncoderReset();
    			this.state5 = RoutineFive.Stop3;
    		} else {
    			MoveForward();
    		}
    	}
    	case Stop3:
	    {
    		Stop();
    		EncoderReset();
    		this.state5 = RoutineFive.Lift2;
    	}
    	break;
    	case Lift2:
    	{
    		CommandLift.ExecutePeriodic(false, 13);
    		if(Lift) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state5 = RoutineFive.MoveBack2;
    		}
    	}
    	break;
    	case MoveBack2:
    	{
    		if(encoderRight.getDistance() <= -105) {
    			EncoderReset();
    			this.state5 = RoutineFive.Stop4;
    		} else {
    			MoveBack();
    		}
    	}
    	break;
    	case Stop4:
    	{
    		Stop();
    		EncoderReset();
    		this.state5 = RoutineFive.Drop;
    	}
    	break;
    	case Drop:
    	{
    		CommandLift.Drop();
    		if(Lift) {
    			EncoderReset();
    			this.state5 = RoutineFive.MoveBack3;
    		}
    	}
    	break;
    	case MoveBack3:
    	{
    		if(encoderRight.getDistance() <= -12) {
    			EncoderReset();
    			this.state5 = RoutineFive.TerminateStop;
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
    public void RoutineSixPeriodic() {
    	switch (this.state6) {
    	case AutoStart:
    	{
    		EncoderReset();
    		this.state6 = RoutineSix.MoveBack;
    	}
    	break;
    	case MoveBack:
    	{
    		MoveBack();
    		if (encoderRight.getDistance() <= -60) {
    			EncoderReset();
    			this.state6 = RoutineSix.TerminateStop;
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
    public void RoutineSevenPeriodic() {
    	switch (this.state7) {
    	case AutoStart:
    	{
    		EncoderReset();
    	}
    	break;
    	case PushForward:
    	{
    		MoveForward();
    		if (encoderRight.getDistance() >= 107) {
        		EncoderReset();
        		this.state7 = RoutineSeven.TerminateStop;
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
    public void RoutineEightPeriodic() {
    	switch (this.state8) {
    	case AutoStart:
    	{
    		EncoderReset();
    		this.state8 = RoutineEight.Lift1;
    	}
    	break;
    	case Lift1:
    	{
    		CommandLift.ExecutePeriodic(true, 13);
    		if(Lift) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state8 = RoutineEight.reverse1;
    		}
    	}
    	break;
    	case reverse1:
    	{
    		if(encoderRight.getDistance() <= -24) {
    			EncoderReset();
    			this.state8 = RoutineEight.stop1;
    		} else {
    			MoveBack();
    		}
    	}
    	break;
    	case stop1:
    	{
    		Stop();
    		EncoderReset();
    		this.state8 = RoutineEight.TurnLeft1;
    	}
    	break;
    	case TurnLeft1:
    	{
    		if (encoderRight.getDistance() >= 17.8678082) {
    			EncoderReset();
    			this.state8 = RoutineEight.forward1;
    		} else {
    			TurnLeft();
    		}
    	}
    	break;
    	case forward1:
    	{
    		if (encoderRight.getDistance() >= 93) {
    			EncoderReset();
    			this.state8 = RoutineEight.stop2;
    		} else {
    			MoveForward();
    		}
    	}
    	break;
    	case stop2:
    	{
    		Stop();
    		EncoderReset();
    		this.state8 = RoutineEight.TurnRight1;
    	}
    	break;
    	case TurnRight1:
    	{
    		if (encoderLeft.getDistance() >= 17.8678082) {
    		EncoderReset();
    		this.state8 = RoutineEight.forward2;
    		} else {
    			TurnRight();
    		}
    	}
    	break;
    	case forward2:
    	{
    		if (encoderRight.getDistance() >= 24) {
    		EncoderReset();
    		this.state8 = RoutineEight.stop3;
    		} else {
    			MoveForward();
    		}
    	} 
    	break;
    	case stop3:
    	{
    		Stop();
    		EncoderReset();
    		this.state8 = RoutineEight.Lift2;
    	}
    	break;
    	case Lift2:
    	{
    		CommandLift.ExecutePeriodic(false, 13);
    		if(Lift) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state8 = RoutineEight.reverse2;
    		}
    	}
    	break;
    	case reverse2:
    	{
    		if (encoderRight.getDistance() <= -24) {
    		EncoderReset();
    		this.state8 = RoutineEight.stop4;
    		} else {
    			MoveBack();
    		}
    	}
    	break;
    	case stop4:
    	{
    		Stop();
    		EncoderReset();
    		this.state8 = RoutineEight.TurnLeft2;
    	}
    	break;
    	case TurnLeft2:
    	{
    		if (encoderRight.getDistance() >= 17.8678082) {
    		EncoderReset();
    		this.state8 = RoutineEight.forward3;
    		} else {
    			TurnLeft();
    		}
    	}
    	break;
    	case forward3:
    	{
    		if (encoderRight.getDistance() >= 93) {
    		EncoderReset();
    		this.state8 = RoutineEight.stop5;
    		} else {
    			MoveForward();
    		}
    	}
    	break;
    	case stop5:
    	{
    		Stop();
    		EncoderReset();
    		this.state8 = RoutineEight.TurnRight2;
    	}
    	break;
    	case TurnRight2:
    	{
    		if (encoderLeft.getDistance() >= 17.8678082) {
    		EncoderReset();
    		this.state8 = RoutineEight.forward4;
    		} else {
    			TurnRight();
    		}
    	}
    	break;
    	case forward4:
    	{
    		if (encoderRight.getDistance() >= 24) {
    		EncoderReset();
    		this.state8 = RoutineEight.stop6;
    		} else {
    			MoveForward();
    		}
    	}
    	break;
    	case stop6:
    	{
    		Stop();
    		EncoderReset();
    		this.state8 = RoutineEight.Lift3;
    	} 
    	break;
    	case Lift3:
    	{
    		CommandLift.ExecutePeriodic(false, 13);
    		if(Lift) {
    			encoderRight.reset();
    			encoderLeft.reset();
    			this.state8 = RoutineEight.reverse3;
    		}
    	}
    	break;
    	case reverse3:
    	{
    		if (encoderRight.getDistance() <= -105) {
    		EncoderReset();
    		this.state8 = RoutineEight.stop7;
    		} else {
    			MoveBack();
    		}
    	}
    	break;
    	case stop7:
    	{
    		Stop();
    		EncoderReset();
    		this.state8 = RoutineEight.Drop;
    	}
    	break;
    	case Drop:
    	{
    		CommandLift.Drop();
    		if(Lift) {
    			EncoderReset();
    			this.state8 = RoutineEight.reverse4;
    		}
    	}
    	break;
    	case reverse4:
    	{ 
    		if (encoderRight.getDistance() <= -12) {
    		EncoderReset();
    		this.state8 = RoutineEight.AutoStop;
    		} else {
    			MoveBack();
    		}
    	}
    	break;
    	case AutoStop:
    	{
    		Stop();
    	}
    	break;
    	} 
    }
    
}

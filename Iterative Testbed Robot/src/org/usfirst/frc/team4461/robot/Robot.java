
package org.usfirst.frc.team4461.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Victor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	edu.wpi.first.wpilibj.Joystick j0 = new edu.wpi.first.wpilibj.Joystick(0);
	edu.wpi.first.wpilibj.Victor v1 = new Victor(1);
	edu.wpi.first.wpilibj.Victor v2 = new Victor(2);
	edu.wpi.first.wpilibj.Victor v3 = new Victor(3);
	edu.wpi.first.wpilibj.Victor v4 = new Victor(4);
	edu.wpi.first.wpilibj.RobotDrive drive = new edu.wpi.first.wpilibj.RobotDrive(v4, v3, v1, v2);
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	edu.wpi.first.wpilibj.PowerDistributionPanel pdp = new PowerDistributionPanel();
    	pdp.clearStickyFaults();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        this.drive.arcadeDrive(j0);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

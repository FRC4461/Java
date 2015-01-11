Getting Started
====

This first lesson will just be about getting up and running with the robot programming systems and GitHub. It is incredibly important that you read all of the linked to information and digest it as best you can as this will be the foundation for all future programming work.

1. Start by reading the documentation on [Installing Eclipse](https://wpilib.screenstepslive.com/s/4485/m/13809/l/145002-installing-eclipse-c-java) and continue following the instructions on each continuing page until you reach "Installing Java 8 on the RoboRIO".
- DO NOT register the vision module until we've activated it on the classmate PC in case that is a single computer license.
- DO NOT follow the instructions on "Installing Java 8 on the RoboRIO" until instructed below
2. Now that you've got your software installed on your machine, let's set up the RoboRIO. These steps only need to be done once per RoboRIO so ask someone if the RoboRIO has already been set up before performing these steps. Ensure that your computer is plugged in, that the robot battery is charged and that no one will accidentally interrupt this process. ***Messing this up can ruin the RoboRIO!***
3. Start by following the steps on [Updating your RoboRIO Firmware](https://wpilib.screenstepslive.com/s/4485/m/13503/l/273817-updating-your-roborio-firmware).  You'll need to plug the RoboRIO in to your laptop via USB to do this process.
4. Once done with that, move on to [Imaging your RoboRUI](https://wpilib.screenstepslive.com/s/4485/m/13503/l/144984-imaging-your-roborio). If the imaging tool seems to never finish getting the current state from the RoboRIO, reboot your laptop.
5. Finally, if all has gone well you're ready to [Install Java on to the RoboRIO](https://wpilib.screenstepslive.com/s/4485/m/13809/l/288822-installing-java-8-on-the-roborio-using-the-frc-roborio-java-installer-java-only).
6. To validate that everything has been installed correctly and is working correctly, load up the "Iterative Testbed Robot" from our GitHub project and run it on the robot. Make sure you've followed the instructions below on using GitHub and have the repository saved locally. Then open Eclipse which you should have installed to C:\Program Files (x86?)\Eclipse in the above steps. Using Eclipse, go to file -> Import, choose "Git" -> "Projects from Git". Then hit next, choose "Use existing local repository", hit next, then click add and browse to where you've locally sunk down the Git repository. Clicking next and finish to the rest of the dialog should import your projects properly. Once the project is open, right click on "Iterative Testbed Robot" and go to Run As -> WPILib Java Deploy.
7. Now that the code is on the robot, boot the driver station (you can find this in the start menu) and, while following correct safety protocols, click Enable robot while you have a joystick connected to your laptop. When you move the joystick, the wheels should spin. If this does not work, consult the electrical team as to how it is wired and look in the source code for the project to see if any of the motor numbers are incorrect.

Congratulations! You've now gotten code running on the robot!



Using GitHub
====
Go to GitHub.com and log in with your account. Then go to https://github.com/FRC4461/Java and click the "Clone in Desktop" button one the right side of the pane. It may propmpt you to install the GitHub desktop software if you don't already have it installed. If that is the case, go ahead and install it. Once the "Clone in Desktop" works, you'll be prompted for where you want to locally save the Git repository on your machine. Choose a path or use the default and click OK. You now have the repository locally on your computer.

Making changes to the GitHub repository
====
1. Go to the local folder where GitHub saved the Git repository locally (what you chose above, by default this will be in your Documents folder in another folder named GitHub)
2. Go to the Java\Lessons\Lesson 1 folder and create a new text file named "YourName.txt"
3. Open up the GitHub Desktop application (found in your start menu) and click on the Java repository on the left side. You'll see "Uncommitted changes" on the right.
4. Click on "Uncommited changes" to show the list and ensure the only change is your new text file. If anything else is there uncheck it.
5. Now we're going to "commit" your change. This is the act of checking in your change locally. It is important to create changes for each substantive change you make as this will be easy to view later and even undo if it turns out that change introduces a problem. If your changes are too big then they can be hard to understand or undo, but if they're too small then they don't represent a full unit of work.
6. Add a description in the boxes that showed up under "Uncommited changes" when you clicked on it. Then click commit.
7. NOTE: committing only saves your changes locally. You have not pushed these changes to github.com yet and they are not available to other users yet!
8. Now we're going to sync your commits with GitHub.com. Note that any changes you have locally that are not in a commit will NOT be sunk with GitHub.com. Always commit before syncing if you want to include all of your changes. You can also sync without having any commits to just pull down changes from other users.
9. Click "Sync" in the upper-right of the GitHub client. The sync button should have the number 1 on it to indicate that you have one commit to sync. Wait until it finishes and then go to https://github.com/FRC4461/Java and verify your change showed up.

Congratulations! You can now use GitHub to manage your changes!
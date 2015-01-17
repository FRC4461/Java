State Machines
====

Read about state machines on http://en.wikipedia.org/wiki/Finite-state_machine.
Create a state machine to represent an autonomous period where the robot moves forward 5 feet, turns left 90 degrees, and then moves forward another 5 feet
  Hint: States can repeat until a specific condition is met (e.g. distance or time)
  Hint: Since we don't have a measurement of distance traveled given to us by the robot, what can we use to approximate the distance?
Now implement the state machine in code
  Create an enum to track what state you're currently in
  Use a switch statement to branch the code for the different states
  Don't forget to go back to an idle state when the routine finishes

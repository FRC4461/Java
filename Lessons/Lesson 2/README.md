Creating a new project
====

For this lesson you'll simply be following along with the instructions in the following pages:
- [Creating Your Benchtop Program](https://wpilib.screenstepslive.com/s/4485/m/13809/l/145307-creating-your-benchtop-test-program) - NOTE: this code is only example code and the competition code should not follow the practices or design in this code!
- [Building and Downloading a Robot Program](https://wpilib.screenstepslive.com/s/4485/m/13809/l/242586-building-and-downloading-a-robot-project-to-the-roborio) - Download the program you created in the first step to the robot
- [Using Riolog](https://wpilib.screenstepslive.com/s/4485/m/13809/l/284333-using-riolog-to-view-console-output) - See below for more information on this step


Using Riolog (or Adding Debugging and Diagnostics to Your Code)
====

After reading the above section on Riolog, take a look at your code from the benchtop program and think of some valuable tracing outputs you might want to have. If a programming mentor is around, discuss what you would want to trace and why it would be helpful in figuring out what is not working correctly.

Now add logging to the code that will output the current joystick X and Y positions on each iteration.  To do so, you'll need to use 'System.out.println()'.

```java
  System.out.println("Joystick X: " + <Code to get joystick X axis> + ", Y: " + <Code to get joystick Y axis>);
```

You will have to discover the code to get the X and Y axis yourself. You'll not that when you type the name of a variable followed by a dot you'll get a menu that lists the properties and functions that exist on that object. If you followed the steps above exactly, you should have a 'Joystick' object by the name of 'stick'.

Now deploy that code to the robot, launch Riolog and ensure that your logging is working correctly.


A Brief Primer on Java Code
====

In programming we use terms that may sound like a foreign language at first, but after some time with them, they'll become second nature. I don't have the space to add a definition for every term you'll come across, but as always, ask a programming mentor or google if you have any questions.

Most Java programs, including the ones we'll be writing for our robot, are made up of classes. A 'class' is a representation of a type of object and contains 'properties' and 'methods' that make sense for a given type of object. You can think of a class as something generic such as "person". A person class might have a few properties such as gender, height, weight, etc... It also might have a few methods on it such as "sitDown()" or "goToSchool()". You'll note that properties are attributes of the object or things that would describe a person whereas methods are usually things that can be done to the person or that the person can do. When we start to talk about an individual we refer to them as an instance of a class. Sometimes this is also referred to as an object. Where the class is a general description of the properties and methods any given person will have, an instance will have actual values assigned to those properties and will represent one person specifically. The following code first defines a person and then creates an instance:

'''java
public class Person {
  // This is a constructor. A constructor has a different defenition than other methods as it does not have a return type.
  // Instead, it is intended to do the initial population of the properties in a class instance.
  public Person(string name) {
    // "this" always refers to the current instance of a given class. To access variables and methods that belong to the current instance you preface them with "this."
    this.name = name;
  }
  
  public string getName() {
    // The return statement gives a value back out of a method. The method is defined as returning as string in the line above so we must return a string or the code will not compile
    return this.name;
  }
  
  // This is a property of the class, but it is declared private. This means that no one outside of the methods within this class can directly set or read its value.
  private string name;
}
'''

Then in some other code elsewhere you can create an instance of a person:

'''java
Person tim = new Person("Tim");
System.out.println(tim.getName());
'''

This will create a new instance of the Person class and assign that instance to a variable named tim. The private name property of the class is set to "Tim" in the constructor and then retrieved using the method "getName()";

In our work with the robot we will have many classes representing different components and functions of the robot. For instance, every motor controller, joystick, solenoid or other device on the robot is represented by an instance of a class. The type of class chosen will be one that matches the physical component such as 'Joystick'. We'll also have classes for collections of components such as 'DriveBase', 'PickupSystem' or 'Autonomous' routines.

Try creating a class of your own with methods and properties. In your testbed code from the beginning of this lesson, set some properties on an instance of the class and call some properties ensuring that you log out results to the Riolog to confirm it works correctly.
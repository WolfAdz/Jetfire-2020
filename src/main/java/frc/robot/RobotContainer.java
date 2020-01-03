/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Constants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Joysticks
  private  XboxController Driver = new XboxController(Constants.Driver);
  private  XboxController Controller = new XboxController(Constants.Controller);

  // Button Setup
  private JoystickButton d_A = new JoystickButton(Driver, Button.kA.value);
  private JoystickButton d_B = new JoystickButton(Driver, Button.kB.value);
  private JoystickButton d_X = new JoystickButton(Driver, Button.kX.value);
  private JoystickButton d_Y = new JoystickButton(Driver, Button.kY.value);
  private JoystickButton d_LB = new JoystickButton(Driver, Button.kBumperLeft.value);
  private JoystickButton d_RB = new JoystickButton(Driver, Button.kBumperRight.value);
  private JoystickButton d_LJC = new JoystickButton(Driver, Button.kStickLeft.value);
  private JoystickButton d_RJC = new JoystickButton(Driver, Button.kStickRight.value);
  private JoystickButton d_Select = new JoystickButton(Driver, Button.kBack.value);
  private JoystickButton d_Start = new JoystickButton(Driver, Button.kStart.value);
  private POVButton d_Pad0 = new POVButton(Driver, 0);
  private POVButton d_Pad90 = new POVButton(Driver, 90);
  private POVButton d_Pad180 = new POVButton(Driver, 180);
  private POVButton d_Pad270 = new POVButton(Driver, 270);

  private JoystickButton c_A = new JoystickButton(Controller, Button.kA.value);
  private JoystickButton c_B = new JoystickButton(Controller, Button.kB.value);
  private JoystickButton c_X = new JoystickButton(Controller, Button.kX.value);
  private JoystickButton c_Y = new JoystickButton(Controller, Button.kY.value);
  private JoystickButton c_LB = new JoystickButton(Controller, Button.kBumperLeft.value);
  private JoystickButton c_RB = new JoystickButton(Controller, Button.kBumperRight.value);
  private JoystickButton c_LJC = new JoystickButton(Controller, Button.kStickLeft.value);
  private JoystickButton c_RJC = new JoystickButton(Controller, Button.kStickRight.value);
  private JoystickButton c_Select = new JoystickButton(Controller, Button.kBack.value);
  private JoystickButton c_Start = new JoystickButton(Controller, Button.kStart.value);


  // The robot's subsystems and commands are defined here...
  private final Rollers s_Rollers = new Rollers();
  private final Chassis s_Chassis = new Chassis();
  private final Elevator s_Elevator = new Elevator();
  private final Pneumatics s_Pneumatics = new Pneumatics();
  private final Misc s_Misc = new Misc();

  private final ComplexAuto c_Auto = new ComplexAuto(s_Chassis, s_Rollers);
  private final DefaultDrive c_Drive = new DefaultDrive(s_Chassis, 
                                            Driver.getRawAxis(1), Driver.getRawAxis(4));

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    s_Chassis.setDefaultCommand(c_Drive);
    s_Rollers.setDefaultCommand(new InstantCommand(s_Rollers::RollStop, s_Rollers));
    s_Elevator.setDefaultCommand(new InstantCommand(s_Elevator::ElevatorStop, s_Elevator));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Driver Controls
    d_A.whileHeld(new DriveStraight(s_Chassis, Driver.getRawAxis(1)));
    //d_Start.whileHeld(new Turn(s_Chassis, Robot.VisionAngle));
    d_LB.whenPressed(new ServoSet(s_Misc, 90));
    d_RB.whenPressed(new ServoSet(s_Misc, 180));
    d_LJC.whenPressed(new InstantCommand(s_Pneumatics::DockShift, s_Pneumatics));
    d_LJC.whenPressed(new InstantCommand(s_Misc::Leds, s_Misc));
    d_Pad0.whenPressed(new Turn(s_Chassis, 0));
    d_Pad90.whenPressed(new Turn(s_Chassis, 90));
    d_Pad180.whenPressed(new Turn(s_Chassis, 180));
    d_Pad270.whenPressed(new Turn(s_Chassis, 270));
    
    // Controller Controls
    c_A.whileHeld(new RunCommand(s_Rollers::RollIn, s_Rollers));
    c_Y.whenPressed(new RunCommand(s_Rollers::RollIn, s_Rollers));
    c_B.whenPressed(new RollInCommand(s_Rollers));
    c_X.whenPressed(new RollInInstantCommand(s_Rollers));
    c_LB.whenPressed(new InstantCommand(s_Pneumatics::Flor, s_Pneumatics));
    c_RB.whenPressed(new InstantCommand(s_Pneumatics::Flor, s_Pneumatics));
    c_LJC.whileHeld(new RunCommand(s_Elevator::ElevatorUp, s_Elevator));
    c_RJC.whileHeld(new RunCommand(s_Elevator::ElevatorDown, s_Elevator));
    c_Select.whileHeld(new RunCommand(s_Elevator::ElevatorStatic, s_Elevator));
  }


  /* Use this to pass the autonomous command to the main {@link Robot} class.
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return c_Auto;
  }
}

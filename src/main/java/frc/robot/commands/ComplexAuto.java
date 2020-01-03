/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Rollers;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ComplexAuto extends SequentialCommandGroup {

  public ComplexAuto(Chassis DriveSubsystem, Rollers RollersSubsystem) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(

      new StartEndCommand(
        () -> DriveSubsystem.ArcadeDrive(0.8, 0), 
        () -> DriveSubsystem.ArcadeDrive(0, 0), DriveSubsystem)
        .beforeStarting(DriveSubsystem::ResetEncoders)
        .withInterrupt(()-> DriveSubsystem.getEncoderAverage() >= 5),
      new Turn(DriveSubsystem, 45),
      new StartEndCommand(
        () -> DriveSubsystem.ArcadeDrive(0.8, 0), 
        () -> DriveSubsystem.ArcadeDrive(0, 0), DriveSubsystem)
        .beforeStarting(DriveSubsystem::ResetEncoders)
        .withInterrupt(()-> DriveSubsystem.getEncoderAverage() >= 2),
        new Turn(DriveSubsystem, -45),
        new RollInInstantCommand(RollersSubsystem)
    );
  }
}

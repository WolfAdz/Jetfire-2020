/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Misc;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ServoSet extends InstantCommand {

  private final Misc s_Misc;
  private final double angle;

  public ServoSet(Misc subsystem, double ang) {
    // Use addRequirements() here to declare subsystem dependencies.
    s_Misc = subsystem;
    angle = ang;
    addRequirements(s_Misc);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    s_Misc.ServoSet(angle);
  }
}

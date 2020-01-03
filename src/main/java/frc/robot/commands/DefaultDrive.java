/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class DefaultDrive extends CommandBase {
 
  private final Chassis s_Chassis;
  private final double v_Fwd;
  private final double v_Rot;

  public DefaultDrive(Chassis subsystem, double fwd, double rot) {
    // Use addRequirements() here to declare subsystem dependencies.
    s_Chassis = subsystem;
    v_Fwd = fwd;
    v_Rot = rot;
    addRequirements(s_Chassis);
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    s_Chassis.Drive(v_Fwd, v_Rot);
  }
}

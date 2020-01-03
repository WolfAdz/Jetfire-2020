/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Rollers extends SubsystemBase {

  private final PWMVictorSPX RollerLeft = new PWMVictorSPX(Constants.RollerLeftPort);
  private final PWMVictorSPX RollerRight = new PWMVictorSPX(Constants.RollerRightPort);

    public void RollIn() {
      RollerLeft.set(-0.3);
      RollerRight.set(0.3);
    }
    
    public void RollOut() {
      RollerLeft.set(0.75);
      RollerRight.set(-0.75);
    }

    public void RollLeft() {
      RollerLeft.set(0.6);
      RollerRight.set(0.35);
    }

    public void RollRight() {
      RollerLeft.set(-0.35);
      RollerRight.set(-0.6);
    }

    public void RollStop() {
      RollerLeft.set(0);
      RollerRight.set(0);
    }

  @Override
  public void periodic() {
    RollerLeft.set(0);
    RollerRight.set(0);
  }
}

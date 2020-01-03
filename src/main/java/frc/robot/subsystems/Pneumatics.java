/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pneumatics extends SubsystemBase {

  // Solenoid Setup
  private final DoubleSolenoid FlorSolenoid = new DoubleSolenoid(
                                  Constants.FlorPort[0], Constants.FlorPort[1]);
  private final DoubleSolenoid PistonSolenoid = new DoubleSolenoid(
                                  Constants.PistonPort[0], Constants.PistonPort[1]);
  private final DoubleSolenoid DockShiftSolenoid = new DoubleSolenoid(
                                  Constants.DockShiftPort[0], Constants.DockShiftPort[1]);

  // Variables
  private static boolean shift = false;
  private static boolean flor = false;
  private static boolean pist = false;

  public void Flor() {
    if (!flor) {
      FlorSolenoid.set(Value.kForward);
      flor = true;
    } else if (flor) {
      FlorSolenoid.set(Value.kReverse);
      flor = false;
    } else {
      FlorSolenoid.set(Value.kOff);
      flor = false;
    }
  }

  public void Piston() {
    if (!pist) {
      PistonSolenoid.set(Value.kForward);
      pist = true;
    } else if (pist) {
      PistonSolenoid.set(Value.kReverse);
      pist = false;
    } else {
      PistonSolenoid.set(Value.kOff);
      pist = false;
    }
  }

  public void DockShift() {
    if (!shift) {
      DockShiftSolenoid.set(Value.kForward);
      shift = true;
    } else if (shift) {
      DockShiftSolenoid.set(Value.kReverse);
      shift = false;
    } else {
      DockShiftSolenoid.set(Value.kOff);
      shift = false;
    }
  }

  public static boolean getFlorStatus() {
    return flor;
  }

  public static boolean getPistonStatus() {
    return pist;
  }

  public static boolean getDockShiftStatus() {
    return shift;
  }

  public void resetSolenoids() {
    FlorSolenoid.set(Value.kReverse);
    PistonSolenoid.set(Value.kReverse);
    DockShiftSolenoid.set(Value.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

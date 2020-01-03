/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {

  private final PWMVictorSPX ElevatorLeft = new PWMVictorSPX(Constants.ElevatorLeftPort);
  private final PWMVictorSPX ElevatorRight = new PWMVictorSPX(Constants.ElevatorRightPort);
  private final Encoder ElevatorEncoder = new Encoder(Constants.ElevatorEncoder[0], 
                                              Constants.ElevatorEncoder[1], false, EncodingType.k4X);

  public Elevator() {
    ElevatorEncoder.setDistancePerPulse(Constants.TickPerRevElevator);
  }

  public void ElevatorUp() {
    ElevatorLeft.set(0.7);
    ElevatorRight.set(-0.7);
  }

  public void ElevatorDown() {
    ElevatorLeft.set(-0.7);
    ElevatorRight.set(0.7);
  }

  public void ElevatorStatic() {
    ElevatorLeft.set(0.3);
    ElevatorRight.set(-0.3);
  }

  public void ElevatorStop() {
    ElevatorLeft.set(0);
    ElevatorRight.set(0);
  }

  public void ResetEncoder() {
    ElevatorEncoder.reset();
  }

  public void getEncoderValue() {
    ElevatorEncoder.getDistance();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

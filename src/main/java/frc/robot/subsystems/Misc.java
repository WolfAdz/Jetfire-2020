/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Misc extends SubsystemBase {

  private final Relay LEDRelay = new Relay(Constants.LEDToggle);
  private final Servo CameraTurret = new Servo(Constants.CameraTurret);

  private boolean relay = false;

    // Camera Controls
    public void ServoSet(double angle) {
      CameraTurret.setAngle(angle);
    }

    public void ServoStop() {
      CameraTurret.set(0);
   }
   
   // LED Controls
   public void Leds() {
    if (!relay) {
      LEDRelay.set(Relay.Value.kForward);
      relay = true;
    } else if (relay) {
      LEDRelay.set(Relay.Value.kOff);  
      relay = false;
    } 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

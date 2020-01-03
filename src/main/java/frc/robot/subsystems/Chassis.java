/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class Chassis extends PIDSubsystem {

  // Differential Drive Setup
  private final DifferentialDrive RobotDrive = new DifferentialDrive(
                  new PWMVictorSPX(Constants.ChassisLeftPort), new PWMVictorSPX(Constants.ChassisRightPort));

  // NavX Setup
  private final static AHRS NavX = new AHRS(SPI.Port.kMXP);

  // Encoder Setup
  private final static Encoder LeftEncoder = new Encoder(
    Constants.ChassisLeftEncoder[0], Constants.ChassisLeftEncoder[1], false, EncodingType.k4X);

  private final static Encoder RightEncoder = new Encoder(
    Constants.ChassisRightEncoder[0], Constants.ChassisRightEncoder[1], false, EncodingType.k4X);
  
  // Variables
  private double RotationValue;
  private double previousX = 0;
  private double dx = 0.3;
  private double previousY = 0;
  private double dy = 0.3;
  private double maxX = 0.85;
  private double maxY = 0.95;

  public Chassis() {
    super(
        // The PIDController used by the subsystem
        new PIDController(Constants.kP, Constants.kI, Constants.kD));
        getController().setTolerance(2.0f);
        getController().enableContinuousInput(-180f, 180f);
        disable();

        LeftEncoder.setDistancePerPulse(Constants.TickPerRevChassis);
        RightEncoder.setDistancePerPulse(Constants.TickPerRevChassis);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    RotationValue = output;
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return NavX.getYaw();
  }

  // Teleop Drive
  public void Drive(double fwd, double rot) {
    // Restict Y
    double y = fwd * maxY;
    if (y > previousY + dy) {
      y = previousY + dy;
    } else if (y < previousY - dy) {
      y = previousY - dy;
    }
    previousY = y;
    // Restrict X
    double x = rot * maxX;
    if (x > previousX + dx) {
      x = previousX + dx;
    } else if (x < previousX - dx) {
      x = previousX - dx;
    }
    previousX = x;

    RobotDrive.arcadeDrive(-y, x);
    disable();
    }

    public void ArcadeDrive(double fwd, double rot) {
      RobotDrive.arcadeDrive(-fwd, rot);
    }

    public void TurnToAngle(double setpoint) {
      if (!isEnabled()) {
        RotationValue = 0;
        getController().setSetpoint(setpoint);
        enable();
      }
      double rotate = RotationValue;
      RobotDrive.arcadeDrive(0, rotate*maxY);
    }

    public void DriveStraight(double fwd) {
      if (!isEnabled()) {
        RotationValue = 0;
        getController().setSetpoint(NavX.getYaw());
        enable();
      }
      double rotate = RotationValue;
      RobotDrive.arcadeDrive(fwd*maxY, rotate*maxX);
    } 

    public static double getYaw() {
      return NavX.getYaw();
    }

    public static double getEncoderValueLeft() {
      return LeftEncoder.getDistance();
    }

    public static double getEncoderValueRight() {
      return RightEncoder.getDistance();
    }

    public double getEncoderAverage() {
      return (RightEncoder.getDistance() + LeftEncoder.getDistance()) / 2;
    }

    public void ResetEncoders() {
      LeftEncoder.reset();
      RightEncoder.reset();
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
}
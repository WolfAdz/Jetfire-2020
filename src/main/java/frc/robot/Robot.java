/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  Compressor Comp = new Compressor();
  public static NetworkTableEntry yaw_angle;
  public double VisionAngle;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

      // Network Table Setup
      NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
      NetworkTable table = ntinst.getTable("tablaCool");
      yaw_angle = table.getEntry("YawAngle");
      yaw_angle.setDefaultDouble(0);

    // Compressor Control
    Comp.setClosedLoopControl(true);

    // Camera Streaming Configuration
    new Thread(() ->{
      UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
      camera.setResolution(320, 240 );
      UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(1);
      camera1.setResolution(320, 240);

      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream  = CameraServer.getInstance().putVideo("Blur", 640, 480);

      Mat source = new Mat();
      Mat output = new Mat();
      while (!Thread.interrupted()) {
        cvSink.grabFrame(source);
        Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
        outputStream.putFrame(output);
      }
    }).start();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    VisionAngle = Chassis.getYaw() - Robot.yaw_angle.getDouble(0.0);

    // Dashboard Widgets
    SmartDashboard.putNumber("Yaw Angle", Chassis.getYaw());
    SmartDashboard.putBoolean("Flor Status", Pneumatics.getFlorStatus());
    SmartDashboard.putBoolean("Pist Status", Pneumatics.getPistonStatus());
    SmartDashboard.putBoolean("DockShift Status", Pneumatics.getDockShiftStatus());
    /*SmartDashboard.putNumber("Enc Left", Chassis.getEncoderValueLeft());
    SmartDashboard.putNumber("Enc Right", Chassis.getEncoderValueRight());
    SmartDashboard.putNumber("Encoder Average", Chassis.getEncoderAverage());
    */
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}

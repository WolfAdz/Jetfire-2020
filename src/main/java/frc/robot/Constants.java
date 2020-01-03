/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // PWM Ports
    public static int ChassisLeftPort = 0;
    public static int ChassisRightPort = 1;
    public static int RollerLeftPort = 2;
    public static int RollerRightPort = 3;
    public static int CameraTurret = 4;
    public static int ElevatorLeftPort = 5;
    public static int ElevatorRightPort = 6;
    
    // Pneumatic Ports
    public static int[] FlorPort = new int[]{0, 1};
    public static int[] PistonPort = new int[]{2, 3};
    public static int[] DockShiftPort = new int[]{4, 5};

    // DIO Ports
    public static int[] ChassisLeftEncoder = new int[]{0, 1};
    public static int[] ChassisRightEncoder = new int[]{2, 3};
    public static int[] ElevatorEncoder = new int[]{4, 5};

    // Joystick Ports
    public static int Driver = 0;
    public static int Controller = 1;

    // Relay Ports
    public static int LEDToggle = 0;

    // Constants
    public static double TickPerRevChassis = (0.77272727266)*(12.5663706144)/360;
    public static double TickPerRevElevator = 0.07696902001;
    public static double kP = 0;
    public static double kI = 0;
    public static double kD = 0;
}

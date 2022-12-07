// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

import frc.robot.Constants.kIO;

import frc.robot.commands.TankDriveCommand;
import frc.robot.commands.RaiseArmCommand;
import frc.robot.commands.LowerArmCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.EjectCommand;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ArmWench;
import frc.robot.subsystems.Intake;

// import frc.robot.commands.AutonomousCommand;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // Creating joysticks
    private final Joystick leftJoy = new Joystick(kIO.LEFT_JOY);
    private final Joystick rightJoy = new Joystick(kIO.RIGHT_JOY);

    private final JoystickButton leftTrigger = new JoystickButton(leftJoy, 1);
    private final JoystickButton rightTrigger = new JoystickButton(rightJoy, 1);
    private final JoystickButton button4 = new JoystickButton(leftJoy, 4);
    private final JoystickButton rightJoystickButton4 = new JoystickButton(rightJoy, 4);
    private final JoystickButton leftJoystickButton4 = new JoystickButton(leftJoy, 4);

    // The robot's subsystems and commands are defined here...
    private final Drivetrain drivetrain = new Drivetrain();
    private final ArmWench armWench = new ArmWench();
    private final Intake intake = new Intake();
   // private final AutonomousCommand autoDrive = new AutonomousCommand(drivetrain);
    // Using a method reference to get the Y axis of the joystick.
    // Different from a normal method call because this sends "instructions" on how
    // to get the value, not the value itself.
    // This is needed to update the command with the joystick's current value.
    private final TankDriveCommand tankDriveCommand = new TankDriveCommand(drivetrain, leftJoy::getY, rightJoy::getY);
    private final RaiseArmCommand raiseArm = new RaiseArmCommand(armWench);
    private final LowerArmCommand lowerArm = new LowerArmCommand(armWench);
    private final IntakeCommand intakeIntake = new IntakeCommand(intake);
    private final EjectCommand eject = new EjectCommand(intake);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // We have to set a default command for the drivetrain so that it'll move when
        // the joystick moves.
        // If we don't do this, then the commmand will not do anything
        drivetrain.setDefaultCommand(tankDriveCommand);
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        rightTrigger.whenHeld(raiseArm);
        leftTrigger.whenHeld(lowerArm);
        rightJoystickButton4.whenHeld(intakeIntake);
        leftJoystickButton4.whenHeld(eject);
        button4.whenHeld(eject);  
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous

        // This drives the robot forward at 30% speed for 3 seconds
        return new RunCommand(() -> drivetrain.drivePercent(0.3, 0.3), drivetrain).withTimeout(3)
               .andThen(drivetrain::stop);

        
    }
}

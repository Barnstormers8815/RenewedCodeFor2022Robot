// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import frc.robot.Constants.kArmMotor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmWench extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private final CANSparkMax armMotor = new CANSparkMax(kArmMotor.ARM_MOTOR, MotorType.kBrushless);

  public ArmWench() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void raiseArm() {
    armMotor.set(kArmMotor.RAISE_ARM_SPEED);
  }

  public void lowerArm() {
    armMotor.set(kArmMotor.LOWER_ARM_SPEED);
  }

  public void stop() {
    armMotor.set(0);
  }
}

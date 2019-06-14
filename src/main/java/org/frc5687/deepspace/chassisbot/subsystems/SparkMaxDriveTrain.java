package org.frc5687.deepspace.chassisbot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import org.frc5687.deepspace.chassisbot.Constants;
import org.frc5687.deepspace.chassisbot.OI;
import org.frc5687.deepspace.chassisbot.Robot;
import org.frc5687.deepspace.chassisbot.RobotMap;
import org.frc5687.deepspace.chassisbot.commands.Drive;

import static org.frc5687.deepspace.chassisbot.Constants.DriveTrain.CREEP_FACTOR;
import static org.frc5687.deepspace.chassisbot.utils.Helpers.applySensitivityFactor;
import static org.frc5687.deepspace.chassisbot.utils.Helpers.limit;

public class SparkMaxDriveTrain extends OutliersSubsystem {
    private CANSparkMax _frontLeftSpark;
    private CANSparkMax _frontRightSpark;
    private CANSparkMax _backLeftSpark;
    private CANSparkMax _backRightSpark;

    private CANEncoder _frontLeftEncoder;
    private CANEncoder _frontRightEncoder;
    private CANEncoder _backLeftEncoder;
    private CANEncoder _backRightEncoder;

    private OI _oi;

    public SparkMaxDriveTrain (Robot robot) {
        info("Constructing VictorSPDriveTrain class.");
        _oi = robot.getOI();

        _frontLeftSpark = new CANSparkMax(RobotMap.CAN.SPARKMAX.LEFT_FRONT_NEO, CANSparkMaxLowLevel.MotorType.kBrushless);
        _backLeftSpark = new CANSparkMax(RobotMap.CAN.SPARKMAX.LEFT_BACK_NEO, CANSparkMaxLowLevel.MotorType.kBrushless);
        _frontRightSpark = new CANSparkMax(RobotMap.CAN.SPARKMAX.RIGHT_FRONT_NEO, CANSparkMaxLowLevel.MotorType.kBrushless);
        _backRightSpark = new CANSparkMax(RobotMap.CAN.SPARKMAX.RIGHT_BACK_NEO, CANSparkMaxLowLevel.MotorType.kBrushless);

        _frontLeftSpark.setInverted(Constants.DriveTrain.LEFT_FRONT_MOTOR_INVERTED);
        _backLeftSpark.setInverted(Constants.DriveTrain.LEFT_BACK_MOTOR_INVERTED);
        _frontRightSpark.setInverted(Constants.DriveTrain.RIGHT_FRONT_MOTOR_INVERTED);
        _backRightSpark.setInverted(Constants.DriveTrain.RIGHT_BACK_MOTOR_INVERTED);

        _frontLeftEncoder = _frontLeftSpark.getEncoder();
        _backLeftEncoder = _backLeftSpark.getEncoder();
        _frontRightEncoder = _frontRightSpark.getEncoder();
        _backRightEncoder = _backRightSpark.getEncoder();
    }


        @Override
    public void updateDashboard() {

    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Drive(this, _oi));
    }

    public void cheesyDrive(double speed, double rotation, boolean creep) {
        metric("Speed", speed);
        metric("Rotation", rotation);

        speed = limit(speed, 1);
        //Shifter.Gear gear = _robot.getShifter().getGear();

        rotation = limit(rotation, 1);

        double leftMotorOutput;
        double rightMotorOutput;

        double maxInput = Math.copySign(Math.max(Math.abs(speed), Math.abs(rotation)), speed);

        if (speed < Constants.DriveTrain.DEADBAND && speed > -Constants.DriveTrain.DEADBAND) {
            metric("Rot/Raw", rotation);
            rotation = applySensitivityFactor(rotation, Constants.DriveTrain.ROTATION_SENSITIVITY);
            if (creep) {
                metric("Rot/Creep", creep);
                rotation = rotation * CREEP_FACTOR;
            }

            metric("Rot/Transformed", rotation);
            leftMotorOutput = rotation;
            rightMotorOutput = -rotation;
            metric("Rot/LeftMotor", leftMotorOutput);
            metric("Rot/RightMotor", rightMotorOutput);
        } else {
            // Square the inputs (while preserving the sign) to increase fine control
            // while permitting full power.
            metric("Str/Raw", speed);
            speed = Math.copySign(applySensitivityFactor(speed, Constants.DriveTrain.SPEED_SENSITIVITY), speed);
            metric("Str/Trans", speed);
            rotation = applySensitivityFactor(rotation, Constants.DriveTrain.TURNING_SENSITIVITY);
            double delta = rotation * Math.abs(speed);
            leftMotorOutput = speed + delta;
            rightMotorOutput = speed - delta;
            metric("Str/LeftMotor", leftMotorOutput);
            metric("Str/RightMotor", rightMotorOutput);
        }

        setPower(limit(leftMotorOutput), limit(rightMotorOutput), true);
    }

    public void setPower(double leftSpeed, double rightSpeed, boolean override) {
        _frontLeftSpark.set(leftSpeed);
        _backLeftSpark.set (leftSpeed);
        _frontRightSpark.set(rightSpeed);
        _backRightSpark.set (rightSpeed);
        metric("Power/Right", rightSpeed);
        metric("Power/Left", leftSpeed);
    }

    public double getLeftPower() {
        return _frontLeftSpark.get();
    }

    public double getRightPower() {
        return _frontRightSpark.get();
    }

    public double getLeftTicks() {return (_frontLeftEncoder.getPosition() + _backLeftEncoder.getPosition()) / 2; }

    public double getRightTicks() { return (_backLeftEncoder.getPosition() + _backRightEncoder.getPosition()) / 2; }

    public double getLeftDistance() { return  getLeftTicks() * Constants.DriveTrain.LEFT_DISTANCE_PER_TICKS; }

    public double getRightDistance() { return  getRightTicks() * Constants.DriveTrain.RIGHT_DISTANCE_PER_TICKS; }

    public double getDistance() { return ( getLeftDistance() + getRightDistance() ) / 2; }




}



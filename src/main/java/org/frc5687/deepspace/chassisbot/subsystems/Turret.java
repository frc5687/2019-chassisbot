package org.frc5687.deepspace.chassisbot.subsystems;


import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import org.frc5687.deepspace.chassisbot.Constants;
import org.frc5687.deepspace.chassisbot.OI;
import org.frc5687.deepspace.chassisbot.Robot;
import org.frc5687.deepspace.chassisbot.RobotMap;
import org.frc5687.deepspace.chassisbot.commands.DriveTurret;
import org.frc5687.deepspace.chassisbot.utils.HallEffect;
import org.frc5687.deepspace.chassisbot.utils.Limelight;
import org.frc5687.deepspace.chassisbot.utils.PoseTracker;

import static org.frc5687.deepspace.chassisbot.Constants.Turret.*;
import static org.frc5687.deepspace.chassisbot.utils.Helpers.limit;

public class Turret extends OutliersSubsystem {

    private OI _oi;
    private PoseTracker _poseTracker;
    private Limelight _limelight;
    private Robot _robot;

    private CANSparkMax _turretSpark;
    private CANEncoder _turretEncoder;

    private HallEffect _turretHall;

    private double _angle0 = 0;

    public Turret(Robot robot) {
        _robot = robot;
        _oi = robot.getOI();
        _limelight = robot.getLimelight();
        _poseTracker = robot.getPoseTracker();

        _turretSpark = new CANSparkMax(RobotMap.CAN.SPARKMAX.TURRET_NEO, CANSparkMaxLowLevel.MotorType.kBrushless);
        _turretSpark.setInverted(Constants.Turret.TURRET_MOTOR_INVERTED);
        _turretEncoder = _turretSpark.getEncoder();
        _turretHall = new HallEffect(RobotMap.DIO.TURRET_HALL);
    }
    @Override
    public void updateDashboard() {
        metric("Turret Angle", getTurrentAngle());
        metric("Turret Position", getTurretPosition());
        metric("Turret Power", getTurretPower());
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveTurret(this, _oi, _limelight, _poseTracker));
    }

    public void enableBreakMode() {
        if (_turretSpark == null) { return; }
        _turretSpark.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void disableBreakMode() {
        if (_turretSpark == null) { return; }
        _turretSpark.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }

    public void setTurretSpeed(double speed) {
        if (_turretSpark == null) { return; }

        speed = limit(speed, MAX_REVERSE_SPEED, MAX_FORWARD_SPEED);
        _turretSpark.set(speed);
    }
    public boolean isHallTriggered() {
        return _turretHall.get();
    }

    public double getTurretPosition() {
        return _turretEncoder.getPosition();
    }

    public double getTurretPower() {return _turretSpark.get(); }

    public void resetTurretEncoder() {
        _angle0 = getTurrentAngle() > MID_TURRET_ANGLE ? MAX_TURRET_ANGLE : MIN_TURRET_ANGLE;
        _turretEncoder.setPosition(0);
    }
    public double getTurrentAngle() {
        return _angle0 + (getTurretPosition() / TICKS_PER_DEGREES);
    }





}

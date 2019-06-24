package org.frc5687.deepspace.chassisbot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.frc5687.deepspace.chassisbot.commands.*;
import org.frc5687.deepspace.chassisbot.subsystems.Shifter;
import org.frc5687.deepspace.chassisbot.utils.AxisButton;
import org.frc5687.deepspace.chassisbot.utils.Gamepad;
import org.frc5687.deepspace.chassisbot.utils.OutliersProxy;
import org.frc5687.deepspace.chassisbot.utils.POV;

import static org.frc5687.deepspace.chassisbot.utils.Helpers.applyDeadband;
import static org.frc5687.deepspace.chassisbot.utils.Helpers.applySensitivityFactor;

public class OI extends OutliersProxy {
    protected Gamepad _driverGamepad;
    protected Button _driverRightStickButton;

    private Button _driverRightBumper;
    private Button _driverLeftBumper;

    private AxisButton _driverRightYAxisUpButton;


    public OI(){
        _driverGamepad = new Gamepad(0);
        _driverRightStickButton = new JoystickButton(_driverGamepad, Gamepad.Buttons.RIGHT_STICK.getNumber());

        _driverRightBumper = new JoystickButton(_driverGamepad, Gamepad.Buttons.RIGHT_BUMPER.getNumber());
        _driverLeftBumper = new JoystickButton(_driverGamepad, Gamepad.Buttons.LEFT_BUMPER.getNumber());


        _driverRightYAxisUpButton = new AxisButton(_driverGamepad,Gamepad.Axes.RIGHT_Y.getNumber(), -.75);

    }


    public void initializeButtons(Robot robot){
        _driverRightBumper.whenPressed(new Shift(robot.getSparkMaxDriveTrain(), robot.getShifter(), Shifter.Gear.LOW, false));
        _driverLeftBumper.whenPressed(new Shift(robot.getSparkMaxDriveTrain(), robot.getShifter(), Shifter.Gear.HIGH, false));
    }

    public boolean isAutoTargetPressed() {
        return _driverRightYAxisUpButton.get();
    }

    public double getDriveSpeed() {
        double speed = -getSpeedFromAxis(_driverGamepad, Gamepad.Axes.LEFT_Y.getNumber());
        speed = applyDeadband(speed, Constants.DriveTrain.DEADBAND);
        return speed;
    }

    public double getDriveRotation() {
        double speed = getSpeedFromAxis(_driverGamepad, Gamepad.Axes.RIGHT_X.getNumber());
        speed = applyDeadband(speed, Constants.DriveTrain.DEADBAND);
        return speed;
    }

    public int getDriverPOV() {
        return POV.fromWPILIbAngle(0, _driverGamepad.getPOV()).getDirectionValue();
    }

    protected double getSpeedFromAxis(Joystick gamepad, int axisNumber) {
        return gamepad.getRawAxis(axisNumber);
    }

    @Override
    public void updateDashboard() {

    }

    private int _driverRumbleCount = 0;
    private long _driverRumbleTime = System.currentTimeMillis();

    public void pulseDriver(int count) {
        // Check to see if we are already rumbling!
        if (_driverRumbleCount > 0) { return; }
        _driverRumbleTime = System.currentTimeMillis() + Constants.OI.RUMBLE_PULSE_TIME;
        _driverRumbleCount = count * 2;
    }


    public void poll() {
        if (_driverRumbleCount > 0) {
            _driverGamepad.setRumble(GenericHID.RumbleType.kLeftRumble, _driverRumbleCount % 2 == 0 ? 0 : 1);
            _driverGamepad.setRumble(GenericHID.RumbleType.kRightRumble, _driverRumbleCount % 2 == 0 ? 0 : 1);
            if (System.currentTimeMillis() > _driverRumbleTime) {
                _driverRumbleTime = System.currentTimeMillis() + Constants.OI.RUMBLE_PULSE_TIME;
                _driverRumbleCount--;
            }
        } else {
            _driverGamepad.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
            _driverGamepad.setRumble(GenericHID.RumbleType.kRightRumble, 0);
        }
    }

    public boolean isKillAllPressed() {
        int driverPOV = getDriverPOV();

        return driverPOV == Constants.OI.KILL_ALL;
    }
    public boolean isOverridePressed() {
        int driverPOV = getDriverPOV();

        return driverPOV == Constants.OI.OVERRIDE;
    }


    public boolean isCreepPressed() {
        return  _driverRightStickButton.get();
    }
}


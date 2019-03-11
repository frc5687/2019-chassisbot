package org.frc5687.deepspace.chassisbot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.frc5687.deepspace.chassisbot.commands.*;
import org.frc5687.deepspace.chassisbot.utils.AxisButton;
import org.frc5687.deepspace.chassisbot.utils.Gamepad;
import org.frc5687.deepspace.chassisbot.utils.OutliersProxy;
import org.frc5687.deepspace.chassisbot.utils.POV;

import static org.frc5687.deepspace.chassisbot.utils.Helpers.applyDeadband;
import static org.frc5687.deepspace.chassisbot.utils.Helpers.applySensitivityFactor;

public class OI extends OutliersProxy {
    protected Gamepad _driverGamepad;
    protected Button _driverRightStickButton;


    public OI(){
        _driverGamepad = new Gamepad(0);
        _driverRightStickButton = new JoystickButton(_driverGamepad, Gamepad.Buttons.RIGHT_STICK.getNumber());
    }

    public void initializeButtons(Robot robot){

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

    protected double getSpeedFromAxis(Joystick gamepad, int axisNumber) {
        return gamepad.getRawAxis(axisNumber);
    }

    @Override
    public void updateDashboard() {

    }


    public void poll() {
    }

    public boolean isCreepPressed() {
        return  _driverRightStickButton.get();
    }
}


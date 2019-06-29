
package org.frc5687.deepspace.chassisbot.commands;

import com.kauailabs.navx.frc.AHRS;
import org.frc5687.deepspace.chassisbot.Robot;
import org.frc5687.deepspace.chassisbot.subsystems.HatchIntake;

public class HoldClawOpen extends OutliersCommand {
    private Robot _robot;
    private HatchIntake _hatchIntake;

    private boolean _wasTriggered = false;


    public HoldClawOpen(Robot robot) {
        _robot = robot;
        _hatchIntake = robot.getHatchIntake();
        requires(_hatchIntake);
    }
    @Override
    protected void initialize() {
        _hatchIntake.pointClaw();
        _wasTriggered = _hatchIntake.isHatchDetected() || _hatchIntake.isShockTriggered();
    }


    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        _hatchIntake.gripClaw();
    }

    @Override
    protected void execute(){
        if (!_wasTriggered && (_hatchIntake.isHatchDetected() || _hatchIntake.isShockTriggered())) {
            _hatchIntake.gripClaw();
        }
    }
}
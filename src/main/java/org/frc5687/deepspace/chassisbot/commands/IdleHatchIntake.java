package org.frc5687.deepspace.chassisbot.commands;

import org.frc5687.deepspace.chassisbot.subsystems.HatchIntake;
import org.frc5687.deepspace.chassisbot.subsystems.OutliersSubsystem;

public class IdleHatchIntake extends OutliersCommand {
    public HatchIntake _hatchIntake;

    public IdleHatchIntake(HatchIntake hatchIntake) {
        _hatchIntake = hatchIntake;
        requires(_hatchIntake);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected boolean isFinished() { return false;}

    @Override
    protected void execute() {
        if (_hatchIntake.isPointed() && (_hatchIntake.isHatchDetected()) || _hatchIntake.isShockTriggered()) {
            _hatchIntake.gripClaw();
        }
    }
}

package org.frc5687.deepspace.chassisbot.commands;

import org.frc5687.deepspace.chassisbot.Constants;
import org.frc5687.deepspace.chassisbot.subsystems.HatchIntake;

public class WristUp extends OutliersCommand {
    private HatchIntake _intake;
    private long _endTime;

    public WristUp(HatchIntake hatchIntake) {
        _intake = hatchIntake;
        requires(_intake);
    }
    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() > _endTime;
    }
    @Override
    protected void initialize() {
        _endTime = _intake.isUp() ? System.currentTimeMillis() : System.currentTimeMillis() + Constants.Intake.CLAW_RAISE_WRIST_MILLI_SEC;
        _intake.raiseWrist();
    }
    @Override
    protected void execute(){
        _intake.raiseWrist();
    }
}

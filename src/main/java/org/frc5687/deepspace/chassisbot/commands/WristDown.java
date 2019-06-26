package org.frc5687.deepspace.chassisbot.commands;

import org.frc5687.deepspace.chassisbot.Constants;

import org.frc5687.deepspace.chassisbot.subsystems.HatchIntake;

public class WristDown extends OutliersCommand {
    private HatchIntake _intake;
    private long _endTime;

    public WristDown(HatchIntake hatchIntake) {
        _intake = hatchIntake;
        requires(_intake);
    }
    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() > _endTime;
    }
    @Override
    protected void initialize() {
        _endTime = _intake.isDown() ? System.currentTimeMillis() : System.currentTimeMillis() + Constants.Intake.CLAW_LOWER_WRIST_MILLI_SEC;
        _intake.lowerWrist();
        _intake.pointClaw();
    }
    @Override
    protected void execute(){
        _intake.lowerWrist();
        _intake.pointClaw();
    }
}

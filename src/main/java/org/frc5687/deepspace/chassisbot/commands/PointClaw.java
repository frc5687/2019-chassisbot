package org.frc5687.deepspace.chassisbot.commands;

import org.frc5687.deepspace.chassisbot.Constants;
import org.frc5687.deepspace.chassisbot.subsystems.HatchIntake;

public class PointClaw extends OutliersCommand {
    private HatchIntake _hatchIntake;
    private long _startTime;

    public PointClaw(HatchIntake hatchIntake) {
        _hatchIntake = hatchIntake;
        requires(_hatchIntake);
    }
    @Override
    protected void initialize() {
        _hatchIntake.pointClaw();
        _startTime = System.currentTimeMillis();
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() > _startTime + Constants.Intake.OPEN_CLAW_MILLI_SEC;
    }

    @Override
    protected void execute(){
    }
}
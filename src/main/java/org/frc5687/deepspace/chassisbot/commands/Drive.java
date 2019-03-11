package org.frc5687.deepspace.chassisbot.commands;

import org.frc5687.deepspace.chassisbot.OI;
import org.frc5687.deepspace.chassisbot.subsystems.DriveTrain;
import org.frc5687.deepspace.chassisbot.utils.PDP;

public class Drive extends OutliersCommand {

    private OI _oi;
    private DriveTrain _driveTrain;

    public Drive(DriveTrain driveTrain, OI oi) {
        _driveTrain = driveTrain;
        _oi = oi;
        requires(_driveTrain);
    }

    @Override
    protected void execute() {
        // Get the base speed from the throttle
        // Get the base speed from the throttle
        double stickSpeed = _oi.getDriveSpeed();

        // Get the rotation from the tiller
        double wheelRotation = _oi.getDriveRotation();

        _driveTrain.cheesyDrive(stickSpeed, wheelRotation, _oi.isCreepPressed());

        metric("StickSpeed", stickSpeed);
        metric("StickRotation", wheelRotation);
        metric("LeftPower", _driveTrain.getLeftPower());
        metric("RightPower", _driveTrain.getRightPower());
    }



    @Override
    protected boolean isFinished() {
        return false;
    }
}

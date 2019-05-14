package org.frc5687.deepspace.chassisbot.commands;

import org.frc5687.deepspace.chassisbot.OI;
import org.frc5687.deepspace.chassisbot.RobotMap;
import org.frc5687.deepspace.chassisbot.subsystems.SparkMaxDriveTrain;
import org.frc5687.deepspace.chassisbot.subsystems.VictorSPDriveTrain;

public class Drive extends OutliersCommand {

    private OI _oi;
    private VictorSPDriveTrain _driveTrainVictor;
    private SparkMaxDriveTrain _driveTrainSpark;

    public Drive(SparkMaxDriveTrain driveTrain, OI oi) {
        _driveTrainSpark = driveTrain;
        _oi = oi;
        requires(_driveTrainSpark);
    }

    @Override
    protected void execute() {
        // Get the base speed from the throttle
        // Get the base speed from the throttle
        double stickSpeed = _oi.getDriveSpeed();

        // Get the rotation from the tiller
        double wheelRotation = _oi.getDriveRotation();

        _driveTrainSpark.cheesyDrive(stickSpeed, wheelRotation, _oi.isCreepPressed());

        metric("StickSpeed", stickSpeed);
        metric("StickRotation", wheelRotation);
        metric("LeftPower", _driveTrainSpark.getLeftPower());
        metric("RightPower", _driveTrainSpark.getRightPower());
    }



    @Override
    protected boolean isFinished() {
        return false;
    }

}

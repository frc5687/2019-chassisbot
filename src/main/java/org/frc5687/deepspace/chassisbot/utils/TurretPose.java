package org.frc5687.deepspace.chassisbot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurretPose extends Pose {

    private double _angle;

    public TurretPose(double angle) {
        super();
        _angle = angle;
    }

    public double getAngle() {
        return _angle;
    }

    public void updateDashboard(String prefix) {
        super.updateDashboard(prefix);
        SmartDashboard.putNumber(prefix + "/angle", _angle);
    }



}

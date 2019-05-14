package org.frc5687.deepspace.chassisbot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by Ben Bernard on 6/20/2016.
 */
public class BasicPose extends Pose {

    private double _angle;

    public BasicPose(double angle) {
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

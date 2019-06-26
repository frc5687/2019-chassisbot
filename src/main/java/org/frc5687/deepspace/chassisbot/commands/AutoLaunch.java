package org.frc5687.deepspace.chassisbot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.deepspace.chassisbot.Robot;

public class AutoLaunch extends CommandGroup {
    public AutoLaunch(Robot robot) {
        addSequential(new AutoDrive(robot.getSparkMaxDriveTrain(),robot.getIMU(), robot.getHatchIntake(),24,.5,false, true, 0, "", 2000));
    }
}

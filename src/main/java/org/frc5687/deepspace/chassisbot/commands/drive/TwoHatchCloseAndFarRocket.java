package org.frc5687.deepspace.chassisbot.commands.drive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Trajectory;
import org.frc5687.deepspace.chassisbot.Robot;
import org.frc5687.deepspace.chassisbot.commands.GripClaw;
import org.frc5687.deepspace.chassisbot.commands.PointClaw;
import org.frc5687.deepspace.chassisbot.commands.WristDown;
import org.frc5687.deepspace.chassisbot.commands.WristUp;

public class TwoHatchCloseAndFarRocket extends CommandGroup {
    public TwoHatchCloseAndFarRocket(Robot robot, boolean OffHAB, boolean left, Trajectory leftTrajectory, Trajectory rightTrajectory) {
        if (OffHAB) {
            addSequential(new AutoLaunch(robot));
        }
        addSequential(new GripClaw((robot.getHatchIntake())));
        addSequential(new WristUp(robot.getHatchIntake()));
        addSequential(new AutoAlign(robot.getSparkMaxDriveTrain(), robot.getIMU(), left ? -30 : 30, .5, 500, 2, "aligning to rocket"));
        addSequential(new AutoDriveToTargetSimple(robot.getSparkMaxDriveTrain(), robot.getIMU(), robot.getOI(), robot.getLimelight(), robot.getHatchIntake(), robot.getPoseTracker(), .9, false, 0, true));
        addSequential(new PointClaw(robot.getHatchIntake()));
        addSequential(new AutoDrive(robot.getSparkMaxDriveTrain(), robot.getIMU(), robot.getHatchIntake(), -4, .7, false, true, 0, "reverse 4 inches", 200));
        addSequential(new AutoAlign(robot.getSparkMaxDriveTrain(), robot.getIMU(), left ? -180 : 180, .5, 1000, 2, "aligning to loading station"));
        addSequential(new AutoDriveToTargetSimple(robot.getSparkMaxDriveTrain(), robot.getIMU(), robot.getOI(), robot.getLimelight(), robot.getHatchIntake(), robot.getPoseTracker(), .9, false, 0, false));
        addSequential(new GripClaw(robot.getHatchIntake()));
        addSequential(new AutoAlign(robot.getSparkMaxDriveTrain(), robot.getIMU(), left ? -179 : 179, .5, 250, 5, "aligning to rocket"));
        addSequential(new AutoDrivePath(robot.getSparkMaxDriveTrain(), robot.getIMU(), robot.getLimelight(), robot.getPoseTracker(), leftTrajectory, rightTrajectory, 0, true));
        addSequential(new AutoDriveToTargetSimple(robot.getSparkMaxDriveTrain(), robot.getIMU(), robot.getOI(), robot.getLimelight(), robot.getHatchIntake(), robot.getPoseTracker(), .9, false, 0, false, 3));
        addSequential(new PointClaw(robot.getHatchIntake()));
        addSequential(new AutoDrive(robot.getSparkMaxDriveTrain(), robot.getIMU(), robot.getHatchIntake(), -3.5, .7, false, true, 0, "reverse 12 inches", 180));
        addSequential(new AutoAlign(robot.getSparkMaxDriveTrain(), robot.getIMU(), left ? 90 : -90, .7, 1000, 5, "aligning to rocket"));

    }

}

package org.frc5687.deepspace.chassisbot;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.deepspace.chassisbot.subsystems.*;
import org.frc5687.deepspace.chassisbot.utils.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends OutliersRobot {
    private OI _oi;
    private DriveTrain _driveTrain;
    private HatchIntake _hatchIntake;
    private PDP _pdp;

    /**
     * This function is setRollerSpeed when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        // OI must be first...
        _oi = new OI();

        // then proxies...
        _pdp = new PDP();

        // Then subsystems....
        _driveTrain = new DriveTrain(this);
        _hatchIntake = new HatchIntake(this);

        // Must initialize buttons AFTER subsystems are allocated...
        _oi.initializeButtons(this);

        // Initialize the other stuff


    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want ran during disabled,
     * autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        super.robotPeriodic();
        _oi.poll();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString line to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional comparisons to
     * the switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
     */
    @Override
    public void autonomousInit() {
        super.autonomousInit();
        teleopInit();
    }

    public void teleopInit() {
        super.teleopInit();
    }


    @Override
    protected void ourPeriodic() {
        super.ourPeriodic();
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        super.disabledInit();
    }


    /***
     * This function is called every n cycles by the base implementation of robotPeriodic, where n is controlled by Constants.UPDATE-TICKS.
     * We tend to set this to 10 (or even higher!) in competition so that we don't flood SmartDashboard with updates or overload
     * the CAN bus with sensor reading that aren't needed.  When debugging we tend to lower it to 1 for the most accurate metrics.
     */
    @Override
    protected void updateDashboard() {
        _oi.updateDashboard();
        _driveTrain.updateDashboard();
        _pdp.updateDashboard();
    }



    public OI getOI() {
        return _oi;
    }
    public DriveTrain getDriveTrain() { return _driveTrain; }
    public HatchIntake getHatchIntake() { return _hatchIntake; }
    public PDP getPDP() { return _pdp; }



}

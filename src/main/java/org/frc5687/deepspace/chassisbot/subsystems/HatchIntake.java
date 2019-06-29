package org.frc5687.deepspace.chassisbot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.frc5687.deepspace.chassisbot.Robot;
import org.frc5687.deepspace.chassisbot.RobotMap;
import org.frc5687.deepspace.chassisbot.commands.IdleHatchIntake;
import org.frc5687.deepspace.chassisbot.utils.HallEffect;

public class HatchIntake extends OutliersSubsystem {

    private Robot _robot;
    private DoubleSolenoid _clawSolenoid;
    private DoubleSolenoid _wristSolenoid;
    private HallEffect _shockHallEffect;

    public HatchIntake(Robot robot) {
        _robot = robot;
        _clawSolenoid = new DoubleSolenoid(RobotMap.PCM.CLAW_OPEN, RobotMap.PCM.CLAW_CLOSE);
        _wristSolenoid = new DoubleSolenoid(RobotMap.PCM.WRIST_UP, RobotMap.PCM.WRIST_DOWN);
        _shockHallEffect = new HallEffect(RobotMap.DIO.SHOCK_HALL);
    }


    @Override
    public void updateDashboard() {
        metric("Wrist", _wristSolenoid.get().name());
        metric("Claw", _clawSolenoid.get().name());
        metric("ShockTriggered", isShockTriggered());
    }

    public void gripClaw(){
        _clawSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void pointClaw() {
        _clawSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void raiseWrist() { _wristSolenoid.set(DoubleSolenoid.Value.kReverse); }

    public void lowerWrist() { _wristSolenoid.set(DoubleSolenoid.Value.kForward); }

    public void releaseWrist() { _wristSolenoid.set(DoubleSolenoid.Value.kOff); }

    public void initDefaultCommand() { setDefaultCommand(new IdleHatchIntake(this));}

    public boolean isDown() {
        return _wristSolenoid.get() == DoubleSolenoid.Value.kForward;
    }

    public boolean isUp() {
        return _wristSolenoid.get() == DoubleSolenoid.Value.kReverse;
    }

    public boolean isHatchDetected() { return false; }

    public boolean isPointed() {
        if(_clawSolenoid.get() == DoubleSolenoid.Value.kReverse){
            return true;
        }
        return false;
    }

    public boolean isShockTriggered() { return _shockHallEffect.get(); }
}

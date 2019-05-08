package org.frc5687.deepspace.chassisbot;

public class Constants {
    /**
     *
     */
    public static final int CYCLES_PER_SECOND = 50;
    public static final int TICKS_PER_UPDATE = 10;
    public static final double METRIC_FLUSH_PERIOD = 1.0;

    public class DriveTrain {
        public static final double DEADBAND = 0.1;
        public static final double SPEED_SENSITIVITY = 0.80;
        public static final double ROTATION_SENSITIVITY = 0.75;

        public static final double CREEP_FACTOR = 0.25;

        public static final boolean LEFT_FRONT_MOTOR_INVERTED = false;
        public static final boolean LEFT_BACK_MOTOR_INVERTED = false;
        public static final boolean RIGHT_FRONT_MOTOR_INVERTED = true;
        public static final boolean RIGHT_BACK_MOTOR_INVERTED = true;
        public static final double TURNING_SENSITIVITY = 0.5;
    }
    public static class OI {
        public static final double AXIS_BUTTON_THRESHHOLD = 0.2;
        public static final long RUMBLE_MILLIS = 250;
        public static final double RUMBLE_INTENSITY = 1.0;
        public static final long RUMBLE_PULSE_TIME = 100;
    }


    public class Limelight {
        public static final double TARGET_HEIGHT = 29;
        public static final double LIMELIGHT_HEIGHT = 41.5;
        public static final double LIMELIGHT_ANGLE = 20;
    }


    /*
     There should be a nested static class for each subsystem and for each autonomous command that needs tuning constants.
     For example:
    public static class VictorSPDriveTrain {
        public static final double DEADBAND = 0.3;
        public static final double SENSITIVITY_LOW_GEAR = 0.8;
        public static final double SENSITIVITY_HIGH_GEAR = 1.0;
        public static final double ROTATION_SENSITIVITY = 1.0;
        public static final double ROTATION_SENSITIVITY_HIGH_GEAR = 1.0;
        public static final double ROTATION_SENSITIVITY_LOW_GEAR = 0.8;
    }
     */

}
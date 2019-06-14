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

        public static final boolean LEFT_FRONT_MOTOR_INVERTED = true;
        public static final boolean LEFT_BACK_MOTOR_INVERTED = true;
        public static final boolean RIGHT_FRONT_MOTOR_INVERTED = false;
        public static final boolean RIGHT_BACK_MOTOR_INVERTED = false;
        public static final double TURNING_SENSITIVITY = 0.5;
        public static final double LEFT_DISTANCE_PER_TICKS = 10;
        public static final double RIGHT_DISTANCE_PER_TICKS = 10;
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
        public static final double OVERALL_LATENCY_MILLIS = 11;
    }

    public class Turret {
        public static final double DEADBAND = 0.1;
        public static final boolean TURRET_MOTOR_INVERTED = true;
        public static final double MAX_SPEED = 0.8;
        public static final double MAX_FORWARD_SPEED = MAX_SPEED;
        public static final double MAX_REVERSE_SPEED = -MAX_SPEED;
        public static final double DEGREES_PER_TICK = 0.0;
        public static final double MAX_TURRET_ANGLE = 360; // in degrees
        public static final double MID_TURRET_ANGLE = 180; // in degrees
        public static final double MIN_TURRET_ANGLE = 0; // in degrees

        public static final double ROTATION_K = 0.03;

        public static final long LOCK_TIME = 80;
        public static final double TOLERANCE = 1;

        public static final double TICKS_PER_DEGREES = 0.2014611111111111111111111111111;
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
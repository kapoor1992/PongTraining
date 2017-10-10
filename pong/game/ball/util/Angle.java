package pong.game.ball.util;

// Used to retrieve ball bounce constants.
public final class Angle {
    // Specify Y-direction to bounce.
    public static final int NONE = -1;
    public static final int TOP = 0;
    public static final int STRAIGHT = 1;
    public static final int BOTTOM = 2;
    
    // Bounce off the player 45 degrees, 0 degrees, or -45 degrees.
    public static final double TOP_RIGHT = -Math.PI / 4;
    public static final double STRAIGHT_RIGHT = 0;
    public static final double BOTTOM_RIGHT = Math.PI / 4;
    
    // Bounce off the agent 135 degrees, 180 degrees, or 225 degrees.
    public static final double TOP_LEFT = -3 * Math.PI / 4;
    public static final double STRAIGHT_LEFT = Math.PI;
    public static final double BOTTOM_LEFT = 3 * Math.PI / 4;
}

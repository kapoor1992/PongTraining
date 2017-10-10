package pong.game.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pong.game.GameElement;
import pong.game.ball.util.Angle;

// Manages the ball.
public class Ball extends GameElement implements ActionListener {
    public static final double SPEED = 3;
    // Used to check if the ball went out of bounds.
    public static final int NO_ESCAPE = -1;
    public static final int LEFT_ESCAPE = 0;
    public static final int RIGHT_ESCAPE = 1; 
    
    private int ballHeight;
    private int ballWidth;
    private int x;
    private int y;
    private double velX;
    private double velY;
    
    public Ball(int areaHeight, int areaWidth, String path, int ballHeight, int ballWidth) {
        super(areaHeight, areaWidth, path);
        
        this.ballHeight = ballHeight;
        this.ballWidth = ballWidth;
        
        reset();
    }
    
    // Randomly throw the ball from the middle of the play area.
    public void reset() {
        x = (areaWidth / 2) - (ballWidth / 2);
        y = (areaHeight / 2) - (ballHeight / 2);
        
        double randAngle = Math.toRadians(Math.round(Math.random() * 360));
        
        // Polar coordinate operations. 2.5x slower at start to account for reaction time.
        velX = SPEED * Math.cos(randAngle) / 2.5;
        velY = SPEED * Math.sin(randAngle) / 2.5;
        
        // Prevents a vertical throw.
        if (velX > -1 && velX < 1)
            reset();
    }
    
    public int getYPos() {
        return y;
    }
    
    // Check for a collision with the player's paddle and return where the ball should be deflected.
    public int didCollideLeft(int paddleX, int paddleY, int paddleWidth, int paddleHeight) {
        if (x >= paddleX && x <= paddleX + paddleWidth && 
            y + (ballHeight / 2) >= paddleY && y + (ballHeight / 2) <= paddleY + paddleHeight) {
            if (y + (ballHeight / 2) < paddleY + (paddleHeight / 3))
                return Angle.TOP;
            if (y + (ballHeight / 2) > paddleY + (2 * paddleHeight / 3))
                return Angle.BOTTOM;
            return Angle.STRAIGHT;
        }
        
        return Angle.NONE;
    }
    
    // Check for a collision with the agent's paddle and return where the ball should be deflected.
    public int didCollideRight(int paddleX, int paddleY, int paddleWidth, int paddleHeight) {
        if (x + ballWidth >= areaWidth - paddleWidth && x + ballWidth < areaWidth && 
            y + (ballHeight / 2) >= paddleY && y + (ballHeight / 2) <= paddleY + paddleHeight) {
            if (y + (ballHeight / 2) < paddleY + (paddleHeight / 3))
                return Angle.TOP;
            if (y + (ballHeight / 2) > paddleY + (2 * paddleHeight / 3))
                return Angle.BOTTOM;
            return Angle.STRAIGHT;
        }
        
        return Angle.NONE;
    }
    
    // Bounce the ball off the player's paddle given the direction.
    public void bounceToRight (int direction) {
        if (direction == Angle.TOP) {
            velX = SPEED * Math.cos(Angle.TOP_RIGHT);
            velY = SPEED * Math.sin(Angle.TOP_RIGHT);
        } else if (direction == Angle.STRAIGHT) {
            velX = SPEED * Math.cos(Angle.STRAIGHT_RIGHT);
            velY = SPEED * Math.sin(Angle.STRAIGHT_RIGHT);
        } else if (direction == Angle.BOTTOM) {
            velX = SPEED * Math.cos(Angle.BOTTOM_RIGHT);
            velY = SPEED * Math.sin(Angle.BOTTOM_RIGHT);
        } else {
            return;
        }
        
        x += velX;
        y += velY;
    }
    
    // Bounce the ball off the agent's paddle given the direction.
    public void bounceToLeft (int direction) {
        if (direction == Angle.TOP) {
            velX = SPEED * Math.cos(Angle.TOP_LEFT);
            velY = SPEED * Math.sin(Angle.TOP_LEFT);
        } else if (direction == Angle.STRAIGHT) {
            velX = SPEED * Math.cos(Angle.STRAIGHT_LEFT);
            velY = SPEED * Math.sin(Angle.STRAIGHT_LEFT);
        } else if (direction == Angle.BOTTOM) {
            velX = SPEED * Math.cos(Angle.BOTTOM_LEFT);
            velY = SPEED * Math.sin(Angle.BOTTOM_LEFT);
        } else {
            return;
        }
        
        x += velX;
        y += velY;
    }
    
    // Check if the ball went out of bounds and return where it did.
    public int escaped() {
        if (x + ballWidth < 0)
            return LEFT_ESCAPE;
        if (x > areaWidth)
            return RIGHT_ESCAPE;
        
        return NO_ESCAPE;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        // Bounce off top wall.
        if (y < 0) {
            velY = -velY;
            y = 0;
        }
        // Bounce off bottom wall.
        if (y > areaHeight - ballHeight) {
            velY = -velY;
            y = areaHeight - ballHeight;
        }
        
        x += velX;
        y += velY;
    }
    
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        
        graphics.drawImage(image, (int)Math.round(x), (int)Math.round(y), null);
    }
}

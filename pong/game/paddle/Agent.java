package pong.game.paddle;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import pong.game.GameElement;

// AI manager.
public class Agent extends GameElement implements ActionListener {
    public static final double MIN_SPEED = 1;
    public static final double LEARNING_RATE = 0.1;
    
    private double speed;
    private int agentHeight;
    private int agentWidth;
    private int x;
    private int y;
    
    public Agent (int areaHeight, int areaWidth, String path, int agentHeight, int agentWidth) {
        super(areaHeight, areaWidth, path);
        
        speed = MIN_SPEED; // Initial speed is smallest allowed.
        
        this.agentHeight = agentHeight;
        this.agentWidth = agentWidth;
        
        // Paddle starts in middle-right.
        x = areaWidth - agentWidth;
        y = (areaHeight / 2) - (agentHeight / 2);
        
        setFocusable(true);
        displaySpeed();
    }
    
    // Adjust intelligence.
    public void hillClimb (double current, double peak) {
        // Player won, get smarter.
        if (current < peak) {
            raiseSpeed();
        // Player lost, get dumber.
        } else if (current > peak) {
            lowerSpeed();
        }
        
        displaySpeed();
    }
    
    public int getXPos() {
        return x;
    }
    
    public int getYPos() {
        return y;
    }
    
    // Moves the agent towards the ball's Y-value.
    public void move (int ballY, int ballHeight) {
        int ballMid = ballY + (ballHeight / 2);
        
        if (y > ballMid)
            y -= speed;
        else if (y + agentHeight < ballMid)
            y += speed;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        // Bounce off top wall.
        if (y < 0) {
            y = 0;
        }
        // Bounce off bottom wall.
        if (y > areaHeight - agentHeight) {
            y = areaHeight - agentHeight;
        }
    }
    
    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
    }
    
    // Get smarter.
    public void raiseSpeed() {
        speed += LEARNING_RATE;
    }
    
    // Get dumber.
    public void lowerSpeed() {
        speed -= LEARNING_RATE;
        
        // But not too dumb...
        if (speed < MIN_SPEED)
            speed = MIN_SPEED;
    }
    
    // Allows user to see how smart/dumb the AI is.
    private void displaySpeed() {
        DecimalFormat df = new DecimalFormat("#.0"); // Prevents lingering decimals from double-type operations.
        System.out.println("Agent Speed: " + df.format(speed));
    }
}

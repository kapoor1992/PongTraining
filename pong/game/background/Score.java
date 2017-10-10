package pong.game.background;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import pong.game.GameElement;

// Keeps track of the score.
public class Score extends GameElement {
    public static final int HEIGHT_OFFSET = 50;
    
    private int x;
    private int y;
    private int leftScore;
    private int rightScore;
    
    public Score(int height, int width) {
        super(height, width);
        
        leftScore = 0;
        rightScore = 0;
        
        x = width / 2;
        y = HEIGHT_OFFSET;
    }
    
    // Increment player score.
    public void updateLeft() {
        leftScore += 1;
    }
    
    // Increment agent score.
    public void updateRight() {
        rightScore += 1;
    }
    
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics.setColor(Color.YELLOW);
        graphics2D.drawString(leftScore + " - " + rightScore, x, y);
    }
}
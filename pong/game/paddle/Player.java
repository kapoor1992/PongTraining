package pong.game.paddle;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pong.game.GameElement;

// Player manager.
public class Player extends GameElement implements ActionListener, KeyListener {
    public static final int SPEED = 50;
    
    private int playerHeight;
    private int playerWidth;
    private int x;
    private int y;
    
    public Player (int areaHeight, int areaWidth, String path, int playerHeight, int playerWidth) {
        super(areaHeight, areaWidth, path);
        
        this.playerHeight = playerHeight;
        this.playerWidth = playerWidth;
        
        // Start in middle-left.
        x = 0;
        y = (areaHeight / 2) - (playerHeight / 2);
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    public int getXPos() {
        return x;
    }
    
    public int getYPos() {
        return y;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        // Bounce off top wall.
        if (y < 0) {
            y = 0;
        }
        // Bounce off bottom wall.
        if (y > areaHeight - playerHeight) {
            y = areaHeight - playerHeight;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        int code = event.getKeyCode();
        
        if (code == KeyEvent.VK_UP) {
            y -= SPEED;
        } else if (code == KeyEvent.VK_DOWN) {
            y += SPEED;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
    }
    
    @Override
    public void keyTyped(KeyEvent event) {
    }
    
    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
    }
}

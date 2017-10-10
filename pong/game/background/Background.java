package pong.game.background;

import java.awt.Graphics;
import pong.game.GameElement;

// Created for the space background.
public class Background extends GameElement {
    public Background(int height, int width, String path) {
        super(height, width, path);
    }
    
    public void draw(Graphics graphics) {
        graphics.drawImage(image, 0, 0, null);
    }
}
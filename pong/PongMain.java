package pong;

import pong.game.GameController;
import java.io.File;
import javax.swing.JOptionPane;

// Entry point.
public class PongMain {
    public static void main(String[] args) throws InterruptedException {
        // Window size.
        int wX = 600;
        int wY = 600;
        // Player size.
        int pX = 15;
        int pY = 100;
        // Agent size.
        int aX = 15;
        int aY = 100;
        // Ball size.
        int bX = 15;
        int bY = 15;
        
        // Grab images.
        String pp = new File("pong/images/player.jpg").getAbsolutePath();
        String ap = new File("pong/images/agent.jpg").getAbsolutePath();
        String bp = new File("pong/images/ball.jpg").getAbsolutePath();
        String wp = new File("pong/images/background.jpg").getAbsolutePath();
        
        // Gives brief information and instructions.
        intro();
        
        // Start game.
        GameController gc = new GameController(wY, wX, pY, pX, aY, aX, bY, bX, pp, ap, bp, wp);
    }
    
    private static void intro() {
        JOptionPane.showMessageDialog(null,
                                      "Welcome to Pong!\n\n" +
                                      "Use the up and down arrows to control your paddle on the left.\n" +
                                      "The computer agent will attempt to match your skill level as the game goes on!",
                                      "Pong",
                                      JOptionPane.PLAIN_MESSAGE);
    }
}
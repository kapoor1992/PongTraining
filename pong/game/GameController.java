package pong.game;

import pong.game.background.Score;
import pong.game.background.Background;
import pong.game.paddle.Agent;
import pong.game.paddle.Player;
import pong.game.ball.Ball;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

// The brain of the game.
public class GameController extends JFrame implements ActionListener {
    private Timer timer;
    private JFrame window;
    private int areaHeight;
    private int areaWidth;
    private int playerHeight;
    private int playerWidth;
    private int agentHeight;
    private int agentWidth;
    private int ballHeight;
    private int ballWidth;
    private String playerPath;
    private String agentPath;
    private String ballPath;
    private String backgroundPath;
    private Player player;
    private Agent agent;
    private Ball ball;
    private Score score;
    private Background background;
    private Painter painter;
    
    public GameController (int areaHeight, int areaWidth, int playerHeight, int playerWidth, int agentHeight, int agentWidth, 
                           int ballHeight, int ballWidth, String playerPath, String agentPath, String ballPath, String backgroundPath) {
        timer = new Timer(5, this);
        
        window = new JFrame("Pong");
        
        this.areaHeight = areaHeight;
        this.areaWidth = areaWidth;
        this.playerHeight = playerHeight;
        this.playerWidth = playerWidth;
        this.agentHeight = agentHeight;
        this.agentWidth = agentWidth;
        this.ballHeight = ballHeight;
        this.ballWidth = ballWidth;
        this.playerPath = playerPath;
        this.agentPath = agentPath;
        this.ballPath = ballPath;
        this.backgroundPath = backgroundPath;
        
        // Setup window.
        initElements();
        attachElements();
        formatWindow();
        
        timer.start();
    }
    
    @Override
    public void actionPerformed (ActionEvent event) {
        // Move agent towards ball's Y-position.
        agent.move(ball.getYPos(), ballHeight);
        
        // Check for a paddle-to-ball hit.
        int leftHit = ball.didCollideLeft(player.getXPos(), player.getYPos(), playerWidth, playerHeight);
        int rightHit = ball.didCollideRight(agent.getXPos(), agent.getYPos(), agentWidth, agentHeight);
        
        // Bounce the ball if it hit a paddle.
        ball.bounceToRight(leftHit);
        ball.bounceToLeft(rightHit);
        
        // Check if the ball escaped.
        int escaped = ball.escaped();
        
        // If it escaped...
        if (escaped != Ball.NO_ESCAPE) {
            // Run hill-climbing.
            agent.hillClimb((double)(Ball.RIGHT_ESCAPE - Ball.LEFT_ESCAPE) / 2, escaped);
            
            // Update score.
            if (escaped == Ball.LEFT_ESCAPE)
                score.updateRight();
            else
                score.updateLeft();
            
            // Reset ball.
            ball.reset();
        }
           
        player.actionPerformed(event);
        agent.actionPerformed(event);
        ball.actionPerformed(event);
        
        painter.repaint();
    }
    
    // Setup elements.
    private void initElements() {
        player = new Player(areaHeight, areaWidth, playerPath, playerHeight, playerWidth);
        agent = new Agent(areaHeight, areaWidth, agentPath, agentHeight, agentWidth);
        ball = new Ball(areaHeight, areaWidth, ballPath, ballHeight, ballWidth);
        score = new Score(areaHeight, areaWidth);
        background = new Background(areaHeight, areaWidth, backgroundPath);
        painter = new Painter(areaHeight, areaWidth, player, agent, ball, score, background);
    }
    
    // Layer the game window.
    private void attachElements() {
        window.add(painter);
        painter.add(background);
        background.add(ball);
        ball.add(player);
        player.add(agent);
        agent.add(score);
    }
    
    // Adjust window parameters.
    private void formatWindow() {
        window.setResizable(false);
        window.setSize(areaHeight, areaWidth);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
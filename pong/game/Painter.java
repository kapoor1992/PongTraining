package pong.game;

import pong.game.background.Score;
import pong.game.background.Background;
import pong.game.paddle.Agent;
import pong.game.paddle.Player;
import pong.game.ball.Ball;
import java.awt.Graphics;

// Draws everything.
public class Painter extends GameElement {
    private Player player;
    private Agent agent;
    private Ball ball;
    private Score score;
    private Background background;
    
    public Painter(int height, int width, Player player, Agent agent, Ball ball, Score score, Background background) {
        super(height, width);
        
        this.player = player;
        this.agent = agent;
        this.ball = ball;
        this.score = score;
        this.background = background;
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        background.draw(graphics);
        ball.draw(graphics);
        player.draw(graphics);
        agent.draw(graphics);
        score.draw(graphics);
    }
}

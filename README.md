# PongTraining
Classic Java Pong training game with Hill-Climbing AI.

DESCRIPTION

This game is the classic version of Pong with some added learning behaviour. The player controls the left paddle using up/down arrows and the computer agent controls the right paddle. The AI uses hill-climbing to become competitive over time. It tries to match the player's skill level and stay there. Every time the player loses, the agent decreases its speed and every time the player wins, it increases its speed. In the implementation, this "peak" level of competitiveness moves after every time that a paddle wins. The AI's low-level strategy is simply to match the Y-value of the ball. Also, the ball bounces either 45 degrees up, straight, or 45 degrees done based on where it hits a paddle.


INSTRUCTIONS

Please note that this has only been tested in the Terminal on Mac OSX Sierra. To run:

	1) cd to just outside the "pong" directory
	2) enter "javac pong/PongMain.java" to compile
	3) enter "java pong.PongMain" to run

Usage video: https://youtu.be/lKI2DBxkS28

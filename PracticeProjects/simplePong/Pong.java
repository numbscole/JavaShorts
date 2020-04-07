package simplePong;
import javafx.application.*;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;

// A block bouncing around on screen to practice javafx animation
public class Pong extends Application {
	
	Scene s;
	Double WIDTH = 600.0, HEIGHT = 400.0, startX=WIDTH/2.0, startY=HEIGHT/2.0;
	AnimationTimer update;
//	AlertBox endMessage;
	PongPlayer player1, player2;
	Circle ball;
	Integer w = 5, h = 5, moving1 = 0, moving2 = 0, maxScore = 3;
	Double speedx = 1.0, speedy = 0.1, playerSpeed = 2.0, delta = .1; 
	Double startspeedX = 1.0, startspeedY = 0.1;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		// Set Title to window (stage)
		primaryStage.setTitle("Block Bouncing on Screen");
//		endMessage = new AlertBox();
		
		// Make a ball
		ball = new Circle();
		ball.setRadius(10);
		ball.setCenterX(startX);
		ball.setCenterY(startY);
		ball.setFill(Color.BROWN);
		
		// Make Players
		player1 = new PongPlayer(HEIGHT, WIDTH, playerSpeed, true);
		player2 = new PongPlayer(HEIGHT, WIDTH, playerSpeed, false);
		
		// Make group to place the cube in
		Group g = new Group();
		g.getChildren().addAll(ball, player1.getRec(), player2.getRec());
//		Canvas c = new Canvas();
		
		// Scene is the contents inside a window (Need to place a scene in our stage)
		s = new Scene(g, WIDTH, HEIGHT, Color.ALICEBLUE);
		primaryStage.setScene(s);
		primaryStage.setResizable(false);
		primaryStage.show();
		updateScene();
	}
	
	private void resetBoard() {
		ball.setCenterX(startX);
		ball.setCenterY(startY);
		player1.resetPosition();
		player2.resetPosition();
		speedx = startspeedX;
		speedy = startspeedY;
		
		checkForWinner();
	}
	
	private void checkForWinner() {
		if (player1.getPoints() == maxScore) {
			String msg = "Player 1 wins: " + player1.getPoints() +  " to " + player2.getPoints();
//			endMessage.display("GAME OVER", msg);
			update.stop();
			System.out.println(msg);
		}
		else if (player2.getPoints() == maxScore) {
			String msg = "Player 2 wins: " + player2.getPoints() +  " to " + player1.getPoints();
//			endMessage.display("GAME OVER", msg);
			update.stop();
			System.out.println(msg);
		}
	}
	
	private void updateScene() {
		update = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				Double x = ball.getCenterX();
				Double y = ball.getCenterY();
				
				//Check if we are hitting a player
				if(player1.checkCollision(x, y)) {
					speedx = -speedx + delta;
					if (speedy > 0)
						speedy += delta;
					else
						speedy -= delta;
				}
				if(player2.checkCollision(x, y)) {
					speedx = -speedx - delta;
					if (speedy > 0)
						speedy += delta;
					else
						speedy -= delta;
				}
				// Check Y
				if(y + h + speedy >= HEIGHT)
					speedy = -speedy;
				if(y + speedy <= 0)
					speedy = -speedy;
				
				// Update position
				ball.setCenterX(x+speedx);
				ball.setCenterY(y+speedy);
				
				// Check for a point
				if (x < 0.0) {
					player2.addPoint();
					resetBoard();
				}
				else if (x > WIDTH) {
					player1.addPoint();
					resetBoard();
				}
				
				s.setOnKeyPressed(e -> {
				    if (e.getCode() == KeyCode.W)
				    	moving1 = 1;
				    if (e.getCode() == KeyCode.S)
				        moving1 = -1;
				    if (e.getCode() == KeyCode.UP)
				        moving2 = 1;
				    if (e.getCode() == KeyCode.DOWN)
				        moving2 = -1;
				});
				s.setOnKeyReleased(e -> {
					if (e.getCode() == KeyCode.W)
				    	moving1 = 0;
				    if (e.getCode() == KeyCode.S)
				        moving1 = 0;
				    if (e.getCode() == KeyCode.UP)
				        moving2 = 0;
				    if (e.getCode() == KeyCode.DOWN)
				        moving2 = 0;
				});
				
				if( moving1 == 1 )
					player1.moveUp();
				if( moving1 == -1 )
					player1.moveDown();
				if( moving2 == 1 )
					player2.moveUp();
				if( moving2 == -1 )
					player2.moveDown();
			}						
		};
		
		update.start();
	}
 }
package simplePong;
import javafx.application.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.animation.AnimationTimer;

// A block bouncing around on screen to practice javafx animation
public class Block extends Application {

	AnimationTimer update;
	Rectangle rec;
	Integer w = 30, h = 30;
	Double speedx = 1.5, speedy = 1.5;
	Integer WIDTH = 500, HEIGHT = 900;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		// Set Title to window (stage)
		primaryStage.setTitle("Block Bouncing on Screen");
		
		// Make a cube
		rec = new Rectangle(135, 135, w, h);
		
		// Make group to place the cube in
		Group g = new Group(rec);
//		Canvas c = new Canvas();
		
		// Scene is the contents inside a window (Need to place a scene in our stage)
		Scene s = new Scene(g, WIDTH, HEIGHT, Color.ALICEBLUE);
		primaryStage.setScene(s);
		primaryStage.setResizable(false);
		primaryStage.show();
		updateScene();
	}
	
	private void updateScene() {
		update = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				Double x = rec.getX();
				Double y = rec.getY();
				
				//Check if we are hitting a wall
				// Check X
				if(x + w + speedx >= WIDTH)
					speedx = -speedx;
				if(x + speedx <= 0)
					speedx = -speedx;
				// Check Y
				if(y + h + speedy >= HEIGHT)
					speedy = -speedy;
				if(y + speedy <= 0)
					speedy = -speedy;
				
				// Update position
				rec.setX(x+speedx);
				rec.setY(y+speedy);
			}						
		};
		
		update.start();
	}
 }
package simplePong;

import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.event.*;

public class Runner extends Application{

	Stage window;
	Scene mainScene;
	Button blockButton;
	Label blockLabel;
	Button PongButton;
	Label PongLabel;
	
	Block blockWindow;
	Pong PongWindow;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		window = primaryStage;
		primaryStage.setResizable(false);
		blockWindow = new Block();
		
		blockLabel = new Label("First simple project");
		blockButton = new Button("Bouncing Block");
		blockButton.setOnAction(e -> {
			try {
				blockWindow.start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("didn't work");
			}
		});
		
		PongWindow = new Pong();
		PongLabel = new Label("Second project");
		PongButton = new Button("Pong");
		PongButton.setOnAction(e -> {
			try {
				PongWindow.start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("didn't work");
			}
		});
		
		// Layout 1 - children are laid out in vertical column
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(blockLabel, blockButton, PongLabel, PongButton);
		mainScene = new Scene(layout1, 200, 200);
		
		
		window.setScene(mainScene);
		window.setTitle("Main Menu");
		window.show();
	}
}

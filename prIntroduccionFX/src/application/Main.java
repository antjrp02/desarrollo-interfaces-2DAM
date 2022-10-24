package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/**
			 * STACK PANEL
			 * 
			 * Button btn = new Button("Click aquí");
			btn.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event) {
					System.out.println("!Hola calvo¡");
				}
			});
			Label lb = new Label("Clic para un don limpio");
			StackPane panel = new StackPane();
			panel.setAlignment(lb, Pos.TOP_CENTER);
			panel.setAlignment(btn, Pos.CENTER);
			panel.getChildren().addAll(lb,btn);
			
			
			//VBOX 
			Button btn1 = new Button("Boton 1");
			Button btn2 = new Button("Boton 2");
			Button btn3 = new Button("Boton 3");
			
			
			HBox panel = new HBox(5);
			panel.setPadding(new Insets (15));
			panel.getChildren().addAll(btn1,btn2,btn3);
			
			Button btn1 = new Button("Boton 1");
			Button btn2 = new Button("Boton 2");
			Button btn3 = new Button("Boton 3");
			VBox panelVertical = new VBox(15);
			panelVertical.setPadding(new Insets(15));
			panelVertical.getChildren().addAll(btn1,btn2,btn3);
			BorderPane panel = new BorderPane();
			panel.setLeft(panelVertical);
			*/
			GridPane panel = new GridPane();
			
			Button btn1 = new Button("Boton 1");
			Button btn2 = new Button("Boton 2");
			Button btn3 = new Button("Boton 3");
			Button btn4 = new Button("Boton 4");
			
			panel.setVgap(15);
			panel.setHgap(15);
			panel.setPadding(new Insets(15));
			panel.add(btn1, 0, 0);
			panel.add(btn2, 1, 0);
			panel.add(btn3, 0, 1);
			panel.add(btn4, 1, 1);
			
			
			Scene scene =new Scene(panel, 400,300);
			primaryStage.getIcons().add(new Image("/application/P.png"));
			primaryStage.setScene(scene);
			primaryStage.setTitle("introduccion a JavaFX");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

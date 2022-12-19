package application;

import java.awt.Desktop.Action;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App  extends Application {
	public void start(Stage primaryStage) {
		
	
		VBox panel = new VBox(15);
		Label lbl_nombre = new Label("Nombre");
		TextField txt_nombre = new TextField();
		Label lbl_contraseña = new Label("Contraseña");
		PasswordField psw_contraseña = new PasswordField();
		Button btn = new Button("Entrar");
		
		Label lbl_bienvenida = new Label();
		
		panel.getChildren().addAll(lbl_nombre, txt_nombre,lbl_contraseña,psw_contraseña,
				btn,lbl_bienvenida);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				lbl_bienvenida.setText("Bienvenido"+txt_nombre.getText());
				
			}

		
		});
		Scene scene = new Scene(panel, 300, 300);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Ejercicio 3");
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
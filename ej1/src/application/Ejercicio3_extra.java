
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ejercicio3_extra
extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane panel = new GridPane();

			Button cero = new Button("0");
			Button uno = new Button("1");
			Button dos = new Button("2");
			Button tres = new Button("3");
			Button cuatro = new Button("4");
			Button cinco = new Button("5");
			Button seis = new Button("6");
			Button siete = new Button("7");
			Button ocho = new Button("8");
			Button nueve = new Button("9");
			Button llamar = new Button("Llamar");
			Button colgar = new Button("Colgar");

			panel.add(siete, 1, 1);
			panel.add(ocho, 2, 1);
			panel.add(nueve, 3, 1);
			panel.add(cuatro, 1, 2);
			panel.add(cinco, 2, 2);
			panel.add(seis, 3, 2);
			panel.add(llamar, 4, 2);
			panel.add(colgar, 4, 3);
			panel.add(uno, 1, 3);
			panel.add(dos, 2, 3);
			panel.add(tres, 3, 3);
			panel.add(cero, 2, 4);

			Scene scene = new Scene(panel, 300, 200);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ejercicio 3");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
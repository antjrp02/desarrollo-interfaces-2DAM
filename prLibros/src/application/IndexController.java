package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class IndexController {
	
	@FXML
	private TextField txtTitulo;
	
	@FXML
	private ChoiceBox cbEditorial;
	
	@FXML
	private TextField txtAutor;
	
	@FXML
	private TextField txtPaginas;
	
	@FXML
	private TableView <Libro> tableLibros;
	
	@FXML
	private TableColumn <Libro, String> columnTitulo;
	
	@FXML
	private TableColumn <Libro, String> columnEditorial;
	
	@FXML
	private TableColumn <Libro, String> columnAutor;
	
	@FXML
	private TableColumn <Libro, Integer> columnPaginas;
	
	@FXML
	private Button btnAnadir;
	
	private ObservableList<Libro> listaLibros =
		FXCollections.observableArrayList(
				new Libro("La Biblia", "Planeta", "Jesús", 500)
		);
	
	public ObservableList<String> listaEditoriales = 
		FXCollections.observableArrayList(
			"Planeta",
			"Altaya",
			"Kadokawa",
			"Penguin Libros" 
		);
	
	@FXML
	private void initialize() {
		
		cbEditorial.setItems(listaEditoriales);
		
		columnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		columnEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
		columnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		columnPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		
		tableLibros.setItems(listaLibros); 
	}
	
	@FXML
	public void anadirLibro(ActionEvent event) {
		if(txtPaginas.getText().isBlank()||cbEditorial.getSelectionModel().isEmpty()||txtTitulo.getText().isBlank()||txtAutor.getText().isBlank()) {
		
				Alert alerta = new Alert(AlertType.WARNING);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("Se han dejado campos en blanco");
				alerta.setContentText("Por favor, introduzca de nuevo los campos del libro");
				alerta.showAndWait();
				
			}
		else {
		if (esNumero(txtPaginas.getText())) {
			Libro l = new Libro(
					txtTitulo.getText(),
					cbEditorial.getValue().toString(),
					txtAutor.getText(),
					Integer.parseInt(txtPaginas.getText())
			);
				
			listaLibros.add(l);
			
			txtTitulo.clear();
			cbEditorial.getSelectionModel().clearSelection();
			txtAutor.clear();
			txtPaginas.clear(); 
		}
		else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error al insertar");
			alerta.setHeaderText("No se ha introducido un número en las páginas");
			alerta.setContentText("Por favor, introduzca un número en las páginas");
			alerta.showAndWait();
		}}
	
	}

	
	@FXML
	public void borrarLibro(ActionEvent event) { 
		try{
			int indiceSeleccionado = tableLibros.getSelectionModel().getSelectedIndex();
		
		tableLibros.getItems().remove(indiceSeleccionado);
		}catch(IndexOutOfBoundsException e){
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("No has seleccionado ninguna tabla");
            alerta.setHeaderText("Ninguna tabla seleccionada");
            alerta.setContentText("Porfavor,selecciona una tabla para borrarla");
            alerta.showAndWait();
        }
	}
	
	public boolean esNumero (String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	
}
package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class IndexController {

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtPrecio;
	
	@FXML
	private ChoiceBox cbCategoria;

	@FXML
	private DatePicker dpFecha;

	@FXML
	private TableView<Producto> tableProductos;

	@FXML
	private TableColumn<Producto, String> columnNombre;

	@FXML
	private TableColumn<Producto, Float> columnPrecio;

	@FXML
	private TableColumn<Producto, String> columnCategoria;

	@FXML
	private TableColumn<Producto, String> columnFecha;

	@FXML
	private Button btnAnadir;

	private ObservableList<Producto> listaProductos = FXCollections
			.observableArrayList();

	public ObservableList<String> listaCategorias = FXCollections.observableArrayList("Fruta/Verdura", "Enlatados", "Bebidas",
			"Carnes","Dulces","Ecologicos","Panaderia");

	@FXML
	private void initialize() {

		cbCategoria.setItems(listaCategorias);

		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		columnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		ObservableList listaProductosBD = getProductosBD();
		tableProductos.setItems(listaProductosBD);
	}
	@FXML
	private ObservableList<Producto> getProductosBD () {
	
		/*
		 * Creamos la ObservableList donde almacenaremos
		 * los nombres obtenidos de la BD
		 */
		ObservableList<Producto> listaProductosBD = 
				FXCollections.observableArrayList();
		
		//	Nos conectamos a la BD
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();
		
		String query = "select * from productos";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Producto p = new Producto(
						
						rs.getString("Nombre"),
						rs.getFloat("precio"),
						rs.getString("categoria"),
						rs.getString("fecha_caducidad"),
						rs.getInt("id") 
				);
				listaProductosBD.add(p);
			}
			
			//	Cerramos la conexión
			connection.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return listaProductosBD;
	}
	@FXML
	public void anadirProducto(ActionEvent event) {
		
		if (txtNombre.getText().isEmpty() || txtPrecio.getText().isEmpty() || cbCategoria.getValue().toString().isEmpty()
				|| dpFecha.getValue().toString().isEmpty()) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error no se ha insertado un campo");
			alerta.setHeaderText("No se ha introducido un campo");
			alerta.setContentText("Porfavor,no dejes ningun campo en blanco");
			alerta.showAndWait();
		} else {
			if (esNumero(txtPrecio.getText())) {
				Producto p = new Producto(txtNombre.getText(), Float.parseFloat(txtPrecio.getText()),cbCategoria.getValue().toString(),dpFecha.getValue().toString());

				//listanombres.add(l);
				//tableProductos.setItems(listanombres);
				
				txtNombre.clear();
				txtPrecio.clear();
				cbCategoria.getSelectionModel().clearSelection();
				dpFecha.getEditor().clear();
				
				//Nos conectamos a la BD
				DatabaseConnection dbConnection = new DatabaseConnection();
				Connection connection = dbConnection.getConnection();
				
				try {
					//Aqui insertamos en la BD
					
					String query = "insert into productos " +"(Nombre, precio,categoria,fecha_caducidad,id_tienda) " + "VALUES (?, ?, ?, ?,1)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, p.getNombre());
					ps.setFloat(2, p.getPrecio());
					ps.setString(3, p.getCategoria());
					ps.setString(4, p.getFecha());
					ps.executeUpdate();
					
					
					//Cerramos la sesion
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				ObservableList listaNombresBD = getProductosBD();
				tableProductos.setItems(listaNombresBD);
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Producto no insertado...");
				alerta.setHeaderText("Precio no especificado");
				alerta.setContentText("Porfavor,introduzca el precio del producto");
				alerta.showAndWait();


			}
		}

	
	}

	@FXML
	public void borrarProducto(ActionEvent event) {

		int indiceSeleccionado = tableProductos.getSelectionModel().getSelectedIndex();
		
		System.out.println("Indice a borrar:" + indiceSeleccionado);
		if (indiceSeleccionado <= -1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("No has seleccionado ninguna tabla");
			alerta.setHeaderText("Ninguna tabla seleccionada");
			alerta.setContentText("Porfavor,selecciona una tabla para borrarla");
			alerta.showAndWait();
		}else {
			
			//tableProductos.getItems().remove(indiceSeleccionado);
			//tableProductos.getSelectionModel().clearSelection();  
			
			//Nos conectamos a la BD
			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();
			
			
			try {
				String query ="delete from productos where id = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				Producto p = tableProductos.getSelectionModel().getSelectedItem();
				
				ps.setInt(1, p.getId());
				ps.executeUpdate();
				
				tableProductos.getSelectionModel().clearSelection();
				//Actualizamos la tabla
				ObservableList listaProductosBD = getProductosBD();
				tableProductos.setItems(listaProductosBD);
				//Cerramos conexion
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		
	}

	public boolean esNumero(String s) {
		try {
			Float.parseFloat(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
}


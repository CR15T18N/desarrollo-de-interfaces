package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	private TextField txtNombre;
	
	@FXML
	private TextField txtPrecio;
	
	@FXML
	private ChoiceBox cbConsola;
	
	@FXML
	private ChoiceBox cbPegi;
	
	@FXML
	private TableView <Videojuego> tableVideojuegos;
	
	@FXML
	private TableColumn <Videojuego, String> columnNombre;
	
	@FXML
	private TableColumn <Videojuego, Integer> columnPrecio;
	
	@FXML
	private TableColumn <Videojuego, String> columnConsola;
	
	@FXML
	private TableColumn <Videojuego, String> columnPegi;
	
	@FXML
	private Button btnAniadir;
	
	@FXML
	private Button btnBorrar;
	
	private ObservableList<Videojuego> listaVideojuedos =
			FXCollections.observableArrayList(new Videojuego("God of War", 19.99f, "PlayStation 5", "18"));
	
	public ObservableList<String> listaConsolas =
			FXCollections.observableArrayList("Nintendo Switch", "PlayStation 5", "Xbox Series X", "Xbox Series S");
	
	public ObservableList<String> listaPegi =
			FXCollections.observableArrayList("3", "7", "12", "16", "18", "!");
	
	@FXML
	private void initialize() {
		
		cbConsola.setItems(listaConsolas);
		cbPegi.setItems(listaPegi);
		
		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnConsola.setCellValueFactory(new PropertyValueFactory<>("consola"));
		columnPegi.setCellValueFactory(new PropertyValueFactory<>("pegi"));
		
		ObservableList listaVideojuegosBD= getVideojuegosBD();
		
		tableVideojuegos.setItems(listaVideojuegosBD); 
		
	}
	
	private ObservableList<Videojuego> getVideojuegosBD () {
		ObservableList<Videojuego> listaVideojuegosBD = FXCollections.observableArrayList();
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();
		String query = "select * from videojuegos";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Videojuego libro = new Videojuego(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getFloat("precio"),
						rs.getString("consola"),
						rs.getString("pegi")
					);
				listaVideojuegosBD.add(libro);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaVideojuegosBD;
	}
	
	@FXML
	public void aniadirVideojuego(ActionEvent event) {
		if (txtNombre.getText().isEmpty()||txtPrecio.getText().isEmpty()||cbConsola.getSelectionModel().isEmpty()||cbPegi.getSelectionModel().isEmpty()) {
			
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Informacion incompleta");
			alerta.setHeaderText("Falta informacion del videojuego");
			alerta.setContentText("Por favor, introduzca todos los campos");
			alerta.showAndWait();
			
		}else {
			if (esNumero(txtPrecio.getText())) {
				Videojuego v = new  Videojuego(
						txtNombre.getText(),
						Float.parseFloat(txtPrecio.getText()),
						cbConsola.getValue().toString(),
						cbPegi.getValue().toString()
				);
				
				//listaVideojuedos.add(v);
				
				txtNombre.clear();
				txtPrecio.clear();
				cbConsola.getSelectionModel().clearSelection();
				cbPegi.getSelectionModel().clearSelection();
				
				DatabaseConnection dbConnection = new DatabaseConnection();
				Connection connection = dbConnection.getConnection();
				
				
				try {
					String query = "insert into videojuegos (nombre, precio, consola, pegi) VALUES (?, ?, ?, ?)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, v.getNombre());
					ps.setFloat(2, v.getPrecio());
					ps.setString(3, v.getConsola());
					ps.setString(4, v.getPegi());
					ps.executeUpdate();
					
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ObservableList listaVideojuegosBD= getVideojuegosBD();
				tableVideojuegos.setItems(listaVideojuegosBD);
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("No se ha introducido un numero en el precio");
				alerta.setContentText("Por favor, introduzca un numero en el precio");
				alerta.showAndWait();
			}
		}
    }
	
	@FXML
	public void borrarVideojuego(ActionEvent event) {
		
		int inidiceSeleccionado = tableVideojuegos.getSelectionModel().getSelectedIndex();
		
		System.out.println("Indice a borrar: "+inidiceSeleccionado);
		
		if(inidiceSeleccionado<=-1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error al borrar");
			alerta.setHeaderText("No se ha seleccionado ningun libro a borrar");
			alerta.setContentText("Por favor, selecciona un libro");
			alerta.showAndWait();
		}else {
			//tableVideojuegos.getItems().remove(inidiceSeleccionado);
			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();
			
			try {
				String query = "delete from videojuegos where id = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				Videojuego videojuego = tableVideojuegos.getSelectionModel().getSelectedItem();
				ps.setInt(1, videojuego.getId());
				ps.executeUpdate();
				
				tableVideojuegos.getSelectionModel().clearSelection();
				
				ObservableList listaVideojuegosBD= getVideojuegosBD();
				tableVideojuegos.setItems(listaVideojuegosBD);
				
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean esNumero (String s) {
		try {
			Float.parseFloat(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}

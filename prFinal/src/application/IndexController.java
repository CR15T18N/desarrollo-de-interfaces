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
	private TextField txtMarca;
	
	@FXML
	private TextField txtModelo;
	
	@FXML
	private ChoiceBox cbEstado;
	
	@FXML
	private TextField txtPrecio;
	
	@FXML
	private TableView <Coche> tableCoches;
	
	@FXML
	private TableColumn <Coche, String> columnMarca;
	
	@FXML
	private TableColumn <Coche, String> columnModelo;
	
	@FXML
	private TableColumn <Coche, String> columnEstado;
	
	@FXML
	private TableColumn <Coche, Integer> columnPrecio;
	
	@FXML
	private Button btnAniadir;
	
	@FXML
	private Button btnBorrar;
	
	public ObservableList<String> listaEstados =
			FXCollections.observableArrayList("Nuevo", "Tuneado", "Dañado", "Usado");
	
	@FXML
	private void initialize() {
		
		cbEstado.setItems(listaEstados);
		
		columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		columnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		columnEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
		columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		
		ObservableList listaCochesBD= getCochesBD();
		
		tableCoches.setItems(listaCochesBD);
		
	}
	
	private ObservableList<Coche> getCochesBD () {
		ObservableList<Coche> listaCochesBD = FXCollections.observableArrayList();
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();
		String query = "select * from coches";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Coche coche = new Coche(
						rs.getInt("id"),
						rs.getString("marca"),
						rs.getString("modelo"),
						rs.getString("estado"),
						rs.getFloat("precio")
					);
				listaCochesBD.add(coche);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCochesBD;
	}
	
	@FXML
	public void aniadirChoche(ActionEvent event) {
		if (txtMarca.getText().isEmpty()||txtModelo.getText().isEmpty()||cbEstado.getSelectionModel().isEmpty()||txtPrecio.getText().isEmpty()) {
			
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Informacion incompleta");
			alerta.setHeaderText("Falta informacion del coche");
			alerta.setContentText("Por favor, introduce todos los campos");
			alerta.showAndWait();
			
		}else {
			if (esNumero(txtPrecio.getText())) {
				Coche c = new  Coche(
						txtMarca.getText(),
						txtModelo.getText(),
						cbEstado.getValue().toString(),
						Float.parseFloat(txtPrecio.getText())
				);
				
				txtMarca.clear();
				txtModelo.clear();
				cbEstado.getSelectionModel().clearSelection();
				txtPrecio.clear();

				DatabaseConnection dbConnection = new DatabaseConnection();
				Connection connection = dbConnection.getConnection();
				
				
				try {
					String query = "insert into coches (marca, modelo, estado, precio) VALUES (?, ?, ?, ?)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, c.getMarca());
					ps.setString(2, c.getModelo());
					ps.setString(3, c.getEstado());
					ps.setFloat(4, c.getPrecio());
					ps.executeUpdate();
					
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ObservableList listaCochesBD= getCochesBD();
				tableCoches.setItems(listaCochesBD);
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("No se ha introducido un numero en el precio");
				alerta.setContentText("Por favor, introduzca un numero en el precio");
				alerta.showAndWait();
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
	
	@FXML
	public void borrarCoche(ActionEvent event) {
		
		int inidiceSeleccionado = tableCoches.getSelectionModel().getSelectedIndex();
		
		System.out.println("Indice a borrar: "+inidiceSeleccionado);
		
		if(inidiceSeleccionado<=-1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error al borrar");
			alerta.setHeaderText("No se ha seleccionado ningun coche para borrar");
			alerta.setContentText("Por favor, selecciona un coche");
			alerta.showAndWait();
		}else {
			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();
			
			try {
				String query = "delete from coches where id = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				Coche videojuego = tableCoches.getSelectionModel().getSelectedItem();
				ps.setInt(1, videojuego.getId());
				ps.executeUpdate();
				
				tableCoches.getSelectionModel().clearSelection();
				
				ObservableList listaCochesBD= getCochesBD();
				tableCoches.setItems(listaCochesBD);
				
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
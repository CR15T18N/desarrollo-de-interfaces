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
	private Button btnAniadir;
	
	@FXML
	private Button btnBorrar;
	
	private ObservableList<Libro> listaLibros =
		FXCollections.observableArrayList();
	
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
		
		ObservableList listaLibrosBD= getLibrosBD();
		
		tableLibros.setItems(listaLibrosBD); 
	}
	
	private ObservableList<Libro> getLibrosBD () {
		ObservableList<Libro> listaLibrosBD = FXCollections.observableArrayList();
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();
		String query = "select * from libros";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Libro libro = new Libro(
						rs.getInt("id"),
						rs.getString("titulo"),
						rs.getString("editorial"),
						rs.getString("autor"),
						rs.getInt("paginas")
					);
				listaLibrosBD.add(libro);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaLibrosBD;
	}
	
	@FXML
	public void aniadirLibro(ActionEvent event) {
		
		if (txtTitulo.getText().isEmpty()||txtPaginas.getText().isEmpty()||txtAutor.getText().isEmpty()||cbEditorial.getSelectionModel().isEmpty()) {
			
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Informacion incompleta");
			alerta.setHeaderText("Falta informacion del libro");
			alerta.setContentText("Por favor, introduzca todos los campos");
			alerta.showAndWait();
			
		}else {
			if (esNumero(txtPaginas.getText())) {
				Libro l = new  Libro(
						txtTitulo.getText(),
						cbEditorial.getValue().toString(),
						txtAutor.getText(),
						Integer.parseInt(txtPaginas.getText())
				);
				
				//listaLibros.add(l);
				//tableLibros.setItems(listaLibros);
				
				txtTitulo.clear();
				cbEditorial.getSelectionModel().clearSelection();
				txtAutor.clear();
				txtPaginas.clear();
				
				DatabaseConnection dbConnection = new DatabaseConnection();
				Connection connection = dbConnection.getConnection();
				
				
				try {
					String query = "insert into libros (titulo, editorial, autor, paginas) VALUES (?, ?, ?, ?)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, l.getTitulo());
					ps.setString(2, l.getEditorial());
					ps.setString(3, l.getAutor());
					ps.setInt(4, l.getPaginas());
					ps.executeUpdate();
					
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ObservableList listaLibrosBD= getLibrosBD();
				tableLibros.setItems(listaLibrosBD); 
				
				
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("No se ha introducido un numero en las paginas");
				alerta.setContentText("Por favor, introduzca un numero en las paginas");
				alerta.showAndWait();
			}
		}
    }
	
	@FXML
	public void borrarLibro(ActionEvent event) {
		
		int inidiceSeleccionado = tableLibros.getSelectionModel().getSelectedIndex();
		
		System.out.println("Indice a borrar: "+inidiceSeleccionado);
		
		if(inidiceSeleccionado<=-1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error al borrar");
			alerta.setHeaderText("No se ha seleccionado ningun libro a borrar");
			alerta.setContentText("Por favor, selecciona un libro");
			alerta.showAndWait();
		}else {
			//tableLibros.getItems().remove(inidiceSeleccionado);
			//tableLibros.getSelectionModel().clearSelection();
			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();
			
			try {
				String query = "delete from libros where id = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				Libro libro = tableLibros.getSelectionModel().getSelectedItem();
				ps.setInt(1, libro.getId());
				ps.executeUpdate();
				
				tableLibros.getSelectionModel().clearSelection();
				
				ObservableList listaLibrosBD= getLibrosBD();
				tableLibros.setItems(listaLibrosBD);
				
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
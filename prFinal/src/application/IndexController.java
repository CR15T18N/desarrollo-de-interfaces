package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
	
	private ObservableList<Coche> listaCoches =
			FXCollections.observableArrayList(new Coche("Opel", "Astra", "Nuevo", 7999.99f));
	
	public ObservableList<String> listaEstados =
			FXCollections.observableArrayList("Nuevo", "Tuneado", "Dañado", "Usado");
	
	@FXML
	private void initialize() {
		
		/*cbConsola.setItems(listaConsolas);
		
		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnConsola.setCellValueFactory(new PropertyValueFactory<>("consola"));
		columnPegi.setCellValueFactory(new PropertyValueFactory<>("pegi"));
		
		ObservableList listaVideojuegosBD= getVideojuegosBD();
		
		tableVideojuegos.setItems(listaVideojuegosBD); */
		
	}
}

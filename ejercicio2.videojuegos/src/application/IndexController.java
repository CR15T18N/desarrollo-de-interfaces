package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
	private ChoiceBox<String> cbConsola;
	
	@FXML
	private ChoiceBox<String> cbPegi;
	
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
	
	private ObservableList<Videojuego> listaVideojuedos =
			FXCollections.observableArrayList(
					new Videojuego("God of War", (float) 19.99, "PlayStation 5", "18")
			);
	
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
		
		tableVideojuegos.setItems(listaVideojuedos); 
	}
}

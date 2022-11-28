package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
		
		tableVideojuegos.setItems(listaVideojuedos);
	}
	
	@FXML
	public void aniadirVideojuego(ActionEvent event) {
		Videojuego v = new  Videojuego(
				txtNombre.getText(),
				Float.parseFloat(txtPrecio.getText()),
				cbConsola.getValue().toString(),
				cbPegi.getValue().toString()
		);
		
		listaVideojuedos.add(v);
		
		txtNombre.clear();
		txtPrecio.clear();
		cbConsola.getSelectionModel().clearSelection();
		cbPegi.getSelectionModel().clearSelection();
    }
}

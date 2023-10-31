package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class viewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;

	@FXML
	private MenuItem menuItemDepartment;

	@FXML
	private MenuItem menuAbout;

	@FXML
	public void actionSeller() {
		System.out.println("Seller");
	}

	@FXML
	public void actionDepartment() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
				controller.setDeparmentService (new DepartmentService());
				controller.updateTableView();
		});
	}

	@FXML
	public void actionAbout() {
		loadView("/gui/About.fxml", x -> {});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	// Carregando a view.
	@FXML
	public <T> void  loadView(String absoluteName, Consumer<T> InitializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVbox.getChildren().get(0);
			mainVbox.getChildren().clear();
			mainVbox.getChildren().add(mainMenu);
			mainVbox.getChildren().addAll(newVbox.getChildren());
			
			
			T controller = loader.getController();
			InitializingAction.accept(controller);
			

		} catch (IOException e) {
			Alerts.showAlert("IoException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	public void loadView2(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVbox.getChildren().get(0);
			mainVbox.getChildren().clear();
			mainVbox.getChildren().add(mainMenu);
			mainVbox.getChildren().addAll(newVbox.getChildren());

			DepartmentListController controller = loader.getController();
			controller.setDeparmentService(new DepartmentService());
			controller.updateTableView();

		} catch (IOException e) {
			Alerts.showAlert("IoException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}

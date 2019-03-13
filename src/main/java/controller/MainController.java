package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainBorderPane;

    private void setCenterBorderPane(String viewPath) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(viewPath));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainBorderPane.setCenter(parent);
    }

    @FXML
    private void initialize() {
        setCenterBorderPane("/view/employeeView.fxml");
    }

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void addEmployee(ActionEvent event) {
        setCenterBorderPane("/view/addView.fxml");
    }

    @FXML
    public void editEmployee(ActionEvent event) {
    }

    @FXML
    public void deleteEmployee(ActionEvent event) {
    }
}

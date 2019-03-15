package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane centerBorderPane;

    @FXML
    private Button btn_back;

    private void setVisibleBtnBack(String activeElement) {
        if(activeElement=="/view/employeeView.fxml")
            btn_back.setVisible(false);
        else
            btn_back.setVisible(true);
    }

    private void setCenterBorderPane(String viewPath) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(viewPath));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setVisibleBtnBack(viewPath);
        centerBorderPane.setCenter(parent);
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
        setCenterBorderPane("/view/deleteView.fxml");
    }

    @FXML
    public void backToMainView() {
        setCenterBorderPane("/view/employeeView.fxml");
    }
}

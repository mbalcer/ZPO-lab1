package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane centerBorderPane;

    @FXML
    private HBox hbox_back;

    @FXML
    private Button btn_back;

    private void setVisibleBtnBack(String activeElement) {
        if(activeElement== "/view/tableView.fxml")
            centerBorderPane.setTop(null);
        else
            centerBorderPane.setTop(hbox_back);
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
        setCenterBorderPane("/view/tableView.fxml");
    }

    @FXML
    void exitAction() {
        System.exit(0);
    }

    @FXML
    public void backToMainView() {
        setCenterBorderPane("/view/tableView.fxml");
    }

    @FXML
    public void onActionEmployee(ActionEvent event) {
        setCenterBorderPane("/view/employeeView.fxml");
    }
}

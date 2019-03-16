package controller;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Employee;

public class AddController {

    @FXML
    private Label lbl_name;

    @FXML
    private TextField tf_name;

    @FXML
    private Label lbl_email;

    @FXML
    private TextField tf_email;

    @FXML
    private Label lbl_salary;

    @FXML
    private TextField tf_salary;

    @FXML
    private Label lbl_info;

    private void clearTextField() {
        tf_name.clear();
        tf_email.clear();
        tf_salary.clear();
    }

    @FXML
    public void addEmployee() {
        String name = tf_name.getText();
        String email = tf_email.getText();
        Double salary = Double.parseDouble(tf_salary.getText());
        Employee newEmployee = new Employee(name,email,salary);

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        employeeDAO.save(newEmployee);
        clearTextField();
        lbl_info.setText("New employee has been added to the database");
    }
}

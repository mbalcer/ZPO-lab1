package controller;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.Employee;
import sun.applet.Main;

import java.util.List;
import java.util.Optional;

public class DeleteController {

    @FXML
    private ComboBox<String> cb_employee;

    @FXML
    private Button btn_delete;

    @FXML
    private Label lbl_info;

    private void fillComboBox() {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        ObservableList<String> nameEmployees = FXCollections.observableArrayList();
        List<Employee> employees = employeeDAO.findAll();
        employees.forEach(employee -> nameEmployees.add(employee.getName()));

        cb_employee.setItems(nameEmployees);
    }

    @FXML
    public void initialize() {
        fillComboBox();
    }

    @FXML
    public void deleteEmployee() {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        try {
            String selectedEmployee = cb_employee.getValue();
            Optional<Employee> optionalEmployee = employeeDAO.findByName(selectedEmployee);

            employeeDAO.delete(optionalEmployee.get());
            fillComboBox();
            lbl_info.setText("The employee was removed correctly");
        } catch (NullPointerException e) {
            return;
        }
    }
}

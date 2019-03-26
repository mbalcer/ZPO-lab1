package controller;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeController {
    @FXML
    private ComboBox<String> cb_employee;

    @FXML
    private Button btn_edit;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_add;

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

    private void fillComboBox() {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        ObservableList<String> nameEmployees = FXCollections.observableArrayList();
        List<Employee> employees = employeeDAO.findAll();
        employees.forEach(employee -> nameEmployees.add(employee.getId()+" - "+employee.getName()));

        cb_employee.setItems(nameEmployees);
    }

    @FXML
    public void initialize() {
        fillComboBox();
    }

    private boolean checkData(String name, String salary) {
        if (name.equals("")) {
            lbl_info.setText("Enter the employee's name");
            return false;
        }
        try {
            Double.parseDouble(tf_salary.getText());
        } catch (NumberFormatException e) {
            lbl_info.setText("The salary must be the number");
            return false;
        }

        return true;
    }

    @FXML
    public void addEmployee() {
        String name = tf_name.getText();
        String email = tf_email.getText();
        String salaryString = tf_salary.getText();

        if (checkData(name, salaryString)) {
            Double salary = Double.parseDouble(salaryString);

            Employee newEmployee = new Employee(name, email, salary);

            EmployeeDAO employeeDAO = new EmployeeDAOImpl();
            employeeDAO.save(newEmployee);
            clearTextField();
            fillComboBox();
            lbl_info.setText("New employee has been added to the database");
        }
    }

    @FXML
    void deleteEmployee() {
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

    @FXML
    void editEmployee() {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        String selectedEmployee = cb_employee.getValue();
        Integer selectedId = Integer.parseInt(selectedEmployee.split(" - ")[0]);
        Optional<Employee> optionalEmployee = employeeDAO.findOne(selectedId);

        tf_name.setText(optionalEmployee.get().getName());
        tf_email.setText(optionalEmployee.get().getEmail());
        tf_salary.setText(String.valueOf(optionalEmployee.get().getSalary()));
    }
}

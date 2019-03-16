package controller;

import dao.EmployeeDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;

public class TableController {

    @FXML
    private TableView<Employee> tbl_employee;

    @FXML
    private TableColumn<Employee, Long> col_id;

    @FXML
    private TableColumn<Employee, String> col_name;

    @FXML
    private TableColumn<Employee, String> col_email;

    @FXML
    private TableColumn<Employee, Double> col_salary;

    private ObservableList<Employee> employees = FXCollections.observableArrayList();


    private void addDataToTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tbl_employee.setItems(employees);
    }

    public void initialize(){
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        employees = (ObservableList<Employee>) employeeDAO.findAll();
        addDataToTable();
    }
}
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;

public class EmployeeController {

    @FXML
    private MenuItem mi_exit;

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

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }

    private void addDataToTable(){
        // konfiguracja wartości z modelu do tabeli
        col_id.setCellValueFactory(new PropertyValueFactory<Employee, Long>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        col_email.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        col_salary.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
        tbl_employee.setItems(employees);
    }

    public void initialize(){
        Employee e1 = new Employee((long) 1,"A", "A", 3222.0);
        Employee e2 = new Employee((long) 2,"B", "B", 33333.0);
        Employee e3 = new Employee((long) 3,"C", "C", 5001.0);
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        // wypełnienie wartości w tabelce
        addDataToTable();


    }

}

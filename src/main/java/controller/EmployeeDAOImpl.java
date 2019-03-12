package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.EmployeeDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAOImpl {


    public List<Employee> findAll() {
        ObservableList<Employee> data = FXCollections.observableArrayList();
        String query = "SELECT * FROM Employee";
        SQLConnection conn = new SQLConnection();
        ResultSet rs = conn.makeQuery(query);

        try {
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Double salary = rs.getDouble("salary");

                Employee row = new Employee(id,name,email,salary);
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.closeConnect(rs);
        }
        return data;
    }
}

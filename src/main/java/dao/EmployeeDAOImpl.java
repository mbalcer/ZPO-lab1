package dao;

import database.SQLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO{

    @Override
    public Optional findOne(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> data = FXCollections.observableArrayList();
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

    @Override
    public Optional findByName(String name) {
        Optional<Employee> optionalEmployee = null;
        String query = "SELECT * FROM Employee WHERE name='"+name+"'";
        SQLConnection conn = new SQLConnection();
        ResultSet rs = conn.makeQuery(query);

        try {
            if(rs.next()) {
                Long idDb = rs.getLong("id");
                String nameDb = rs.getString("name");
                String emailDb = rs.getString("email");
                Double salaryDb = rs.getDouble("salary");


                Employee employee = new Employee(idDb, nameDb, emailDb, salaryDb);
                optionalEmployee = Optional.of(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.closeConnect(rs);
        }

        return optionalEmployee;
    }

    @Override
    public void delete(Employee employee) {
        String query = "DELETE FROM Employee WHERE id="+employee.getId();
        SQLConnection conn = new SQLConnection();
        PreparedStatement preparedStmt = null;

        try {
            preparedStmt = conn.getConnect().prepareStatement(query);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(Employee employee) {
        Optional<Employee> optionalEmployee = findByName(employee.getName());
        String query = null;
        if(optionalEmployee==null) {
            query = "INSERT INTO Employee(name, email, salary) VALUES ('"+
                employee.getName()+"', '"+ employee.getEmail()+"', "+employee.getSalary()+")";
        } else {
            query = "UPDATE Employee SET name='"+employee.getName()+"', email='"+employee.getEmail()+
                    "', salary="+employee.getSalary()+" WHERE id="+optionalEmployee.get().getId();
        }

        SQLConnection conn = new SQLConnection();
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.getConnect().prepareStatement(query);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

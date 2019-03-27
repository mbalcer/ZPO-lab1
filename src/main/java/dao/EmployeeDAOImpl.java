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

    private Optional<Employee> findEmployee(String query) {
        Optional<Employee> optionalEmployee = null;
        SQLConnection conn = new SQLConnection();
        ResultSet rs = conn.makeQuery(query);

        try {
            if(rs.next()) {
                Integer idDb = rs.getInt("id");
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
    public Optional findOne(Integer id) {
        String query = "SELECT * FROM Employee WHERE id="+id;
        return findEmployee(query);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> data = FXCollections.observableArrayList();
        String query = "SELECT * FROM Employee";
        SQLConnection conn = new SQLConnection();
        ResultSet rs = conn.makeQuery(query);

        try {
            while (rs.next()) {
                Integer id = rs.getInt("id");
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
        String query = "SELECT * FROM Employee WHERE name='"+name+"'";
        return findEmployee(query);
    }

    @Override
    public void delete(Employee employee) {
        String query = "DELETE FROM Employee WHERE id="+employee.getId();
        SQLConnection conn = new SQLConnection();
        conn.makeQueryToDatabase(query);
        conn.closeConnect();
    }

    @Override
    public void save(Employee employee) {
        String query = null;
        if(employee.getId()==null) {
            query = "INSERT INTO Employee(name, email, salary) VALUES ('"+
                employee.getName()+"', '"+ employee.getEmail()+"', "+employee.getSalary()+")";
        } else {
            query = "UPDATE Employee SET name='"+employee.getName()+"', email='"+employee.getEmail()+
                    "', salary="+employee.getSalary()+" WHERE id="+employee.getId();
        }

        SQLConnection conn = new SQLConnection();
        conn.makeQueryToDatabase(query);
        conn.closeConnect();
    }

}

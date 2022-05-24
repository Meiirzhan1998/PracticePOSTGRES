import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataBase {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/students_repository";

    Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    Statement statement = connection.createStatement();
    Scanner scanner = new Scanner(System.in);
    public DataBase() throws SQLException {}
    public void connectToDB() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public List<Student> showStudentList() throws SQLException {
            List<Student> studentList = new ArrayList<>();
            ResultSet rs = statement.executeQuery("SELECT * FROM students ORDER BY id");
            while(rs.next()){
                Student student= new Student();
                student.setName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setMiddleName(rs.getString("middle_name"));
                student.setAge(rs.getInt("age"));
                studentList.add(student);
            }
            return studentList;
    }
    public void addStudent () throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into students (first_name, middle_name, last_name, age) VALUES (?,?, ?, ?);");
        System.out.println("---------------");
        System.out.println("Введите имя студента: ");
        String studentName = scanner.next();
        System.out.println("Введите отчество студента: ");
        String studentMiddleName = scanner.next();
        System.out.println("Введите фамилию студента: ");
        String studentLastName = scanner.next();
        System.out.println("Введите возраст студента: ");
        int studentAge= scanner.nextShort();
        ps.setString(1, studentName);
        ps.setString(2, studentMiddleName);
        ps.setString(3, studentLastName);
        ps.setInt(4, studentAge);
        ps.executeUpdate();
        ps.close();
    }

    public void searchStudentByName () throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT first_name FROM students WHERE first_name ILIKE ?");
        System.out.println("Введите имя студента: ");
        String studentName = scanner.next();
        ps.setString(1, "%"+studentName+"%");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("first_name"));
        }
        ps.close();
    }
}

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataBase db = new DataBase();
        db.connectToDB();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Показать список всех студентов");
            System.out.println("2. Добавить студента");
            System.out.println("3. Поиск студента по имени");
            System.out.println("4. Выйти");
            int command = scanner.nextInt();
            if (command == 1) {
                System.out.println(db.showStudentList());
            } else if (command == 2) {
                db.addStudent();
            } else if (command == 3) {
                db.searchStudentByName();
            } else if (command == 4) {
                System.exit(0);
            } else {
                System.err.println("Команда не распознана");
            }
        }
    }
}


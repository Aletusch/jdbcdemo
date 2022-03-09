import java.sql.*;

public class Jdbcdemo {
    public static void main(String[] args) {
        System.out.println("JDBC Demo!");
        //INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, 'Alexander Tusch', 'aletusch@tsn.at'), (NULL, 'Ricky Bantito', 'ricky.bantito@gangster.com ');
        selectAllDemo();
        insertStudentDemo("namedestudent","mail@mail.mail");
        selectAllDemo();
        UpdateStudentDemo(4,"neuername","neueEmail@mail.com");
        selectAllDemo();
        deleteStudentDemo(5);
        findAllByName();

    }

    private static void findAllByName() {
        System.out.println("Find all by name Demo mit JDBC");

        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";
        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung zur DB hergestellt!");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `student`WHERE LOWER(`student`.`name`) LIKE  `%tito%`");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("Student aus der DB: ID " + id + " Name: " + name + " EMAIL: " + email);

            }

        } catch (SQLException e) {
            System.out.println("Fehler beim Aufbau der Verbingung zur DB: " + e.getMessage());
        }
    }

    public static void deleteStudentDemo(int studentId) {
        System.out.println("Delete Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";
        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung zur DB hergestellt!");
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM `student`WHERE `student`.`id`= ? ");
            try {
                preparedStatement.setInt(1, studentId);
                int rowAffected =preparedStatement.executeUpdate();
                System.out.println("Anzahl der gelöschten Datensätze: " + rowAffected);

            } catch (SQLException ex) {
                System.out.println("Fehler im SQL Delete Statement: " + ex.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }
    }

    public static void UpdateStudentDemo(int id,String neuerName,String neueEmail) {
        System.out.println("Update Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";
        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung zur DB hergestellt!");
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE `student` SET `name` = ?, `email`= ? WHERE `student`.`id`=?");
            try {
                preparedStatement.setString(1, neuerName);
                preparedStatement.setString(2, neueEmail);
                preparedStatement.setInt(3, id);

                int affectedRows = preparedStatement.executeUpdate();
                System.out.println("Anzahl der aktualisierten Datensätze: " + affectedRows);

            } catch (SQLException ex) {
                System.out.println("Fehler im SQL Update Statement: " + ex.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }
    }

    public static void insertStudentDemo(String name,String mail) {
        System.out.println("Insert Demo mit JDBC");

        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";
        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung zur DB hergestellt!");
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, ?,?)");
            try {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, mail);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println(rowAffected + " Datensätze eingefügt");

            } catch (SQLException ex) {
                System.out.println("Fehler im SQL insert Statement: " + ex.getMessage());
            }


        } catch (SQLException e) {
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }

    }

    public static void selectAllDemo() {
        System.out.println("Select Demo mit JDBC");

        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";
        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung zur DB hergestellt!");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `student`");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("Student aus der DB: ID " + id + " Name: " + name + " EMAIL: " + email);

            }

        } catch (SQLException e) {
            System.out.println("Fehler beim Aufbau der Verbingung zur DB: " + e.getMessage());
        }
    }
}

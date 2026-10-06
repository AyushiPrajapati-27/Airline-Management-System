import java.sql.*;

public class Conn {

    Connection con;
    Statement statement;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/airlinemanagementsystem", "root", "W7301@jqir#"
            );

            statement = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
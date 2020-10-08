import java.sql.*;
import java.util.Properties;

public class Repository {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=codehub";

    //Database credentials
    static final String USERNAME = "sa";
    static final String PASSWORD = "passw0rd";

    private Connection conn;

    public Repository() {

        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //Establishing connection with the database
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connection with database established successfully...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        Statement stmt;
        String sql;
        try {
            stmt = conn.createStatement();
            //Check if table exists and delete!
            sql = "DROP TABLE IF EXISTS Student";
            stmt.executeUpdate(sql);

            //Create table
            sql = "create table Student (" +
                    "id int  PRIMARY KEY IDENTITY(1,1), " +
                    "fname nVARCHAR(255), " +
                    "lname nVARCHAR(255) )";
            stmt.executeUpdate(sql);
            System.out.println("-Table Student created....");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insert(){
        String sql = "INSERT INTO Student " +
                "(fname, lname) " +
                "VALUES (?,?)";

        PreparedStatement insertStudent = null;
        try {
            insertStudent = conn.prepareStatement(sql);

            insertStudent.setString(1, "Alexandros");
            insertStudent.setString(2, "Kanakis");
            insertStudent.executeUpdate();

            insertStudent.setString(1, "Ioannis");
            insertStudent.setString(2, "Daniil");
            insertStudent.executeUpdate();

            insertStudent.setString(1, "Ioannis");
            insertStudent.setString(2, "Klian");
            insertStudent.executeUpdate();

            System.out.println("-New students inserted into table Student...");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void query(){
        Statement stmt;
        String query = "SELECT * FROM Student";


        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while (result.next()){
                System.out.println("-Student[ "+
                        "Fname: "+result.getString("fname")+
                        " | Lname: "+result.getString("lname")+" ]"
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
}

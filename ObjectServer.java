import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

import com.mysql.cj.MysqlConnection;

public class ObjectServer {
         static String FirstName;
         static String LastName;
         static String Email;

    public static void Connect(int id) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        String query = "select * from student where id='" + id + "'";

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "root");

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery(query);

            // 4. Process the result set

            // We need to store the info from the DB in an array wala haja hakka
            while (myRs.next()) {
                FirstName = myRs.getString("first_name");
                LastName = myRs.getString("last_name");
                Email = myRs.getString("email");
                   
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (myRs != null) {
                    myRs.close();
                }

                if (myStmt != null) {
                    myStmt.close();
                }

                if (myConn != null) {
                    myConn.close();
                }
            }
        }

        public static void main(String[] argv) throws Exception {
        // why 5000 ?
        ServerSocket s = new ServerSocket(5000);
        System.out.println("Server started");
        while (true) {
        Socket t = s.accept();// wait for client to connect
        System.out.println("Server Connected");
        ObjectInputStream b = new ObjectInputStream(t.getInputStream());
        //so il 3 ostor he4om need to change i dont know how but we nee to get those
        //infos ml bd
        int received = b.readInt();
        Connect(received);
        PrintWriter output = new PrintWriter(t.getOutputStream(), true);

        // We need to formulate some kind of message from the data we got frm db
        output.println("Nom : " + FirstName + " Prenom : "+ LastName  + " Email : "+ Email + "\n");
        b.close();
        output.close();
        t.close();
        }

        }

    }
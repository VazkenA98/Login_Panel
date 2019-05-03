import javax.swing.*;
import java.io.File;
import java.sql.*;

public class JDBC extends PassCode {
    static Connection conn;
    static int newid;

    public JDBC(int op, String name, String email, String id, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/uni";
            conn = DriverManager.getConnection(url, "root", "admin");
            int a = op;
            switch (a) {
                case 1:
                    doSelectTest(email, password);
                    break;
                case 2:
                    doInsertTest(name, email, id, password);
                    break;
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println(ex.getMessage());
        } catch (
                SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void doSelectTest(String email, String password) {
        System.out.println("[OUTPUT FROM SELECT]");
        String query = "SELECT * FROM uni.accounts";
        Statement st = null;
        ResultSet reset = null;
        String result = "";

        try {
            st = conn.createStatement();
            reset = st.executeQuery(query);
            String encryptedPass = finalShape(password);
            while (reset.next()) {
                if (email.equals(reset.getString("email")) && encryptedPass.equals(reset.getString("password"))) {
                    int id = reset.getInt("id");
                    String namesql = reset.getString("name");
                    String emailsql = reset.getString("email");
                    String useridsql = reset.getString("userid");
                    String passwordsql = reset.getString("password");
                    result = "Name : " + namesql + "\n" + "E-mail : " + emailsql + "\n" + "ID : " + useridsql + "\n" + "Password : " + passwordsql;
                }

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(result);
        JOptionPane.showMessageDialog(null, result);
    }


    public static String getsqlPath() {
        try {
            // Create a file object
            File f = new File("c:\\program files\\MySQL\\MySQL Workbench 8.0 CE");

            // Get the path of the given file f
            String path = f.getPath();

            // Display the file path of the file object
            return path;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "";
    }


    //////////////////////////////////////////////////////////////////////////////////////////
    private void doInsertTest(String name, String email, String id, String password) {
        System.out.print("\n[Performing INSERT] ... ");
        String sql = "INSERT INTO accounts(id,name,email,userid,password) VALUES(?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from uni.accounts");
                while (rs.next()) {

                    newid = rs.getInt(1) + 1;
                }

            } catch (Exception e) {
                System.out.println(e);
            }
            String passEncrypted = finalShape(password);

            pstmt.setInt(1, newid);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, id);
            pstmt.setString(5, passEncrypted);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}

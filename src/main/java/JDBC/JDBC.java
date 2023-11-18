package JDBC;

import java.sql.*;

public class JDBC {
    private Connection con;
    private static final String URL = "jdbc:mysql://localhost:3306/contacts";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public JDBC() {

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void finalize() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 查询根据名字
    public ResultSet select_name(String name) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE name = ?";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);

        ResultSet rs = pstmt.executeQuery();

        return rs;
    }

    // 查询根据电话号码
    public ResultSet select_phone(String phone) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE phone = ?";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, phone);

        ResultSet rs = pstmt.executeQuery();

        return rs;
    }

    // 新增
    public Boolean insert(String name, String phone, String email, String address) {
        String sql = "INSERT INTO contacts (name, phone, email, address) VALUES (?,?,?,?)";

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 修改名字
    public void update_name(int id, String name) throws SQLException{
        String sql = "UPDATE contacts SET name = ? WHERE id = ?";

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 修改电话
    public void update_phone(int id, String phone) throws SQLException{
        String sql = "UPDATE contacts SET phone = ? WHERE id = ?";

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, phone);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //修改邮箱
    public void update_email(int id, String email) throws SQLException{
        String sql = "UPDATE contacts SET email = ? WHERE id = ?";

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //修改地址
    public void update_address(int id, String address) throws SQLException{
        String sql = "UPDATE contacts SET address = ? WHERE id = ?";

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, address);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除
    public Boolean delete(int id) {
        String sql = "DELETE FROM contacts WHERE id = ?";

        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

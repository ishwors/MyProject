/**
 * CONTROLLER
 */
package com.apex.ishwor.dao;

import com.apex.ishwor.database.ConnectionFactory;
import com.apex.ishwor.dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserDAO {

    Connection con;
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    String[] searchColumnNames = {"ID", "Full Name", "User Name", "Contact", "Email"};//Variable set for searchColumnNames on SearchUser();

    public UserDAO() {
        con = new ConnectionFactory().getConnection();
    }

    /**
     *
     * @param user
     */
    public void adduser(UserDTO user) {

        String query = "INSERT INTO user values(?,?,?,?,?,?)";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getFullname());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getContact());
            pstmt.setString(6, user.getEmail());

            int result = pstmt.executeUpdate();

            if (result != -1) {
                JOptionPane.showMessageDialog(null, "Your record inserted successfully.");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     *
     * @return
     */
    public ResultSet getQueryResult() {
        String sql = "SELECT * FROM user";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    /**
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData md = rs.getMetaData();
        Vector<String> cn = new Vector<String>();
        int count = md.getColumnCount();

        for (int i = 1; i <= count; i++) {
            cn.add(md.getColumnName(i));
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();

            for (int ci = 1; ci <= count; ci++) {
                vector.add(rs.getObject(ci));
            }

            data.add(vector);
        }
        return new DefaultTableModel(data, cn);
    }

    public UserDTO editUser(JTable table) {

        UserDTO ue = new UserDTO();
        ue.setId((Integer) table.getValueAt(table.getSelectedRow(), 0));
        ue.setFullname((String) table.getValueAt(table.getSelectedRow(), 1));
        ue.setUsername((String) table.getValueAt(table.getSelectedRow(), 2));
        ue.setPassword((String) table.getValueAt(table.getSelectedRow(), 3));
        ue.setContact((String) table.getValueAt(table.getSelectedRow(), 4));
        ue.setEmail((String) table.getValueAt(table.getSelectedRow(), 5));

        return ue;

    }

    public void updateUser(UserDTO userUpdate) {

        try {

            String query = "update user set id=?, fullname=?, username=?, contact=?, email=? where id=?";

            pstmt = con.prepareStatement(query);
            pstmt.setInt(6, userUpdate.getId());

            pstmt.setInt(1, userUpdate.getId());
            pstmt.setString(2, userUpdate.getFullname());
            pstmt.setString(3, userUpdate.getUsername());
            pstmt.setString(4, userUpdate.getContact());
            pstmt.setString(5, userUpdate.getEmail());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "One record updated.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void delete(String value) {

        try {
            String query = "delete from user where id=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, value);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "One record deleted.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public Vector<String> getColumnName() {
        String query = "SELECT id, fullname, username, contact, email FROM user ";
        Vector<String> columnNames = new Vector<String>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnNames;
    }

    public DefaultTableModel searchUser(String name, String value) throws Exception {
        String sql = "";
        if ("id".equalsIgnoreCase(name)) {
            sql = "SELECT id,fullname,username,contact,email FROM user WHERE " + name + "='" + Integer.parseInt(value) + "'";
        } else {
            sql = "SELECT id,fullname,username,contact,email FROM user WHERE " + name + "='" + value + "'";
        }
        //stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(searchColumnNames[column - 1]);
        }
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        if (!rs.next()) {
            JOptionPane.showMessageDialog(null, "No search found.");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();

                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
        }
        return new DefaultTableModel(data, columnNames);
    }

    public Vector<String> getCName() {

        Vector<String> columnNames = new Vector<String>();
        columnNames.add("ID");
        columnNames.add("Full Name");
        columnNames.add("User Name");
        columnNames.add("Contact");
        columnNames.add("Email");

        return columnNames;
    }

    public void ChangePassword(String username, String oldpassword, String newpassword) {
        try {
            String query = "update user set password=? where username=? and password=?";
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, newpassword);
            pstmt.setString(2, username);
            pstmt.setString(3, oldpassword);
            int result = pstmt.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Password Changed Successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "User doesn't exist.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}

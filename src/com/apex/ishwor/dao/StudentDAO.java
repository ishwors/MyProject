/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apex.ishwor.dao;

import com.apex.ishwor.database.ConnectionFactory;
import com.apex.ishwor.dto.StudentDTO;
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

/**
 *
 * @author Dell
 */
public class StudentDAO {

    Connection con;
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    String[] searchColumnNames = {"ID", "Name", "Gender", "Address", "Program", "Contact", "Email"};

    public StudentDAO() {
        con = new ConnectionFactory().getConnection();
    }

    /**
     *
     * @param s
     */
    public void addStudent(StudentDTO s) {
        String query = "INSERT INTO student value (?,?,?,?,?,?,?)";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, s.getId());
            pstmt.setString(2, s.getName());
            pstmt.setString(3, s.getGender());
            pstmt.setString(4, s.getAddress());
            pstmt.setString(5, s.getProgram());
            pstmt.setString(6, s.getContact());
            pstmt.setString(7, s.getEmail());
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Your Recor Inseerted.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @return
     */
    public ResultSet getQueryResult() {
        String sql = "SELECT * FROM student";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
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
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();

        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }

    public StudentDTO editStudent(JTable table) {

        StudentDTO se = new StudentDTO();

        se.setId((Integer) table.getValueAt(table.getSelectedRow(), 0));
        se.setName((String) table.getValueAt(table.getSelectedRow(), 1));
        se.setGender((String) table.getValueAt(table.getSelectedRow(), 2));
        se.setAddress((String) table.getValueAt(table.getSelectedRow(), 3));
        se.setProgram((String) table.getValueAt(table.getSelectedRow(), 4));
        se.setContact((String) table.getValueAt(table.getSelectedRow(), 5));
        se.setEmail((String) table.getValueAt(table.getSelectedRow(), 6));

        return se;

    }

    public void updateStudent(StudentDTO su) {

        try {
            String query = "update student set name=?, gender=?, address=?, program=?, contact=?, email=? where id=?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, su.getName());
            pstmt.setString(2, su.getGender());
            pstmt.setString(3, su.getAddress());
            pstmt.setString(4, su.getProgram());
            pstmt.setString(5, su.getContact());
            pstmt.setString(6, su.getEmail());
            pstmt.setInt(7, su.getId());

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "One record recorded.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void delete(String value) {
        try {
            String query = "delete from student where id=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, value);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "One record deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Search Student Section    
    public Vector<String> getColumnName() {
        String query = "SELECT id, name, gender, address, program, contact, email FROM student ";
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

    public DefaultTableModel searchStudent(String name, String value) throws Exception {
        String sql = "";
        if ("id".equalsIgnoreCase(name)) {
            sql = "SELECT id, name, gender, address, program, contact, email FROM student WHERE " + name + "='" + Integer.parseInt(value) + "'";
        } else {
            sql = "SELECT id, name, gender, address, program, contact, email FROM student WHERE " + name + "='" + value + "'";
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
        columnNames.add("Student Name");
        columnNames.add("Gender");
        columnNames.add("Address");
        columnNames.add("Program");
        columnNames.add("Contact");
        columnNames.add("Email");

        return columnNames;
    }

    /// {"ID", "Name", "Gender", "Address", "Program", "Contact", "Email"}
}

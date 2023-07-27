/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.dao;

import com.mycompany.schoolmanagementsystem.model.Student;
import com.mycompany.schoolmanagementsystem.utility.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rrieshavv
 */
public class DatabaseOperation {
    public int save(Student student){
        int countRow = 0;
        try {
            Connection conn = DatabaseConnection.getDatabaseConnection();
            String insert_query = "insert into student(name, address, country, email, password) values(?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(insert_query);
            statement.setString(1,student.getName());
            statement.setString(2,student.getAddress());
            statement.setString(3,student.getCountry());
            statement.setString(4,student.getEmail());
            statement.setString(5,student.getPassword());
            countRow = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countRow;
    }

    public ArrayList<Student> getAll() throws SQLException {
        Connection conn = DatabaseConnection.getDatabaseConnection();
        String view_query = "Select * from student";
        PreparedStatement statement = conn.prepareStatement(view_query);
        ResultSet row = statement.executeQuery();
        
        ArrayList <Student> list = new ArrayList<>();
        
        while(row.next()){
            Student student = new Student();
            student.setId(row.getInt(1));
            student.setName(row.getString("name"));
            student.setAddress(row.getString("address"));
            student.setCountry(row.getString("country"));
            student.setEmail(row.getString("email"));
           
            
            list.add(student);
            
        }
        return list;
    }
}

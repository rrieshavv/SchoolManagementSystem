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

    public Student getById(int id) throws SQLException {
        Connection con = DatabaseConnection.getDatabaseConnection();
        String query = "Select * from student where id=?";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, id);
        ResultSet row=st.executeQuery();
        
        Student student = new Student();
        
        if(row.next()){
            
            student.setName(row.getString("name"));
            student.setAddress(row.getString("address"));
            student.setCountry(row.getString("country"));
            student.setEmail(row.getString("email"));
            
        }
        return student;
    }

    public Student update(Student student, int id) throws SQLException {
        Connection con = DatabaseConnection.getDatabaseConnection();
        String update_query = "update student set name=?, address=?, country=?,email=? where id=?";
        PreparedStatement st = con.prepareStatement(update_query);
        st.setString (1, student.getName());
        st.setString(2, student.getAddress());
        st.setString(3, student.getCountry());
        st.setString(4, student.getEmail());
        st.setInt(5, id);
        
        int row = st.executeUpdate();
        Student updateStudent=null;
        if(row>=1){
            updateStudent=this.getById(id);
        }
        return updateStudent;
        
    }
}

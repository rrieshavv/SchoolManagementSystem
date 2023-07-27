/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.controller;

import com.mycompany.schoolmanagementsystem.dao.DatabaseOperation;
import com.mycompany.schoolmanagementsystem.model.Student;
import com.mycompany.schoolmanagementsystem.utility.PasswordHash;
import com.mycompany.schoolmanagementsystem.view.AdminDashboard;
import com.mycompany.schoolmanagementsystem.view.StudentRegisterForm;
import com.mycompany.schoolmanagementsystem.view.ViewStudentData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rrieshavv
 */
public class StudentManagementController {
    private static StudentManagementController controller;
    private DatabaseOperation operation;
    private ViewStudentData viewData;
    //contructor defined
    private StudentManagementController()
    {
        operation = new DatabaseOperation();
        viewData = new ViewStudentData();
    }
    public static StudentManagementController getControllerObject()
    {
        if(controller == null)
        {
            controller = new StudentManagementController();
        }
        return controller;
    }

    public void getStudentRegisterForm() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentRegisterForm().setVisible(true);
            }
        });
    }

    public void getStudentFormData(StudentRegisterForm form) {
        String name = form.getNameTextField().getText();
        String address = form.getAddressTextField().getText();
        String country = (String) form.getjComboBox1().getSelectedItem();
        String email = form.getEmailTextField().getText();
        String password = String.valueOf(form.getPasswordField1().getPassword());
        String hashPassword = PasswordHash.getPasswordHash(password);
        
        Student student = new Student(name,address,country,email,hashPassword);
        int count = this.operation.save(student);
        
        if(count>=1)
        {
            form.dispose();
            new AdminDashboard().setVisible(true);
            
        }
        else
        {
            JOptionPane.showMessageDialog(form, "Please input data!");
            form.setVisible(true);
        }
    }

    public void getData() {
        
        try {
            ArrayList<Student> list = this.operation.getAll();
            DefaultTableModel model = (DefaultTableModel) this.viewData.getViewTable().getModel();
            for(Student student:list)
            {
                model.addRow(new Object[]{student.getId(), student.getName(),student.getAddress(),student.getCountry(),student.getEmail()});
            }
            this.viewData.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

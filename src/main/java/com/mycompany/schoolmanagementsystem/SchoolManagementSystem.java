/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.schoolmanagementsystem;

import com.mycompany.schoolmanagementsystem.view.AdminDashboard;

/**
 *
 * @author Rrieshavv
 */
public class SchoolManagementSystem {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }
    
}

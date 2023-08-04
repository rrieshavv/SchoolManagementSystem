/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.utility;

import com.mycompany.schoolmanagementsystem.SchoolManagementSystem;
import com.mycompany.schoolmanagementsystem.controller.StudentManagementController;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;



/**
 *
 * @author Rrieshavv
 */
public class EditCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton editButton;
    private int id;
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public EditCellEditor(){
        this.editButton = new JButton("edit");
        editButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                
                StudentManagementController  controller = StudentManagementController.getControllerObject();
                
                controller.findById(id);
            }
        
        });
    }

    @Override 
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
        this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
        return editButton;
    }

    @Override
    public Object getCellEditorValue(){
        return null;
    }   
}

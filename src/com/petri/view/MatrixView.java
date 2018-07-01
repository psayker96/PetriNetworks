/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petri.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.*;

/**
 *
 * @author User
 */
public class MatrixView extends JDialog {
    private static final Dimension SIZE = new Dimension(200, 90);
    private JTextField textField;
    private JButton confirmButton;
    private JButton cancelButton;

    public MatrixView() {
        initDialog();
        initComponents();
    }

    private void initDialog() {
        setTitle("Enter marking");
        setModal(true);
        setMinimumSize(SIZE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 35));
        confirmButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        add(textField, BorderLayout.PAGE_START);
        add(confirmButton, BorderLayout.LINE_START);
        add(cancelButton, BorderLayout.LINE_END);
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JTextField getTextField() {
        return textField;
    }

}

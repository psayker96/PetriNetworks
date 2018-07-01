/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petri.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author User
 */
public class PetriView extends JFrame {
        private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu analysisMenu;
    private JMenuItem newFileMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem matrixMenu;
    private ToolBar toolBar;
    private JPanel bottomPanel;
    private Drawer drawer;

    public PetriView() {
        initFrame();
        initComponents();
        setLocationRelativeTo(null);
    }
    private void initFrame() {
        setMinimumSize(new Dimension(1170, 700));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void initComponents() {
        initBottompanel();
        initDrawer();
    }

    private void initBottompanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        initMenu();
        initToolbar();
        add(bottomPanel, BorderLayout.NORTH);
    }

    private void initMenu() {
        menuBar = new JMenuBar();
        initFileMenu();
        initAnalysisMenu();
        bottomPanel.add(menuBar, BorderLayout.NORTH);
    }

    private void initFileMenu() {
        fileMenu = new JMenu("File");
        initFileSubMenus();
        menuBar.add(fileMenu);
    }

    private void initFileSubMenus() {
        newFileMenuItem = new JMenuItem("New");
        exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(newFileMenuItem);
        fileMenu.add(exitMenuItem);
    }

    private void initAnalysisMenu() {
        analysisMenu = new JMenu("Analysis");
        matrixMenu = new JMenuItem("Matrix");
        analysisMenu.add(matrixMenu);
        menuBar.add(analysisMenu);
    }

    private void initToolbar() {
        toolBar = new ToolBar();
        toolBar.setMinimumSize(new Dimension((int) getBounds().getSize().getWidth(), 70));
        bottomPanel.add(toolBar, BorderLayout.CENTER);
    }

    private void initDrawer() {
        drawer = new Drawer();
        add(drawer, BorderLayout.CENTER);
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public Drawer getDrawer() {
        return drawer;
    }

    public JMenuItem getMatrixMenu() {
        return matrixMenu;
    }

}

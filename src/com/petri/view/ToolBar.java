/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petri.view;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author User
 */
public class ToolBar extends JToolBar {
    private JButton newButton;
    private JButton runButton;
    private JButton stopButton;
    private ButtonGroup buttonGroup;
    private JToggleButton dragButton;
    private JToggleButton deleteTransitionButton;
    private JToggleButton addTransitionButton;
    private JToggleButton deleteTokenButton;
    private JToggleButton addTokenButton;
    private JToggleButton deleteArcButton;
    private JToggleButton addArcButton;
    private JToggleButton deletePlaceButton;
    private JToggleButton addPlaceButton;

    public ToolBar() {
        init();
    }

    private void init() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setRollover(true);
        buttonGroup = new ButtonGroup();
        initNewFileButton();
        initRunButton();
        initStopButton();
        initDragButton();
        initDeleteTransitionButton();
        initAddTransitionButton();
        initDeleteTokenButton();
        initAddTokenButton();
        initDeleteArcButton();
        initAddArcButton();
        initDeletePlaceButton();
        initAddPlaceButton();
    }

    private void initNewFileButton() {
        newButton = new JButton();
        newButton.setIcon(new ImageIcon(ToolBar.class.getClassLoader().getResource("newrks.png")));
        newButton.setText("New");
        newButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGroup.add(newButton);
        add(newButton);
    }

    private void initRunButton() {
        runButton = new JButton();
        runButton.setIcon(new ImageIcon(ToolBar.class.getClassLoader().getResource("Starts.png"))); // NOI18N
        runButton.setText("Run");
        runButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        runButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGroup.add(runButton);
        add(runButton);
    }

    private void initStopButton() {
        stopButton = new JButton();
        stopButton.setIcon(new ImageIcon(ToolBar.class.getClassLoader().getResource("Stop.png"))); // NOI18N
        stopButton.setText("Stop");
        stopButton.setFocusable(false);
        stopButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stopButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGroup.add(stopButton);
        add(stopButton);
    }

    private void initDragButton() {
        dragButton = new JToggleButton();
        dragButton.setIcon(new javax.swing.ImageIcon(ToolBar.class.getClassLoader().getResource("moveeworks.png"))); // NOI18N
        dragButton.setText("Drag Element");
        dragButton.setFocusable(false);
        dragButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dragButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGroup.add(dragButton);
        add(dragButton);
    }

    private void initDeleteTransitionButton() {
        deleteTransitionButton = new JToggleButton();
        deleteTransitionButton.setIcon(new javax.swing.ImageIcon(ToolBar.class.getClassLoader().getResource("transitionerases.png"))); // NOI18N
        deleteTransitionButton.setText("Del Transition");
        deleteTransitionButton.setFocusable(false);
        deleteTransitionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteTransitionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGroup.add(deleteTransitionButton);
        add(deleteTransitionButton);
    }

    private void initAddTransitionButton() {
        addTransitionButton = new JToggleButton();
        addTransitionButton.setIcon(new javax.swing.ImageIcon(ToolBar.class.getClassLoader().getResource("transition.png"))); // NOI18N
        addTransitionButton.setText("Add Transition");
        addTransitionButton.setFocusable(false);
        addTransitionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addTransitionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGroup.add(addTransitionButton);
        add(addTransitionButton);
    }

    private void initDeleteTokenButton() {
        deleteTokenButton = new JToggleButton();
        deleteTokenButton.setIcon(new javax.swing.ImageIcon(ToolBar.class.getClassLoader().getResource("erasepe_arc_up_gestureworks.png"))); // NOI18N
        deleteTokenButton.setText("Del Token");
        deleteTokenButton.setFocusable(false);
        deleteTokenButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteTokenButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGroup.add(deleteTokenButton);
        add(deleteTokenButton);
    }

    private void initAddTokenButton() {
        addTokenButton = new JToggleButton();
        addTokenButton.setIcon(new javax.swing.ImageIcon(ToolBar.class.getClassLoader().getResource("stroke_shape_arc_up_gestureworks.png"))); // NOI18N
        addTokenButton.setText("Add Token");
        addTokenButton.setFocusable(false);
        addTokenButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addTokenButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGroup.add(addTokenButton);
        add(addTokenButton);
    }

    private void initDeleteArcButton() {
        deleteArcButton = new JToggleButton();
        deleteArcButton.setIcon(new javax.swing.ImageIcon(ToolBar.class.getClassLoader().getResource("arcs.png"))); // NOI18N
        deleteArcButton.setText("Del Arc");
        deleteArcButton.setFocusable(false);
        deleteArcButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteArcButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGroup.add(deleteArcButton);
        add(deleteArcButton);
    }

    private void initAddArcButton() {
        addArcButton = new JToggleButton();
        addArcButton.setIcon(new javax.swing.ImageIcon(ToolBar.class.getClassLoader().getResource("arcadds.png"))); // NOI18N
        addArcButton.setText("Add Arc");
        addArcButton.setFocusable(false);
        addArcButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addArcButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGroup.add(addArcButton);
        add(addArcButton);
    }

    private void initDeletePlaceButton() {
        deletePlaceButton = new JToggleButton();
        deletePlaceButton.setIcon(new javax.swing.ImageIcon(ToolBar.class.getClassLoader().getResource("2s.png"))); // NOI18N
        deletePlaceButton.setText("Del Place");
        deletePlaceButton.setFocusable(false);
        deletePlaceButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deletePlaceButton.setName(""); // NOI18N
        deletePlaceButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGroup.add(deletePlaceButton);
        add(deletePlaceButton);
    }

    private void initAddPlaceButton() {
        addPlaceButton = new JToggleButton();
        addPlaceButton.setIcon(new javax.swing.ImageIcon(ToolBar.class.getClassLoader().getResource("3s.png"))); // NOI18N
        addPlaceButton.setText("Add Place");
        addPlaceButton.setFocusable(false);
        addPlaceButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addPlaceButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGroup.add(addPlaceButton);
        add(addPlaceButton);
    }

    public JButton getNewButton() {
        return newButton;
    }

    public JToggleButton getAddPlaceButton() {
        return addPlaceButton;
    }

    public JToggleButton getDeletePlaceButton() {
        return deletePlaceButton;
    }

    public JToggleButton getAddTransitionButton() {
        return addTransitionButton;
    }

    public JToggleButton getDragButton() {
        return dragButton;
    }

    public JToggleButton getAddTokenButton() {
        return addTokenButton;
    }

    public JToggleButton getAddArcButton() {
        return addArcButton;
    }

    public JButton getRunButton() {
        return runButton;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petri.controller;
import com.petri.shape.*;
import com.petri.view.MatrixView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class MatrixController {
    
    private MatrixView view;
    private int[][] c;
    private int[][] f;
    private int[][] h;
    private int[] initMarking;

    public void calculateMatrix(List<Place> places, List<Transition> transitions) {
        f = calcF(places, transitions);
        h = calcH(places, transitions);
        c = subtractionOfMatrices(h, f);
        System.out.println("Matrix c");
        printArray(c);
        initMarking = calcInitMarking(places);
    }

    private int[] calcInitMarking(List<Place> places) {
        int[] result = new int[places.size()];
        System.out.println("Init marking matrix :");
        for (int i = 0; i < places.size(); i++) {
            if (!places.get(i).getTokens().isEmpty()) {
                result[i] = 1;
            }
            System.out.print(result[i] + " ");
        }
        System.out.println("");
        return result;
    }

    private int[][] calcF(List<Place> places, List<Transition> transitions) {
        int[][] result = new int[places.size()][transitions.size()];
        for (Place place : places) {
            for (Arc arc : place.getOutputArcs()) {
                for (Transition transition : transitions) {
                    if (arc.getTransition().equals(transition)) {
                        result[places.indexOf(place)][transitions.indexOf(transition)] = 1;
                    }
                }
            }
        }


        System.out.println("Matrix F :");
        printArray(result);
        result = transposeMatrix(result);
        System.out.println("Matrix Ft");
        printArray(result);
        return result;
    }

    private int[][] calcH(List<Place> places, List<Transition> transitions) {
        int[][] result = new int[transitions.size()][places.size()];
        for (Transition transition : transitions) {
            for (Arc arc : transition.getOutputArcs()) {
                for (Place place : places) {
                    if (arc.getPlace().equals(place)) {
                        result[transitions.indexOf(transition)][places.indexOf(place)] = 1;
                    }
                }
            }
        }
        System.out.println("Matrix H : ");
        printArray(result);
        return result;
    }

    private int[][] subtractionOfMatrices(int[][] a, int[][] b) {
        int m = a.length;
        int n = a[m - 1].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    private int[][] transposeMatrix(int[][] input) {
        int m = input.length;
        int n = input[m - 1].length;
        int[][] result = new int[n][m];

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                result[j][i] = input[i][j];
            }
        }
        return result;
    }

    private void printArray(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void show() {
        initView();
        view.setVisible(true);
    }

    private void initView() {
        view = new MatrixView();
        initConfirmButton();
        initCancelButton();
    }

    private void initConfirmButton() {
        view.getConfirmButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTextField().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Matrix not entered");
                } else {
                    String text = view.getTextField().getText();
                    String[] split = text.split(" ");
                    int[] vector = new int[split.length];
                    for (int i = 0; i < split.length; i++) {
                        vector[i] = Integer.valueOf(split[i]);
                    }
                    System.out.println("Entered vector - ");
                    for (String string : split) {
                        System.out.print(string + " ");
                    }
                    System.out.println("");

                    if (vector.length == c.length) {
                        int[] multiplyVectorToMatrix = multiplyVectorToMatrix(vector, c);

                        if (multiplyVectorToMatrix.length == initMarking.length) {
                            int[] result = summationVectors(initMarking, multiplyVectorToMatrix);

                            String resultString = "Result vector :";
                            for (int i : result) {
                                resultString += Integer.toString(i) + " ";
                            }
                            JOptionPane.showMessageDialog(view, resultString);
                        }
                    } else {
                        JOptionPane.showMessageDialog(view, "Vector size is incompatible");
                    }
                    closeView();
                }

            }
        });
    }

    private int[] summationVectors(int[] first, int[] second) {
        int[] result = new int[first.length];
        for (int i = 0; i < first.length; i++) {
            result[i] = first[i] + second[i];
        }
        return result;
    }

    private int[] multiplyVectorToMatrix(int[] vector, int[][] matrix) {
        int m = matrix.length;
        int n = matrix[m - 1].length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = 0; j < matrix.length; j++) {
                temp += matrix[j][i] * vector[j];
            }
            result[i] = temp;
        }

        System.out.println("Multiply ^");
        for (int i : result) {
            System.out.print(i + " ");
        }
        return result;
    }

    private void initCancelButton() {
        view.getCancelButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                closeView();
            }
        });
    }

    private void closeView() {
        view.setVisible(false);
        view.dispose();
    }

}

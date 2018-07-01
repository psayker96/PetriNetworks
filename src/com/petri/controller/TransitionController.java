/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petri.controller;
import com.petri.shape.Transition;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class TransitionController {
        private List<Transition> transitions;

    public TransitionController() {
        transitions = new ArrayList<Transition>();
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    public void drawTransitions(Graphics graphics) {
        for (int i = 0; i < transitions.size(); i++) {
            transitions.get(i).draw(graphics);
            if (graphics != null) {
                graphics.drawString("T" + i, (int) transitions.get(i).getShape().getX(), (int) transitions.get(i).getShape().getY());
            }
        }
    }

    public Transition getObjectFromPoint(Point point) {
        for (Transition transition : transitions) {
            if (transition.getShape().contains(point)) {
                return transition;
            }
        }
        return null;
    }

    List<Transition> getTransitions() {
        return transitions;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petri.shape;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Transition implements ShapeI {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 70;
    private Rectangle shape;
    private List<Arc> outputArcs;
    private List<Arc> inputArcs;
    private boolean preferred;
    
    public Transition(Point point) {
        shape = new Rectangle(point.getLocation().x, point.getLocation().y, WIDTH, HEIGHT);
        outputArcs = new ArrayList<Arc>();
        inputArcs = new ArrayList<Arc>();
    }

    @Override
    public void draw(Graphics graphics) {
        if (graphics != null) {
            graphics.setColor(Color.BLACK);
            ((Graphics2D) graphics).draw(shape);
        }
    }

    @Override
    public Rectangle getShape() {
        return shape;
    }

    public void addArc(Arc arc) {
        if (arc.isFromPlace()) {
            inputArcs.add(arc);
        } else {
            outputArcs.add(arc);
        }
    }

    public List<Arc> getOutputArcs() {
        return outputArcs;
    }

    public List<Arc> getInputArcs() {
        return inputArcs;
    }

    @Override
    public void setLocation(Point point) {
        shape.setLocation(point);
        for (Arc arc : outputArcs) {
            arc.setTransition(this);
        }
        for (Arc arc : inputArcs) {
            arc.setTransition(this);
        }
    }

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

}

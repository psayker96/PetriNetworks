/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petri.shape;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Place implements ShapeI {
    public static int DIAMETER = 70;
    private Ellipse2D shape;
    private List<Token> tokens;
    private List<Arc> inputArcs;
    private List<Arc> outputArcs;

    public Place(Point point) {
        shape = new Ellipse2D.Double(point.getLocation().x, point.getLocation().y, DIAMETER, DIAMETER);
        tokens = new ArrayList<Token>();
        outputArcs = new ArrayList<Arc>();
        inputArcs = new ArrayList<Arc>();
    }

    @Override
    public void draw(Graphics g) {
        if (g != null) {
            Graphics2D graphics = (Graphics2D) g;
            g.setColor(Color.BLACK);
            graphics.draw(shape);
            for (Token token : tokens) {
                token.draw(graphics);
            }
            for (Arc arc : outputArcs) {
                arc.draw(g);
            }
            for (Arc arc : inputArcs) {
                arc.draw(g);
            }
        }
    }

    @Override
    public Ellipse2D getShape() {
        return shape;
    }

    @Override
    public void setLocation(Point point) {
        shape.setFrame(point.getX(), point.getY(), DIAMETER, DIAMETER);
        for (Token token : tokens) {
            token.setLocation(point);
        }
        for (Arc arc : outputArcs) {
            arc.setPlace(this);
        }
        for (Arc arc : inputArcs) {
            arc.setPlace(this);
        }
    }

    public void addToken(Token token) {
        token.calcOffset(this);
        tokens.add(token);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void addArc(Arc arc) {
        if (arc.isFromPlace()) {
            outputArcs.add(arc);
        } else {
            inputArcs.add(arc);
        }
    }

    public List<Arc> getOutputArcs() {
        return outputArcs;
    }

    public List<Arc> getInputArcs() {
        return inputArcs;
    }

}

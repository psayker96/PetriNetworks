/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petri.shape;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author User
 */
public class Token implements ShapeI {
    private static final int DIAMETER = 15;
    private Ellipse2D shape;
    private int xOffset;
    private int yOffset;

    public Token(Point point) {
        shape = new Ellipse2D.Double(point.getLocation().x, point.getLocation().y, DIAMETER, DIAMETER);
    }

    @Override
    public void draw(Graphics graphics) {
        if (graphics != null) {
            ((Graphics2D) graphics).draw(shape);
        }
    }

    @Override
    public Ellipse2D getShape() {
        return shape;
    }

    @Override
    public void setLocation(Point point) {
        shape.setFrame(point.getX() - xOffset, point.getY() - yOffset, DIAMETER, DIAMETER);
    }

    public void calcOffset(Place place) {
        xOffset = (int) (place.getShape().getX() - shape.getX());
        yOffset = (int) (place.getShape().getY() - shape.getY());
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public Token copy() {
        Token token = new Token(new Point((int) shape.getX(), (int) shape.getY()));
        token.setxOffset(xOffset);
        token.setyOffset(yOffset);
        return token;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petri.shape;
import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author User
 */
public class Arc implements ShapeI {
    private final int ARR_SIZE = 6;
    private Line2D shape;
    private boolean fromPlace;
    private Place place;
    private Transition transition;

    public Arc(Place place) {
        shape = new Line2D.Double(place.getShape().getCenterX(), place.getShape().getCenterY(),
                place.getShape().getCenterX(), place.getShape().getCenterY());
        this.place = place;
        fromPlace = true;
    }

    public Arc(Transition transition) {
        shape = new Line2D.Double(transition.getShape().getCenterX(), transition.getShape().getCenterY(),
                transition.getShape().getCenterX(), transition.getShape().getCenterY());
        this.transition = transition;
        fromPlace = false;
    }

    @Override
    public void draw(Graphics g) {
        if (g != null) {
            g.setColor(Color.BLACK);
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.draw(shape);
            double dx = shape.getX2() - shape.getX1(), dy = shape.getY2() - shape.getY1();
            double angle = Math.atan2(dy, dx);
            int len = (int) Math.sqrt(dx * dx + dy * dy);
            AffineTransform at = AffineTransform.getTranslateInstance(shape.getX1(), shape.getY1());
            at.concatenate(AffineTransform.getRotateInstance(angle));
            Shape arrow = at.createTransformedShape(new Polygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                    new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4));
            graphics2D.fill(arrow);
        }
    }

    @Override
    public Line2D getShape() {
        return shape;
    }

    @Override
    public void setLocation(Point point) {
        shape.setLine(shape.getP1(), point);
    }

    public Place getPlace() {
        return place;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setPlace(Place place) {
        this.place = place;
        if (fromPlace) {
            shape.setLine(new Point2D.Double(place.getShape().getCenterX(), place.getShape().getCenterY()), shape.getP2());
        } else {
            shape.setLine(shape.getP1(), new Point2D.Double(place.getShape().getCenterX(), place.getShape().getCenterY()));
        }
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
        if (fromPlace) {
            shape.setLine(shape.getP1(), new Point2D.Double(transition.getShape().getCenterX(), transition.getShape().getCenterY()));
        } else {
            shape.setLine(new Point2D.Double(transition.getShape().getCenterX(), transition.getShape().getCenterY()), shape.getP2());
        }
    }

    public void addToPlaceAndTrnasition() {
        place.addArc(this);
        transition.addArc(this);
    }

    public boolean isFromPlace() {
        return fromPlace;
    }

}

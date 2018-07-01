/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petri.controller;
import com.petri.shape.*;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class PlaceController {
        private List<Place> places;

    public PlaceController() {
        places = new ArrayList<Place>();
    }

    public void addPlace(Place place) {
        places.add(place);
    }

    public void drawPlaces(Graphics graphics) {
        for (int i = 0; i < places.size(); i++) {
            places.get(i).draw(graphics);
            if (graphics != null) {
                graphics.drawString("P" + i, (int) places.get(i).getShape().getX(), (int) places.get(i).getShape().getY());
            }
        }
    }

    public void addToken(Point point) {
        Token token = new Token(point);
        for (Place place : places) {
            if (place.getShape().contains(token.getShape().getBounds())) {
                place.addToken(token);
                return;
            }
        }
    }

    public Place getObjectFromPoint(Point point) {
        for (Place place : places) {
            if (place.getShape().contains(point)) {
                return place;
            }
        }
        return null;
    }

    List<Place> getPlaces() {
        return places;
    }

}

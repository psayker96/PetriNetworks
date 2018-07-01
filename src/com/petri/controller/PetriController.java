/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petri.controller;
import com.petri.shape.*;
import com.petri.view.PetriView;
import com.petri.view.TransitionPreferPopup;
import java.awt.Point;
import java.awt.event.*;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author User
 */
public class PetriController {
        private PetriView view;
    private State state;
    private PlaceController placeController;
    private TransitionController transitionController;
    private ShapeI shapeToDrag;
    private Arc arcToDraw;

    public PetriController() {
        initSubControllers();
        initView();
        initDrawer();
    }

    private void initSubControllers() {
        placeController = new PlaceController();
        transitionController = new TransitionController();
    }

    private void initView() {
        view = new PetriView();
        initNewButton();
        initAddPlaceButton();
        initDeletePlaceButton();
        initAddTransitionButton();
        initAddTokenButton();
        initAddArcButton();
        initDragButton();
        initRunButton();
        initMatrixMenu();
    }

    private void initNewButton() {
        view.getToolBar().getNewButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                placeController.getPlaces().clear();
                transitionController.getTransitions().clear();
                arcToDraw = null;
                shapeToDrag = null;
                redrawShapes();
            }
        });
    }

    private void initAddPlaceButton() {
        view.getToolBar().getAddPlaceButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setState(State.ADD_PLACE);
            }
        });
    }

    private void initDeletePlaceButton() {
        view.getToolBar().getDeletePlaceButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setState(State.DELETE_PLACE);
            }
        });
    }

    private void initAddTransitionButton() {
        view.getToolBar().getAddTransitionButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setState(State.ADD_TRANSITION);
            }
        });
    }

    private void initAddTokenButton() {
        view.getToolBar().getAddTokenButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setState(State.ADD_TOKEN);
            }
        });
    }

    private void initAddArcButton() {
        view.getToolBar().getAddArcButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setState(State.ADD_ARC);
            }
        });
    }

    private void initDragButton() {
        view.getToolBar().getDragButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setState(State.DRAG);
            }
        });
    }

    private void initRunButton() {
        view.getToolBar().getRunButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setState(State.RUN);
            }
        });
    }
    
    private void initMatrixMenu(){
        view.getMatrixMenu().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MatrixController controller = new MatrixController();
                controller.calculateMatrix(placeController.getPlaces(), transitionController.getTransitions());
                controller.show();
            }
        });
    }

    private void initDrawer() {
        view.getDrawer().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (state == State.DRAG) {
                    chooseShapeToDraw(e.getPoint());
                } else if (state == State.ADD_ARC) {
                    Place place = placeController.getObjectFromPoint(e.getPoint());
                    if (place != null) {
                        arcToDraw = new Arc(place);
                        redrawShapes();
                        return;
                    }

                    Transition transition = transitionController.getObjectFromPoint(e.getPoint());
                    if (transition != null) {
                        arcToDraw = new Arc(transition);
                        redrawShapes();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Transition transition = transitionController.getObjectFromPoint(e.getPoint());
                if (transition != null && arcToDraw != null && arcToDraw.isFromPlace()) {
                    arcToDraw.setTransition(transition);
                    arcToDraw.addToPlaceAndTrnasition();
                    arcToDraw = null;
                    redrawShapes();
                    return;
                }

                Place place = placeController.getObjectFromPoint(e.getPoint());
                if (place != null && arcToDraw != null && !arcToDraw.isFromPlace()) {
                    arcToDraw.setPlace(place);
                    arcToDraw.addToPlaceAndTrnasition();
                    arcToDraw = null;
                    redrawShapes();
                    return;
                }
                arcToDraw = null;
                redrawShapes();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    Transition transition = transitionController.getObjectFromPoint(e.getPoint());
                    if (transition != null) {
                        TransitionPreferPopup popup = new TransitionPreferPopup(e.getLocationOnScreen());
                        popup.setTransition(transition);
                        popup.setVisible(true);
                    }
                    return;
                }
                if (state == State.ADD_PLACE) {
                    placeController.addPlace(new Place(e.getPoint()));
                    placeController.drawPlaces(view.getDrawer().getGraphics());
                } else if (state == State.ADD_TRANSITION) {
                    transitionController.addTransition(new Transition(e.getPoint()));
                    transitionController.drawTransitions(view.getDrawer().getGraphics());
                } else if (state == State.ADD_TOKEN) {
                    placeController.addToken(e.getPoint());
                    placeController.drawPlaces(view.getDrawer().getGraphics());
                } else if (state == State.DELETE_PLACE) {
                    Place place = placeController.getObjectFromPoint(e.getPoint());
                    if (place != null) {
                        placeController.getPlaces().remove(place);
                        redrawShapes();
                    }
                } else if (state == State.RUN) {
                    replaceToken(e.getPoint());
                }
            }
        });

        view.getDrawer().addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (state == State.DRAG && shapeToDrag != null) {
                    shapeToDrag.setLocation(e.getPoint());
                } else if (state == State.ADD_ARC && arcToDraw != null) {
                    arcToDraw.setLocation(e.getPoint());
                }
                redrawShapes();
            }
        });
    }

    private void replaceToken(Point point) {
        Place startPlace = placeController.getObjectFromPoint(point);
        if (startPlace != null && !startPlace.getTokens().isEmpty()) {
            if (!startPlace.getOutputArcs().isEmpty()) {
                Transition transition = getPreferredTransition(startPlace.getOutputArcs());
                if (!transition.getOutputArcs().isEmpty()) {
                    Token token = startPlace.getTokens().get(startPlace.getTokens().size() - 1);
                    startPlace.getTokens().remove(token);
                    for (Arc outputArc : transition.getOutputArcs()) {
                        Place finishPlace = outputArc.getPlace();
                        Token copyToken = token.copy();
                        copyToken.setLocation(new Point((int) finishPlace.getShape().getX(), (int) finishPlace.getShape().getY()));
                        finishPlace.addToken(copyToken);
                    }
                    redrawShapes();
                }
            }
        }
    }
    
    private Transition getPreferredTransition(List<Arc> arcs){
        Transition result= arcs.get(0).getTransition();
        for (Arc arc : arcs) {
            if(arc.getTransition().isPreferred()){
                result = arc.getTransition();
                break;
            }
        }
        return result;
    }

    private void chooseShapeToDraw(Point point) {
        ShapeI objectFromPoint = placeController.getObjectFromPoint(point);
        if (objectFromPoint != null) {
            shapeToDrag = objectFromPoint;
            return;
        }
        objectFromPoint = transitionController.getObjectFromPoint(point);
        if (objectFromPoint != null) {
            shapeToDrag = objectFromPoint;
        }
    }

    private void redrawShapes() {
        view.getDrawer().clearGraphics();
        placeController.drawPlaces(view.getDrawer().getGraphics());
        transitionController.drawTransitions(view.getDrawer().getGraphics());
        if (arcToDraw != null) {
            arcToDraw.draw(view.getDrawer().getGraphics());
        }
    }

    private void setState(State state) {
        this.state = state;
        System.out.println("State changed to " + this.state);
    }

    PetriView getView() {
        return view;
    }

    State getState() {
        return state;
    }

    PlaceController getPlaceController() {
        return placeController;
    }

    TransitionController getTransitionController() {
        return transitionController;
    }

    ShapeI getShapeToDrag() {
        return shapeToDrag;
    }

    Arc getArcToDraw() {
        return arcToDraw;
    }

    public static void main(String[] args) {
        new PetriController().getView().setVisible(true);
    }

}

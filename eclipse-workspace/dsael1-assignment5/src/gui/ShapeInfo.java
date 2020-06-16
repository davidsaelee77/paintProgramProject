/*
 * TCSS 305 – Autumn 2018
 */
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

/**
 * {@code} Custom class to assist with storing all shapes ever drawn.
 * 
 * @author David Saelee
 * @version 11/23/2018
 *
 */
public class ShapeInfo {
    /**
     * Stores shape value.
     */
    private final Shape myShape;
    /**
     * Stores color value.
     */
    private final Color myColor;
    /**
     * Stores brush thickness value.
     */
    private final int myThickness;

    /**
     * Shape constructor to set arguments to values.
     * 
     * @param theShape shape of object.
     * @param theColor color setting.
     * @param theThickness brush thickness setting.
     */
    public ShapeInfo(final Shape theShape, final Color theColor, final int theThickness) {
        myShape = theShape;
        myColor = theColor;
        myThickness = theThickness;
    }
    /**
     * Draw method used to set color, brush width,
     * and shape declarations to the graphics object.
     * 
     * @param theG2d graphic object.
     */
    public void draw(final Graphics2D theG2d) {
        theG2d.setPaint(myColor);
        theG2d.setStroke(new BasicStroke(myThickness));
        theG2d.draw(myShape);
    }
}

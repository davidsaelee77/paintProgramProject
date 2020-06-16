/*
 * TCSS 305 – Autumn 2018
 */
package tool;

import java.awt.Point;
import java.awt.Shape;

/**
 * {@code} Drawing tool interface to implement drawing methods.
 * 
 * @author David Saelee
 * @version 11/23/2018
 *
 */
public interface DrawingTools {

    /**
     * Returns drawn shape.
     * 
     * @return the shape that is drawn.
     */
    Shape getShape();

    /**
     * Sets the start point of the tool when mouse is clicked.
     * 
     * @param thePoint start point.
     */
    void setStartPoint(Point thePoint);

    /**
     * Sets the end point of the tool when mouse is clicked.
     * 
     * @param thePoint end point.
     */
    void setEndPoint(Point thePoint);
    /**
     * Method use to clear drawing panel. 
     * 
     */
    void clear();

}

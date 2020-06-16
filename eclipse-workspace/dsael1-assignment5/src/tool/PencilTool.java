/*
 * TCSS 305 – Autumn 2018
 */

package tool;

import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 * {@code} Extends abstract point logic class to map behavior to tool. Draws object whenever
 * the mouse is clicked, dragged and released on the panel.
 * 
 * @author David Saelee
 * @version 11/23/2018
 *
 */
public class PencilTool extends AbstractPointLogic {
    /**
     * Stores path object value.
     */
    private Path2D myPath;

    /**
     * Method determines if the start and end points are equal to initial anchor
     * point, returns null if it is. Creates a new path object if point is not
     * null.
     * 
     * @return path object.
     * 
     */
    @Override
    public Shape getShape() {

        if (getStartPoint().equals(NO_POINT) || getEndPoint().equals(NO_POINT)) {

            return null;

        }

        if (myPath == null) {

            myPath = new Path2D.Double();

            myPath.moveTo(getStartPoint().getX(), getStartPoint().getY());

        }
        myPath.lineTo(getEndPoint().getX(), getEndPoint().getY());

        return myPath;
    }

    /**
     * Method to set pencil path back to null when image is cleared from drawing
     * panel.
     * 
     */
    @Override
    public void clear() {
        super.clear();
        myPath = null;

    }

}

/*
 * TCSS 305 – Autumn 2018
 */

package tool;

import java.awt.Point;

/**
 * {@code} Abstract point class used to set the start and end points of a tool
 * when mouse is clicked, dragged and released.
 * 
 * @author David Saelee
 * @version 11/23/2018
 *
 */
public abstract class AbstractPointLogic implements DrawingTools {

    /**
     * The initial anchor point for shape drawn by tool.
     * 
     * **CODE BORROWED FROM PRESISTENT+GRAPHICS EXAMPLE ZIP FILE**
     */
    public static final Point NO_POINT = new Point(-100, -100);

    /**
     * Start point when mouse is clicked.
     */
    private Point myStartPoint;

    /**
     * End point when mouse is clicked.
     */
    private Point myEndPoint;

    /**
     * Constructor to set initial anchor point.
     */
    public AbstractPointLogic() {

        clear();
    }

    /**
     * Setter method to set the start point.
     * 
     * @param thePoint holds the start point value.
     * 
     */
    @Override
    public void setStartPoint(final Point thePoint) {
        myStartPoint = thePoint;
    }

    /**
     * Setter method to set the end point.
     * 
     * @param thePoint holds the end point value.
     * 
     */
    @Override
    public void setEndPoint(final Point thePoint) {
        myEndPoint = thePoint;
    }

    /**
     * Getter method to return start point.
     * 
     * @return the start point.
     */
    protected Point getStartPoint() {
        return myStartPoint;
    }

    /**
     * Getter method to return end point.
     * 
     * @return the end point.
     */
    protected Point getEndPoint() {
        return myEndPoint;
    }

    /**
     * Method used to reset point to anchor point when objects are cleared from
     * drawing panel.
     * 
     */
    public void clear() {

        myStartPoint = NO_POINT;
        myEndPoint = NO_POINT;
    }

}

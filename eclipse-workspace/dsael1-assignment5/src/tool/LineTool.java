/*
 * TCSS 305 – Autumn 2018
 */

package tool;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * {@code} Extends abstract point logic class to map behavior to tool. Draws line between two
 * points.
 * 
 * @author David Saelee
 * @version 11/23/2018
 *
 */
public class LineTool extends AbstractPointLogic {
    /**
     * Takes two point parameters(start, finish) and draws a line between the
     * two points.
     *
     * @return line between two points.
     */
    @Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }

}

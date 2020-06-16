/*
 * TCSS 305 – Autumn 2018
 */
package tool;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * {@code} Extends abstract point logic class to map behavior to tool. Draws a ellipse.
 * 
 * @author David Saelee
 * @version 11/23/2018
 *
 */
public class EllipseTool extends AbstractPointLogic {
    /**
     * Takes the start and end point divided by 2 then another corner to 
     * use the setFrameFrom center method to draw an ellipse.
     */
    @Override
    public Shape getShape() {

        final Point2D center =
                        new Point2D.Double((getStartPoint().getX() + getEndPoint().getX()) / 2,
                                           (getStartPoint().getY()
                                            + getEndPoint().getY()) / 2);

        final Ellipse2D.Double ellipse = new Ellipse2D.Double();

        ellipse.setFrameFromCenter(center, getEndPoint());

        return ellipse;
    }

}

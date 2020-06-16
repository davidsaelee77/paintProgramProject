/*
 * TCSS 305 – Autumn 2018
 */

package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import tool.DrawingTools;

/**
 * {@code} Drawing area consisting of mouse & mouse motion listeners.
 * 
 * @author David Saelee
 * @version 11/23/2018
 *
 */
public class DrawingPanel extends JPanel {
    /**
     * Serial version ID used during deserialization to verify serialized
     * objects have loaded classes.
     */
    private static final long serialVersionUID = 8023105703358161194L;
    /**
     * Stores value of shapes.
     */
    private final List<ShapeInfo> myShapes;
    /**
     * The Tool currently in use.
     */
    private DrawingTools myCurrentTool;
    /**
     * The thickness currently set.
     */
    private int myCurrentThickness;
    /**
     * The color currently set.
     */
    private Color myCurrentColor;
    /**
     * Stores the clear button components.
     */
    private JMenuItem myClearButton;

    /**
     * Constructs the drawing area and adds mouse motion and mouse listeners to
     * panel. Sets the cursor and constructs the array list for shapes. Passes
     * in the clear button GUI components to notify GUI when valid objects are
     * drawn on panel.
     * 
     * @param theClear clear button components.
     */
    public DrawingPanel(final JMenuItem theClear) {

        setBackground(Color.WHITE);

        final MouseInputAdapter mouseFunctions = new MyMouseFunction();
        addMouseListener(mouseFunctions);
        addMouseMotionListener(mouseFunctions);

        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        myShapes = new ArrayList<ShapeInfo>();

        this.myClearButton = theClear;
    }

    /**
     * Clears the shapes from drawing surface and updates.
     */
    public void clear() {
        myShapes.clear();
        repaint();
    }

    /**
     * Sets tool.
     * 
     * @param theTool current tool value.
     */
    public void setCurrentTool(final DrawingTools theTool) {
        myCurrentTool = theTool;
    }

    /**
     * Most of this method's code was taken from the HW's assignments hint
     * portion of the PDF file. Used RenderingHints to smooth the graphics on the panel (blurs
     * the pixels). Calls ShapeInfo custom class to store the arguments passed
     * in to notify GUI what to draw on panel. **CODE BORROWED FROM 2D POWER
     * POINT AND FROM HW ASSIGNMENT HINTS**
     * 
     * @param theGraphic place in super to keep functionality provided by
     *            JPanels implementation.
     */
    @Override
    public void paintComponent(final Graphics theGraphic) {
        super.paintComponent(theGraphic);
        final Graphics2D g2D = (Graphics2D) theGraphic;

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        for (ShapeInfo s : myShapes) {
            s.draw(g2D);
        }

        final ShapeInfo drawnShape = getCurrentShape();

        if (drawnShape != null) {

            drawnShape.draw(g2D);

        }

    }

    /**
     * Method used to return the shape information. Checks to determine if the
     * shape is not null (for pencil only) before returning values.
     * 
     * @return shape, color and thickness arguments.
     */
    private ShapeInfo getCurrentShape() {
        final Shape s = myCurrentTool.getShape();

        if (s == null) {

            return null;
        }

        return new ShapeInfo(s, myCurrentColor, myCurrentThickness);
    }

    /**
     * Thickness setter method.
     * 
     * @param theThickness sets brush thickness value.
     */
    public void setMyThickness(final int theThickness) {

        this.myCurrentThickness = theThickness;

    }

    /**
     * Color setter method.
     * 
     * @param theColor sets color value.
     */
    public void setMyColor(final Color theColor) {

        this.myCurrentColor = theColor;

    }

    /**
     * Thickness getter method.
     * 
     * @return brush thickness value.
     */
    public int getThickness() {

        return this.myCurrentThickness;

    }

    /**
     * Color getter method.
     * 
     * @return myColor value.
     */
    public Color getColor() {

        return this.myCurrentColor;

    }

    /**
     * Listens for mouse events to draw on our panel.
     */
    private class MyMouseFunction extends MouseInputAdapter {

        /**
         * Records mouse press action. If brush thickness is set to 0 then will
         * not register mouse press.
         * 
         * @param theE no argument passed into method.
         * 
         */
        @Override
        public void mousePressed(final MouseEvent theE) {
            if (getThickness() > 0) {
                myCurrentTool.setStartPoint(theE.getPoint());
            }

        }

        /**
         * 
         * Records the points as mouse is dragged across the drawing surface.
         * 
         * @param theE sets present point to the current point.
         */
        @Override
        public void mouseDragged(final MouseEvent theE) {
            if (getThickness() > 0) {
                myCurrentTool.setEndPoint(theE.getPoint());
                repaint();
            }
        }

        /**
         * Retrieves the current shape when mouse is released. Adds drawn shape
         * to list of shapes info. Enables clear button when valid image is
         * drawn. Pencil will check if path is not null before drawing. If path
         * is null Pencil will create new path object.
         * 
         * 
         * @param theE no argument passed into method.
         */
        @Override
        public void mouseReleased(final MouseEvent theE) {
            if (getThickness() > 0) {
                myShapes.add(getCurrentShape());
                myClearButton.setEnabled(true);
                myCurrentTool.clear();
            }

        }
    }

}

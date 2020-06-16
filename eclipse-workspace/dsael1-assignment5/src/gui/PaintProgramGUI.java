/*
 * TCSS 305 – Autumn 2018
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import tool.DrawingTools;
import tool.EllipseTool;
import tool.LineTool;
import tool.PencilTool;
import tool.RectangleTool;

/**
 * {@code} Constructs buttons, menu items and menu for GUI.
 * 
 * @author David Saelee
 * @version 11/23/2018
 */
public class PaintProgramGUI extends JFrame {
    /**
     * Serial version ID used during deserialization to verify serialized
     * objects have loaded classes.
     */
    private static final long serialVersionUID = 5947888059576561167L;
    /**
     * Declared constant to set window size to 1/3.
     */
    private static final int INITIAL_SIZE = 3;
    /**
     * The major tick spacing for the FPS slider. **CODE BORROWED FROM RODE RAGE
     * ASSIGNMENT**.
     */
    private static final int MAJOR_TICK_SPACING = 5;
    /**
     * The minor tick spacing for the FPS slider. **CODE BORROWED FROM RODE RAGE
     * ASSIGNMENT**.
     */
    private static final int MINOR_TICK_SPACING = 1;
    /**
     * Initial slider thickness. **CODE BORROWED FROM RODE RAGE ASSIGNMENT**.
     */
    private static final int INITIAL_SLIDER_VALUE = 2;
    /**
     * Maximum value for slider thickness. **CODE BORROWED FROM RODE RAGE
     * ASSIGNMENT**.
     */
    private static final int MAX_SLIDER_VALUE = 15;
    /**
     * Default color specification.
     */
    private static final Color UW_PURPLE = new Color(51, 0, 111);
    /**
     * Stores title value.
     */
    private static String title = "TCSS 305 Paint Program";
    /**
     * Stores color action.
     */
    private final ColorAction myColorAction;
    /**
     * Stores the screen size dimension value.
     */
    private Dimension myIntialDimension = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * Stores an array of tool values.
     */
    private ArrayList<ButtonFunctionConstructor> myToolButton;
    /**
     * Stores a drawing panel value.
     */
    private final DrawingPanel myPanel;
    /**
     * Stores the clear button components.
     */
    private final JMenuItem myClearButton = new JMenuItem("Clear");
    /**
     * Stores line tool value.
     */
    private final LineTool myLineTool;
    /**
     * Stores pencil tool value.
     */
    private final PencilTool myPencilTool;
    /**
     * Stores rectangle tool value.
     */
    private final RectangleTool myRectangleTool;
    /**
     * Stores ellipse tool value.
     */
    private final EllipseTool myEllipseTool;

    /**
     * Constructor that initializes GUI components.
     */
    public PaintProgramGUI() {
        super(title);

        myPanel = new DrawingPanel(myClearButton);

        myLineTool = new LineTool();
        myPencilTool = new PencilTool();
        myRectangleTool = new RectangleTool();
        myEllipseTool = new EllipseTool();
        myColorAction = new ColorAction();

        myPanel.setCurrentTool(myLineTool);
        myPanel.setMyColor(UW_PURPLE);
        myPanel.setMyThickness(INITIAL_SLIDER_VALUE);

    }

    /**
     * Method to initialize GUI by calling the button constructors, initializing
     * the drawing surface and setting up other frame functions.
     */
    public void start() {

        setupImageToButton();
        setSize(myIntialDimension.width / INITIAL_SIZE,
                myIntialDimension.height / INITIAL_SIZE);
        add(myPanel, BorderLayout.CENTER);

        final ImageIcon icon =
                        new ImageIcon(getClass().getResource("/cropped-bitcoin-favicon.png"));
        setIconImage(icon.getImage());

        setLocationRelativeTo(null);
        add(setupMenuBar(), BorderLayout.NORTH);
        add(setupJToolBar(), BorderLayout.SOUTH);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Constructs three menu items and adds them to the options menu.
     * 
     * @return JMenuBar.
     */
    private JMenuBar setupMenuBar() {

        final JMenuBar bar = new JMenuBar();

        final JMenu menuOptions = new JMenu("Options");
        final JMenu menuThick = new JMenu("Thickness");
        final JMenuItem menuColor = new JMenuItem(myColorAction);

        menuThick.add(setupJslider());
        menuOptions.add(menuThick);
        menuOptions.add(new JSeparator());

        menuOptions.add(menuColor);
        menuOptions.add(new JSeparator());

        myClearButton.setEnabled(false);
        myClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theE) {
                myPanel.clear();
                myClearButton.setEnabled(false);
            }
        });
        menuOptions.add(myClearButton);

        final JMenu menuTool = new JMenu("Tool");

        final ButtonGroup btngrp = new ButtonGroup();
        for (final ButtonFunctionConstructor ta : myToolButton) {
            final JRadioButtonMenuItem btn = new JRadioButtonMenuItem(ta);
            btngrp.add(btn);
            menuTool.add(btn);
        }
        bar.add(menuOptions);
        bar.add(menuTool);

        bar.add(setupHelpMenu());

        return bar;

    }

    /**
     * Helper method to construct help menu.
     * 
     * @return JMenu.
     */
    private JMenu setupHelpMenu() {
        final JMenu menuHelp = new JMenu("Help");
        final JMenuItem menuAbout = new JMenuItem("About...");

        menuAbout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {

                // a message dialog with an icon
                JOptionPane.showMessageDialog(null, "Autumn 2018\nDavid Saelee", title,
                                              JOptionPane.PLAIN_MESSAGE,
                                              new ImageIcon(getClass().getResource
                                                            ("/bitcoin-small.png")));
            }

        });
        menuHelp.add(menuAbout);
        return menuHelp;
    }

    /**
     * Helper method to construct bottom tool bar.
     * 
     * @return JToolBar.
     */
    private JToolBar setupJToolBar() {

        final JToolBar menuToolBar = new JToolBar();

        menuToolBar.add(myColorAction);

        final ButtonGroup btngrp2 = new ButtonGroup();
        for (final ButtonFunctionConstructor bta : myToolButton) {
            final JToggleButton tb = new JToggleButton(bta);
            btngrp2.add(tb);
            menuToolBar.add(tb);

        }

        return menuToolBar;

    }

    /**
     * Code used to construct slider for brush thickness. **CODE BORROWED FROM
     * RODE RAGE ASSIGNMENT**.
     * 
     * @return JSlider.
     */
    private JSlider setupJslider() {

        final JSlider thicknessSlider = new JSlider(SwingConstants.HORIZONTAL, 0,
                                                    MAX_SLIDER_VALUE, INITIAL_SLIDER_VALUE);
        thicknessSlider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        thicknessSlider.setMinorTickSpacing(MINOR_TICK_SPACING);
        thicknessSlider.setPaintLabels(true);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.addChangeListener(new ChangeListener() {
            /** Called in response to slider events in this window. */
            @Override
            public void stateChanged(final ChangeEvent theEvent) {

                myPanel.setMyThickness(thicknessSlider.getValue());

            }
        });
        return thicknessSlider;

    }

    /**
     * Constructs an array of tool objects with their respective image and
     * action.
     * 
     */
    private void setupImageToButton() {
        myToolButton = new ArrayList<ButtonFunctionConstructor>();

        myToolButton.add(new ButtonFunctionConstructor("Line", 
                                                       new ImageIcon(getClass().
                                                                     getResource("/line.gif")),
                                                       myLineTool));
        myToolButton.add(new ButtonFunctionConstructor("Pencil",
                                                       new ImageIcon(getClass().
                                                                     getResource
                                                                     ("/pencil.gif")),
                                                       myPencilTool));
        myToolButton.add(new ButtonFunctionConstructor("Rectangle", 
                                                       new ImageIcon(getClass().
                                                                     getResource
                                                                     ("/rectangle.gif")),
                                                       myRectangleTool));
        myToolButton.add(new ButtonFunctionConstructor("Ellipse", 
                                                       new ImageIcon(getClass().
                                                                     getResource
                                                                     ("/ellipse.gif")),
                                                       myEllipseTool));

    }

    /**
     * {@code} Inner class used to construct a paint tool object and to map the
     * behavior and image to the created JToggle button.
     * 
     */
    private class ButtonFunctionConstructor extends AbstractAction {

        /**
         * Serial version ID used during deserialization to verify serialized
         * objects have loaded classes.
         */
        private static final long serialVersionUID = -2515263049911935413L;
        /**
         * Stores the selected tool from the menu.
         */
        private DrawingTools mySetTool;

        /**
         * Button constructor that takes in 3 arguments and maps values to menu
         * button.
         * 
         * **CODE BORROWED FROM PRESISTENT+GRAPHICS EXAMPLE ZIP FILE**
         * 
         * @param theToolName name of the tool.
         * @param theIcon the image value.
         * @param theTool the tool value.
         */
        ButtonFunctionConstructor(final String theToolName, final Icon theIcon,
                                  final DrawingTools theTool) {

            super(theToolName, theIcon);

            mySetTool = theTool;

            putValue(Action.SMALL_ICON, theIcon);

            final ImageIcon icon = (ImageIcon) theIcon;
            final Image largeImage = icon.getImage().
                            getScaledInstance(15, -1, java.awt.Image.SCALE_SMOOTH);
            final ImageIcon largeIcon = new ImageIcon(largeImage);
            putValue(Action.LARGE_ICON_KEY, largeIcon);
            putValue(Action.SHORT_DESCRIPTION, theToolName);
            putValue(Action.SELECTED_KEY, true);
        }

        /**
         * The scope of this inner method is restricted to each tools function.
         * This method simply sets the tools behavior when selected.
         * 
         * @param arg0 no argument passed into method.
         */
        @Override
        public void actionPerformed(final ActionEvent arg0) {
            myPanel.setCurrentTool(mySetTool);

        }
    }

    /**
     * {@code} Initializes the color button and adds the action to button.
     * 
     */
    private final class ColorAction extends AbstractAction {
        /**
         * Serial version ID used during deserialization to verify serialized
         * objects have loaded classes.
         */
        private static final long serialVersionUID = 6475934076937508698L;

        /**
         * Constructor used to display menu item title.
         */
        private ColorAction() {
            super("Color...");
        }

        /**
         * Adds action to color button. Sets the color when button is pressed.
         * 
         * @param theE no argument passed into method.
         */
        @Override
        public void actionPerformed(final ActionEvent theE) {
            final Color c = JColorChooser.showDialog(null, "Choose Color", myPanel.getColor());
            if (c != null) {

                myPanel.setMyColor(c);

            }

        }
    }

}

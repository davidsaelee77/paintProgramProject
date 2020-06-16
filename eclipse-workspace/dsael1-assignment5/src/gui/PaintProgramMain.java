/*
 * TCSS 305 – Autumn 2018
 */

package gui;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * {@code} Client code to run Paint Program GUI.
 * 
 * @author David Saelee
 * @version 11/23/2018
 *
 */
public final class PaintProgramMain {
    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private PaintProgramMain() {
        throw new IllegalStateException();

    }
    /**
     * The main method, invokes the Paint Program GUI. Sets metal
     * look and feel to paint program GUI.
     * 
     * @param theArgs Command line arguments.

     */
    public static void main(final String[] theArgs) {
        
        
        /**
         * Sets paint programs user interface look and feel to metal.
         * 
         * **CODE BORROWED FROM PRESISTENT+GRAPHICS EXAMPLE ZIP FILE**
         */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        } catch (final InstantiationException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaintProgramGUI().start();
            }
        });

    }
}

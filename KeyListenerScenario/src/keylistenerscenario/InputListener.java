/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keylistenerscenario;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

/**
 *
 * @author gbarbieri
 */
public class InputListener implements KeyListener {

    private GlViewer glViewer;
    private boolean oPressed = false;
    private boolean ctrlPressed = false;

    public InputListener(GlViewer glViewer) {

        this.glViewer = glViewer;
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.print("keyPressed " + ke.getKeyCode());

        if (ke.isAutoRepeat()) {
            System.out.print(" autoRepeat!");
        }
        System.out.println("");

        switch (ke.getKeyCode()) {

            case KeyEvent.VK_O:
                oPressed = true;
                break;

            case KeyEvent.VK_CONTROL:
                ctrlPressed = true;
                break;
        }

        check();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.print("keyReleased " + ke.getKeyCode());

        if (ke.isAutoRepeat()) {
            System.out.print(" autoRepeat!");
        }
        System.out.println("");

        switch (ke.getKeyCode()) {

            case KeyEvent.VK_O:
                oPressed = false;
                break;

            case KeyEvent.VK_CONTROL:
                ctrlPressed = false;
                break;
        }
        check();
    }

    private void check() {

        if (oPressed && ctrlPressed) {

//            System.out.println(""+SwingUtilities.isEventDispatchThread());
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    System.out.println("fileChooser opens");
                    oPressed = false;
                    ctrlPressed = false;
                    JFileChooser fileChooser = new JFileChooser();

                    if (fileChooser.showOpenDialog(EC_GUI.main) != JFileChooser.APPROVE_OPTION) {
                        System.out.println("fileChooser closed");
                    }
                }
            });
        }
    }
}

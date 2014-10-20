/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keylistenerscenario;

import com.jogamp.newt.event.NEWTEvent;
import com.jogamp.newt.event.NEWTEventFiFo;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 *
 * @author gbarbieri
 */
public class EC_GUI extends javax.swing.JFrame {

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        border_panel = new javax.swing.JPanel();
        centerPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        border_panel.setLayout(new java.awt.BorderLayout());

        centerPanel.setLayout(new java.awt.BorderLayout());
        border_panel.add(centerPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(border_panel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EC_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        
//        //</editor-fold>
//        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                EC_GUI ec_gui = new EC_GUI();
                ec_gui.pack();
                ec_gui.setVisible(true);
            }
        });
    }

    private GlViewer glViewer;
    public static EC_GUI main;
    private boolean oPressed = false;
    private boolean ctrlPressed = false;
    private NEWTEventFiFo newtEventFifo;
    private NEWTEvent newtEvent;

    /**
     * Creates new form EC_GUI1
     */
    public EC_GUI() {

        JFrame.setDefaultLookAndFeelDecorated(false);
//        this.setUndecorated(true);

//        addKeyListener(new KeyListener() {
//
//            @Override
//            public void keyTyped(KeyEvent e) {
//                System.out.println("typed ");
////                System.out.println(
////                        +e.getExtendedKeyCode()
////                        + " " + e.getID()
////                        + " " + e.getKeyChar()
////                        + " " + e.getKeyCode()
////                        + " " + e.getKeyLocation()
////                        + " " + e.getModifiers()
////                        + " " + e.getModifiersEx()
////                );
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                System.out.println("pressed");
////                System.out.println(
////                        +e.getExtendedKeyCode()
////                        + " " + e.getID()
////                        + " " + e.getKeyChar()
////                        + " " + e.getKeyCode()
////                        + " " + e.getKeyLocation()
////                        + " " + e.getModifiers()
////                        + " " + e.getModifiersEx()
////                );
//                switch (e.getKeyCode()) {
//
//                    case KeyEvent.VK_CONTROL:
//                        ctrlPressed = true;
//                        break;
//                    case KeyEvent.VK_O:
//                        oPressed = true;
//                        break;
//                }
//                check();
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                System.out.println("released");
//                switch (e.getKeyCode()) {
//
//                    case KeyEvent.VK_CONTROL:
//                        ctrlPressed = false;
//                        break;
//                    case KeyEvent.VK_O:
//                        oPressed = false;
//                        break;
//                }
//            }
//        });
        initComponents();

//        Action openFileChooserAction = new AbstractAction() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("actionPerformed");
//                openFileChooser();
//            }
//        };
//        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
//        centerPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "openFileChooser");
//        centerPanel.getActionMap().put("openFileChooser", openFileChooserAction);
//
//        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
//        manager.addKeyEventDispatcher(new MyDispatcher());
        
        setSize(800, 600);
        setLocation(30, 30);

        main = this;

        initGlViewer();
        
//        InputListener2 listener2 = new InputListener2();
//        listener2.run();
//        Thread thread = new Thread(new InputListener2());
//        thread.start();

//        setVisible(true);

//        glViewer.getNewtCanvasAWT().requestFocus();
    }

    private void check() {

        if (oPressed && ctrlPressed) {

            openFileChooser();
        }
    }

    private void openFileChooser() {
        //            System.out.println(""+SwingUtilities.isEventDispatchThread());
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                System.out.println("fileChooser opens");
                JFileChooser fileChooser = new JFileChooser();

                if (fileChooser.showOpenDialog(EC_GUI.main) != JFileChooser.APPROVE_OPTION) {
                    System.out.println("fileChooser closed");
                }
            }
        });
    }

    private void initGlViewer() {
        /**
         * Viewer.
         */
        glViewer = new GlViewer();
        glViewer.setup();
//        System.out.println(""+SwingUtilities.isEventDispatchThread());
        centerPanel.add(glViewer.getNewtCanvasAWT());
//        centerPanel.setFocusable(false);
//        SwingUtilities.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                centerPanel.add(glViewer.getNewtCanvasAWT());
//                centerPanel.setFocusable(false);
//            }
//        });
    }

    private class MyDispatcher implements KeyEventDispatcher {

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {

            if (e.getID() == KeyEvent.KEY_PRESSED) {

                System.out.println("pressed");

            } else if (e.getID() == KeyEvent.KEY_RELEASED) {

                System.out.println("released");

            } else if (e.getID() == KeyEvent.KEY_TYPED) {

                System.out.println("typed");

            }
            return false;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel border_panel;
    private javax.swing.JPanel centerPanel;
    // End of variables declaration//GEN-END:variables
}

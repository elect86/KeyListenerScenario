/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keylistenerscenario;

import com.jogamp.newt.Window;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.NEWTEvent;
import com.jogamp.newt.event.NEWTEventFiFo;
import com.jogamp.newt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gbarbieri
 */
public class InputListener2 implements Runnable {

//    private int step = 20;
    private int step = 1000;
    private NEWTEvent event;
    private NEWTEventFiFo eventFifo = new NEWTEventFiFo();
    
    @Override
    public void run() {

        while (true) {
            
            try {
                Thread.sleep(step);
            } catch (InterruptedException ex) {
                Logger.getLogger(InputListener2.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(""+eventFifo.size());
            
            while (null != (event = eventFifo.get())) {
            
                System.out.println("in");
                
                if (event instanceof KeyEvent) {
                    System.out.println("keyEvent");
                    final KeyEvent keyEvent = (KeyEvent) event;
                    
                    switch (keyEvent.getKeyChar()) {
                        case 'q':
                            System.out.println("q");
                            break;
                        case 'f':
                            System.out.println("f");
                            break;
                    }
                }
            }
        }
    }
}

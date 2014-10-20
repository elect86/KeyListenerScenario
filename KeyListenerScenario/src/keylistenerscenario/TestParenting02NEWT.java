/**
 * Copyright 2010 JogAmp Community. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY JogAmp Community ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL JogAmp Community OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of JogAmp Community.
 */
package keylistenerscenario;

import javax.media.opengl.*;
import javax.media.nativewindow.*;

import com.jogamp.newt.*;
import com.jogamp.newt.event.*;
import com.jogamp.newt.opengl.*;

import java.io.IOException;

// import com.jogamp.opengl.test.junit.jogl.demos.es1.RedSquareES1;
// import com.jogamp.opengl.test.junit.jogl.demos.es1.GearsES1;
public class TestParenting02NEWT {

    static int width = 640;
    static int height = 480;
    static long durationPerTest = 500;

    static void destroyWindow(final Display display, final Screen screen, final Window window, final GLWindow glWindow) {
        if (null != glWindow) {
            glWindow.destroy();
        }
        if (null != window) {
            window.destroy();
        }
        if (null != screen) {
            screen.destroy();
        }
        if (null != display) {
            display.destroy();
        }
    }

    public void test01NewtOnNewtParentChildDraw() throws InterruptedException {
        final GLCapabilities caps = new GLCapabilities(null);

        final Display display = NewtFactory.createDisplay(null); // local display

        final Screen screen = NewtFactory.createScreen(display, 0); // screen 0

        int x = 1;
        int y = 1;

        final NEWTEventFiFo eventFifo = new NEWTEventFiFo();

        final Window window1 = NewtFactory.createWindow(screen, caps);

        final GLWindow glWindow1 = GLWindow.create(window1);

        glWindow1.setSize(width, height);

        glWindow1.setTitle("test01NewtOnNewtParentChildDraw - PARENT");
        glWindow1.setPosition(x, y);
        //glWindow1.addKeyListener(new TraceKeyAdapter(new KeyAction(eventFifo)));
        //glWindow1.addWindowListener(new TraceWindowAdapter());

//        final GLEventListener demo1 = new RedSquareES2();

        // glWindow1.addGLEventListener(demo1);
        glWindow1.setVisible(true);
        CapabilitiesImmutable capsChosen = glWindow1.getGraphicsConfiguration().getChosenCapabilities();

        final Window window2 = NewtFactory.createWindow(window1, caps);
        window2.setUndecorated(true);

        final GLWindow glWindow2 = GLWindow.create(window2);

        glWindow2.setSize(width / 2, height / 2);
        //Assert.assertEquals(width/2,glWindow2.getWidth());
        //Assert.assertEquals(height/2,glWindow2.getHeight());
        glWindow2.setTitle("test01NewtOnNewtParentChildDraw - CHILD");
        glWindow2.setPosition(glWindow1.getWidth() / 2, glWindow1.getHeight() / 2);
        //glWindow2.addKeyListener(new TraceKeyAdapter(new KeyAction(eventFifo)));
        //glWindow2.addWindowListener(new TraceWindowAdapter(new WindowAction(eventFifo)));
        // glWindow2.addMouseListener(new TraceMouseAdapter());

//        final GLEventListener demo2 = new GearsES2();

        // glWindow2.addGLEventListener(demo2);
        glWindow2.setVisible(true);
        capsChosen = glWindow2.getGraphicsConfiguration().getChosenCapabilities();

        boolean shouldQuit = false;
        long duration = durationPerTest;
        final long step = 20;
        NEWTEvent event;

        while (duration > 0 && !shouldQuit) {
            glWindow1.display();
            glWindow2.display();
            duration -= step;
            x += 1;
            y += 1;
            // glWindow1.setPosition(x,y);
            glWindow2.setPosition(glWindow1.getWidth() / 2, glWindow1.getHeight() / 2 - y);
            Thread.sleep(step);

            while (null != (event = eventFifo.get())) {
                final Window source = (Window) event.getSource();
                if (WindowEvent.EVENT_WINDOW_DESTROY_NOTIFY == event.getEventType()) {
                    shouldQuit = true;
                } else if (event instanceof KeyEvent) {
                    final KeyEvent keyEvent = (KeyEvent) event;
                    switch (keyEvent.getKeyChar()) {
                        case 'q':
                            shouldQuit = true;
                            break;
                        case 'f':
                            source.setFullscreen(!source.isFullscreen());
                            break;
                    }
                }
            }
        }
        destroyWindow(null, null, window2, glWindow2);
        destroyWindow(display, screen, window1, glWindow1);
    }

    static int atoi(final String a) {
        int i = 0;
        try {
            i = Integer.parseInt(a);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return i;
    }

    public static void main(final String args[]) throws IOException {

        TestParenting02NEWT testParenting02NEWT = new TestParenting02NEWT();
    }

}

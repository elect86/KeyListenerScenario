/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keylistenerscenario;

import com.jogamp.newt.awt.NewtCanvasAWT;
import com.jogamp.newt.opengl.GLWindow;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;

/**
 *
 * @author gbarbieri
 */
public class GlViewer implements GLEventListener{

    private GLWindow glWindow;
    private NewtCanvasAWT newtCanvasAWT;
    private InputListener inputListener;
    
    public GlViewer() {

        GLProfile gLProfile = GLProfile.getDefault();

        GLCapabilities gLCapabilities = new GLCapabilities(gLProfile);

        glWindow = GLWindow.create(gLCapabilities);
        /*
         *  We combine NEWT GLWindow inside existing AWT application (the main JFrame) 
         *  by encapsulating the glWindow inside a NewtCanvasAWT canvas.
         */
        newtCanvasAWT = new NewtCanvasAWT(glWindow);
    }
    
    public void setup() {

        inputListener = new InputListener(this);

        glWindow.addKeyListener(inputListener);
        
        glWindow.addGLEventListener(this);

        glWindow.setAutoSwapBufferMode(false);
    }

    @Override
    public void init(GLAutoDrawable glad) {
       
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
    
    }

    @Override
    public void display(GLAutoDrawable glad) {
    
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    
    }

    public NewtCanvasAWT getNewtCanvasAWT() {
        return newtCanvasAWT;
    }
}

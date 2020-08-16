package lab.pkg4;

import java.util.Iterator;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glFlush;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 *
 * @author Matheus Pacheco
 */
public class StudentLWJGLController implements CS355LWJGLController
{

    //This is a model of a house.
    //It has a single method that returns an iterator full of Line3Ds.
    //A "Line3D" is a wrapper class around two Point2Ds.
    //It should all be fairly intuitive if you look at those classes.
    //If not, I apologize.
    private WireFrame model = new HouseModel();

    private Point3D camera = new Point3D(0, -5, -20);
    private LWJGLSandbox sandbox = new LWJGLSandbox();
    private double rotation;
    private boolean mode;
    //This method is called to "resize" the viewport to match the screen.
    //When you first start, have it be in perspective mode.
    @Override
    public void resizeGL()
    {
        glMatrixMode(GL_PROJECTION);
        gluPerspective(60, 4 / 3, 1, 100);
        glViewport(0, 0, LWJGLSandbox.DISPLAY_WIDTH, LWJGLSandbox.DISPLAY_HEIGHT);
        rotation = 0;
        mode = false;
    }

    @Override
    public void update()
    {

    }

    //This is called every frame, and should be responsible for keyboard updates.
    //An example keyboard event is captured below.
    //The "Keyboard" static class should contain everything you need to finish
    // this up.
    @Override
    public void updateKeyboard()
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_W))
        {
            camera.z += Math.cos(Math.toRadians(rotation));
            camera.x -= Math.sin(Math.toRadians(rotation));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A))
        {            
            camera.z += Math.cos(Math.toRadians(rotation-90));
            camera.x -= Math.sin(Math.toRadians(rotation-90));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S))
        {
            camera.z -= Math.cos(Math.toRadians(rotation));
            camera.x += Math.sin(Math.toRadians(rotation));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D))
        {
            camera.z -= Math.cos(Math.toRadians(rotation-90));
            camera.x += Math.sin(Math.toRadians(rotation-90));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_Q))
        {
            rotation--;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_E))
        {            
            rotation++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_R))
        {
            camera.y--;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_F))
        {
            camera.y++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_H))
        {
            camera.x = 0;
            camera.y = -5;
            camera.z = -20;
            rotation = 0;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_O))
        {
            mode = true;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_P))
        {
            mode = false;
        }
    }

    //This method is the one that actually draws to the screen.
    @Override
    public void render()
    {
        //This clears the screen.
        glClear(GL_COLOR_BUFFER_BIT);
        glFlush();
        
        //Do your drawing here.
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        if(mode)
            glOrtho(-30, 30, -30, 30, 1, 100);
        else
            gluPerspective(60, 4 / 3, 1, 100);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glRotatef((float)rotation, 0, 1, 0);
        glTranslatef((float) camera.x, (float) camera.y, (float) camera.z);
        
        for (Iterator iterator = model.getLines(); iterator.hasNext();)
        {

            Line3D line = (Line3D) iterator.next();
            
            
            //HOUSE 1 * 1
            glPushMatrix();      
            glTranslatef(0, 0, 0);
            glColor3f(1.0f, 0.0f, 0.0f);
            glBegin(GL_LINES);
            glVertex3d(line.start.x, line.start.y, line.start.z);
            glVertex3d(line.end.x, line.end.y, line.end.z);       
            glEnd();
            glPopMatrix();
            
            //HOUSE 1 * 2
            glPushMatrix();   
            glRotatef(180, 0, 1, 0);
            glTranslatef(0, 0, -30);
            glColor3f(1.0f, 0.0f, 0.0f);
            glBegin(GL_LINES);
            glVertex3d(line.start.x, line.start.y, line.start.z);
            glVertex3d(line.end.x, line.end.y, line.end.z);       
            glEnd();
            glPopMatrix();
            
            //HOUSE 2 * 1
            glPushMatrix();            
            glTranslatef(15, 0, 0);
            glColor3f(0.0f, 1.0f, 0.0f);
            glBegin(GL_LINES);
            glVertex3d(line.start.x, line.start.y, line.start.z);
            glVertex3d(line.end.x, line.end.y, line.end.z);      
            glEnd();
            glPopMatrix();          
            
            //HOUSE 2 * 2
            glPushMatrix();            
            glRotatef(180, 0, 1, 0);
            glTranslatef(-15, 0, -30);
            glColor3f(0.0f, 1.0f, 0.0f);
            glBegin(GL_LINES);
            glVertex3d(line.start.x, line.start.y, line.start.z);
            glVertex3d(line.end.x, line.end.y, line.end.z);      
            glEnd();
            glPopMatrix();
            
            //HOUSE 3 * 1
            glPushMatrix();       
            glTranslatef(30,0,0);
            glColor3f(0.0f, 0.0f, 1.0f);
            glBegin(GL_LINES);
            glVertex3d(line.start.x, line.start.y, line.start.z);
            glVertex3d(line.end.x, line.end.y, line.end.z);     
            glEnd();
            glPopMatrix();
            
            //HOUSE 3 * 2
            glPushMatrix();       
            glRotatef(180, 0, 1, 0);
            glTranslatef(-30, 0, -30);
            glColor3f(0.0f, 0.0f, 1.0f);
            glBegin(GL_LINES);
            glVertex3d(line.start.x, line.start.y, line.start.z);
            glVertex3d(line.end.x, line.end.y, line.end.z);     
            glEnd();
            glPopMatrix();
            
            //HOUSE 4 * 1
            glPushMatrix();       
            glTranslatef(45, 0, 0);
            glColor3f(1.0f, 1.0f, 0.0f);
            glBegin(GL_LINES);
            glVertex3d(line.start.x, line.start.y, line.start.z);
            glVertex3d(line.end.x, line.end.y, line.end.z);    
            glEnd();
            glPopMatrix();
            
            //HOUSE 4 * 2
            glPushMatrix();       
            glRotatef(180, 0, 1, 0);
            glTranslatef(-45, 0, -30);
            glColor3f(1.0f, 1.0f, 0.0f);
            glBegin(GL_LINES);
            glVertex3d(line.start.x, line.start.y, line.start.z);
            glVertex3d(line.end.x, line.end.y, line.end.z);    
            glEnd();
            glPopMatrix();
            
            //HOUSE
            glPushMatrix();       
            
            glTranslatef(60, 0, 15);
            glRotatef(-90, 0, 1, 0);
            glColor3f(1.0f, 0.0f, 1.0f);
            glBegin(GL_LINES);
            glVertex3d(line.start.x, line.start.y, line.start.z);
            glVertex3d(line.end.x, line.end.y, line.end.z);     
            glEnd();
            glPopMatrix();      

        }
        
    }

}

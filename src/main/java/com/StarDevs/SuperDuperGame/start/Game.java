package com.StarDevs.SuperDuperGame.start;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * Created by Void on 27.08.2014.
 */
public class Game {
    static Point[] points = new Point[100000];
    static Random random = new Random();
    public static Texture wood;
    public Game() {

        wood = loadTeture("wood");

        for (int i = 0; i < points.length; i++) {
            points[i] = new Point((random.nextFloat() - 0.5f) * 1000f, (random.nextFloat() - 0.5f) * 1000f, random.nextInt(1000) - 500);
        }
    }

   public static Texture loadTeture(String key){
       try {

         return  TextureLoader.getTexture("jpg", new FileInputStream(new File("res/"+key+".jpg")));

       } catch (IOException e) {
           e.printStackTrace(); return null;
       }
   }

    private static class Point{
        float x, y, z;
        public Point(float x, float y, float z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    static float x = 0;
    public static void render(){
        glPushMatrix();{
        glTranslatef(0,0,-10);
        glRotatef(x, 1, 1, 0);
        wood.bind();

        glBegin(GL_QUADS);
        glColor3f(1f,0,0);  glTexCoord2f(0, 0); glVertex3f(-1, -1, 1);
        glColor3f(0,1,0);   glTexCoord2f(0, 1); glVertex3f(-1, 1, 1);
        glColor3f(0,0,1);   glTexCoord2f(1, 1); glVertex3f(1, 1, 1);
        glColor3f(1,0,1);   glTexCoord2f(1, 0); glVertex3f(1, -1, 1);

        glColor3f(1f,0,0);  glTexCoord2f(0, 0); glVertex3f(-1, -1, -1);
        glColor3f(0,1,0);   glTexCoord2f(0, 1); glVertex3f(-1, 1, -1);
        glColor3f(0,0,1);   glTexCoord2f(1, 1); glVertex3f(1, 1, -1);
        glColor3f(1,0,1);   glTexCoord2f(1, 0); glVertex3f(1, -1, -1);

        glColor3f(1f,0,0);  glTexCoord2f(0, 0); glVertex3f(-1, -1, -1);
        glColor3f(0,1,0);   glTexCoord2f(0, 1); glVertex3f(-1, -1, 1);
        glColor3f(0,0,1);   glTexCoord2f(1, 1); glVertex3f(-1, 1, 1);
        glColor3f(1,0,1);   glTexCoord2f(1, 0); glVertex3f(-1, 1, -1);

        glColor3f(1f,0,0);  glTexCoord2f(0, 0); glVertex3f(1, -1, -1);
        glColor3f(0,1,0);   glTexCoord2f(0, 1); glVertex3f(1, -1, 1);
        glColor3f(0,0,1);   glTexCoord2f(1, 1); glVertex3f(1, 1, 1);
        glColor3f(1,0,1);   glTexCoord2f(1, 0); glVertex3f(1, 1, -1);

        glColor3f(1f,0,0);  glTexCoord2f(0, 0); glVertex3f(-1, -1, -1);
        glColor3f(0,1,0);   glTexCoord2f(0, 1); glVertex3f(1, -1, -1);
        glColor3f(0,0,1);   glTexCoord2f(1, 1); glVertex3f(1, -1, 1);
        glColor3f(1,0,1);   glTexCoord2f(1, 0); glVertex3f(-1, -1, 1);

        glColor3f(1f,0,0);  glTexCoord2f(0, 0); glVertex3f(-1, 1, -1);
        glColor3f(0,1,0);   glTexCoord2f(0, 1); glVertex3f(1, 1, -1);
        glColor3f(0,0,1);   glTexCoord2f(1, 1); glVertex3f(1, 1, 1);
        glColor3f(1,0,1);   glTexCoord2f(1, 0); glVertex3f(-1, 1, 1);


        glEnd();
            glPopMatrix();

        glColor3f(0.8f, 0.8f, 0.3f);
        glBegin(GL_POINTS);
        for(Point p : points){
            glVertex3f(p.x, p.y, p.z);
        }
        glEnd();
        glBegin(GL_LINES);  //Red - x
        glColor3f(1,0,0);
        glVertex3i(-50,0, 0);
        glColor3f(1,0,0);
        glVertex3i(50,0, 0);
        glEnd();
        glBegin(GL_LINES);  //Blue - y
        glColor3f(0,1,1);
        glVertex3i(0,-50, 0);
        glColor3f(0,1,1);
        glVertex3i(0,50, 0);
        glEnd();
        glBegin(GL_LINES); //Yellow - z
        glColor3f(0.5f,0.5f,0.5f);
        glVertex3i(0,0, -50);
        glColor3f(0.5f,0.5f,0.5f);
        glVertex3i(0,0, 50);
        glEnd();


        }


        x += 1f;

    }
}

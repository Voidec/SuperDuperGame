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
import org.lwjgl.util.vector.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * Created by Void on 27.08.2014.
 */
public class Game {
    static Point[] points = new Point[100000];
    static Random random = new Random();
    public static Texture wood;
    public static int objectDisplayList;
    public Game() {

        wood = loadTeture("wood");

        for (int i = 0; i < points.length; i++) {
            points[i] = new Point((random.nextFloat() - 0.5f) * 1000f, (random.nextFloat() - 0.5f) * 1000f, random.nextInt(1000) - 500);
        }
        objectDisplayList = glGenLists(1);
        glNewList(objectDisplayList, GL_COMPILE);{
            Model m = OBJloader.loadModel(new File("res/USSEnterprise.obj"));

            glColor3f(1,1,1);
            glBegin(GL_TRIANGLES);
            for(Face face : m.faces) {
                org.lwjgl.util.vector.Vector3f n1 = m.normals.get((int) face.normal.x - 1);
                glNormal3f(n1.x, n1.y, n1.z);
                org.lwjgl.util.vector.Vector3f v1 = m.vertices.get((int) face.vertex.x - 1);
                glVertex3f(v1.x, v1.y, v1.z);
                org.lwjgl.util.vector.Vector3f n2 = m.normals.get((int) face.normal.y - 1);
                glNormal3f(n2.x, n2.y, n2.z);
                org.lwjgl.util.vector.Vector3f v2 = m.vertices.get((int) face.vertex.y - 1);
                glVertex3f(v2.x, v2.y, v2.z);
                org.lwjgl.util.vector.Vector3f n3 = m.normals.get((int) face.normal.z - 1);
                glNormal3f(n3.x, n3.y, n3.z);
                org.lwjgl.util.vector.Vector3f v3 = m.vertices.get((int) face.vertex.z - 1);
                glVertex3f(v3.x, v3.y, v3.z);
            }
            glEnd();
        }
        glEndList();
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
    public static void render(){
        glPushMatrix();
        glTranslatef(Variables.enterpriseX,Variables.enterpriseY,Variables.enterpriseZ);
        glRotatef(Variables.EnterpriseRotY, 0,1,0);
        glCallList(objectDisplayList);
        Variables.enterpriseY +=0.01f;
        Variables.enterpriseX+=0.01f;
        glPopMatrix();

        glPushMatrix();{
        glTranslatef(0,0,-10);
        glRotatef(Variables.cubeRotateX, 1, 1, 0);
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
        glPushMatrix();
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
        glPopMatrix();
        Variables.cubeRotateX += 1f;

    }
}

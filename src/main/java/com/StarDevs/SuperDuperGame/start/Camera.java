package com.StarDevs.SuperDuperGame.start;

/**
 * Created by Void on 27.08.2014.
 */
import com.sun.deploy.util.BufferUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

import java.io.File;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class Camera {
    public static float x, y, z, rx, ry, rz, fov, aspect, near, far;

    public Camera(float fov, float aspect, float near, float far){
        x = 0;y=0;z=0;rx=0;ry=0;rz=0;
        this.fov = fov;
        this.aspect = aspect;
        this.near = near;
        this.far = far;
        initProjection();
    }
    public static int objectDisplayList;
    private void initProjection(){
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(fov,aspect,near,far);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glShadeModel(GL_SMOOTH);
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glLightModel(GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(new float[]{0.05f,0.05f, 0.05f, 1}));
        glLight(GL_LIGHT0,GL_DIFFUSE, asFloatBuffer(new float[]{1.5f,1.5f, 1.5f, 1}));
        glEnable(GL_COLOR_MATERIAL);
        glColorMaterial(GL_FRONT, GL_DIFFUSE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);


        objectDisplayList = glGenLists(1);
        glNewList(objectDisplayList, GL_COMPILE);{
            Model m = OBJloader.loadModel(new File("res/USSEnterprise.obj"));

            glColor3f(1,1,1);
            glBegin(GL_TRIANGLES);
            for(Face face : m.faces) {
                Vector3f n1 = m.normals.get((int) face.normal.x - 1);
                glNormal3f(n1.x, n1.y, n1.z);
                Vector3f v1 = m.vertices.get((int) face.vertex.x - 1);
                glVertex3f(v1.x, v1.y, v1.z);
                Vector3f n2 = m.normals.get((int) face.normal.y - 1);
                glNormal3f(n2.x, n2.y, n2.z);
                Vector3f v2 = m.vertices.get((int) face.vertex.y - 1);
                glVertex3f(v2.x, v2.y, v2.z);
                Vector3f n3 = m.normals.get((int) face.normal.z - 1);
                glNormal3f(n3.x, n3.y, n3.z);
                Vector3f v3 = m.vertices.get((int) face.vertex.z - 1);
                glVertex3f(v3.x, v3.y, v3.z);
            }
            glEnd();
        }
        glEndList();


    }
    public void useViev(){
        glRotatef(rx, 1, 0, 0);
        glRotatef(ry, 0, 1, 0);
        glRotatef(rz, 0, 0, 1);
        glTranslated(x, y, z);
    }
    public void move(float amt, float dir){
        z += amt * Math.sin(Math.toRadians(ry + 90 * dir));
        x += amt * Math.cos(Math.toRadians(ry + 90 * dir));
    }
    public void rotateY(float amt){
        ry += amt;
    }
    public static FloatBuffer asFloatBuffer(float[] values){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
}

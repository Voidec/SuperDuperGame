package com.StarDevs.SuperDuperGame.start;

/**
 * Created by Void on 27.08.2014.
 */
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class Camera {
    public static float x, y, z, rx, ry, rz, fov, aspect, near, far;
    public static String ViewMode = "Enterprise";

    public Camera(float fov, float aspect, float near, float far){
        x = 0;y=0;z=0;rx=0;ry=90;rz=0;
        this.fov = fov;
        this.aspect = aspect;
        this.near = near;
        this.far = far;
        initProjection();
    }

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
    }
    public void useView(){
        if(ViewMode == "Enterprise") {
            glRotatef(rx, 1, 0, 0);
            glRotatef(ry, 0, 1, 0);
            glRotatef(rz, 0, 0, 1);
            x = -Variables.enterpriseX;
            y = -Variables.enterpriseY - 5;
            z = -Variables.enterpriseZ;
            ry = Variables.EnterpriseRotY;
            glTranslated(x, y, z);

        } else if (ViewMode == "FreeMode"){
            glRotatef(rx, 1, 0, 0);
            glRotatef(ry, 0, 1, 0);
            glRotatef(rz, 0, 0, 1);
            glTranslated(x, y, z);
        } else if(ViewMode == "EnterpriseMoveMode"){
            Variables.enterpriseX = -x;
            Variables.enterpriseY = -y-5;
            Variables.enterpriseZ = -z;
            Variables.EnterpriseRotY = 180-ry;
            glRotatef(rx, 1, 0, 0);
            glRotatef(ry, 0, 1, 0);
            glRotatef(rz, 0, 0, 1);
            glTranslated(x, y, z);
        } else if(ViewMode == "EnterpriseBackMoveMode"){
            Variables.enterpriseX = -x+5;
            Variables.enterpriseY = -y-5;
            Variables.enterpriseZ = -z;
            Variables.EnterpriseRotY = 180-ry;
            glRotatef(rx, 1, 0, 0);
            glRotatef(ry, 0, 1, 0);
            glRotatef(rz, 0, 0, 1);
            glTranslated(x, y, z);
        }

    }
    public void camMove(float amt, float dir){
        z += amt * Math.sin(Math.toRadians(ry + 90 * dir));
        x += amt * Math.cos(Math.toRadians(ry + 90 * dir));
    }
    public void camRotateY(float amt){
        ry += amt;
    }
    public static FloatBuffer asFloatBuffer(float[] values){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
}

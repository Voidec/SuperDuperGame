package com.StarDevs.SuperDuperGame.start;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;
import java.util.Random;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;

public class Start {
    private float angle = 30f;
    public Start(){
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Stars");
            Display.create();
        } catch (LWJGLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        Point[] points = new Point[100000];
        Random random = new Random();

        for(int i =0; i < points.length; i++){
            points[i] = new Point((random.nextFloat() - 0.5f)*100f, (random.nextFloat() - 0.5f) * 100f, random.nextInt(200) - 200);
        }

        float speed = 0.0f,speed2y = 0, speed2x =0, speed2z=0,angle2 = 0;
        float speedx = 0.0f;
        float speedy = 0.0f;
        int q = 1;
        while(!Display.isCloseRequested()) {
            if(q == 1) {
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                gluPerspective(angle, 640f/480f, 0.001f, 100);
                glMatrixMode(GL_MODELVIEW);
                q =0;
            }

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glTranslatef(speedx,speedy,speed);
            glRotatef(angle2, speed2x, speed2y, speed2z);

            glBegin(GL_POINTS);
            for(Point p : points){
                glVertex3f(p.x,p.y,p.z);
            }
            glEnd();

            glBegin(GL_QUADS);
            glVertex3i(30,30 , -5); // up-left
            glVertex3i(35,30, -10); // up-right
            glVertex3i(35,35, -10); //bot-right
            glVertex3i(30,35, - 10); //bot-left
            glEnd();
            glBegin(GL_LINES);
            glVertex3i(-50,0, -10);
            glVertex3i(50,0, -10);
            glEnd();
            if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)){
                speed2y += 0.1f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)){
                speed2y -= 0.1f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)){
                speed2x += 0.1f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)){
                speed2x -= 0.1f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)){
                speed2z += 0.1f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)){
                speed2z -= 0.1f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)){
                angle2 += 0.1f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3)){
                angle2 -= 0.1f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
                angle += 0.1f;
                q = 1;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_X)){
                angle -= 0.1f;
                q = 1;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
                speed += 0.01f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_E)){
                speed -= 0.01f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_A)){
                speedx += 0.01f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_D)){
                speedx -= 0.01f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_W)){
                speedy -= 0.01f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_S)){
                speedy += 0.01f;
            }
            while(Keyboard.next()) {
                if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
                    speed = 0f;
                    speedx = 0f;
                    speedy = 0f;
                }
                if(Keyboard.isKeyDown(Keyboard.KEY_C)){
                    speed = 0f;
                    speedx = 0f;
                    speedy = 0f;
                    angle = 30f;
                    glLoadIdentity();
                }
            }

            Display.update();
            Display.sync(60);

        }
        Display.destroy();
        System.exit(0);
    }

    private static class Point{
        float x, y, z;
        public Point(float x, float y, float z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    public static void main(String[] args){
        new Start();
    }
}

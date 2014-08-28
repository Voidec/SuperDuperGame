package com.StarDevs.SuperDuperGame.start;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import java.io.File;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 * Created by Void on 27.08.2014.
 */
public class Main {
    static Variables var;
    static Input inp;
    static Game game;
    static Camera cam;

    public static void main(String[] args) {

        initDisplay();
       // initGL();
        initGame(); // init game objects
        gameLoop();
        cleanUp();
    }

    private static void initDisplay() {

        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.setTitle("Stars");
            Display.create();
            Display.setVSyncEnabled(true);
        } catch (LWJGLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static void initGL() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(var.angle, 640f/480f, 0.001f, 1000);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_DEPTH_TEST);
    }

    private static void initGame() {
        var = new Variables();
        inp = new Input();
        game = new Game();
        cam = new Camera(70, (float)Display.getWidth() / (float)Display.getHeight(), 0.01f, 1000);

    }

    private static void gameLoop() {
        while(!Display.isCloseRequested()){
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            //glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
            getInput();// init game input
            update();   // init game logic
            render();
        }
    }
    static float x = 0, y =0, z =-20;
    private static void getInput() {
        inp.update();
    }
    private static void update() {
        glPushMatrix();
        glTranslatef(x,y,z);
        glRotatef(90, 0,1,0);
        glCallList(Camera.objectDisplayList);
        y +=0.01f;
        x+=0.01f;
        glPopMatrix();
        glLight(GL_LIGHT0, GL_POSITION, cam.asFloatBuffer(new float[]{var.Lightx,var.Lighty, var.Lightz, 1f}));
    }
    private static void render() {
        glLoadIdentity();


        cam.useViev();
        game.render();
        Display.update();
        Display.sync(60);

    }

    private static void cleanUp() {
        Display.destroy();
        System.exit(0);
    }
}

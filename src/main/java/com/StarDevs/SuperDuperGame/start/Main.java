package com.StarDevs.SuperDuperGame.start;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Void on 27.08.2014.
 */
public class Main {
    static Game game;
    static Camera cam;

    public static void main(String[] args) {

        initDisplay();
        initGL();
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
        cam = new Camera(70, (float)Display.getWidth() / (float)Display.getHeight(), 0.01f, 1000);
    }
    private static void initGame() {
        game = new Game();
    }
    private static void gameLoop() {
        while(!Display.isCloseRequested()){
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            getInput();// init game input
            update();   // init game logic
            render();
        }
    }
    private static void getInput() {
        Input.update();
    }
    private static void update() {
        glLight(GL_LIGHT0, GL_POSITION, cam.asFloatBuffer(new float[]{Variables.Lightx,Variables.Lighty, Variables.Lightz, 1f}));
    }
    private static void render() {
        glLoadIdentity();
        cam.useView();
        game.render();
        Display.update();
        Display.sync(60);
    }
    private static void cleanUp() {
        Display.destroy();
        System.exit(0);
    }
}

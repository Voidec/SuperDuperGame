package com.StarDevs.SuperDuperGame.start;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Void on 27.08.2014.
 */
public class Input {
    Variables var = new Variables();

    public void update(){
        if(Mouse.isButtonDown(0)){
            System.out.println("We've clicked at " + getMousePosition().toString());
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            Main.cam.move(0.2f, 1);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            Main.cam.move(-0.2f, 1);
        }
        if(Keyboard.isKeyDown((Keyboard.KEY_A))){
            Main.cam.move(0.2f, 0);
        }
        if(Keyboard.isKeyDown((Keyboard.KEY_D))){
            Main.cam.move(-0.2f, 0);
        }
        if(Keyboard.isKeyDown((Keyboard.KEY_Z))){
            Main.cam.rotateY(-1f);
        }
        if(Keyboard.isKeyDown((Keyboard.KEY_X))){
            Main.cam.rotateY(1f);
        }
        if(Keyboard.isKeyDown((Keyboard.KEY_Q))){
            Camera.y -= 0.2;
        }
        if(Keyboard.isKeyDown((Keyboard.KEY_E))){
            Camera.y += 0.2;
        }
        if(Keyboard.isKeyDown((Keyboard.KEY_NUMPAD7))){
            var.Lightx += 0.1;
        }
        if(Keyboard.isKeyDown((Keyboard.KEY_NUMPAD9))){
            var.Lightx -= 0.1;
        }
        if(Keyboard.isKeyDown((Keyboard.KEY_NUMPAD4))){
            var.Lighty += 0.1;
        }
        if(Keyboard.isKeyDown((Keyboard.KEY_NUMPAD6))){
            var.Lighty -= 0.1;
        }
        if(Keyboard.isKeyDown((Keyboard.KEY_NUMPAD1))){
            var.Lightz += 0.1;
        }
        if(Keyboard.isKeyDown((Keyboard.KEY_NUMPAD3))){
            var.Lightz -= 0.1;
        }
    }
    public static Vector2f getMousePosition(){
        return new Vector2f(Mouse.getX(), Mouse.getY());
    }
}

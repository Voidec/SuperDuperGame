package com.StarDevs.SuperDuperGame.start;

import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Void on 27.08.2014.
 */
public class Model {
    public List<Vector3f> vertices = new ArrayList<Vector3f>();
    public List<Vector3f> normals = new ArrayList<Vector3f>();
    public List<Face> faces = new ArrayList<Face>();
    public Model(){

    }

}

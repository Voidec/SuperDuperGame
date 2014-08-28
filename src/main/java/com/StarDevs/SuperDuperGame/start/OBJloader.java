package com.StarDevs.SuperDuperGame.start;

import org.lwjgl.util.vector.Vector3f;

import java.io.*;

/**
 * Created by Void on 27.08.2014.
 */
public class OBJloader {
    public static Model loadModel(File f){
        BufferedReader reader = null;
        Model m = new Model();
        try {
            reader = new BufferedReader(new FileReader(f));

        String line;
        while((line = reader.readLine()) != null){
            if(line.startsWith("v ")){
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                m.vertices.add(new Vector3f(x, y, z));
            } else if(line.startsWith("vn ")){
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                m.normals.add(new Vector3f(x, y, z));
            } else if(line.startsWith("f ")){
                float x = Float.valueOf(line.split(" ")[1].split("/")[0]);
                float y = Float.valueOf(line.split(" ")[2].split("/")[0]);
                float z = Float.valueOf(line.split(" ")[3].split("/")[0]);
                float x1 = Float.valueOf(line.split(" ")[1].split("/")[2]);
                float y1 = Float.valueOf(line.split(" ")[2].split("/")[2]);
                float z1 = Float.valueOf(line.split(" ")[3].split("/")[2]);
                Vector3f vertexIndices = new Vector3f(x, y, z);
                Vector3f normalIndices = new Vector3f(x1, y1, z1);
                m.faces.add(new Face(vertexIndices, normalIndices));
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("not done");
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("not done");
            e.printStackTrace();
        }
        return m;
    }
}

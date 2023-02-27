package org.JE.JE2Editor.Tools;

import org.JE.JE2.Scene.Scene;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveScene {

    public static void saveSceneToFile(String directory, String sceneName, SceneObject[] objects){
        if(directory.endsWith("/"))
            directory = directory.substring(0, directory.length()-1);
        if(directory.endsWith("\\"))
            directory = directory.substring(0, directory.length()-1);

        if(!new File(directory).exists())
            new File(directory).mkdirs();

        File sceneFile = new File(directory + "/" + sceneName);

        FileWriter writer;
        try {
            writer = new FileWriter(sceneFile);
            write(writer, objects);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving scene: " + e.getMessage());
        }
    }

    private static void write(FileWriter writer, SceneObject[] objects){
        for (SceneObject object :
                objects) {
            try {
                writer.write(object.toString());
            } catch (IOException e) {
                System.out.println("Error saving object: " + object.sceneRef.identity());
            }
        }
    }
}
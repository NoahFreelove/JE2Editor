package org.JE.JE2Editor.Tools;

import org.JE.JE2Editor.EditorUI.Elements.SceneObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveScene {

    public static void saveSceneToFile(String filePath, SceneObject[] objects){


        File sceneFile = new File(filePath);

        FileWriter writer;
        try {
            writer = new FileWriter(sceneFile);
            write(writer, objects);
            writer.close();
            System.out.println("Saved scene to: " + sceneFile.getAbsolutePath());
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

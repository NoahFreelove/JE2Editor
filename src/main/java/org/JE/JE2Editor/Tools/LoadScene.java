package org.JE.JE2Editor.Tools;

import org.JE.JE2.Objects.GameObject;
import org.JE.JE2.Objects.Identity;
import org.JE.JE2.Objects.Scripts.Base.Script;
import org.JE.JE2.Objects.Scripts.Common.Transform;
import org.JE.JE2Editor.EditorScene;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

public class LoadScene {

    public static void loadSceneFromFile(String filepath) {
        ArrayList<String> lines = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filepath));

            // read all lines to String[]
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }catch (Exception e){
            System.out.println("error while reading from file: " + filepath);
            e.printStackTrace();
        }

        EditorScene.instance.reset();

        GameObject gameObject = new GameObject();

        for (String line : lines) {
            if(line.equals("start"))
                gameObject = new GameObject();
            else if(line.equals("end"))
                EditorScene.instance.addToScene(new SceneObject(gameObject));
            else if(line.startsWith("id:"))
                gameObject.setIdentity((Identity)deserialize(line.substring(3)));
            else{
                Script readScript = (Script) deserialize(line);
                readScript.load();

                if(readScript instanceof Transform t){
                    t.setAttachedObject(gameObject);
                    gameObject.setScript(0, t);
                }
                else {
                    gameObject.addScript(readScript);
                }
            }
        }

    }
    private static Object deserialize(String input){
        try {
            byte[] bytes = Base64.getDecoder().decode(input);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object o = ois.readObject();
            ois.close();
            return o;
        }
        catch (Exception ignore){
            System.out.println("error deserializing: " + input);
            return new Object();
        }
    }
}

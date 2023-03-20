package org.JE.JE2Editor.Tools;

import org.JE.JE2.Objects.GameObject;
import org.JE.JE2.Objects.Identity;
import org.JE.JE2.Objects.Lights.Light;
import org.JE.JE2.Objects.Scripts.Base.Script;
import org.JE.JE2.Objects.Scripts.Common.Transform;
import org.JE.JE2.Rendering.Renderers.SpriteRenderer;
import org.JE.JE2.Rendering.Texture;
import org.JE.JE2.Resources.ResourceLoader;
import org.JE.JE2Editor.EditorScene;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class LoadScene {

    public static void loadSceneFromFile(String[] inputLines, String name) {
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(inputLines));

        /*try {
            Scanner scanner = new Scanner(new File(filepath));

            // read all lines to String[]
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }catch (Exception e){
            System.out.println("error while reading from file: " + filepath);
            e.printStackTrace();
        }*/

        EditorScene.instance.reset();

        GameObject gameObject = new GameObject();
        SceneObject sceneObject = new SceneObject(gameObject);
        for (String line : lines) {
            if(line.equals("start")) {
                gameObject = new GameObject();
                sceneObject = new SceneObject(gameObject);
            }
            else if(line.equals("end")) {
                EditorScene.instance.addToScene(sceneObject);
                boolean hasRenderer = false;
                for (Script s :
                        sceneObject.scripts) {
                    if(s instanceof SpriteRenderer sr){
                        hasRenderer = true;
                        String fp = sr.getTextureFilepath();

                        if(ResourceLoader.get(fp) != null){
                            File f = new File(ResourceLoader.get(fp));
                            if(!f.exists() || f.isDirectory()){
                                continue;
                            }
                            sceneObject.sceneRef.getSpriteRenderer().setTexture(new Texture(ResourceLoader.getBytes(fp)));
                        }
                        String nfp = sr.getNormalFilepath();

                        if(ResourceLoader.get(nfp) != null){
                            File f = new File(ResourceLoader.get(nfp));
                            if(!f.exists() || f.isDirectory()){
                                continue;
                            }
                            sceneObject.sceneRef.getSpriteRenderer().setNormalTexture(new Texture(ResourceLoader.getBytes(nfp)));
                        }
                    }
                }
                if(!hasRenderer)
                    sceneObject.sceneRef.getSpriteRenderer().invalidateShader();
            }
            else if(line.startsWith("id:"))
                gameObject.setIdentity((Identity)deserialize(line.substring(3)));
            else{
                Script readScript = (Script) deserialize(line);
                //readScript.load();
                // we dont want to load script behaviour in the editor for safety reasons
                // (and it doesnt work)

                if(readScript instanceof Transform t){
                    t.setAttachedObject(gameObject);
                    sceneObject.scripts.set(0,t);
                    sceneObject.sceneRef.addScript(new TransformUpdater(t));

                    gameObject.setScript(0, t);
                }
                else if (readScript instanceof Light light){
                    EditorScene.instance.addLight(light);
                    sceneObject.scripts.add(light);
                }
                else {
                    sceneObject.scripts.add(readScript);
                }
            }
        }
        ProjectInfo.activeScenePath = ProjectInfo.sceneDir + "\\" +  name + ".JEScene";
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

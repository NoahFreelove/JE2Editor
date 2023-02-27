package org.JEEditor;

import JE.Manager;
import JE.Objects.GameObject;
import JE.Rendering.Camera;
import JE.Window.WindowPreferences;
import org.joml.Vector2i;

public class Startup {
    public static void main(String[] args) {
        Manager.start(new WindowPreferences(new Vector2i(1920,1080), "JE2 Editor", false, true));
        GameObject camObject = new GameObject();
        Camera camera = new Camera();
        camObject.addScript(camera);
        CameraController cc = new CameraController();
        cc.camRef = camera;
        camObject.addScript(cc);
        EditorScene.instance.add(camObject);
        EditorScene.instance.setCamera(camera);
        Manager.setScene(EditorScene.instance);

        /*GameObject basic = new GameObject();
        basic.setIdentity(new Identity("Object", "gameObject"));

        GameObject basic2 = new GameObject();
        basic2.setIdentity(new Identity("Object", "gameObject"));

        SpriteRenderer sr = new SpriteRenderer();
        SceneObject s = new SceneObject(new Identity("Cool object", "notGameObject"), new Texture(ResourceLoader.get("font.png")), new Vector2f(1,1), new Script[]{sr});
        *//*Manager.addKeyReleasedCallback(new KeyReleasedEvent() {
            @Override
            public void invoke(int i, int i1) {
                if(i == Keyboard.nameToCode("F")){
                    SaveScene.saveSceneToFile("bin/", "coolScene.JEScene", EditorScene.instance.getObjects());
                }
                else if(i == Keyboard.nameToCode("G")){
                    LoadScene.loadSceneFromFile("bin/coolScene.JEScene");
                }
            }
        });*//*
        EditorScene.instance.addToScene(s);*/

        EditorScene.instance.resetHierarchy();
        EditorScene.instance.resetInspector();
        EditorScene.instance.resetFileExplorer();
    }
}
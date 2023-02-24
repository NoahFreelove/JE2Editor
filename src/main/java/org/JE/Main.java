package org.JE;

import JE.Manager;
import JE.Objects.GameObject;
import JE.Objects.Identity;
import JE.Rendering.Camera;
import JE.Rendering.Renderers.SpriteRenderer;
import JE.Rendering.Texture;
import JE.Resources.ResourceLoader;
import JE.Window.WindowPreferences;
import org.JE.ScriptElement.ScriptObject;
import org.joml.Vector2f;
import org.joml.Vector2i;

public class Main {
    public static void main(String[] args) {
        Manager.start(new WindowPreferences(new Vector2i(1280,720), "JE2 Editor", false, false));
        GameObject camObject = new GameObject();
        Camera camera = new Camera();
        camObject.addScript(camera);
        CameraController cc = new CameraController();
        cc.camRef = camera;
        camObject.addScript(cc);
        EditorScene.instance.add(camObject);
        EditorScene.instance.setCamera(camera);
        Manager.setScene(EditorScene.instance);

        GameObject basic = new GameObject();
        basic.setIdentity(new Identity("Object", "gameObject"));

        GameObject basic2 = new GameObject();
        basic2.setIdentity(new Identity("Object", "gameObject"));

        SpriteRenderer sr = new SpriteRenderer();
        SceneObject s = new SceneObject(new Identity("Cool object", "notGameObject"), new Texture(ResourceLoader.get("font.png")), new Vector2f(1,1), new ScriptObject[]{new ScriptObject(sr)});

        EditorScene.instance.addToScene(s);

        EditorScene.instance.resetHierarchy();
        EditorScene.instance.resetInspector();
        EditorScene.instance.resetFileExplorer();
    }
}
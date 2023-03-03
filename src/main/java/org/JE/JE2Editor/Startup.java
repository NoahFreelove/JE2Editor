package org.JE.JE2Editor;

import org.JE.JE2.Manager;
import org.JE.JE2.Objects.GameObject;
import org.JE.JE2.Objects.Identity;
import org.JE.JE2.Objects.Scripts.Base.Script;
import org.JE.JE2.Rendering.Camera;
import org.JE.JE2.Rendering.Renderers.SpriteRenderer;
import org.JE.JE2.Rendering.Texture;
import org.JE.JE2.Resources.ResourceLoader;
import org.JE.JE2.Window.WindowPreferences;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;
import org.joml.Vector2f;
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


        SpriteRenderer sr = new SpriteRenderer();
        SceneObject s = new SceneObject(new Identity("Cool object", "notGameObject"), new Texture(ResourceLoader.get("font.png")), new Vector2f(1,1), new Script[]{sr});
        /*Manager.addKeyReleasedCallback(new KeyReleasedEvent() {
            @Override
            public void invoke(int i, int i1) {
                if(i == Keyboard.nameToCode("F")){
                    SaveScene.saveSceneToFile("bin/", "coolScene.JEScene", EditorScene.instance.getObjects());
                }
                else if(i == Keyboard.nameToCode("G")){
                    LoadScene.loadSceneFromFile("bin/coolScene.JEScene");
                }
            }
        });*/
        EditorScene.instance.addToScene(s);

        EditorScene.instance.resetHierarchy();
        EditorScene.instance.resetInspector();
        EditorScene.instance.resetFileExplorer();
    }
}
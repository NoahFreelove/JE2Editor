package org.JE.JE2Editor;

import org.JE.JE2.Manager;
import org.JE.JE2.Objects.GameObject;
import org.JE.JE2.Objects.Identity;
import org.JE.JE2.Objects.Scripts.Script;
import org.JE.JE2.Rendering.Camera;
import org.JE.JE2.Rendering.Renderers.SpriteRenderer;
import org.JE.JE2.Rendering.Texture;
import org.JE.JE2.Resources.DataLoader;
import org.JE.JE2.Window.WindowPreferences;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;
import org.JE.JE2Editor.Tools.ProjectInfo;
import org.joml.Vector2f;
import org.joml.Vector2i;

public class EditorTest {
    public static void main(String[] args) {
        Manager.start(new WindowPreferences(new Vector2i(1280,720), "JE2 Editor", false, true));
        GameObject camObject = new GameObject();
        Camera camera = new Camera();
        camObject.addScript(camera);
        CameraController cc = new CameraController();
        cc.camRef = camera;
        camObject.addScript(cc);

        Manager.setScene(EditorScene.instance);

        SpriteRenderer sr = new SpriteRenderer();
        sr.setTexture(Texture.checkExistElseCreate("baseGameObject",-1,"texture1.png"));
        sr.setNormalTexture(Texture.checkExistElseCreate("baseGameObject_N",-1,"texture1_N.png"));
        SceneObject s = new SceneObject(new Identity("Cool object", "notGameObject"), Texture.checkExistElseCreate("fontIcon",-1,"font.png"), new Vector2f(1,1), new Script[]{sr});

        ProjectInfo.loadProjectInfo(DataLoader.getBytesAsString("project.txt"));
        EditorScene.instance.reset();
        EditorScene.instance.add(camObject);
        EditorScene.instance.setCamera(camera);
        EditorScene.instance.addToScene(s);

    }
}
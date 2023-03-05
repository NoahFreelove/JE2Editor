package org.JE.JE2Editor;

import org.JE.JE2.Manager;
import org.JE.JE2.Objects.GameObject;
import org.JE.JE2.Rendering.Camera;
import org.JE.JE2.Window.WindowPreferences;
import org.JE.JE2Editor.Tools.LoadScene;
import org.JE.JE2Editor.Tools.ProjectInfo;
import org.joml.Vector2i;

public class Start {
    public static void start(String[] projectFile, String[] defaultEditorScene, Vector2i windowSize){
        ProjectInfo.loadProjectInfo(projectFile);
        Manager.start(new WindowPreferences(windowSize, "JE2 Editor", false, true));

        GameObject camObject = new GameObject();
        Camera camera = new Camera();
        camObject.addScript(camera);
        CameraController cc = new CameraController();
        cc.camRef = camera;
        camObject.addScript(cc);
        EditorScene.instance.add(camObject);
        EditorScene.instance.setCamera(camera);
        Manager.setScene(EditorScene.instance);
        EditorScene.instance.reset();
        LoadScene.loadSceneFromFile(defaultEditorScene, "Scene");
    }
}

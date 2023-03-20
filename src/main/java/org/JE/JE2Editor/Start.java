package org.JE.JE2Editor;

import org.JE.JE2.Manager;
import org.JE.JE2.Resources.ResourceLoader;
import org.JE.JE2.Window.WindowPreferences;
import org.JE.JE2Editor.Tools.LoadScene;
import org.JE.JE2Editor.Tools.ProjectInfo;
import org.joml.Vector2i;

public class Start {
    public static void start(String[] projectFile, Vector2i windowSize){
        ProjectInfo.loadProjectInfo(projectFile);
        Manager.start(new WindowPreferences(windowSize, "JE2 Editor", false, true));
        Manager.setScene(EditorScene.instance);
        System.out.println("Started editor loading default scene: " + ProjectInfo.activeScenePath);
        LoadScene.loadSceneFromFile(ResourceLoader.readTextFile(ProjectInfo.activeScenePath), "Scene");
    }
}

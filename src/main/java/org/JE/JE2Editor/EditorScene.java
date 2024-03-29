package org.JE.JE2Editor;

import org.JE.JE2.IO.UserInput.Keyboard.Keyboard;
import org.JE.JE2.Manager;
import org.JE.JE2.Objects.GameObject;
import org.JE.JE2.Rendering.Camera;
import org.JE.JE2.Resources.DataLoader;
import org.JE.JE2.Resources.ResourceLoadingPolicy;
import org.JE.JE2.Resources.ResourceManager;
import org.JE.JE2.Scene.Scene;
import org.JE.JE2.UI.GetScaledPosition;
import org.JE.JE2.UI.UIElements.Buttons.Button;
import org.JE.JE2.UI.UIElements.Label;
import org.JE.JE2.UI.UIElements.Spacer;
import org.JE.JE2.Utility.GarbageCollection;
import org.JE.JE2Editor.EditorUI.*;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;
import org.JE.JE2Editor.EditorUI.Tools.*;
import org.JE.JE2Editor.Tools.LoadScene;
import org.JE.JE2Editor.Tools.ProjectInfo;
import org.JE.JE2Editor.Tools.SaveScene;
import org.joml.Vector2f;
import org.lwjgl.nuklear.Nuklear;

import java.util.ArrayList;


public class EditorScene extends Scene {
    public static EditorScene instance = new EditorScene();
    private ArrayList<SceneObject> worldObjects = new ArrayList<>();

    public EditorScene(){
        ResourceManager.policy = ResourceLoadingPolicy.CHECK_BY_NAME;
        addUI(HierarchyWindow.instance);
        addUI(InspectorWindow.instance);
        addUI(FileExplorer.instance);
        addUI(FileViewer.instance);
        addUI(ToolsWindow.instance);

        Keyboard.addKeyPressedEvent((code, mods) -> {
            if(Keyboard.nameToCode("F1") == code){
                resetHierarchy();
            }
            else if(Keyboard.nameToCode("F2") == code){
                resetInspector();
            }
            else if(Keyboard.nameToCode("F3") == code){
                resetFileExplorer();
            }
            else if(Keyboard.nameToCode("F12") == code){
                resetHierarchy();
                resetInspector();
                resetFileExplorer();
                resetToolsWindow();
            }
        });
    }

    public void resetFileExplorer() {
        if(!world.UI.contains(FileExplorer.instance)){
            world.UI.add(FileExplorer.instance);
        }
        else{
            FileExplorer.instance.setPos(GetScaledPosition.getScaledPosition(0f,80f,new Vector2f(1280,720), new Vector2f(Manager.getWindowSize().x(),Manager.getWindowSize().y())));
            FileExplorer.instance.setSize(GetScaledPosition.getScaledPosition(80f,20f,new Vector2f(1280,720), new Vector2f(Manager.getWindowSize().x(),Manager.getWindowSize().y())));
        }
        FileExplorer.instance.refresh();
    }

    public void resetToolsWindow(){
        if(!world.UI.contains(ToolsWindow.instance))
            world.UI.add(ToolsWindow.instance);

        ToolsWindow.instance.children.clear();
        ToolsWindow.instance.children.add(new Label("Tools", Nuklear.NK_TEXT_CENTERED));

        ToolsWindow.instance.setPos(GetScaledPosition.getScaledPosition(80f,80f,new Vector2f(1280,720), new Vector2f(Manager.getWindowSize().x(),Manager.getWindowSize().y())));
        ToolsWindow.instance.setSize(GetScaledPosition.getScaledPosition(20f,20f,new Vector2f(1280,720), new Vector2f(Manager.getWindowSize().x(),Manager.getWindowSize().y())));
        ToolsWindow.instance.children.add(new Button("Save Scene", () -> {
            SaveScene.saveSceneToFile(ProjectInfo.activeScenePath, EditorScene.instance.getObjects());
        }));

        ToolsWindow.instance.children.add(new Button("Load Scene", () -> {
            LoadScene.loadSceneFromFile(DataLoader.readTextFile(ProjectInfo.activeScenePath), "scene");
        }));
        ToolsWindow.instance.children.add(new CompileScriptsButton());
        ToolsWindow.instance.children.add(new RunGameButton());

        ToolsWindow.instance.children.add(new BuildGameButton());
        ToolsWindow.instance.children.add(new Button("Garbage Collection", GarbageCollection::takeOutDaTrash));
        ToolsWindow.instance.children.add(new ToggleLightingModeButton());
    }

    public void resetInspector(){
        if(!world.UI.contains(InspectorWindow.instance)){
            world.UI.add(InspectorWindow.instance);
        }
        else{
            InspectorWindow.instance.children.clear();
            InspectorWindow.instance.setPos(GetScaledPosition.getScaledPosition(80f,0f,new Vector2f(1280,720), new Vector2f(Manager.getWindowSize().x(),Manager.getWindowSize().y())));
            InspectorWindow.instance.setSize(GetScaledPosition.getScaledPosition(20f,80f,new Vector2f(1280,720), new Vector2f(Manager.getWindowSize().x(),Manager.getWindowSize().y())));
        }
    }

    public void resetHierarchy(){
        if(!world.UI.contains(HierarchyWindow.instance)){
            world.UI.add(HierarchyWindow.instance);
        }
        else {
            HierarchyWindow.instance.setPos(new Vector2f(0,0));
            HierarchyWindow.instance.children.clear();

            worldObjects.forEach(sceneObject -> {
                HierarchyWindow.instance.addItem(sceneObject, 0);
                HierarchyWindow.instance.children.add(new Spacer());
            });
            HierarchyWindow.instance.children.add(new Spacer());

            HierarchyWindow.instance.children.add(new Button("Add Object", () -> {
                SceneObject object = new SceneObject();
                object.sceneRef.setIdentity("Empty GameObject", "Untagged");
                object.sceneRef.getSpriteRenderer().invalidateShader();
                addToScene(object);
            }));

            HierarchyWindow.instance.setSize(GetScaledPosition.getScaledPosition(20f,80f,new Vector2f(1280,720), new Vector2f(Manager.getWindowSize().x(),Manager.getWindowSize().y())));
        }
    }

    public void addToScene(SceneObject object){
        add(object.sceneRef);
        if(object.sceneRef.getParent() != object.sceneRef)
            return;
        if(!worldObjects.contains(object))
            worldObjects.add(object);
        resetHierarchy();
    }

    public void removeFromScene(SceneObject object){
        remove(object.sceneRef);
        worldObjects.remove(object);
        resetInspector();
        resetHierarchy();
    }
    public SceneObject[] getObjects(){
        return worldObjects.toArray(new SceneObject[0]);
    }

    public void reset() {
        worldObjects.clear();
        world.gameObjects.clear();
        world.lights.clear();
        resetHierarchy();
        resetInspector();
        resetToolsWindow();
        resetFileExplorer();

        GameObject camObject = new GameObject();
        Camera camera = new Camera();
        camObject.addScript(camera);
        CameraController cc = new CameraController();
        cc.camRef = camera;
        camObject.addScript(cc);
        EditorScene.instance.setCamera(camera);
        EditorScene.instance.add(camObject);
    }
}

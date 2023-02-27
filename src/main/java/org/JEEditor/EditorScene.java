package org.JEEditor;

import JE.IO.UserInput.Keyboard.Keyboard;
import JE.Manager;
import JE.Scene.Scene;
import JE.UI.GetScaledPosition;
import JE.UI.UIElements.Buttons.Button;
import JE.UI.UIElements.Spacer;
import org.JEEditor.EditorUI.Elements.SceneObject;
import org.JEEditor.EditorUI.FileExplorer;
import org.JEEditor.EditorUI.FileViewer;
import org.JEEditor.EditorUI.HierarchyWindow;
import org.JEEditor.EditorUI.InspectorWindow;
import org.JEEditor.Tools.LoadScene;
import org.JEEditor.Tools.SaveScene;
import org.joml.Vector2f;

import java.util.ArrayList;


public class EditorScene extends Scene {
    public static EditorScene instance = new EditorScene();
    private ArrayList<SceneObject> worldObjects = new ArrayList<>();

    public EditorScene(){
        addUI(HierarchyWindow.instance);
        addUI(InspectorWindow.instance);
        addUI(FileExplorer.instance);
        addUI(FileViewer.instance);

        Keyboard.keyPressedEvents.add((code, mods) -> {
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
            }
        });
    }

    public void resetFileExplorer() {
        if(!world.UI.contains(FileExplorer.instance)){
            world.UI.add(FileExplorer.instance);
        }
        else{
            FileExplorer.instance.setPos(GetScaledPosition.getScaledPosition(0f,80f,new Vector2f(1280,720), new Vector2f(Manager.getWindowSize().x(),Manager.getWindowSize().y())));
            FileExplorer.instance.setSize(GetScaledPosition.getScaledPosition(100f,20f,new Vector2f(1280,720), new Vector2f(Manager.getWindowSize().x(),Manager.getWindowSize().y())));
        }
        FileExplorer.instance.refresh();
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
                addToScene(object);
            }));
            HierarchyWindow.instance.children.add(new Button("Save Scene", () -> {
                SaveScene.saveSceneToFile("bin/","scene.JEScene", EditorScene.instance.getObjects());
            }));

            HierarchyWindow.instance.children.add(new Button("Load Scene", () -> {
                LoadScene.loadSceneFromFile("bin/scene.JEScene");
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
    public SceneObject[] getObjects(){
        return worldObjects.toArray(new SceneObject[0]);
    }

    public void reset() {
        worldObjects.clear();
        world.gameObjects.clear();
        world.lights.clear();
        resetHierarchy();
        resetInspector();
    }
}

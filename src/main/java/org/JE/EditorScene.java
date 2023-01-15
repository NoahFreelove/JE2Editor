package org.JE;

import JE.IO.UserInput.KeyPressedEvent;
import JE.IO.UserInput.Keyboard;
import JE.Manager;
import JE.Objects.Base.GameObject;
import JE.Scene.Scene;
import JE.UI.GetScaledPosition;
import org.JE.UI.FileExplorer;
import org.JE.UI.HierarchyWindow;
import org.JE.UI.InspectorWindow;
import org.joml.Vector2f;

public class EditorScene extends Scene {
    public static EditorScene instance = new EditorScene();
    public EditorScene(){
        addUI(HierarchyWindow.instance);
        addUI(InspectorWindow.instance);
        addUI(FileExplorer.instance);

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
    }

    public void resetInspector(){
        if(!world.UI.contains(InspectorWindow.instance)){
            world.UI.add(InspectorWindow.instance);
        }
        else{
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
            HierarchyWindow.instance.setSize(GetScaledPosition.getScaledPosition(20f,80f,new Vector2f(1280,720), new Vector2f(Manager.getWindowSize().x(),Manager.getWindowSize().y())));
        }

    }

    @Override
    public void add(GameObject object){
        super.add(object);
        HierarchyWindow.instance.addItem(object);
    }
}

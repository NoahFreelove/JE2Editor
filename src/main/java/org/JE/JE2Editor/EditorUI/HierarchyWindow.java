package org.JE.JE2Editor.EditorUI;

import org.JE.JE2.UI.UIElements.Label;
import org.JE.JE2.UI.UIObjects.UIWindow;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;
import org.joml.Vector2f;

import static org.lwjgl.nuklear.Nuklear.*;

public class HierarchyWindow extends UIWindow {

    public static HierarchyWindow instance = new HierarchyWindow(new Vector2f(0,0));

    public HierarchyWindow(Vector2f pos){
        super("Hierarchy");
        setPos(pos);
        windowOptions = NK_WINDOW_SCALABLE | NK_WINDOW_MINIMIZABLE | NK_WINDOW_MOVABLE;
    }

    public void addItem(SceneObject object, int i){
        children.add(new ObjectHierarchyButton(object));

        if(object.sceneRef.getChildren().length>0){
            if(i == 0){
                children.add(new Label("Children:"));
            }
            else {
                children.add(new Label("Children^" + (i+1) + ":"));

            }
            for (SceneObject c :
                    object.children) {
                addItem(c,i+1);
            }

            if(i == 0){
                children.add(new Label("End Children"));
            }
            else {
                children.add(new Label("End Children^" + (i+1)));

            }
        }
    }
}

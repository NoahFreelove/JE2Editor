package org.JE.UI;

import JE.Objects.Base.GameObject;
import JE.UI.UIElements.Button;
import JE.UI.UIObjects.UIWindow;
import org.joml.Vector2i;

import static org.lwjgl.nuklear.Nuklear.*;

public class HierarchyWindow extends UIWindow {
    public static HierarchyWindow instance = new HierarchyWindow(new Vector2i(0,0));
    public HierarchyWindow(Vector2i pos){
        super("Hierarchy");
        homePosition = pos;
        windowOptions = NK_WINDOW_SCALABLE | NK_WINDOW_MINIMIZABLE | NK_WINDOW_MOVABLE;
    }

    public void addItem(GameObject object){
        children.add(new Button(object.getIdentity().name + " : " + object.getIdentity().tag, () -> InspectorWindow.instance.setItem(object)));
    }
}

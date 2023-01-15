package org.JE.UI;

import JE.Objects.Base.GameObject;
import JE.UI.UIElements.Buttons.StyledButton;

import JE.UI.UIObjects.UIWindow;
import org.joml.Vector2f;

import static org.lwjgl.nuklear.Nuklear.*;

public class HierarchyWindow extends UIWindow {

    public static HierarchyWindow instance = new HierarchyWindow(new Vector2f(0,0));

    public HierarchyWindow(Vector2f pos){
        super("Hierarchy");
        setPos(pos);
        windowOptions = NK_WINDOW_SCALABLE | NK_WINDOW_MINIMIZABLE | NK_WINDOW_MOVABLE;
    }

    public void addItem(GameObject object){
        children.add(new StyledButton(object.getIdentity().name + " : " + object.getIdentity().tag, () -> InspectorWindow.instance.setItem(object), Styles.buttonStyle));
    }
}

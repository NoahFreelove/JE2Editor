package org.JE.UI;

import JE.Objects.Base.GameObject;
import JE.Objects.Components.Base.Component;
import JE.UI.UIElements.Buttons.Button;
import JE.UI.UIObjects.UIWindow;
import org.JE.UI.Elements.ComponentElement;
import org.joml.Vector2f;
import org.joml.Vector2i;

import static org.lwjgl.nuklear.Nuklear.*;

public class InspectorWindow extends UIWindow {
    public static InspectorWindow instance = new InspectorWindow(new Vector2f(200,0));
    private GameObject selected = new GameObject();

    public InspectorWindow(Vector2f pos){
        super("Inspector");
        setPos(pos);
        windowOptions = NK_WINDOW_SCALABLE | NK_WINDOW_MINIMIZABLE | NK_WINDOW_MOVABLE;
    }


    public void setItem(GameObject object){
        selected = object;
        children.clear();

        selected.getComponents().forEach((c) -> {
            children.add(new ComponentElement(c));
        });
    }
}

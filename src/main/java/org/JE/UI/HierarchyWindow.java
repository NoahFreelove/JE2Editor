package org.JE.UI;

import JE.Objects.Base.GameObject;
import JE.UI.UIElements.Buttons.StyledButton;

import JE.UI.UIElements.Label;
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

    public void addItem(GameObject object, int i){
        children.add(new StyledButton(object.getIdentity().name + " : " + object.getIdentity().tag, Styles.buttonStyle, () -> InspectorWindow.instance.setItem(object)));

        if(object.getChildren().length>0){
            if(i == 0){
                children.add(new Label("Children:"));
            }
            else {
                children.add(new Label("Children^" + (i+1) + ":"));

            }
            for (GameObject c :
                    object.getChildren()) {
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

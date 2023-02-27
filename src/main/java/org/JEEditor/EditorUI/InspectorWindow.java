package org.JEEditor.EditorUI;


import JE.Objects.Scripts.Base.Script;
import JE.UI.UIObjects.UIWindow;
import org.JEEditor.EditorUI.Elements.ScriptElement;
import org.JEEditor.EditorUI.Elements.SceneObject;
import org.JEEditor.ScriptElement.AddScriptButton;
import org.joml.Vector2f;

import static org.lwjgl.nuklear.Nuklear.*;

public class InspectorWindow extends UIWindow {
    public static InspectorWindow instance = new InspectorWindow(new Vector2f(200,0));
    public SceneObject selected = new SceneObject();

    public InspectorWindow(Vector2f pos){
        super("Inspector");
        setPos(pos);
        windowOptions = NK_WINDOW_SCALABLE | NK_WINDOW_MINIMIZABLE | NK_WINDOW_MOVABLE;
    }


    public void setItem(SceneObject object){
        selected = object;
        children.clear();

        for (Script c :
                selected.scripts) {
            children.add(new ScriptElement(c));
        }
        children.add(new AddScriptButton(object));
    }
}

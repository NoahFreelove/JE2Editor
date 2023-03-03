package org.JE.JE2Editor.EditorUI;

import org.JE.JE2.Objects.Scripts.Base.Script;
import org.JE.JE2.UI.UIObjects.UIWindow;
import org.JE.JE2Editor.EditorUI.Elements.ScriptElement;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;
import org.JE.JE2Editor.ScriptElement.AddScriptButton;
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

        children.add(new IdentityChanger(object));

        for (Script c :
                selected.scripts) {
            children.add(new ScriptElement(c));
        }
        children.add(new AddScriptButton(object));
        children.add(new DeleteObjectButton(object));
    }

}

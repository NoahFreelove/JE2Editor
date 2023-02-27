package org.JEEditor.ScriptElement;

import JE.Objects.Scripts.Base.Script;
import JE.UI.UIElements.Buttons.Button;
import JE.UI.UIElements.TextField;
import JE.UI.UIElements.UIElement;
import org.JEEditor.EditorUI.Elements.SceneObject;
import org.JEEditor.EditorUI.InspectorWindow;

public class AddScriptButton extends UIElement {
    private TextField tf;
    private Button button;
    private SceneObject selected;
    private String text;

    public AddScriptButton(SceneObject selected) {
        tf =  new TextField(128);
        text = "Add Component";

        button = new Button(text, this::add);
        this.selected = selected;
    }

    private void add(){
        try {
            String className = tf.getValue();
            Class<?> c = Class.forName(className);
            Script s = (Script)c.getConstructor().newInstance();
            selected.scripts.add(s);
            InspectorWindow.instance.setItem(selected);
        }
        catch (Exception e){
            text = "Class Does Not Exist!";
        }
    }

    @Override
    protected void render() {
        button.text = text;
        tf.requestRender();
        button.requestRender();

    }
}

package org.JE.ScriptElement;

import JE.Objects.Scripts.Base.Script;
import JE.UI.UIElements.Buttons.Button;
import JE.UI.UIElements.TextField;
import JE.UI.UIElements.UIElement;
import org.JE.EditorUI.Elements.SceneObject;
import org.JE.EditorUI.InspectorWindow;

public class AddScriptButton extends UIElement {
    private TextField tf;
    private Button button;
    private SceneObject selected;

    public AddScriptButton(SceneObject selected) {
        tf =  new TextField(128);
        button = new Button("Add Component", this::add);
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
            e.printStackTrace();
        }
    }

    @Override
    protected void render() {
        tf.requestRender();
        button.requestRender();

    }
}

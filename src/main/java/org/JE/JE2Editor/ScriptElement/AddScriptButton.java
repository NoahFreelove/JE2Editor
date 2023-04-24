package org.JE.JE2Editor.ScriptElement;

import org.JE.JE2.Objects.Scripts.Script;
import org.JE.JE2.Rendering.Renderers.Renderer;
import org.JE.JE2.Rendering.Shaders.ShaderProgram;
import org.JE.JE2.UI.UIElements.Buttons.Button;
import org.JE.JE2.UI.UIElements.TextField;
import org.JE.JE2.UI.UIElements.UIElement;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;
import org.JE.JE2Editor.EditorUI.InspectorWindow;

public class AddScriptButton extends UIElement {
    private TextField tf;
    private Button button;
    private SceneObject selected;
    private String text;

    public AddScriptButton(SceneObject selected) {
        tf =  new TextField(128);
        text = "Add Script";

        button = new Button(text, this::add);
        this.selected = selected;
    }

    private void add(){
        try {
            String className = tf.getValue();
            Class<?> c = Class.forName(className);
            Script s = (Script)c.getConstructor().newInstance();
            if(s instanceof Renderer)
                selected.sceneRef.getRenderer().getVAO().setShaderProgram(ShaderProgram.spriteShader());
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

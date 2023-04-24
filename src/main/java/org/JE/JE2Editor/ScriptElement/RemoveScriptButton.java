package org.JE.JE2Editor.ScriptElement;

import org.JE.JE2.Objects.Scripts.Script;
import org.JE.JE2.Rendering.Renderers.Renderer;
import org.JE.JE2.UI.UIElements.Buttons.Button;
import org.JE.JE2.UI.UIElements.UIElement;
import org.JE.JE2Editor.EditorUI.InspectorWindow;
import org.JE.JE2Editor.EditorUI.StringFormatter;

public class RemoveScriptButton extends UIElement {
    private Button remove;
    private Script script;
    public RemoveScriptButton(Script script) {
        this.script = script;
        remove = new Button("Remove " + StringFormatter.capAndSplit(script.getClass().getSimpleName()), this::remove);
    }

    private void remove(){
        if(script.getRestrictions().canBeRemoved)
        {
            InspectorWindow.instance.selected.scripts.remove(script);
            if(script instanceof Renderer)
            {
                boolean hasRenderer = false;
                for (Script s: InspectorWindow.instance.selected.scripts) {
                    if(s instanceof Renderer)
                        hasRenderer = true;
                }
                if(!hasRenderer)
                    InspectorWindow.instance.selected.sceneRef.getSpriteRenderer().invalidateShader();
            }
            InspectorWindow.instance.setItem(InspectorWindow.instance.selected);
        }
    }

    @Override
    protected void render() {
        remove.requestRender();
    }
}

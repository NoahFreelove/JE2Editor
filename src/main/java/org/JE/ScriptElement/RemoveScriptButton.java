package org.JE.ScriptElement;

import JE.Objects.Scripts.Base.Script;
import JE.UI.UIElements.Buttons.Button;
import JE.UI.UIElements.UIElement;
import org.JE.EditorUI.InspectorWindow;

public class RemoveScriptButton extends UIElement {
    private Button remove;
    private Script script;
    public RemoveScriptButton(Script script) {
        this.script = script;
        remove = new Button("Remove " + script.getClass().getSimpleName(), this::remove);
    }

    private void remove(){
        if(script.getRestrictions().canBeRemoved)
        {
            InspectorWindow.instance.selected.scripts.remove(script);
            InspectorWindow.instance.setItem(InspectorWindow.instance.selected);
        }
    }

    @Override
    protected void render() {
        remove.requestRender();
    }
}

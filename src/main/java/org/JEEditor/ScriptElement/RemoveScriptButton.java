package org.JEEditor.ScriptElement;

import JE.Objects.Scripts.Base.Script;
import JE.UI.UIElements.Buttons.Button;
import JE.UI.UIElements.UIElement;
import org.JEEditor.EditorUI.InspectorWindow;
import org.JEEditor.EditorUI.StringFormatter;

public class RemoveScriptButton extends UIElement {
    private Button remove;
    private Script script;
    public RemoveScriptButton(Script script) {
        this.script = script;
        remove = new Button("Remove " + StringFormatter.capSplit(script.getClass().getSimpleName()), this::remove);
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

package org.JE.JE2Editor.ScriptElement;

import org.JE.JE2.Objects.Scripts.Base.Script;
import org.JE.JE2.UI.UIElements.Buttons.Button;
import org.JE.JE2.UI.UIElements.UIElement;
import org.JE.JE2Editor.EditorUI.InspectorWindow;
import org.JE.JE2Editor.EditorUI.StringFormatter;

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

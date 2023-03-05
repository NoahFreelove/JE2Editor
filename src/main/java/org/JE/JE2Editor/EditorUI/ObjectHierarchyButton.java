package org.JE.JE2Editor.EditorUI;

import org.JE.JE2.UI.UIElements.Buttons.StyledButton;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;

public class ObjectHierarchyButton extends StyledButton {

    private SceneObject objRef;
    public ObjectHierarchyButton(SceneObject obj) {
        super("", Styles.buttonStyle, () -> InspectorWindow.instance.setItem(obj));
        this.objRef = obj;
    }

    @Override
    protected void render() {
        text = objRef.sceneRef.identity().name + " : " + objRef.sceneRef.identity().tag;
        super.render();
    }
}

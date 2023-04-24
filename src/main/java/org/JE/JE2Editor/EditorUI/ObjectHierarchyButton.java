package org.JE.JE2Editor.EditorUI;

import org.JE.JE2.UI.UIElements.Buttons.Button;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;

public class ObjectHierarchyButton extends Button {

    private SceneObject objRef;
    public ObjectHierarchyButton(SceneObject obj) {
        super("", () -> InspectorWindow.instance.setItem(obj));
        setStyle(Styles.buttonStyle);
        this.objRef = obj;
    }

    @Override
    protected void render() {
        text = objRef.sceneRef.identity().name + " : " + objRef.sceneRef.identity().tag;
        super.render();
    }
}

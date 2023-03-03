package org.JE.JE2Editor.EditorUI;

import org.JE.JE2.UI.UIElements.Label;
import org.JE.JE2.UI.UIElements.TextField;
import org.JE.JE2.UI.UIElements.UIElement;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;

public class IdentityChanger extends UIElement {

    private SceneObject ref;
    private TextField name;
    private TextField tag;
    private Label nameLabel;
    private Label tagLabel;

    public IdentityChanger(SceneObject sceneObject) {
        this.ref = sceneObject;
        name = new TextField(128);
        name.setValue(sceneObject.sceneRef.identity().name);
        tag = new TextField(64);
        tag.setValue(sceneObject.sceneRef.identity().tag);

        nameLabel = new Label("Name:");
        tagLabel = new Label("Tag:");
    }

    @Override
    protected void render() {
        nameLabel.requestRender();
        name.requestRender();
        tagLabel.requestRender();
        tag.requestRender();
        ref.sceneRef.identity().name = name.getValue();
        ref.sceneRef.identity().tag = tag.getValue();
    }

}

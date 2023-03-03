package org.JE.JE2Editor.EditorUI;

import org.JE.JE2.UI.UIElements.Buttons.Button;
import org.JE.JE2Editor.EditorScene;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;

public class DeleteObjectButton extends Button {

    public DeleteObjectButton(SceneObject sceneObject) {
        super("Delete " + sceneObject.sceneRef.identity().name, () -> EditorScene.instance.removeFromScene(sceneObject));
    }
}

package org.JE;

import JE.Objects.Base.GameObject;
import JE.Scene.Scene;
import JE.UI.UIObjects.UIObject;
import JE.UI.UIObjects.UIWindow;
import org.JE.UI.HierarchyWindow;
import org.JE.UI.InspectorWindow;
import org.joml.Vector2i;

public class EditorScene extends Scene {
    public static EditorScene instance = new EditorScene();
    public EditorScene(){
        addUI(HierarchyWindow.instance);
        addUI(InspectorWindow.instance);
    }

    @Override
    public void add(GameObject object){
        super.add(object);
        HierarchyWindow.instance.addItem(object);
    }
}

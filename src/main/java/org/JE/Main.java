package org.JE;

import JE.Manager;
import JE.Objects.Base.GameObject;
import JE.Objects.Identity;
import JE.Objects.Base.Sprite;
import JE.Window.WindowPreferences;
import org.joml.Vector2i;

public class Main {
    public static void main(String[] args) {
        Manager.start(new WindowPreferences(new Vector2i(1920,1080), "JE2 Editor", false, true));
        Manager.setScene(EditorScene.instance);
        Sprite sprite = new Sprite();
        sprite.setIdentity(new Identity("Sprite", "gameObject"));
        GameObject basic = new GameObject();
        basic.setIdentity(new Identity("Object", "gameObject"));
        basic.setParent(sprite);
        GameObject basic2 = new GameObject();
        basic2.setIdentity(new Identity("Object", "gameObject"));
        basic2.setParent(basic);

        GameObject secondUp = new GameObject();
        secondUp.setIdentity(new Identity("Object", "gameObject"));
        secondUp.setParent(sprite);
        EditorScene.instance.add(sprite);

        EditorScene.instance.resetHierarchy();
        EditorScene.instance.resetInspector();
        EditorScene.instance.resetFileExplorer();
    }
}
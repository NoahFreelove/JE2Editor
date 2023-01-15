package org.JE;

import JE.Manager;
import JE.Objects.Base.GameObject;
import JE.Objects.Base.Identity;
import JE.Objects.Base.Sprites.Sprite;
import JE.Window.Window;
import JE.Window.WindowPreferences;
import org.joml.Vector2i;

public class Main {
    public static void main(String[] args) {
        Manager.start(new WindowPreferences(new Vector2i(1000,1000), "JE2 Editor", false, true));
        Manager.setScene(EditorScene.instance);
        Sprite sprite = new Sprite();
        sprite.setIdentity(new Identity("Sprite", "gameObject"));
        GameObject basic = new GameObject();
        basic.setIdentity(new Identity("Basic", "gameObject"));
        EditorScene.instance.add(sprite,basic);
    }
}
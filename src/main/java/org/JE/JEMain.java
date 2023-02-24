package org.JE;
import JE.Scene.Scene;
import JE.Window.WindowPreferences;
import JE.Manager;
import org.joml.Vector2i;
public class JEMain {
public static void main(String[] args) {
Manager.start(new WindowPreferences(new Vector2i(1280, 720), "JE2 Editor", false, true));
Scene scene0 = new Scene().load("scene.JEScene");
Manager.addBuildScene(scene0);
Manager.setScene(0);
}
}

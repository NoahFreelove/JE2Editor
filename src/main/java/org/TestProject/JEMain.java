package org.TestProject;
import org.JE.JE2.Scene.Scene;
import org.JE.JE2.Window.WindowPreferences;
import org.JE.JE2.Manager;
import org.joml.Vector2i;
public class JEMain {
public static void main(String[] args) {
Manager.start(new WindowPreferences(new Vector2i(1920, 1080), "Game", false, true));
/*Scene scene0 = new Scene().load("scene.JEScene");
Manager.addBuildScene(scene0);*/
Manager.setScene(0);
}
}

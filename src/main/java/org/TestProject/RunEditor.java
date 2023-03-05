package org.TestProject;

import org.JE.JE2.Resources.ResourceLoader;
import org.joml.Vector2i;

public class RunEditor {
    public static void main(String[] args) {
        org.JE.JE2Editor.Start.start(ResourceLoader.getBytesAsString("project.txt"), ResourceLoader.getBytesAsString("scene.JEScene"), new Vector2i(2560,1440));
    }
}

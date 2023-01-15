package org.JE.UI;

import JE.Objects.Base.GameObject;
import JE.UI.UIElements.Buttons.StyledButton;
import JE.UI.UIObjects.UIWindow;
import org.joml.Vector2f;

import java.io.File;

import static org.lwjgl.nuklear.Nuklear.*;

public class FileExplorer extends UIWindow {

    public static FileExplorer instance = new FileExplorer(new Vector2f(400,0));

    public FileExplorer(Vector2f pos){
        super("File Explorer");
        setPos(pos);
        windowOptions = NK_WINDOW_SCALABLE | NK_WINDOW_MINIMIZABLE | NK_WINDOW_MOVABLE;
    }

    public void addFile(File file){
        //children.add(new StyledButton(object.getIdentity().name + " : " + object.getIdentity().tag, () -> InspectorWindow.instance.setItem(object), Styles.buttonStyle));
    }
}

package org.JE.UI;

import JE.Rendering.Texture;
import JE.UI.UIObjects.UIWindow;
import org.joml.Vector2f;

import static org.lwjgl.nuklear.Nuklear.*;

public class FileViewer extends UIWindow {
    public static FileViewer instance = new FileViewer(new Vector2f(200,0));

    public FileViewer(Vector2f pos) {
        super("File Viewer");
        setPos(pos);
        windowOptions = NK_WINDOW_SCALABLE | NK_WINDOW_MINIMIZABLE | NK_WINDOW_MOVABLE;
    }

    public void viewFile(Filetype ft, String fp){
        if(ft == Filetype.IMAGE){
            Texture texture = new Texture(fp);
            System.out.println("Viewing image: " + fp);
        }
    }
}

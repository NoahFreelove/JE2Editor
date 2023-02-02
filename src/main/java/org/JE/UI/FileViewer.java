package org.JE.UI;

import JE.UI.UIElements.UIImage;
import JE.UI.UIObjects.UIWindow;
import org.joml.Vector2f;

import static org.lwjgl.nuklear.Nuklear.*;

public class FileViewer extends UIWindow {
    public static FileViewer instance = new FileViewer(new Vector2f(200,0));
    private UIImage image = new UIImage();

    public FileViewer(Vector2f pos) {
        super("File Viewer");
        setPos(pos);
        windowOptions = NK_WINDOW_SCALABLE | NK_WINDOW_MINIMIZABLE | NK_WINDOW_MOVABLE;
        children.add(image);
    }

    public void viewFile(Filetype ft, String fp){

        image.setVisible(false);
        if(ft == Filetype.IMAGE){
            image.setImage(fp);
            image.setVisible(true);
        }
    }
}

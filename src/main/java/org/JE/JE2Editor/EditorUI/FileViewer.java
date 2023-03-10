package org.JE.JE2Editor.EditorUI;

import org.JE.JE2.UI.UIElements.Style.Color;
import org.JE.JE2.UI.UIElements.UIImage;
import org.JE.JE2.UI.UIObjects.UIWindow;
import org.joml.Vector2f;

import java.io.File;
import java.util.Scanner;

import static org.lwjgl.nuklear.Nuklear.*;

public class FileViewer extends UIWindow {
    public static FileViewer instance = new FileViewer(new Vector2f(200,0));
    private UIImage image = new UIImage();
    private MultiLine text = new MultiLine(new String[]{},NK_TEXT_ALIGN_LEFT, Color.WHITE);

    public FileViewer(Vector2f pos) {
        super("File Viewer");
        setPos(pos);
        windowOptions = NK_WINDOW_SCALABLE | NK_WINDOW_MINIMIZABLE | NK_WINDOW_MOVABLE;
        children.add(image);
        children.add(text);
        isVisible = false;
    }

    public void viewFile(Filetype ft, String fp){
        isVisible = true;

        image.setVisible(false);
        text.setVisible(false);
        if(ft == Filetype.IMAGE){
            image.setImage(fp);
            image.setVisible(true);
        }
        else if (ft == Filetype.TEXT){
            try {
                Scanner sc = new Scanner(new File(fp));
                StringBuilder sb = new StringBuilder();
                while(sc.hasNextLine()){
                    sb.append(sc.nextLine());
                    sb.append("\n");
                }
                String[] allLines = sb.toString().split("\n");
                text.set(allLines, NK_TEXT_ALIGN_LEFT, Color.WHITE);

            }
            catch (Exception ignore){}
            text.setVisible(true);
        }
    }
}

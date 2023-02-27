package org.JEEditor.EditorUI;

import JE.Resources.ResourceLoader;
import JE.UI.UIElements.Buttons.TextImageButton;
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
        refresh();
    }

    public void refresh(){
        children.clear();
        searchDir("src/main/resources/");
    }

    public void searchDir(String directory){
        // Call addFile() for every file in given directory
        File dir = new File(directory);
        File[] files = dir.listFiles();
        if(files == null)
            return;
        for (File file : files) {
            if (file.isDirectory()) {
                searchDir(file.getAbsolutePath());
            } else {
                addFile(file);
            }
        }
    }

    public void addFile(File file){
        Filetype ft = decide(file);
        children.add(new TextImageButton(file.getName(), ResourceLoader.getBytes(getFileTypeIcon(ft)), ()->{
            FileViewer.instance.viewFile(ft, file.getAbsolutePath());
        }, new Vector2f(128,128)));
    }

    public Filetype decide(File file){
        String extension = file.getName().substring(file.getName().lastIndexOf(".")+1).toLowerCase();

        return switch (extension)
        {
            case "png", "jpg" -> Filetype.IMAGE;
            case "ttf" -> Filetype.FONT;
            case "txt" -> Filetype.TEXT;
            default -> Filetype.UNKNOWN;
        };
    }

    public String getFileTypeIcon(Filetype ft){
        return switch (ft){
            case IMAGE -> "image.png";
            case FONT -> "font.png";
            default -> "unknown.png";
        };
    }
}

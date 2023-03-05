package org.JE.JE2Editor.EditorUI.Tools;

import org.JE.JE2.UI.UIObjects.UIWindow;

public class ToolsWindow extends UIWindow {
    public static ToolsWindow instance = new ToolsWindow();
    public ToolsWindow(){
        super("Tools");
        windowOptions = 0;
    }
}

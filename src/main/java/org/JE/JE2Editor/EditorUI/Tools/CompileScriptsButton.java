package org.JE.JE2Editor.EditorUI.Tools;

import org.JE.JE2.UI.UIElements.Buttons.Button;
import org.JE.JE2Editor.Tools.ProjectInfo;

public class CompileScriptsButton extends Button {

    public CompileScriptsButton() {
        super("Compile All JE2Scripts");
        onClickEvent = new Runnable() {
            @Override
            public void run() {
                org.JE.JEScript.Tools.compileAll(ProjectInfo.sourceDirectory);
            }
        };
    }
}

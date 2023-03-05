package org.JE.JE2Editor.EditorUI.Tools;

import org.JE.JE2.UI.UIElements.Buttons.Button;
import org.JE.JE2.Window.WindowPreferences;
import org.JE.JE2Editor.Tools.ProjectInfo;

public class BuildGameButton extends Button {

    public BuildGameButton(){
        super("Build Game");
        onClickEvent = this::build;
    }

    @Override
    protected void render() {
        super.render();
    }

    private void build(){
        org.JE.JECompiler.Tools.startCompileChain(ProjectInfo.sceneDir, ProjectInfo.runPath, ProjectInfo.runPackage,
                new WindowPreferences(1920,1080, "Game"), ProjectInfo.sourceDirectory, ProjectInfo.outPath,
                ProjectInfo.javaC, ProjectInfo.je2Jar, ProjectInfo.jarName, ProjectInfo.jarC,
                ProjectInfo.je2ScriptJar, ProjectInfo.je2CompilerJar);
    }
}

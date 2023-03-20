package org.JE.JE2Editor.EditorUI.Tools;

import org.JE.JE2.Objects.Scripts.Base.Script;
import org.JE.JE2.Rendering.Renderers.Renderer;
import org.JE.JE2.Rendering.Shaders.ShaderProgram;
import org.JE.JE2.UI.UIElements.Checkboxes.StyledCheckbox;
import org.JE.JE2Editor.EditorScene;
import org.JE.JE2Editor.EditorUI.Elements.SceneObject;
import org.JE.JE2Editor.EditorUI.Styles;

public class ToggleLightingModeButton extends StyledCheckbox {
    public ToggleLightingModeButton(){
        super(false,"Simulate Shaders", Styles.checkBoxStyle);
        onChange = (value) -> {
            setLighting();
        };
    }

    @Override
    protected void render() {
        super.render();
    }

    private void setLighting(){
        if(!isChecked){
            EditorScene.instance.world.gameObjects.forEach(object ->{
                if(object.getRenderer() !=null) {
                    if(object.getRenderer().getVAO().getShaderProgram().valid())
                    {
                        object.getRenderer().getVAO().getShaderProgram().destroy();
                        object.getRenderer().getVAO().setShaderProgram(ShaderProgram.spriteShader());
                    }
                }
            });
        }
        else{
            for (SceneObject sceneObject : EditorScene.instance.getObjects()) {

                int program = 0;
                Renderer rendererRef = null;
                for (Script s :
                        sceneObject.scripts) {
                    if(s instanceof Renderer renderer) {
                        rendererRef = renderer;
                        program = rendererRef.defaultShaderIndex;
                    }
                }
                if(sceneObject.sceneRef.getRenderer() !=null && rendererRef != null) {
                    if(sceneObject.sceneRef.getRenderer().getVAO().getShaderProgram().valid())
                    {
                        sceneObject.sceneRef.getRenderer().baseColor = rendererRef.baseColor;
                        sceneObject.sceneRef.getRenderer().getVAO().getShaderProgram().destroy();
                        sceneObject.sceneRef.getRenderer().getVAO().setShaderProgram(ShaderProgram.getProgramFromIndex(program));
                    }
                }
            }
        }
    }
}

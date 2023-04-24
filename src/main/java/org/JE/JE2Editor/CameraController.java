package org.JE.JE2Editor;

import org.JE.JE2.IO.UserInput.Keyboard.Keyboard;
import org.JE.JE2.IO.UserInput.Mouse.Mouse;
import org.JE.JE2.Manager;
import org.JE.JE2.Objects.Scripts.Script;
import org.JE.JE2.Rendering.Camera;
import org.jbox2d.common.MathUtils;
import org.joml.Vector2f;

public class CameraController extends Script {
    public Camera camRef;
    public float speed = 5f;
    private Vector2f prevMousePos = new Vector2f();

    @Override
    public void start(){
    }

    @Override
    public void update(){
        Vector2f deltaPos = Mouse.getMousePosition().sub(prevMousePos);
        deltaPos = deltaPos.mul(0.333f);
        /*if(Keyboard.isComboPressed(ComboList.UP)){
            camRef.getAttachedObject().getTransform().translateY(speed* Manager.deltaTime());
        }
        if(Keyboard.isComboPressed(ComboList.DOWN)){
            camRef.getAttachedObject().getTransform().translateY(-speed* Manager.deltaTime());
        }
        if(Keyboard.isComboPressed(ComboList.LEFT)){
            camRef.getAttachedObject().getTransform().translateX(-speed* Manager.deltaTime());
        }
        if(Keyboard.isComboPressed(ComboList.RIGHT)){
            camRef.getAttachedObject().getTransform().translateX(speed* Manager.deltaTime());
        }*/

        if(Keyboard.isKeyPressed(Keyboard.nameToCode("2"))){
            camRef.zoomMultiplier = MathUtils.clamp(camRef.zoomMultiplier+0.01f, 0.1f, 2f);

        }
        if(Keyboard.isKeyPressed(Keyboard.nameToCode("1"))){
            camRef.zoomMultiplier = MathUtils.clamp(camRef.zoomMultiplier-0.01f, 0.1f, 10f);
        }

        if(Mouse.isPressed(Mouse.nameToCode("RIGHT"))){
            camRef.getAttachedObject().getTransform().translateX(-deltaPos.x* Manager.deltaTime());
            camRef.getAttachedObject().getTransform().translateY(deltaPos.y* Manager.deltaTime());
        }
        prevMousePos = Mouse.getMousePosition();
    }
}

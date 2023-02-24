package org.JE;

import JE.IO.UserInput.Keyboard.Combos.ComboList;
import JE.IO.UserInput.Keyboard.KeyReleasedEvent;
import JE.IO.UserInput.Keyboard.Keyboard;
import JE.Manager;
import JE.Objects.Scripts.Base.Script;
import JE.Rendering.Camera;
import org.jbox2d.common.MathUtils;
import org.lwjgl.system.MathUtil;

public class CameraController extends Script {
    public Camera camRef;
    public float speed = 5f;

    @Override
    public void start(){
        Manager.addKeyReleasedCallback(new KeyReleasedEvent() {
            @Override
            public void invoke(int i, int i1) {

            }
        });
    }

    @Override
    public void update(){
        if(Keyboard.isComboPressed(ComboList.UP)){
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
        }

        if(Keyboard.isKeyPressed(Keyboard.nameToCode("2"))){
            camRef.zoomMultiplier = MathUtils.clamp(camRef.zoomMultiplier+0.01f, 0.1f, 10f);

        }
        if(Keyboard.isKeyPressed(Keyboard.nameToCode("1"))){
            camRef.zoomMultiplier = MathUtils.clamp(camRef.zoomMultiplier-0.01f, 0.1f, 10f);
        }
    }
}

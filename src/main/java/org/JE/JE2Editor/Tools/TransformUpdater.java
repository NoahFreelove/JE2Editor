package org.JE.JE2Editor.Tools;

import org.JE.JE2.Objects.Scripts.Base.Script;
import org.JE.JE2.Objects.Scripts.Common.Transform;

public class TransformUpdater extends Script {

    private Transform script;

    public TransformUpdater(Transform script){
        this.script = script;
    }

    @Override
    public void update(){
        if(getAttachedObject() != null)
        {
            getAttachedObject().setTransform(script);
        }
    }
}

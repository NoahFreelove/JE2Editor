package org.JE.ScriptElement;

import JE.Objects.Scripts.Base.Script;

import java.lang.reflect.Field;

public class ScriptObject {
    public Script scriptRef;

    public ScriptObject(Script scriptRef){
        this.scriptRef = scriptRef;
    }
}

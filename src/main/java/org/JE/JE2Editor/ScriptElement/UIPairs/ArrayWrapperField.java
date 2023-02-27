package org.JE.JE2Editor.ScriptElement.UIPairs;

import org.JE.JE2Editor.ScriptElement.FieldType;

import java.lang.reflect.Field;

public class ArrayWrapperField extends FieldUIPair{

    public ArrayWrapperField(Field field, FieldType ft, Object[] values) {
        super(field, ft, new Object());

    }

    private void set(){

    }

    @Override
    protected void render(){}
}

package org.JEEditor.ScriptElement.UIPairs;

import org.JEEditor.ScriptElement.FieldType;

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

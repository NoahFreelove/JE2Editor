package org.JE.ScriptElement.UIPairs;

import JE.UI.UIElements.UIElement;
import org.JE.ScriptElement.FieldType;

import java.lang.reflect.Field;

public class FieldUIPair extends UIElement {
    public Field field;
    public FieldType ft;
    public Object ref;
    public FieldUIPair(Field field, FieldType ft, Object ref) {
        this.field = field;
        this.ft = ft;
        this.ref = ref;
    }

    @Override
    protected void render() {
    }
}

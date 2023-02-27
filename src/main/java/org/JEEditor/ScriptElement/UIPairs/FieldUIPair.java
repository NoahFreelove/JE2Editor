package org.JEEditor.ScriptElement.UIPairs;

import JE.UI.UIElements.UIElement;
import JE.Window.UIHandler;
import org.JEEditor.ScriptElement.FieldType;
import org.lwjgl.nuklear.Nuklear;

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
        Nuklear.nk_label(UIHandler.nuklearContext,field.getName() + "???", Nuklear.NK_TEXT_ALIGN_LEFT);
    }
}

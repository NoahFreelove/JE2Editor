package org.JE.JE2Editor.ScriptElement.UIPairs;

import org.JE.JE2.UI.UIElements.Style.Color;
import org.JE.JE2.UI.UIElements.UIElement;
import org.JE.JE2.Window.UIHandler;
import org.JE.JE2Editor.ScriptElement.FieldType;
import org.lwjgl.nuklear.Nuklear;

import java.lang.reflect.Field;

import static org.lwjgl.nuklear.Nuklear.nk_label_colored;

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
        nk_label_colored(UIHandler.nuklearContext,field.getName() + "???",Nuklear.NK_TEXT_ALIGN_LEFT, Color.WHITE.nkColor());
    }
}

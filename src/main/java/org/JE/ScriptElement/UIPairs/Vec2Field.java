package org.JE.ScriptElement.UIPairs;

import JE.Window.UIHandler;
import org.JE.EditorUI.StringFormatter;
import org.JE.ScriptElement.FieldType;
import org.joml.Vector2f;
import org.lwjgl.nuklear.Nuklear;

import java.lang.reflect.Field;

public class Vec2Field extends FieldUIPair{

    private Vector2f value;
    private FloatField x;
    private FloatField y;
    private String title;

    public Vec2Field(Field field, Vector2f value, Object ref, String title) {
        super(field, FieldType.VEC2, ref);
        this.value = value;
        this.title = StringFormatter.capSplit(title);
        x = new FloatField(field, this.value, "X", -10000, this.value.x, 10000, 1f, 0.1f);
        y = new FloatField(field, this.value, "Y", -10000, this.value.y, 10000, 1f, 0.1f);
    }

    @Override
    protected void render(){
        Nuklear.nk_label(UIHandler.nuklearContext,title, Nuklear.NK_TEXT_ALIGN_LEFT);
        x.render();
        y.render();
        value.x = x.getValue();
        value.y = y.getValue();

        try {
            field.set(ref, value);
        } catch (Exception e) {
            System.out.println("vec2 field err: " + e.getMessage());
        }
    }
}

package org.JE.JE2Editor.ScriptElement.UIPairs;

import org.JE.JE2.UI.UIElements.Style.Color;
import org.JE.JE2.Window.UIHandler;
import org.JE.JE2Editor.EditorUI.StringFormatter;
import org.JE.JE2Editor.ScriptElement.FieldType;
import org.joml.Vector2f;
import org.lwjgl.nuklear.Nuklear;

import java.lang.reflect.Field;

import static org.lwjgl.nuklear.Nuklear.nk_label_colored;

public class Vec2Field extends FieldUIPair{

    private Vector2f value;
    private FloatField x;
    private FloatField y;
    private String title;

    public Vec2Field(Field field, Vector2f value, Object ref, String title) {
        super(field, FieldType.VEC2, ref);
        this.value = value;
        this.title = StringFormatter.capAndSplit(title);
        x = new FloatField(field, this.value, "X", -10000, this.value.x, 10000, 1f, 0.1f, false);
        y = new FloatField(field, this.value, "Y", -10000, this.value.y, 10000, 1f, 0.1f, false);
    }

    @Override
    protected void render(){
        nk_label_colored(UIHandler.nuklearContext,title,Nuklear.NK_TEXT_ALIGN_LEFT, Color.WHITE.nkColor());
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

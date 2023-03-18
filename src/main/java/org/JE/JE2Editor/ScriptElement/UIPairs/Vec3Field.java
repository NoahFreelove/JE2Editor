package org.JE.JE2Editor.ScriptElement.UIPairs;

import org.JE.JE2.UI.UIElements.Style.Color;
import org.JE.JE2.Window.UIHandler;
import org.JE.JE2Editor.EditorUI.StringFormatter;
import org.JE.JE2Editor.ScriptElement.FieldType;
import org.joml.Vector3f;
import org.lwjgl.nuklear.Nuklear;

import java.lang.reflect.Field;

import static org.lwjgl.nuklear.Nuklear.nk_label_colored;

public class Vec3Field extends FieldUIPair {
    private Vector3f value;
    private FloatField x;
    private FloatField y;
    private FloatField z;
    private String title;

    public Vec3Field(Field field, Vector3f vector3f, Object ref, String title) {
        super(field, FieldType.VEC3, ref);
        this.value = vector3f;
        this.title = StringFormatter.capAndSplit(title);
        x = new FloatField(field, value, title + "X", -10000, value.x, 10000, 1f, 0.1f, false);
        y = new FloatField(field, value,  title + "Y", -10000, value.y, 10000, 1f, 0.1f, false);
        z = new FloatField(field, value,  title + "Z", -10000, value.z, 10000, 1f, 0.1f, false);
    }
    @Override
    protected void render(){
        nk_label_colored(UIHandler.nuklearContext,title,Nuklear.NK_TEXT_ALIGN_LEFT, Color.WHITE.nkColor());
        x.render();
        y.render();
        z.render();
        value.x = x.getValue();
        value.y = y.getValue();
        value.z = z.getValue();

        try {
            field.set(ref, value);
        } catch (Exception e) {
            System.out.println("vec3 field err: " + e.getMessage());
        }
    }
}

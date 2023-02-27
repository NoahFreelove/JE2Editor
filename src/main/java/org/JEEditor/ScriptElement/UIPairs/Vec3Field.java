package org.JEEditor.ScriptElement.UIPairs;

import JE.Window.UIHandler;
import org.JEEditor.EditorUI.StringFormatter;
import org.JEEditor.ScriptElement.FieldType;
import org.joml.Vector3f;
import org.lwjgl.nuklear.Nuklear;

import java.lang.reflect.Field;

public class Vec3Field extends FieldUIPair {
    private Vector3f value;
    private FloatField x;
    private FloatField y;
    private FloatField z;
    private String title;
    public Vec3Field(Field field, Vector3f vector3f, Object ref, String title) {
        super(field, FieldType.VEC3, ref);
        this.value = vector3f;
        this.title = StringFormatter.capSplit(title);
        x = new FloatField(field, vector3f, "X", -10000, value.x, 10000, 1f, 0.1f, false);
        y = new FloatField(field, vector3f, "Y", -10000, value.y, 10000, 1f, 0.1f, false);
        z = new FloatField(field, vector3f, "Z", -10000, value.z, 10000, 1f, 0.1f, false);
    }
    @Override
    protected void render(){
        Nuklear.nk_label(UIHandler.nuklearContext,title, Nuklear.NK_TEXT_ALIGN_LEFT);
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
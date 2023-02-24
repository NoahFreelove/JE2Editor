package org.JE.ScriptElement.UIPairs;

import org.JE.ScriptElement.FieldType;
import org.joml.Vector3f;

import java.lang.reflect.Field;

public class Vec3Field extends FieldUIPair {
    private Vector3f value;
    private FloatField x;
    private FloatField y;
    private FloatField z;
    public Vec3Field(Field field, Vector3f vector3f, Object ref) {
        super(field, FieldType.VEC3, ref);
        this.value = vector3f;
        x = new FloatField(field, vector3f, "X", Float.MIN_VALUE, value.x, Float.MAX_VALUE, 1f, 1);
        y = new FloatField(field, vector3f, "Y", Float.MIN_VALUE, value.y, Float.MAX_VALUE, 1f, 1);
        z = new FloatField(field, vector3f, "Z", Float.MIN_VALUE, value.z, Float.MAX_VALUE, 1f, 1);
    }
    @Override
    protected void render(){

        x.render();
        y.render();
        z.render();
        value.x = x.getValue();
        value.y = y.getValue();
        value.z = z.getValue();

        try {
            field.set(ref, value);
        } catch (IllegalAccessException e) {
            System.out.println("vec3 field err: " + e.getMessage());
        }
    }
}

package org.JE.JE2Editor.ScriptElement.UIPairs;

import org.JE.JE2.Window.UIHandler;
import org.JE.JE2Editor.EditorUI.StringFormatter;
import org.JE.JE2Editor.ScriptElement.FieldType;
import org.lwjgl.nuklear.NkContext;

import java.lang.reflect.Field;

import static org.lwjgl.nuklear.Nuklear.nk_property_float;

public class FloatField extends FieldUIPair {
    private String label;
    private NkContext context;

    private final float[] value = new float[1];
    private float min = 0;
    private float max = 0;
    private float step = 0.01f;
    private float incPerPixel = 0.001f;
    private boolean update;

    public FloatField(Field f, Object ref, String label, float min, float value, float max, float step, float incPerPixel, boolean update) {
        super(f, FieldType.FLOAT,ref);
        this.context = UIHandler.nuklearContext;
        this.label = StringFormatter.capAndSplit(label);
        this.min = min;
        this.value[0] = value;
        this.max = max;
        this.step = step;
        this.incPerPixel = incPerPixel;
        this.update = update;
    }

    public void setValue(float value) {
        this.value[0] = value;
    }

    public float getValue() {
        return value[0];
    }

    @Override
    public void render() {
        nk_property_float(context, label, min, value, max, step, incPerPixel);

        if(!update)
            return;
        try {
            field.set(ref, value[0]);
        } catch (Exception e) {
            System.out.println("float field err: " + e.getMessage());
        }
    }
}
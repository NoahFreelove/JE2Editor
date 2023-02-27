package org.JEEditor.ScriptElement.UIPairs;

import JE.Window.UIHandler;
import org.JEEditor.EditorUI.StringFormatter;
import org.JEEditor.ScriptElement.FieldType;
import org.lwjgl.nuklear.NkContext;

import java.lang.reflect.Field;

import static org.lwjgl.nuklear.Nuklear.nk_property_float;

public class DoubleField extends FieldUIPair {
    private String label;
    private NkContext context;

    private final float[] value = new float[1];
    private float min = 0;
    private float max = 0;
    private float step = 0.01f;
    private float incPerPixel = 0.001f;
    private boolean update;

    public DoubleField(Field f, Object ref, String label, float min, double value, float max, float step, float incPerPixel, boolean update) {
        super(f, FieldType.FLOAT,ref);
        this.context = UIHandler.nuklearContext;
        this.label = StringFormatter.capSplit(label);
        this.min = min;
        this.value[0] = (float)value;
        this.max = max;
        this.step = step;
        this.incPerPixel = incPerPixel;
        this.update = update;
    }

    public void setValue(double value) {
        this.value[0] = (float) value;
    }

    public double getValue() {
        return value[0];
    }

    @Override
    public void render() {
        nk_property_float(context, label, min, value, max, step, incPerPixel);

        if(!update)
            return;
        try {
            field.set(ref, (float)value[0]);
        } catch (Exception e) {
            System.out.println("float field err: " + e.getMessage());
        }
    }
}
package org.JEEditor.ScriptElement.UIPairs;

import JE.Window.UIHandler;
import org.JEEditor.ScriptElement.FieldType;

import java.lang.reflect.Field;

import static org.lwjgl.nuklear.Nuklear.nk_property_int;

public class IntField extends FieldUIPair {

    private String label;
    private final int[] value = new int[1];
    private int min = 0;
    private int max = 0;
    private int incPerPixel = 1;

    public IntField(Field field, String label, Object ref, int min, int initValue, int max) {
        super(field, FieldType.INT, ref);
        this.label = label;
        this.min = min;
        this.max = max;
        this.value[0] = initValue;
    }

    public void setValue(int value) {
        this.value[0] = value;
    }

    public int getValue() {
        return value[0];
    }

    @Override
    protected void render() {
        nk_property_int(UIHandler.nuklearContext, label, min, value, max, 1, incPerPixel);
        try {
            field.set(ref, value[0]);
        } catch (Exception e) {
            System.out.println("int field err: " + e.getMessage());
        }
    }
}

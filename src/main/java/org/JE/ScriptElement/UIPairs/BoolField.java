package org.JE.ScriptElement.UIPairs;

import JE.UI.UIElements.Checkboxes.Checkbox;
import org.JE.ScriptElement.FieldType;

import java.lang.reflect.Field;

public class BoolField extends FieldUIPair{
    boolean value;
    Checkbox checkbox;
    public BoolField(Field field, boolean defaultValue, Object ref) {
        super(field, FieldType.BOOL, ref);
        this.value = defaultValue;
        checkbox = new Checkbox(defaultValue, field.getName());
    }

    @Override
    protected void render(){
        checkbox.requestRender();
        this.value = checkbox.isChecked;
        try {
            field.set(ref, value);
        } catch (IllegalAccessException e) {
            System.out.println("bool field err: " + e.getMessage());
        }
    }
}

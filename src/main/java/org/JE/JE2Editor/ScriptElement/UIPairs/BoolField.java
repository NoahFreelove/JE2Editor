package org.JE.JE2Editor.ScriptElement.UIPairs;

import org.JE.JE2.UI.UIElements.Checkbox;
import org.JE.JE2Editor.EditorUI.StringFormatter;
import org.JE.JE2Editor.EditorUI.Styles;
import org.JE.JE2Editor.ScriptElement.FieldType;

import java.lang.reflect.Field;

public class BoolField extends FieldUIPair{
    boolean value;
    Checkbox checkbox;
    public BoolField(Field field, boolean defaultValue, Object ref) {
        super(field, FieldType.BOOL, ref);
        this.value = defaultValue;
        checkbox = new Checkbox(defaultValue, StringFormatter.capAndSplit(field.getName()));
        checkbox.setStyle(Styles.checkBoxStyle);
    }

    @Override
    protected void render(){
        checkbox.requestRender();
        this.value = checkbox.isChecked;
        try {
            field.setBoolean(ref, value);
        } catch (Exception e) {
            System.out.println("bool field err: " + e.getMessage());
        }
    }
}

package org.JE.JE2Editor.ScriptElement.UIPairs;

import org.JE.JE2.UI.UIElements.Checkboxes.StyledCheckbox;
import org.JE.JE2.UI.UIElements.Style.Color;
import org.JE.JE2.UI.UIElements.Style.StyleInfo;
import org.JE.JE2Editor.EditorUI.StringFormatter;
import org.JE.JE2Editor.ScriptElement.FieldType;

import java.lang.reflect.Field;

public class BoolField extends FieldUIPair{
    boolean value;
    StyledCheckbox checkbox;
    public BoolField(Field field, boolean defaultValue, Object ref) {
        super(field, FieldType.BOOL, ref);
        this.value = defaultValue;
        checkbox = new StyledCheckbox(defaultValue, StringFormatter.capSplit(field.getName()), new StyleInfo().setInactiveColor(Color.RED).setPressedColor(Color.GREEN).setHoverColor(Color.GREEN));
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

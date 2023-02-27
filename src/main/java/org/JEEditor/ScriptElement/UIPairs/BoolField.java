package org.JEEditor.ScriptElement.UIPairs;

import JE.UI.UIElements.Checkboxes.StyledCheckbox;
import JE.UI.UIElements.Style.Color;
import JE.UI.UIElements.Style.StyleInfo;
import org.JEEditor.EditorUI.StringFormatter;
import org.JEEditor.ScriptElement.FieldType;

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

package org.JEEditor.ScriptElement.UIPairs;

import JE.UI.UIElements.TextField;
import JE.Window.UIHandler;
import org.JEEditor.EditorUI.StringFormatter;
import org.JEEditor.ScriptElement.FieldType;
import org.lwjgl.nuklear.Nuklear;

import java.lang.reflect.Field;

public class StringField extends FieldUIPair{
    private String value;
    private TextField tf;
    private String title;
    public StringField(Field field, String defaultValue, Object ref, String title) {
        super(field, FieldType.STRING, ref);
        this.title = StringFormatter.capSplit(title);
        this.value = defaultValue;
        this.tf = new TextField(128);
    }
    @Override
    protected void render() {
        Nuklear.nk_label(UIHandler.nuklearContext,title, Nuklear.NK_TEXT_ALIGN_LEFT);
        tf.requestRender();

        try {
            field.set(ref, value);
            value = tf.getValue();
        } catch (Exception e) {
            System.out.println("string field err: " + e.getMessage());
        }
    }
}
